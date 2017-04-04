package modele;
import com.jogamp.opengl.GL2;
public class Maison extends ObjetSimple3D{

        public void init( GL2 gl){

        }

        public void appliqueChangementRepere(GL2 gl){
        }
        public void update(){
//            transx+=0.1;
        }

        public void affiche(GL2 gl){
        gl.glEnable(gl.GL_DEPTH_TEST);

        gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
        gl.glPushMatrix();
        this.appliqueChangementRepere(gl);

        gl.glBegin(gl.GL_TRIANGLE_FAN );

        //toit
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glVertex3d(0,0.5,0);

        gl.glVertex3d(1, -1, 0);
        gl.glVertex3d(0, -1, 1);
        gl.glVertex3d(-1, -1, 0);
        gl.glVertex3d(0, -1, -1);
        gl.glVertex3d(1, -1, 0);
        gl.glEnd();
        //maison body


        gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
        gl.glBegin(gl.GL_QUAD_STRIP);
        gl.glColor3f(253/255f, 241/255f, 184/255f);
        gl.glVertex3d(-0.6, -2, 0);
        gl.glVertex3d(-0.6, -1, 0);


        gl.glVertex3d(0, -2, -0.6);
        gl.glVertex3d(0, -1, -1);

        gl.glVertex3d(0.6, -2, 0);
        gl.glVertex3d(0.6, -1, 0);

        gl.glVertex3d(0, -2, 0.6);
        gl.glVertex3d(0, -1, 0.6);

        gl.glVertex3d(-0.6, -2, 0);
        gl.glVertex3d(-0.6, -1, 0);
        gl.glEnd();



        gl.glPopMatrix();
    }


}
