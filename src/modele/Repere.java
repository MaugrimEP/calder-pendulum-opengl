package modele;
import com.jogamp.opengl.GL2;
public class Repere extends ObjetSimple3D{

        double transx=0.;
        public void init( GL2 gl){
           }

        public void appliqueChangementRepere(GL2 gl){
        gl.glTranslated(transx,-2.,0.);
        }
        public void update(){

        }


    void fleche(GL2  gl,float t1,float t2,float t3,float taille,char dim){

        switch(dim)
        {
            case 'x':
                gl.glBegin(GL2.GL_LINE_STRIP);
                gl.glVertex3f (t1-taille, t2+taille, t3);
                gl.glVertex3f (t1, t2, t3);
                gl.glVertex3f (t1-taille, t2-taille, t3);
                gl.glEnd();
                break;
            case 'y':
                gl.glBegin(GL2.GL_LINE_STRIP);
                gl.glVertex3f (t1, t2-taille, t3+taille);
                gl.glVertex3f (t1, t2, t3);
                gl.glVertex3f (t1, t2-taille, t3-taille);
                gl.glEnd();
                break;
            case 'z':
                gl.glBegin(GL2.GL_LINE_STRIP);
                gl.glVertex3f (t1, t2+taille, t3-taille);
                gl.glVertex3f (t1, t2, t3);
                gl.glVertex3f (t1, t2-taille, t3-taille);
                gl.glEnd();
                break;
            default :;
        }
    }


    void repere(GL2  gl,float t) {
        // TODO Auto-generated method stub

        float coef=1;
        gl.glPushAttrib(GL2.GL_LIGHTING);

        gl.glDisable(GL2.GL_LIGHTING);

        gl.glLineWidth(1.0f);
        gl.glBegin(GL2.GL_LINES);
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glVertex3f (t, 0.0f, 0.0f);
        gl.glVertex3f (-t, 0.0f, 0.0f);
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glVertex3f ( 0.0f, 0.0f, coef*t);
        gl.glVertex3f ( 0.0f, 0.0f, -t*coef);
        gl.glColor3f(0.0f,1.0f,1.0f);
        gl.glVertex3f ( 0.0f, t, 0.0f);
        gl.glVertex3f ( 0.0f, -t, 0.0f);
        gl.glEnd();
        gl.glColor3f(1.0f,0.0f,0.0f);
        fleche(gl,t, 0.0f, 0.0f,t*0.1f ,'x');
        gl.glColor3f(0.0f,1.0f,0.0f);
        fleche(gl,0.0f, 0.0f, coef*t,t*0.1f ,'z');
        gl.glColor3f(0.0f,1.0f,1.0f);
        fleche(gl,0.0f, t, 0.0f,t*0.1f ,'y');
        gl.glLineWidth(0.1f);
        gl.glPopAttrib();

    }


    public void affiche(GL2 gl){
        gl.glPushMatrix();
        repere(gl,1);
        gl.glPopMatrix();
    }

}
