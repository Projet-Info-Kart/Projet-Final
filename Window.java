
import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class Window extends JFrame {

        PanelMenu menu = new PanelMenu();
        PanelSelectionKart selectionKart = new PanelSelectionKart();
        Tutorial tuto = new Tutorial();
        CardLayout layout = new CardLayout();
        SplitPanelSelection splitSelection = new SplitPanelSelection();   
        final int LARGPIX=1200;
        final int HAUTPIX=840;
        PanelField field=new PanelField(1,1);
        
    public Window(){
        setTitle("Pro Kart Racing 2015");
        setSize(LARGPIX,HAUTPIX);
        setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
           
        add(menu);
        add(field);
        add(selectionKart);
        add(splitSelection);
        add(tuto);
        field.setVisible(false);
        selectionKart.setVisible(false);
        splitSelection.setVisible(false);
        tuto.setVisible(false);
 
        this.addKeyListener(new Window_this_keyAdapter(this));
        this.setFocusable(true);
    
        //action du button OnePlayer
        menu.OnePlayer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                menu.setVisible(false);
                selectionKart.setVisible(true);
                repaint();
            }
        });
     
        //action du button Tuto 
        menu.Tuto.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                menu.setVisible(false);
                tuto.setVisible(true);
                repaint();
            }
        });   
        
        tuto.Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                tuto.setVisible(false);
                menu.setVisible(true);
                repaint();
            }
        });
        
        //action du menu Exit        
        menu.Exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                System.exit(0);
            }
        });
    
        //action du button back to main menu
        selectionKart.Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                selectionKart.setVisible(false);
                menu.setVisible(true);
                repaint();
            }
        });
    
        //action du button Next
        selectionKart.Next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                repaint();
            }
        });
    
        //action du button Previous
        selectionKart.Previous.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent actionEvent){
                 repaint();
              }
        });
    
        //action du button Pick        
        selectionKart.Pick.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                choixKart();
                selectionKart.setVisible(false);
                field.setVisible(true);
                field.activeCompteur();
                
                repaint();
            }
        });
     
        //action du button 2 Players
        menu.TwoPlayers.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                new Window2().setVisible(true);
                dispose();
                repaint();
            }
        });

        //action du btton back
        field.Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                field.setVisible(false);
                menu.setVisible(true);
                repaint();
            }
        });


        setVisible(true);
    }
       
    public void choixKart(){
        int position=selectionKart.getPosition();
        field.kart1.setImage(position,1);//1 correspond au mode 1 joueur
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
       
    private class Window_this_keyAdapter extends KeyAdapter{
            private Window adaptee;
            Window_this_keyAdapter(Window adaptee){
               this.adaptee=adaptee; 
            }
            public void keyPressed(KeyEvent e){
                field.this_keyPressed(e);
                
            }
            public void keyReleased(KeyEvent e){
                field.this_keyReleased(e);
               
            }
            
            
            
        }    
        
}

