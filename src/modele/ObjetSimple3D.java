package modele;
import java.util.Random;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
public abstract class ObjetSimple3D implements Objet3D {

  public GLUT myGlut= new GLUT();

  static float ACCELERATION_MIN=-1f;
  static float ACCELERATION_MAX=1f;

  public float x;
  public float y;
  public float z;

  public float a=0f;
  public float acceleration;

  public float[] couleur;

  public ObjetSimple3D()
  {
    super();
    setRandomCouleur();
  }

  abstract public void init(GL2 gl); // initialisation
  public void appliqueChangementRepere(GL2 gl) // Applique les transformations à effectuer avant l'affichage de l'objet
  {

    gl.glTranslated(x,y,z);
    gl.glRotatef(a,0,1,0);
  }

  public int enfantRandom(int min,int max)
  {
    Random rand = new Random();
    return rand.nextInt((max + 1 - min) + min) ;

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
    updateXYZ();
  }

  public void updateXYZ()
  {
    //la hauteur ne change jamais (Z), il faut positionner tout ça sur un cercle trigo
    //la coordonnée en X est donc le sinus de A
    //la coordonnée en Z est donc le cosinus de A
    //mais il faut les multiplier par la norme du vecteur (0,0,0)>>(x,0,z);
    double angleRad = Math.toRadians(a);
    float cosinus = (float)Math.cos(angleRad);
    float sinus = (float)Math.sin(angleRad);

    x = sinus * x;
    y=y;
    z= cosinus * z;
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
    this.acceleration = getRandomAcceleration();
  }

  public float getRandomAcceleration()
  {
    Random rand = new Random();
    return rand.nextFloat() * (ACCELERATION_MAX - ACCELERATION_MIN) + ACCELERATION_MIN;
  }

  public double distanceTo(ObjetSimple3D o)
  {
    return Math.sqrt(Math.pow(o.x-x,2.0)+Math.pow(o.y-y,2.0)+Math.pow(o.z-z,2.0));
  }

  public void printDistance(Pendule o)
  {
    double distance = distanceTo(o);
    System.out.println(distance);
  }

}
