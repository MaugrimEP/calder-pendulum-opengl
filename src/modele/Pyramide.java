package modele;
import com.jogamp.opengl.GL2;
public class Pyramide extends ObjetSimple3D{

        public float angle=0;
        public float acceleration=1;

        public void init( GL2 gl){

        }

        public void appliqueChangementRepere(GL2 gl){
          gl.glRotatef(angle,0,1,0);
        }
        public void update(){
//            transx+=0.1;
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

        gl.glBegin(gl.GL_TRIANGLE_FAN );

        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glVertex3d(0,0.5,0);

        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glVertex3d(1, -1, 0);

        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glVertex3d(0, -1, 1);
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glVertex3d(-1, -1, 0);

        gl.glColor3f(0.0f,0.0f,1.0f);
        gl.glVertex3d(0, -1, -1);

        gl.glVertex3d(1, -1, 0);

        gl.glEnd();



        gl.glPopMatrix();
    }


}
