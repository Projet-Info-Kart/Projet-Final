import java.awt.Dimension;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelSelectionKart extends JPanel {
    
    JButton Next = new JButton("Next");
    JButton Previous = new JButton("Previous");
    JButton Pick = new JButton("Pick this one !");
    JButton Back = new JButton("Back to main menu");
    
    Image kart1;
    Image kart2;
    Image kart3;
    
    int position = 1;
    
    public PanelSelectionKart(){
        
        setLayout(new GridLayout (2,2,350,600));
        
        Next.setMaximumSize(new Dimension (150,50)); //règle la taille horizontale
        Next.setPreferredSize(new Dimension (150,50));
        
        Previous.setMaximumSize(new Dimension (150,50)); //règle la taille horizontale
        Previous.setPreferredSize(new Dimension (150,50));
        
        Pick.setMaximumSize(new Dimension (250,50)); //règle la taille horizontale
        Pick.setPreferredSize(new Dimension (250,50));
        
        Back.setMaximumSize(new Dimension (150,50)); //règle la taille horizontale
        Back.setPreferredSize(new Dimension (150,50));
        
        //Règle la taille du texte dans chaque button
        Font newButtonFont=new Font(Next.getFont().getName(),Next.getFont().getStyle(),30);
        Next.setFont(newButtonFont);
        Previous.setFont(newButtonFont);
        Font newButtonPickFont=new Font(Pick.getFont().getName(),Pick.getFont().getStyle(),25);
        Pick.setFont(newButtonPickFont);
        Font newButtonBackFont=new Font(Back.getFont().getName(),Back.getFont().getStyle(),20);
        Back.setFont(newButtonBackFont);
        
        add(Previous);
        add(Next);
        add(Back);
        add(Pick);
        
        try { 
            kart1= ImageIO.read(new File("selectionKartMario.png")); 
            //System.out.println("selectionKartMario"+kart1);
        }catch(Exception err) { 
            System.out.println("selectionKartMario.png"+" introuvable !");             
            System.exit(1); 
            } 
        
        try { 
            kart2= ImageIO.read(new File("selectionKartDonkey.png")); 
            //System.out.println("selectionKartDonkey"+kart2);
        }catch(Exception err) { 
            System.out.println("selectionKartDonkey.png"+" introuvable !");             
            System.exit(1); 
            } 
        
    
        try { 
            kart3= ImageIO.read(new File("selectionKartYoshi.png")); 
            //System.out.println("selectionKartYoshi"+kart3);
        }catch(Exception err) { 
            System.out.println("selectionKartYoshi.png"+" introuvable !");             
            System.exit(1); 
            } 
    
      //action de Next, incremente la variable position avec des conditions aux limites de position
      Next.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent actionEvent){
           position=position+1;
            if(position==0){
                position=3;
            }
            if(position==4){
                position=1;
            }
        } 
        });
    
     //action de Previous, decremente la variable position avec des conditions aux limites de position
    Previous.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent actionEvent){
             position=position-1;
             if(position==0){
                 position=3;
             }
             if(position==4){
                 position=1;
             }
         } 
         });
     
    }
    
    //Chaque image correspond à une valeur de la variable position
    public void paint(Graphics g){
        
        switch (position) {
        case 1 : g.drawImage(kart1,0,0,getWidth(),getHeight(),null);//paint l'image en adaptant la taille de l'écran
            break;
        case 2 : g.drawImage(kart2,0,0,getWidth(),getHeight(),null);
            break;
        case 3 : g.drawImage(kart3,0,0,getWidth(),getHeight(),null);
            break;
        }
        Next.repaint();
        Previous.repaint();
        Back.repaint();
        Pick.repaint();
       
    }
    
    public int getPosition(){
        return position;    
    }
}
