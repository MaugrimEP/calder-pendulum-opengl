package vue;
import controleur.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.*;



public class Clavier implements KeyListener{
    Controleur controleur;

    public  Clavier(Controleur c){
        controleur = c;
    }


    public void keyTyped(KeyEvent e){
        key(e.getKeyChar(), 1, 1);
    }



    void key(char key,int x,int y) {
        switch ( key ) {
            case 'h'  : controleur.deplacerCam(0.2,0,0);
                        break;
            case 'k'  : controleur.deplacerCam(-0.2,0,0);
                        break;
            case 'u'  : controleur.deplacerCam(0,0.2,0);
                        break;
            case 'j'  : controleur.deplacerCam(0,-0.2,0);
                        break;
            case 'v'  : ((modele.Theiere)this.controleur.m.objets[1]).setColor(0f,1f,0f);
                        break;
            case 'r'  : ((modele.Theiere)this.controleur.m.objets[1]).setColor(255f,0f,0f);
                        break;
            case 'n'  : ((modele.Theiere)this.controleur.m.objets[1]).setColor(0f,0f,0f);
                        break;
            case 'b'  : ((modele.Theiere)this.controleur.m.objets[1]).setColor(0f,0f,255f);
                        break;
            case 'B'  : ((modele.Theiere)this.controleur.m.objets[1]).setColor(1f,1f,1f);
                        break;
            case 'm'  : ((modele.Theiere)this.controleur.m.objets[1]).setColor(255f, 0f, 255f);
                        break;
            case 'c'  : ((modele.Theiere)this.controleur.m.objets[1]).setColor(0f,1f,1f);
                        break;
    }
    }
    /*« v » : vert
    « r »: rouge
    « b »:bleu
    « n »:noir
    « B »:blanc
    « m »:magenta
    « c »:cyan
    */


    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_PAGE_UP : controleur.deplacerCam(0,0,-0.2);
                break;
            case KeyEvent.VK_PAGE_DOWN : controleur.deplacerCam(0,0,+0.2);
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT :
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;


        }


    }

    public void keyReleased(KeyEvent e){
    }

}
