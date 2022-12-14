package modele;

import java.io.*;
import java.nio.*;

import java.util.Scanner;

import com.jogamp.opengl.GL2;
import com.jogamp.common.nio.Buffers;

import org.json.*;
import org.json.JSONException;


public class JSON_VBO_shading extends Maillage3D
{
  DoubleBuffer vertex_buffer;
  IntBuffer index_buffer;
  DoubleBuffer normal_buffer;

  int[] buffers = new int[ 3 ];

  Pendule monPendule;
  String filename;

  public JSON_VBO_shading(String filename,Pendule monPendule)
  {
    this.monPendule=monPendule;
    this.filename=filename;
  }


  private static double[] crossproduct( double[] v0, double[] v1 )
  {
    double[] res = new double[ 3 ];
    res[0] = v0[1] * v1[2] - v0[2] * v1[1];
    res[1] = v0[2] * v1[0] - v0[0] * v1[2];
    res[2] = v0[0] * v1[1] - v0[1] * v1[0];

    return res;
  }


  private static double[] normalize( double[] v )
  {
    double norm = Math.sqrt( v[ 0 ] * v[ 0 ] + v[ 1 ] * v[ 1 ] + v[ 2 ] * v[ 2 ] );
    for( int i = 0 ; i < v.length ; ++i )
    {
      v[ i ] = v[ i ] / norm;
    }
    return v;
  }


  private static DoubleBuffer genNormalBuffer( DoubleBuffer vertices, IntBuffer indices )
  {
    DoubleBuffer normals = Buffers.newDirectDoubleBuffer( vertices.capacity() );

    double[] v0 = new double[ 3 ];
    double[] v1 = new double[ 3 ];
    double[] res = new double[ 3 ];

    for( int i = 0 ; i < indices.capacity() ; i+=3 )
    {
      int i0 = indices.get( i );
      int i1 = indices.get( i + 1 );
      int i2 = indices.get( i + 2 );

      v0[ 0 ] = vertices.get( 3 * indices.get( i + 1 ) ) - vertices.get( 3 * indices.get( i ) );
      v0[ 1 ] = vertices.get( 3 * indices.get( i + 1 ) + 1 ) - vertices.get( 3 * indices.get( i ) + 1 );
      v0[ 2 ] = vertices.get( 3 * indices.get( i + 1 ) + 2 ) - vertices.get( 3 * indices.get( i ) + 2 );

      v1[ 0 ] = vertices.get( 3 * indices.get( i + 2 ) ) - vertices.get( 3 * indices.get( i ) );
      v1[ 1 ] = vertices.get( 3 * indices.get( i + 2 ) + 1 ) - vertices.get( 3 * indices.get( i ) + 1 );
      v1[ 2 ] = vertices.get( 3 * indices.get( i + 2 ) + 2 ) - vertices.get( 3 * indices.get( i ) + 2 );

      res = normalize( crossproduct( v0, v1 ) );

      normals.put( 3 * indices.get( i )    , normals.get( 3 * indices.get( i )     ) + res[0] );
      normals.put( 3 * indices.get( i ) + 1, normals.get( 3 * indices.get( i ) + 1 ) + res[1] );
      normals.put( 3 * indices.get( i ) + 2, normals.get( 3 * indices.get( i ) + 2 ) + res[2] );

      normals.put( 3 * indices.get( i + 1 )    , normals.get( 3 * indices.get( i + 1 )     ) + res[0] );
      normals.put( 3 * indices.get( i + 1 ) + 1, normals.get( 3 * indices.get( i + 1 ) + 1 ) + res[1] );
      normals.put( 3 * indices.get( i + 1 ) + 2, normals.get( 3 * indices.get( i + 1 ) + 2 ) + res[2] );

      normals.put( 3 * indices.get( i + 2 )    , normals.get( 3 * indices.get( i + 2 )     ) + res[0] );
      normals.put( 3 * indices.get( i + 2 ) + 1, normals.get( 3 * indices.get( i + 2 ) + 1 ) + res[1] );
      normals.put( 3 * indices.get( i + 2 ) + 2, normals.get( 3 * indices.get( i + 2 ) + 2 ) + res[2] );
    }

    for( int i = 0 ; i < normals.capacity() ; i+=3 )
    {
      res[ 0 ] = normals.get( i );
      res[ 1 ] = normals.get( i + 1 );
      res[ 2 ] = normals.get( i + 2 );
      res = normalize( res );
      normals.put( i    , res[ 0 ] );
      normals.put( i + 1, res[ 1 ] );
      normals.put( i + 2, res[ 2 ] );
    }

    return normals;
  }


  public void init( GL2 gl)
  {
    /**
     * Initialisation de l'??clairage
     */
    gl.glEnable( GL2.GL_LIGHTING );
    gl.glEnable( GL2.GL_LIGHT0 ); // Activation de la lumi??re n??1.

    // Position de la lumi??re.
    float[] l_pos = { 10.0f,10.0f,0.0f,0.0f };
    gl.glLightfv(GL2.GL_LIGHT0,GL2.GL_POSITION,Buffers.newDirectFloatBuffer( l_pos) );

    // Couleur de la composante diffuse (directionnelle) de la lumi??re.
    float[] diffuse = {1.0f,1.0f,1.0f,1.0f};
    gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, Buffers.newDirectFloatBuffer( diffuse) );

    /**
     * Chargement du mod??le.
     */


    gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);

    // G??n??ration de 3 identifiants de buffers : 0) sommets 1) indices 2) normales.
    gl.glGenBuffers(3, buffers, 0 );

    String jsonString = "";

    try
    {
      jsonString = new Scanner( new File("maillages/"+filename+".json")).useDelimiter("\\Z").next();
    }
    catch( FileNotFoundException e )
    {
      System.err.println("File not found.");
      System.exit( 0 );
    }

    try
    {
      JSONObject object = new JSONObject(jsonString);

      // Sommets.
      JSONArray vertices = object.getJSONArray( "vertices" ).getJSONObject( 0 ).getJSONArray( "values" );

      vertex_buffer = Buffers.newDirectDoubleBuffer( vertices.length() );

      for( int i = 0 ; i < vertices.length() ; ++i )
      {
	vertex_buffer.put( i, vertices.getDouble( i ) );
      }
      // Activation du buffer 0 pour stocker les sommets sur la carte graphique.
      gl.glBindBuffer( GL2.GL_ARRAY_BUFFER, buffers[ 0 ] );
      // Remplissage du buffer ?? partir des donn??es du fichier JSON.
      gl.glBufferData(GL2.GL_ARRAY_BUFFER, vertex_buffer.capacity() * Buffers.SIZEOF_DOUBLE,
		      vertex_buffer, GL2.GL_STATIC_DRAW);
      // Les donn??es sont en m??moire graphique donc plus besoin de donner le pointeur en m??moire.
      gl.glVertexPointer( 3, GL2.GL_DOUBLE, 0, 0 );

      // Indices. Idem que pour les sommets.
      JSONArray indices = object.getJSONArray( "connectivity" ).getJSONObject( 0 ).getJSONArray( "indices" );

      index_buffer = Buffers.newDirectIntBuffer(indices.length() );

      for( int i = 0 ; i < indices.length() ; ++i )
      {
	index_buffer.put( i, indices.getInt( i ) );
      }
      // Activation du buffer 1 pour stocker les indices sur la carte graphique.
      gl.glBindBuffer(GL2.GL_ELEMENT_ARRAY_BUFFER, buffers[ 1 ] );
      gl.glBufferData(GL2.GL_ELEMENT_ARRAY_BUFFER,
		      index_buffer.capacity() * Buffers.SIZEOF_INT,
		      index_buffer, GL2.GL_STATIC_DRAW);

      // Normales. Ne sont pas pr??sentes dans le fichier donc il faut les calculer ?? partir
      // des triangles du mod??le. Stockage dans le buffer 2.
      normal_buffer = genNormalBuffer( vertex_buffer, index_buffer );
      gl.glBindBuffer( GL2.GL_ARRAY_BUFFER, buffers[ 2 ] );
      gl.glBufferData(GL2.GL_ARRAY_BUFFER,
		      normal_buffer.capacity() * Buffers.SIZEOF_DOUBLE,
      		      normal_buffer,
		      GL2.GL_STATIC_DRAW);
      gl.glNormalPointer( GL2.GL_DOUBLE, 0, 0 );
    }
    catch( JSONException e )
    {
      System.err.println( e );
      System.exit( 0 );
    }
  }


  public void appliqueChangementRepere(GL2 gl){
    gl.glTranslatef(monPendule.x, monPendule.y, monPendule.z);
    gl.glRotatef(monPendule.a,0,1,0);
  }


  public void affiche(GL2 gl)
  {
    gl.glEnableClientState( GL2.GL_VERTEX_ARRAY );
    gl.glEnableClientState( GL2.GL_NORMAL_ARRAY );

    gl.glPushMatrix();

    this.appliqueChangementRepere(gl);

    gl.glColor3f(monPendule.couleur[0],monPendule.couleur[1],monPendule.couleur[2]);

    // Activation des buffers contenants les donn??es du mod??le.
    gl.glBindBuffer( GL2.GL_ARRAY_BUFFER, buffers[ 0 ] );
    gl.glBindBuffer( GL2.GL_ELEMENT_ARRAY_BUFFER, buffers[ 1 ] );
    gl.glBindBuffer( GL2.GL_ARRAY_BUFFER, buffers[ 2 ] );

    // Dessin du mod??le.
    gl.glDrawElements( GL2.GL_TRIANGLES, index_buffer.capacity(), GL2.GL_UNSIGNED_INT, 0 );

    gl.glPopMatrix();

    gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glDisableClientState( GL2.GL_NORMAL_ARRAY );
  }

  public void update()
  {

  }

}
