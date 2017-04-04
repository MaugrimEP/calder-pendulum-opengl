package vue;
import controleur.*;
import modele.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Vue extends JFrame implements GLEventListener{
    private Modele modele;
    private GLU glu = new GLU();
    private GLUT glut = new GLUT();
    private Controleur c;
    private static final long serialVersionUID = 1L;
 //   private  Affichage3D affichage=new Affichage3D();

    public Vue(Controleur c, Modele m){
        this.modele = m;
        this.setTitle("TP Programmation 3D");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final GLCanvas zoneAffichage= new GLCanvas ();
        this.c = c ;
        final Souris souris=new Souris(c);
        zoneAffichage.addMouseListener(souris);

        final Clavier clavier =new Clavier(c);
        zoneAffichage.addKeyListener(clavier);

        zoneAffichage.addGLEventListener(this);
        this.getContentPane().add(zoneAffichage);

        final Animator anim = new Animator(zoneAffichage);
        anim.start(); // Pour raffraichir automatiquement l'affichage.
    }

    @Override
    public void display(GLAutoDrawable drawable) {
            GL2 gl = drawable.getGL().getGL2();
            gl.glClear (GL2.GL_COLOR_BUFFER_BIT |GL2.GL_DEPTH_BUFFER_BIT );
            float fps=drawable.getAnimator().getLastFPS();
            this.setTitle("Framerate: "+fps);
            modele.positionnerCam(gl,glu);

            for(Objet3D objet : modele.getObjets()){
                objet.affiche(gl);
            }
        }

    @Override
        public void dispose(GLAutoDrawable drawable) {
        }


    @Override
        public void init(GLAutoDrawable drawable) {
            GL2 gl = drawable.getGL().getGL2();
            gl.glClearColor (0f, 0f, 0f, 1.0f);
            //gl.glEnable(GL2.GL_DEPTH_TEST);
            drawable.getAnimator().setUpdateFPSFrames(10, null);
            drawable.getGL().setSwapInterval(0);
            modele.init(gl);
        }

    @Override
        public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                int height) {
            GL2 gl = drawable.getGL().getGL2();
            gl.glViewport (0, 0, (int) width, height);
            gl.glMatrixMode (GL2.GL_PROJECTION);
            gl.glLoadIdentity ();
            glu.gluPerspective(70.0,  width/(float) height, 0.01, 150.0);
            gl.glMatrixMode(GL2.GL_MODELVIEW);
        }



}
