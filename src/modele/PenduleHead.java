package modele;
import com.jogamp.opengl.GL2;
import java.util.ArrayList;
import java.util.Random;

public class PenduleHead extends ObjetSimple3D{

  static float RADIUS=1.5f;
  static int RESOLUTION=6;

  public ArrayList<Pendule> enfants;

  public PenduleHead(int nombreEtage,int enfantsParEtage)
  {
    super();

    System.out.println("Creation PendulHead");

    enfants=new ArrayList<Pendule>();

    int enfantEtageSuivant = enfantsParEtage+1;

    int nextParent = enfantRandom(0,enfantEtageSuivant-2);// les nombres doivent aller de 0 à enfantsParEtage-1
    System.out.println("Range random :"+0+" up to "+ (enfantEtageSuivant-2));
    System.out.println("nextParent "+nextParent);


    if(nombreEtage>0)
    {
      float accelerationEnfants = getRandomAcceleration();
      for(int i=0;i<enfantsParEtage;++i)
      {
        enfants.add(new Pendule(nombreEtage-1,enfantEtageSuivant,this,i,accelerationEnfants,nextParent));
      }
    }
  }

  public void init(GL2 gl){
  }

  public void appliqueChangementRepere(GL2 gl){
    super.appliqueChangementRepere(gl);
  }

  public void update(){
    super.update();
    updateXYZ();
  }

  public void affiche(GL2 gl){
    super.affiche(gl);

    //System.out.println(this);

    gl.glEnable(gl.GL_DEPTH_TEST);
    gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
    this.drawLines(gl);

    gl.glPushMatrix();
    this.appliqueChangementRepere(gl);
    this.myGlut.glutSolidSphere(RADIUS, RESOLUTION, RESOLUTION);
    gl.glPopMatrix();

  }


  public ArrayList<Pendule> getEnfants()
  {
    ArrayList<Pendule> tous = new ArrayList<Pendule>();
    for(Pendule enfant : enfants)
    {
      tous.addAll(enfant.getEnfants());
    }
    return tous;
  }

  public void drawLines(GL2 gl)
  {//on va pour chaque enfants draw une line qui sera horizontal jusqu'a la position de l'enfant et puis vertical jusqu'a la hauteur de l'enfant
    gl.glBegin(GL2.GL_LINES);
    for(Pendule enfant : enfants)
    {
      gl.glColor3f(1,0,0);

      gl.glVertex3f(x,y,z);//descendre 1/2 de la distance
      gl.glVertex3f(x,y-(y-enfant.y)/2,z);

      gl.glVertex3f(x,y-(y-enfant.y)/2,z);//déplacement latéral
      gl.glVertex3f(enfant.x,y-(y-enfant.y)/2,enfant.z);

      gl.glVertex3f(enfant.x,y-(y-enfant.y)/2,enfant.z);//descendre le reste
      gl.glVertex3f(enfant.x,enfant.y,enfant.z);
    }
    gl.glEnd();
  }

  public String toString()
  {
    return "HEAD : "+x+" "+y+" "+z;
  }

}
