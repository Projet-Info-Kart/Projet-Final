import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ConfirmationExit extends JFrame {
    JButton Yes = new JButton("YES !");
    JButton No = new JButton("NO !");
    
    public ConfirmationExit(){
        setTitle("Do you really want to quit ?");
        setSize(600,220); 
        JPanel Panel = new JPanel ();
        setLayout(new BorderLayout());
       
    }
}
