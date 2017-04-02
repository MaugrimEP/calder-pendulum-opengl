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
            ((modele.Theiere)this.controleur.m.objets[1]).acceleration+=1;
            break;
          }

          case MouseEvent.BUTTON2 ://molette
          {
            System.out.println("bouton2");
            break;
          }

          case MouseEvent.BUTTON3 ://droit
          {
            ((modele.Theiere)this.controleur.m.objets[1]).acceleration-=1;
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
