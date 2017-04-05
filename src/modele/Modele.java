package modele;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import java.util.ArrayList;
import java.util.Random;

public class Modele{
    public Objet3D[] objets;
    private class Camera{
        double x=0,y=0,z=3;
        double dirX=0,dirY=0,dirZ=0;
        double upX=0,upY=1,upZ=0;
        Camera(){

        }
        void positionner(GL2 gl, GLU glu){
            gl.glLoadIdentity();
            glu.gluLookAt ( x,y,z ,dirX,dirY,dirZ,upX,upY,upZ);
            //glu.gluLookAt(0,10,0,0,0,0,1,0,0);
        }
        void deplacer(double dx, double dy, double dz)
        {
            x+=dx;
            dirX+=dx;
            z+=dz;
            dirZ+=dz;
            y+=dy;
            dirY+=dy;
        }
    }

    Camera cam=new Camera();
    public  void deplacerCam(double dx, double dy, double dz){
        cam.deplacer(dx,dy,dz);
    }

    public void positionnerCam(GL2 gl, GLU glu){
        cam.positionner(gl, glu);
    }

    public String getRandomFileName()
    {
      String lesNoms[] = {"elephant_low_ply","low_poly_tree","wolf_low_poly","X-Wing"};
      int longueur = lesNoms.length;
      Random rand = new Random();
      int indice = rand.nextInt(longueur - 0 ) + 0;
      System.out.println(lesNoms[indice]);
      return lesNoms[indice];

    }


    public Modele(){

      PenduleHead penduleHead = new PenduleHead(2,2);//nb étages, enfants par étages, 1,2
      Repere repere = new Repere();

      ArrayList<Objet3D> lesobjs = new ArrayList<Objet3D>();
      lesobjs.add(penduleHead);
      lesobjs.add(repere);
      ArrayList<Objet3D> lesEnfants= new ArrayList<Objet3D>();
      lesEnfants.addAll(penduleHead.getEnfants());
      lesobjs.addAll(lesEnfants);
      for(int d=0;d<lesEnfants.size();++d)
      {
        lesobjs.add(new JSON_VBO_shading(getRandomFileName(),(Pendule)lesEnfants.get(d)));
      }

      int size=lesobjs.size();

      objets=new Objet3D[size];

      for(int i=0;i<size;++i)
      {
        objets[i]=lesobjs.get(i);
      }


    }
    public void init(GL2 gl){
        for (Objet3D objet : objets)
            objet.init(gl);
    }
    public Objet3D[] getObjets(){
        return objets;
    }
    public void update(){
      int i=0;
        for (Objet3D objet : objets)
        {
          objet.update();
          //System.out.println(i+" "+objet);
          i++;
        }
    }

}
