package modele;
import java.util.Random;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
public abstract class ObjetSimple3D implements Objet3D {

  public GLUT myGlut= new GLUT();

  static float ACCELERATION_MIN=1f;
  static float ACCELERATION_MAX=1f;

  public float x;
  public float y;
  public float z;

  public float a=0f;
  public float acceleration=1f;

  public float[] couleur;

  public ObjetSimple3D()
  {
    super();
    setRandomAcceleration();
    setRandomCouleur();
  }

  abstract public void init(GL2 gl); // initialisation
  public void appliqueChangementRepere(GL2 gl) // Applique les transformations à effectuer avant l'affichage de l'objet
  {
    gl.glRotatef(a,0,1,0);
    gl.glTranslated(x,y,z);
  }


  public void update() // permet de faire évoluer l'objet
  {
    updateAngle();
  }

  public void affiche(GL2 gl)
  {
    gl.glColor3f(couleur[0],couleur[1],couleur[2]);
  }

  public void updateAngle()
  {
    if(a>=360)
    {
      a=0;
    }
    a += acceleration ;
  }

  public void setRandomCouleur()
  {
    couleur = new float[3];
    Random rand = new Random();
    for(int i=0;i<3;++i)
    {
      couleur[i]= (rand.nextInt(255 - 0 + 1) + 0)/255f;
    }
  }

  public void setRandomAcceleration()
  {
    Random rand = new Random();
    this.acceleration= rand.nextFloat() * (ACCELERATION_MAX - ACCELERATION_MIN) + ACCELERATION_MIN;
  }

}
