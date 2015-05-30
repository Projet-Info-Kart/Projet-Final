import java.awt.Color;
import java.awt.Graphics;

public class Cadeau extends Item{
    private boolean visible;//quand le cadeau est touché par un kart, il devient invisible pour quelques secondes
    private static double rayon=15;
    
    
    
    public Cadeau(double x, double y, double dx, double dy){
        super(x,y,dx,dy);
        visible=true;
        this.nomObjet="CADEAU";
    }
    
    public boolean collision(Item item){
        boolean colli=false;
        if (item.nomObjet=="KART" && this.visible){
            for (int i=0;i<5;i++){
                if (Math.sqrt((this.x-item.tabx[i])*(this.x-item.tabx[i])+(this.y-item.taby[i])*(this.y-item.taby[i]))<rayon){
                    colli=true;
                    this.visible=false;
                    this.tempsVie=0;
                }
            }
        }return colli;
    }
    
   /* public void drawGraphTest(Graphics g){
        if (visible){
            g.setColor(Color.pink);
            g.fillOval((int)x,(int)(576-y),(int)rayon,(int)rayon);//rayon du cercle à adapter avec l'échelle
        }
    }*/
    public void draw(Graphics g,int x,int y){
        if (visible){
            g.setColor(Color.pink);
            g.fillOval(x,y,(int)rayon,(int)rayon);//rayon du cercle à adapter avec l'échelle
        }
    }
    
    public void rendVisible(){
        if (!visible){
            if (tempsVie>250){
                visible=true;
            }
        }
    }
    
    public void doCollision(Item item){
        if (!((Kart)item).Bonus()){
            int b=(int)((Math.random()*3)+1);//nombre entier aléatoire entre 1 et 3
            ((Kart)item).setBonus(b);
        } 
    }
    
    public void move(){
        
    }


}
