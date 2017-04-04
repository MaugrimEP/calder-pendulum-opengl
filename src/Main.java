import vue.*;
import controleur.*;
import modele.*;
public class Main {
    public static void main(String [] args){
        Modele m=new Modele();
        Controleur controleur = new Controleur(m);
        Vue vue = new Vue(controleur, m);
        vue.setVisible(true);
    }
}
