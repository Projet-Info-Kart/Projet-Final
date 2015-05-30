import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tutorial extends JPanel {
    
    JLabel J12 = new JLabel("                                                                                                           J1                              J2");
    JLabel Avancer = new JLabel("Avancer                                                                                              Z                                Up");
    JLabel Freiner = new JLabel("Freiner                                                                                               S                              Down");
    JLabel Droite = new JLabel("Droite                                                                                                 D                              Right");
    JLabel Gauche = new JLabel("Gauche                                                                                              Q                              Left");
    JLabel Bonus1 = new JLabel("Poser banane/Poser Bombe/Tirer missile en arrière                        Space                            2");
    JLabel Bonus2 = new JLabel("Tirer missile en avant                                                                        Shift                             5");
    
    JLabel Vide = new JLabel ("");
    
    JButton Back = new JButton("Back to main menu");

        public Tutorial(){
            setLayout(new GridLayout(12,1));
            
            //couleur des textes des JLabel : noir 
            J12.setForeground(Color.black);
            Avancer.setForeground(Color.black);
            Freiner.setForeground(Color.black);
            Droite.setForeground(Color.black);
            Gauche.setForeground(Color.black);
            Bonus1.setForeground(Color.black);
            Bonus2.setForeground(Color.black);
            
            add(J12);
            add(Avancer);
            add(Freiner);
            add(Droite);
            add(Gauche);
            add(Bonus1);
            add(Bonus2);
            
            //ajoute de l'espace entre les commandes et le button back 
            add(Vide);
            add(Vide);
            
            //button back to main menu
            add(Back);
            
            //police des textes de JLabel
            Font chronoFont=new Font(J12.getFont().getName(),J12.getFont().getStyle(),20);
            J12.setFont(chronoFont);
            Avancer.setFont(chronoFont);
            Freiner.setFont(chronoFont);
            Droite.setFont(chronoFont);
            Gauche.setFont(chronoFont);
            Bonus1.setFont(chronoFont);
            Bonus2.setFont(chronoFont);
            
            //police du button back to main menu
            Font newButtonBackFont=new Font(Back.getFont().getName(),Back.getFont().getStyle(),20);
            Back.setFont(newButtonBackFont);
        }
}