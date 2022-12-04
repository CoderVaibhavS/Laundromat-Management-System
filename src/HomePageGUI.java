

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.*;


public class HomePageGUI extends JFrame implements ActionListener{
    JFrame frame;
    JComboBox<String> menu;
    JButton b;
    String actions[] = {"Register","Drop Laundry","Check Status","Display Account","Recieve Laundry"};
    String input = "";

    public HomePageGUI(){
    super("Welcome to Laundromat");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);
    setLocation(430, 100);
    setLayout(new FlowLayout());
    setVisible(true);

    b = new JButton();
    b.addActionListener(this);
    b.setText("Choose");

    menu = new JComboBox<String>(actions);
    add(menu);
    add(b);
    }
    public static String setInput(String option){
        switch(option){
            case "Register": return "S";
            case "Drop Laundry": return "D";
            case "Check Status": return "C";
            case "Display Account": return "B";
            case "Recieve Laundry": return "R";
            default: return "";
        }
    }

    public void actionPerformed(ActionEvent ae){
        input = setInput(menu.getSelectedItem().toString());
        Commands c = new Commands();
        c.execute(input);

    }
    public String getInput(){
        return input;
    }
    
}