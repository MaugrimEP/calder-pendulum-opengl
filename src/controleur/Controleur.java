package controleur;
import modele.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controleur {
    public final Modele m;
    public Controleur(final Modele m){
        this.m=m;
        Timer t=new Timer();
        TimerTask task = new TimerTask() {
            public void run()
            {
                m.update();
            }
        };
        t.scheduleAtFixedRate(task, (long)0., (long)5.) ;
    }
    public void deplacerCam(double x, double y, double z){
        m.deplacerCam(x,y,z);
    }
}
