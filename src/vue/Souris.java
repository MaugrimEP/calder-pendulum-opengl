package vue;
import controleur.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.*;


public class Souris implements MouseListener{

    Controleur controleur;

    public  Souris(Controleur controleur){
        this.controleur = controleur;
    }


    public void mouseClicked (MouseEvent me) {

        switch (me.getButton())
        {
          case MouseEvent.NOBUTTON :
          {
            break;
          }

          case MouseEvent.BUTTON1 ://gauche
          {

            break;
          }

          case MouseEvent.BUTTON2 ://molette
          {

            break;
          }

          case MouseEvent.BUTTON3 ://droit
          {
            
            break;
          }


        }


    }
    public void mouseEntered (MouseEvent me) {}
    public void mousePressed (MouseEvent me) {
    }
    public void mouseReleased (MouseEvent me) {
    }
    public void mouseExited (MouseEvent me) {}

}
