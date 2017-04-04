package modele;
import com.jogamp.opengl.GL2;
public class Triangle extends ObjetSimple3D{

        public void init( GL2 gl){

        }

        public void appliqueChangementRepere(GL2 gl){
        }
        public void update(){
//            transx+=0.1;
        }

        public void affiche(GL2 gl){
        gl.glColor3f(0.0f,1.0f,1.0f);
        gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
        gl.glPushMatrix();
        this.appliqueChangementRepere(gl);
        gl.glBegin(GL2.GL_TRIANGLES);
            gl.glVertex3d( 0, 0, 0);
            gl.glVertex3d(2, 0, 0);
              gl.glVertex3d(2, 1, 0);

        gl.glEnd();
        gl.glPopMatrix();
    }


}
