import java.awt.Color;
import java.awt.Graphics;

public class Bombe extends Projectile{//WORK IN PROGRESS il faut trouver comment détecter s'il y a des karts dans la zone d'explosion quand elle explose
    
    private static double rayExpl;//le rayon de l'explosion
    
    public Bombe(double x, double y,double dx, double dy){
        super(x,y,dx,dy,Color.black);  
        rayExpl=3;
        this.nomObjet="BOMBE";
    }
    
    public boolean quandExploser(){
        boolean exp=false;
        if (tempsVie>160){//la bombe explose au bout de 4 secondes, on incrémente le temps de vie toutes les 25ms --->160 itérations en tout
            exp=true;
        }return exp;
    }
    public void doCollision(Item item){
        this.explosion();
    }
    public boolean colliExplosion(Item item){
        boolean colli=false;
        if (item.nomObjet=="KART"){
            for (int i=0;i<5;i++){
                if (Math.sqrt((this.x-item.tabx[i])*(this.x-item.tabx[i])+(this.y-item.taby[i])*(this.y-item.taby[i]))<rayExpl){
                    colli=true;
                }
            }
            
        }
        else{//on a donc affaire à un bonus
            if (Math.sqrt((this.x-item.x)*(this.x-item.x)+(this.y-y)*(this.y-item.y))<rayExpl){
                colli=true;
            }
        }
        
        return colli;
        }

    public void explosion(){
        for (int i=0;i<graphTest.nbJoueurs;i++){//regarde si des karts sont présents dans l'explosion
            Item O=graphTest.Items.get(i);
            if (this.colliExplosion(O)){
                O.frontSpeed=0;
                double X=O.x-this.x;
                double Y=O.y-this.y;
                double distance = Math.sqrt(X*X+Y*Y);
                O.x=x+(X/distance)*5;//pousse le kart dans la direction opposée au centre de l'explosion
                O.y=y+(Y/distance)*5;
            }
        }
        for (int i=graphTest.nbJoueurs; i<graphTest.Items.size();i++){//regarde si des bonus sont compris dans l'explosion
            Item O=graphTest.Items.get(i);
            if (this.colliExplosion(O)){
                O.actif=false;
            }
        }

    }
    
    public void move(){
        
    }
   
   /* public void drawGraphTest(Graphics g){
        g.setColor(Color.black);
        g.fillOval((int)x,(int)(576-y),10,10);//rayon du cercle à adapter avec l'échelle
    }
    public void draw(Graphics g, int x, int y){
        g.setColor(Color.black);
        g.fillOval(x,y,10,10);//rayon du cercle à adapter avec l'échelle
    }*/
}
