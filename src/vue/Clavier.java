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
            case 'v'  :
                        break;
            case 'r'  :
                        break;
            case 'n'  :
                        break;
            case 'b'  :
                        break;
            case 'B'  :
                        break;
            case 'm'  :
                        break;
            case 'c'  :
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
