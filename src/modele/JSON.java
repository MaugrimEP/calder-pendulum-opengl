package modele;

import java.io.*;
import java.nio.*;

import java.util.Scanner;

import com.jogamp.opengl.GL2;
import com.jogamp.common.nio.Buffers;

import org.json.*;
import org.json.JSONException;


public class JSON extends Maillage3D
{
  DoubleBuffer vertex_buffer;
  IntBuffer index_buffer;

  public void init( GL2 gl)
  {
    String jsonString = "";

    try
    {
      jsonString = new Scanner( new File("maillages/wolf_low_poly.json")).useDelimiter("\\Z").next();
    }
    catch( FileNotFoundException e )
    {
      System.err.println("File not found.");
      System.exit( 0 );
    }

    try
    {
      JSONObject object = new JSONObject(jsonString);

      JSONArray vertices = object.getJSONArray( "vertices" ).getJSONObject( 0 ).getJSONArray( "values" );

      JSONArray indices = object.getJSONArray( "connectivity" ).getJSONObject( 0 ).getJSONArray( "indices" );

      vertex_buffer = Buffers.newDirectDoubleBuffer( vertices.length() );

      for( int i = 0 ; i < vertices.length() ; ++i )
      {
	vertex_buffer.put( i, vertices.getDouble( i ) );
      }

      index_buffer = Buffers.newDirectIntBuffer(indices.length() );

      for( int i = 0 ; i < indices.length() ; ++i )
      {
	index_buffer.put( i, indices.getInt( i ) );
      }
    }
    catch( JSONException e )
    {
      System.err.println( e );
      System.exit( 0 );
    }
  }

  public void appliqueChangementRepere(GL2 gl){
    //gl.glTranslatef(0.0f, 0.0f, -10.0f);
  }

  public void affiche(GL2 gl) {
    gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
    gl.glVertexPointer(3, GL2.GL_DOUBLE, 0, vertex_buffer);

    gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_LINE);

    gl.glPushMatrix();

    this.appliqueChangementRepere(gl);

    gl.glColor3f(0.0f,0.0f,1.0f);
    gl.glDrawElements( GL2.GL_TRIANGLES, index_buffer.capacity(), GL2.GL_UNSIGNED_INT, index_buffer );

    gl.glPopMatrix();
  }

  public void update()
  {

  }

}
