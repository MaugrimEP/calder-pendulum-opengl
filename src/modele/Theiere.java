package modele;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class Theiere extends ObjetSimple3D
{

  public GLUT glut = new GLUT();
  float posX=0.0f;
  float posY=0.0f;
  float posZ=0.0f;

  public float angle=1f;
  public float acceleration=1.0f;

  float R=0.0f;
  float V=1.0f;
  float B=1.0f;

  public void init(GL2 gl)
  {

  }

  public void appliqueChangementRepere(GL2 gl)
  {
    gl.glTranslated(posX,posY,posZ);
    gl.glRotatef(angle,0,1,0);

  }

  public void update()
  {
    //posX+=0.2;
    if(angle>=360)
    {
      angle=1.0f;
    }
    angle+= acceleration ;
  }

  public void setColor(float r,float v,float b)
  {
    R=r;
    V=v;
    B=b;
  }

  public void affiche(GL2 gl)
  {
      this.appliqueChangementRepere(gl);
      gl.glColor3f(R,V,B);
      this.glut.glutSolidTeapot(0.4f);

  }
}
