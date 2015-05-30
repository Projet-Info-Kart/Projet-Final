
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Window3 extends JFrame{
    final int LARGPIX=1200;
    final int HAUTPIX=840;
    
    PanelField field1=new PanelField(1,2);
    PanelField field2=new PanelField(2,2);
    
    
    public Window3(){
        setTitle("Pro Kart Racing 2015");
        setSize(LARGPIX,HAUTPIX); 
        GridLayout layout = new GridLayout(1, 2,10,0);// 10 correspond à un espace de 10 pixel entre 2 colonnes= séparation entre les panels
        setLayout(layout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(field1);
        add(field2);
        
        field1.Items.add(field1.kart1);     // ajout des 2 kart à la liste d'objets
        field1.Items.add(field2.kart1);     // Items étant en static, on peut l'ajouter à field1.Items ou field2.Items sans changement
        
        field1.setVisible(true);
        field2.setVisible(true);
        
        this.addKeyListener(new Window3_this_keyAdapter(this));
        this.setFocusable(true);
        
        field1.Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                new Window().setVisible(true);
                dispose();
            }
        });
        
        field2.Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                new Window().setVisible(true);
                dispose();
            }
        });
        
        Timer timer=new Timer(50,new TimerAction());
        timer.start();
        
    }
    

    class TimerAction implements ActionListener{
        public void actionPerformed(ActionEvent e){
        
        // Detection de la fin du jeu
        if (field1.nbTours>=3|| field2.nbTours>=3){ 
            field1.end=true;
            field2.end=true;
        } 
    }}
    
    /** Détermine qui le joueur gagnant
     * Retourne le numéro du joueur gagnant*/
    public int findWinner(){
        if(field1.nbTours>field2.nbTours){return 1;}
        else{return 2;}
    }
    
    
    private class Window3_this_keyAdapter extends KeyAdapter{
        private Window3 adaptee;
        Window3_this_keyAdapter(Window3 adaptee){
            this.adaptee=adaptee; 
        }
        public void keyPressed(KeyEvent e){
            field1.this_keyPressed(e);
            field2.this_keyPressed(e);        
        }
        public void keyReleased(KeyEvent e){
            field1.this_keyReleased(e);
            field2.this_keyReleased(e);
        }
    }
}

      
