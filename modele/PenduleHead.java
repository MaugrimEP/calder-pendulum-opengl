package modele;
import com.jogamp.opengl.GL2;
import java.util.ArrayList;

public class PenduleHead extends ObjetSimple3D{

  static float RADIUS=1.5f;
  static int RESOLUTION=6;

  public ArrayList<Pendule> enfants;

  public PenduleHead(int nombreEtage,int enfantsParEtage)
  {
    super();

    System.out.println("Creation PendulHead");

    enfants=new ArrayList<Pendule>();

    if(nombreEtage>0)
    {
      for(int i=0;i<enfantsParEtage;++i)
      {
        enfants.add(new Pendule(nombreEtage-1,enfantsParEtage+1,this,i));
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
  }

  public void affiche(GL2 gl){
    super.affiche(gl);

    //System.out.println("Head :"+x+","+y+","+z);

    gl.glEnable(gl.GL_DEPTH_TEST);
    gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_LINE);
    gl.glPushMatrix();
    this.appliqueChangementRepere(gl);

    this.myGlut.glutSolidSphere(RADIUS, RESOLUTION, RESOLUTION);

    gl.glEnd();
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

}
