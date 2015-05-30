import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SplitPanelSelection extends JPanel {
    
    PanelSelectionKart selectionKart1 = new PanelSelectionKart();
    
    public SplitPanelSelection(){
        setLayout(new BorderLayout());   
   
    add(selectionKart1,BorderLayout.EAST);
    add(selectionKart1,BorderLayout.WEST);
   }
    
    public void paint(Graphics g){
        
        repaint();
       
    }
}
