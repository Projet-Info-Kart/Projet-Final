import java.awt.BorderLayout;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Window2 extends JFrame {
    
    PanelSelectionKart selectionKart1 = new PanelSelectionKart();
    PanelSelectionKart selectionKart2 = new PanelSelectionKart();
    
    final int LARGPIX=1200;
    final int HAUTPIX=840;
   
    //boolean player1 = false;      //H : A quoi serve ces variables?
    //boolean player2 = false;
    
    int choice = 0;
    
    Window3 wind=new Window3();
    
    public Window2 (){
        setTitle("Pro Kart Racing 2015");
        setSize(LARGPIX,HAUTPIX); 
        GridLayout layout = new GridLayout(1, 2);
        setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(selectionKart1);
        add(selectionKart2);
        
        selectionKart1.setVisible(true);
        selectionKart2.setVisible(true);
      
        //action du button Next
        selectionKart1.Next.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent actionEvent){
                   repaint();
               }
           });
           
        selectionKart2.Next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                repaint();
            }
        });
        
        //action du button Previous
        selectionKart1.Previous.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent actionEvent){
                repaint();
                }
            });
            
        selectionKart2.Previous.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent actionEvent){
                repaint();
             }
         });

        //action du button back to main menu
        selectionKart1.Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                new Window().setVisible(true);
                dispose();
            }
        });
     
        selectionKart2.Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                new Window().setVisible(true);
                dispose();
            }
        });
        
        //action du button Pick        
        selectionKart1.Pick.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                choice=choice+1;
                selectionKart1.Pick.setEnabled(false);
                choixKart(wind.field1,1);
                
                //si les deux joueurs ont choisi leur personnage, le jeu se lance.
                if (choice == 2){
                    wind.setVisible(true);
                    wind.field1.activeCompteur();
                    wind.field2.activeCompteur();
                    dispose();
                }
                
                repaint();
            }
         });
        
        selectionKart2.Pick.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                choice=choice+1;
                selectionKart2.Pick.setEnabled(false);
                choixKart(wind.field2,2);
                
                if (choice == 2){
                    wind.setVisible(true);
                    wind.field1.activeCompteur();
                    wind.field2.activeCompteur();
                    dispose();
                    
                }
                
                repaint();
            }
         });
        
        
        
    }
    
    public void choixKart(PanelField field, int i){
        int position;
        if(i==1){position=selectionKart1.getPosition();}
        else{position=selectionKart2.getPosition();}
        field.kart1.setImage(position,2);//2 correspond au mode 2 joueurs
        switch (position) {
            case 1 :field.kart1.setAdherence(1);
                    field.kart1.setMaxSpeed(15);
                    field.kart1.setPoids(150);
                    break;
        
            case 2 :field.kart1.setAdherence(0.95);
                    field.kart1.setMaxSpeed(15.5);
                    field.kart1.setPoids(160);
                    break;
            case 3 :field.kart1.setAdherence(1.1);
                    field.kart1.setMaxSpeed(14.5);
                    field.kart1.setPoids(140);
                    break;
        }
    } 
    
}
