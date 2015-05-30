import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.Timer;

public class graphTest extends JFrame {
    Image background;
    BufferedImage ArrierePlan;
    Graphics buffer;
    int xpos=0;
    Kart kart1;
    Kart kart2;
    boolean ToucheHaut,ToucheBas,ToucheDroite,ToucheGauche,FlecheHaut,FlecheBas,FlecheDroite,FlecheGauche,Touche5,Touche2,Shift,Space;
    char tourne;
    char freine;
    static ArrayList <Item> Items;//liste de tous les objets actifs
    double X; //le x du kart que l'on  récupérera une fois par boucle pour éviter d'avoir à appeler tous les temps les accesseurs
    double Y;
    double DX;
    double DY;
    static int nbJoueurs=1;//2 si on est en mode 2joueurs static pour les tests
    
    
    
    class TimerAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boucle_principale_jeu();
        }
    }

    public graphTest(){
        try{
            background=ImageIO.read(new File("stars.jpg"));
        }catch (IOException e){
            System.out.println("Could not load image file");
            System.exit(1);
        }
        ToucheHaut=false;ToucheBas=false;ToucheDroite=false;ToucheGauche=false; 
        
        this.setTitle("Karting");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,576);
        this.setVisible(true);
        //Rectangle Ecran=new Rectangle(getInsets().left,getInsets().top,getSize().width-getInsets().right-getInsets().left,getSize().height-getInsets().bottom-getInsets().top);
        kart1=new Kart(300,300,0,1,15,10,0.1,150,1);
        kart2=new Kart(400,400,0,1,15,10,0.1,150,1);
        this.addKeyListener(new graphTest_this_keyAdapter(this));
        ArrierePlan=new BufferedImage(2000,2000,BufferedImage.TYPE_INT_RGB);
        buffer=ArrierePlan.getGraphics();
        
        Items=new ArrayList <Item>();//les karts sont toujours ajoutés en premier dans la liste, puis les bonus après
        Items.add(kart1);
        Cadeau c=new Cadeau(320,320,1,0);//test avec un cadeau
        Items.add(c);
        
        Timer timer=new Timer(25,new TimerAction());
        timer.start();
        
        
    }
    public void boucle_principale_jeu(){
         X=kart1.getX();
         Y=kart1.getY();
         DX=kart1.getdx();
         DY=kart1.getdy();

        if (ToucheBas){
            freine='y';
            kart1.freine();
        }
        if (ToucheGauche){
            tourne='g';
        }
        else if (ToucheDroite){
            tourne='d';
        }
        else{
            tourne='0';
        }
        if (ToucheHaut && (ToucheGauche || ToucheDroite )) { 
            kart1.avance(1); 
        }
        else if (ToucheHaut){
            kart1.avance(0);
        }
        kart1.tourne(tourne);
        if (ToucheHaut==false && (ToucheGauche || ToucheDroite )){ 
            kart1.ralentit(1); 
        }
        else if (ToucheHaut==false){
            kart1.ralentit(0);
        }
        kart1.derapage(tourne,freine);
        freine='n';
        if (kart1.Bonus()){//Si le kart a un bonus dispo
            if (Shift || Space ){//FlecheHaut pour tirer missile haut, FlecheBas pour le tirer en bas, Space pour poser bombe ou banane
                if (kart1.getNomBonus()=="MISSILE"){
                    if (Shift){
                        Missile m=new Missile(X+DX*2.5,Y+DY*2.5,DX,DY);//le missile est créé devant si on tire devant
                        Items.add(m);
                        kart1.setABonus(false);
                    }
                    else if (Space){
                        Missile m=new Missile(X-DX*2.5,Y-DY*2.5,-DX,-DY);//ou derrière si on tire derrière
                        Items.add(m);
                        kart1.setABonus(false);
                    }
                }
                else if (kart1.getNomBonus()=="BANANE"){
                    if (Space){
                        Banane b=new Banane(X-DX*2.5,Y-DY*2.5,DX,DY);
                        Items.add(b);
                        kart1.setABonus(false);
                    }
                }
                else if (kart1.getNomBonus()=="BOMBE"){
                    if (Space){
                        Bombe b=new Bombe(X-DX*2.5,Y-DY*2.5,DX,DY);
                        Items.add(b);
                        kart1.setABonus(false);
                    }
                }
            }
        }
        kart1.calculTheta();
        kart1.coordCoinsX();
        kart1.coordCoinsY();
        /* if (kart1.colliMurs()){//en commentaire pour les tests
            kart1.setX(X-DX*2.5);
            kart1.setY(Y-DY*2.5);
            kart1.setSpeed(0);                        
        }*/
        if (nbJoueurs==2){
            X=kart2.getX();
            Y=kart2.getY();
            DX=kart2.getdx();
            DY=kart2.getdy();

            if (FlecheBas){
               freine='y';
               kart2.freine();
            }
            if (FlecheGauche){
               tourne='g';
            }
            else if (FlecheDroite){
               tourne='d';
            }
            else{
               tourne='0';
            }
            if (FlecheHaut && (FlecheGauche || FlecheDroite )) {
               kart2.avance(1); 
            }
            else if (FlecheHaut){
               kart2.avance(0);
            }
            kart2.tourne(tourne);
            if (FlecheHaut==false && (FlecheGauche || FlecheDroite )){
               kart2.ralentit(1); 
            }
            else if (FlecheHaut==false){
               kart2.ralentit(0);
            }
            kart2.derapage(tourne,freine);
            freine='n';
            if (kart2.Bonus()){//Si le kart a un bonus dispo
               if (Touche5 || Touche2 ){//FlecheHaut pour tirer missile haut, FlecheBas pour le tirer en bas, Space pour poser bombe ou banane
                   if (kart2.getNomBonus()=="MISSILE"){
                       if (Touche5){
                           Missile m=new Missile(X+DX*2.5,Y+DY*2.5,DX,DY);//le missile est créé devant si on tire devant
                           Items.add(m);
                           kart2.setABonus(false);
                       }
                       else if (Touche2){
                           Missile m=new Missile(X-DX*2.5,Y-DY*2.5,-DX,-DY);//ou derrière si on tire derrière
                           Items.add(m);
                           kart2.setABonus(false);
                       }
                   }
                   else if (kart2.getNomBonus()=="BANANE"){
                       if (Touche2){
                           Banane b=new Banane(X-DX*2.5,Y-DY*2.5,DX,DY);
                           Items.add(b);
                           kart2.setABonus(false);
                       }
                   }
                   else if (kart2.getNomBonus()=="BOMBE"){
                       if (Touche2){
                           Bombe b=new Bombe(X-DX*2.5,Y-DY*2.5,DX,DY);
                           Items.add(b);
                           kart2.setABonus(false);
                       }
                   }
               }
            }
            kart2.coordCoinsX();
            kart2.coordCoinsY();
            if (kart2.colliMurs()){
                kart2.setX(X-DX*2.5);
                kart2.setY(Y-DY*2.5);
                kart2.setSpeed(0);    
            }
            if (kart1.collision(kart2)){
                //WORK IN PROGRESS méthode de collision entre les 2 karts à créer
            }  
        }
        for (int i=0;i<Items.size();i++){
            Item O=Items.get(i);
            O.setTemps();
            O.move();
        }
        
        for (int i=nbJoueurs;i<Items.size();i++){
            Item O = Items.get(i);
            for (int j=0;j<nbJoueurs;j++){//ça teste la collision avec les karts
                Item l=Items.get(j);
                if (O.collision(l)){
                    O.doCollision(l);
                }
            }
            for (int j=i+1;j<Items.size();j++){//puis avec les autres bonus
            Item l=Items.get(j);
                if (O.collision(l)){
                    O.doCollision(l);
                }
            }
        }
        
        for (int i=nbJoueurs;i<Items.size();i++){//regarde si une bombe doit exploser d'elle meme
            Item O=Items.get(i);
            if (O.nomObjet=="BOMBE"){
                if (((Bombe)O).quandExploser()){
                   ((Bombe)O).explosion();
                }
            }
            if(O.nomObjet=="CADEAU"){
                ((Cadeau)O).rendVisible();
                
            }
        }
        //Garbage collector
        for (int k=nbJoueurs; k<Items.size(); k++) {
            Item O = Items.get(k);
            if (O.actif==false) {
                Items.remove(k);
                    k--; 
            }
        }
        
        repaint();
    }
    public void paint(Graphics g){
        buffer.drawImage(background,0,0,this);
        //Graphics2D g2d=(Graphics2D)buffer;//partie où on fait pivoter l'image
        //AffineTransform transformer=g2d.getTransform();
        //kart1.drawGraphTest(buffer);
        //System.out.println(thetaIm);
        //g2d.rotate(-thetaIm,kart1.getX(),kart1.getY());
        //g.drawImage(ArrierePlan,0,0,this);
        for (int k=0; k<Items.size(); k++) {
            Item O = Items.get(k);
            //O.drawGraphTest(buffer);
        }
        // dessine une seule fois le buffer dans le Panel
        g.drawImage(ArrierePlan,0,0,this);
  
    }
    
     void this_keyPressed(KeyEvent e){
        int code= e.getKeyCode();
        System.out.println("Key pressed : "+code);
        if (code==68){
             ToucheDroite=true;
        }
        
        else if (code==81){
             ToucheGauche=true;
        } 
        
        else if (code==83){
            ToucheBas=true;
        } 
        
        else if (code==90){
            ToucheHaut=true;
        }
        else if (code==16){
            Shift=true;
        }
        else if (code==32){
            Space=true;
        }
        
        if (ToucheHaut && ToucheBas){
            ToucheHaut=false;
            ToucheBas=false;
        }
        if (ToucheGauche && ToucheDroite){
            ToucheGauche=false;
            ToucheDroite=false;
        }
        //TOUCHES DU KART 2
        if (code==38){
            FlecheHaut=true;//flèche de la droite du clavier, différent de ToucheHaut (z)
        }
        else if (code==40){
            FlecheBas=true;
        }
        else if (code==37){
            FlecheDroite=true;
        }
        else if (code==39){
            FlecheGauche=true;
        }
        else if (code==101){
            Touche5=true;
        }
        else if (code==98){
            Touche2=true;
        }
        
        if (FlecheHaut && FlecheBas){
            FlecheHaut=false;
            FlecheBas=false;
        }
        if (FlecheGauche && FlecheDroite){
            FlecheGauche=false;
            FlecheDroite=false;
        }
    }

    void this_keyReleased(KeyEvent e){
        int code=e.getKeyCode();
        System.out.println("Key released : "+code);
        if (code==68){
            ToucheDroite=false;
        }
        else if (code==81){
            ToucheGauche=false;
        }   
        else if (code==83){
            ToucheBas=false;
        } 
        else if (code==90){
                ToucheHaut=false;
        }
        else if (code==38){
            FlecheHaut=false;//flèche de la droite du clavier, différent de ToucheHaut (z)
        }
        else if (code==16){
            Shift=false;
        }
        else if (code==40){
            FlecheBas=false;
        }
        else if (code==32){
            Space=false;
        }
        else if (code==101){
            Touche5=false;
        }
        else if (code==98){
            Touche2=false;
        }
        else if (code==37){
            FlecheDroite=false;
        }
        else if (code==39){
            FlecheGauche=false;
        }
    } 
    
    private class graphTest_this_keyAdapter extends KeyAdapter{
        private graphTest adaptee;
        graphTest_this_keyAdapter(graphTest adaptee){
           this.adaptee=adaptee; 
        }
        public void keyPressed(KeyEvent e){
            adaptee.this_keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            adaptee.this_keyReleased(e);
        }  
        
    }
    public static void main(String args[]){
        graphTest fenetre=new graphTest();
    }

}
