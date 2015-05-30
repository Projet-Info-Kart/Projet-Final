import java.awt.Color;
import java.awt.Graphics;

public class Banane extends Projectile{
    
    public Banane(double x, double y,double dx, double dy){
        super(x,y,dx,dy,Color.yellow); 
        this.nomObjet="BANANE";
    }
    
    public void doCollision(Item item){
        item.frontSpeed=0;
        this.actif=false;
        item.actif=false;
    }
    
    public void move(){
            
    }
        
  /*  public void drawGraphTest(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval((int)x,(int)(576-y),5,5);//rayon du cercle à adapter aver l'échelle
    }
    
    public void draw(Graphics g,int x, int y){
        g.setColor(Color.yellow);
        g.fillOval(x,y,5,5);//rayon du cercle à adapter aver l'échelle
    }*/
}
