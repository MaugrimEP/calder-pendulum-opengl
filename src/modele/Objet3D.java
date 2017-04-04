package modele;
import com.jogamp.opengl.GL2;
public interface Objet3D{
        public void init(GL2 gl); // initialisation
        public void appliqueChangementRepere(GL2 gl); // Applique les transformations à effectuer avant l'affichage de l'objet
        public void update(); // permet de faire évoluer l'objet
        public void affiche(GL2 gl);
}
