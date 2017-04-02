package modele;
import com.jogamp.opengl.GL2;
import java.util.ArrayList;
public class Pendule extends ObjetSimple3D{

  static float RADIUS=1f;
  static int RESOLUTION=6;

  static float HAUTEUR=5;
  static float DECALAGE_ENFANT=2f;

  ObjetSimple3D parent;

  public ArrayList<Pendule> enfants;

  int numeroEnfant;

  public Pendule(int nombreEtage,int enfantsParEtage, ObjetSimple3D parent,int numeroEnfant)
  {
    super();

    System.out.println("Creation Pendule : etage "+nombreEtage+" numero :"+numeroEnfant);

    this.parent=parent;
    this.numeroEnfant=numeroEnfant;

    enfants=new ArrayList<Pendule>();
    if(nombreEtage!=0)
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

    x=parent.x+DECALAGE_ENFANT*numeroEnfant;
    y=parent.y-HAUTEUR;
    z=parent.z;

    a=parent.a+a;
  }

  public void affiche(GL2 gl){
    super.affiche(gl);

    System.out.println("Enfant :"+x+","+y+","+z);

    gl.glEnable(gl.GL_DEPTH_TEST);
    gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
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
    tous.add(this);
    return tous;
  }

}
