package modele;
import com.jogamp.opengl.GL2;
import java.util.ArrayList;
public class Pendule extends ObjetSimple3D{

  static float RADIUS=1f;
  static int RESOLUTION=6;

  static float HAUTEUR=5;
  static float DECALAGE_ENFANT=5f;

  ObjetSimple3D parent;

  public ArrayList<Pendule> enfants;

  int numeroEnfant;

  int numeroEtage;

  public Pendule(int nombreEtage,int enfantsParEtage, ObjetSimple3D parent,int numeroEnfant, float acceleration, int nextParent)
  {
    super();

    System.out.println("Creation Pendule : etage "+nombreEtage+" numero :"+numeroEnfant);

    this.parent=parent;
    this.numeroEnfant=numeroEnfant;
    this.acceleration=acceleration;
    this.numeroEtage=nombreEtage;

    int enfantEtageSuivant = enfantsParEtage+1;


    enfants=new ArrayList<Pendule>();
    if(nombreEtage!=0 && numeroEnfant==nextParent)
    {
      nextParent = enfantRandom(0,enfantEtageSuivant-2);
      System.out.println("Range random :"+0+" up to "+ (enfantEtageSuivant-2));
      System.out.println("nextParent "+nextParent);
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

  // public void updateXYZ()
  // {
  //   //la hauteur ne change jamais (Z), il faut positionner tout ça sur un cercle trigo
  //   //la coordonnée en X est donc le sinus de A
  //   //la coordonnée en Z est donc le cosinus de A
  //   //mais il faut les multiplier par la norme du vecteur (0,0,0)>>(x,0,z);
  //
  //   float distanceInitiale = (float)distancePlanTo(parent); // L
  //
  //   double angleRad = Math.toRadians(a);
  //   float cosinus = (float)Math.cos(angleRad);
  //   float sinus = (float)Math.sin(angleRad);
  //
  //   float distanceOrigine = (float)distancePlanOrigine();//l , peut etre egale a O
  //
  //   float scaling = (distanceOrigine==0)?0:distanceInitiale/distanceOrigine;
  //   //je veux L = scaling * l
  //   x = sinus * scaling;
  //   y=y;
  //   z= cosinus * scaling;
  // }

  public void update(){
    updateAngle();
    updateXYZ();

    x=parent.x+DECALAGE_ENFANT*numeroEnfant;
    y=parent.y-HAUTEUR;
    z=parent.z;
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
    //this.myGlut.glutSolidSphere(RADIUS, RESOLUTION, RESOLUTION);
    gl.glPopMatrix();

  }

  public ArrayList<Pendule> getEnfants()
  {
    ArrayList<Pendule> tous = new ArrayList<Pendule>();
    tous.add(this);
    for(Pendule enfant : enfants)
    {
      tous.addAll(enfant.getEnfants());
    }

    return tous;
  }

  public void drawLines(GL2 gl)
  {//on va pour chaque enfants draw une line qui sera horizontal jusqu'a la position de l'enfant et puis vertical jusqu'a la hauteur de l'enfant

    if(enfants.size()>0)
    {
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
        printDistance(enfant);
        System.out.println(enfant);


      }
      System.out.println("\n");
      gl.glEnd();
    }

  }

  public String toString()
  {
    return "Enfant "+numeroEnfant+" etage "+numeroEtage+" "+x+" "+y+" "+z+" "+a;
  }

  public void printDistance(Pendule o)
  {
    double distance = distanceTo(o);
    System.out.println("C "+numeroEnfant+" f "+numeroEtage+" to C "+o.numeroEnfant+" f "+o.numeroEtage+" = "+distance);
  }

}
