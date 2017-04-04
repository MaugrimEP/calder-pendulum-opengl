package modele;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
public class Sattelite extends ObjetSimple3D{

        public float angle=0;
        public float acceleration=1f;

        public float posX=0;
        public float posY=0;
        public float posZ=0;

        public GLUT myGlut= new GLUT();

        public Planete myPlanete;


        public void init( GL2 gl){
        }



        public void appliqueChangementRepere(GL2 gl){
          gl.glRotatef(angle,0,1,0);

          gl.glTranslated(myPlanete.posX+posX,myPlanete.posY+posY,myPlanete.posZ+posZ);

        }
        public void update(){
              if(angle>=360)
              {
                angle=0;
              }
              angle += acceleration ;
        }

        public void affiche(GL2 gl){
        gl.glEnable(gl.GL_DEPTH_TEST);

        gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
        gl.glPushMatrix();
        this.appliqueChangementRepere(gl);

        this.myGlut.glutSolidSphere(0.10, 20,20);

        gl.glEnd();
        gl.glPopMatrix();
    }


}
