

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.*;
import java.awt.FlowLayout;

public class StudentLogin {
    public static void main(String[] args) {
        new StudentLoginGUI();
    }
}

class StudentLoginGUI extends JFrame implements ActionListener{
    JTextField t1,t2;
    JLabel message;
    JButton login;
    String id,password;


    public StudentLoginGUI(){
        setVisible(true);
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());


        t1 = new JTextField(20);
        t2  = new JTextField(20);
        // t1.setText("Enter id");
        message = new JLabel("Student Login");
        login = new JButton();
        login.addActionListener(this);
        login.setText("Login");
        add(message);
        add(t1);
        add(t2);
        add(login);

    }
    public void actionPerformed(ActionEvent ae){
        id=t1.getText();
        password=t2.getText();
        System.err.println(id + password);

    }

}