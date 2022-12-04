

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;
import java.awt.FlowLayout;

public class RegisterGUI extends JFrame implements ActionListener{
    JTextField t1,t2;
    JComboBox<String> hostel;
    JComboBox<String> plans;
    JLabel l1,l2;
    JButton b;
    String hostelList[] = {"GANDHI","KRISHNA","RAM","BUDH","SHANKAR","VYAS","VK","BHAGIRATH","SR","CVR","MALVIYA","MEERA","RANA PRATAP","ASHOK"};
    String plansList[] = new String[Admin.washPlans.size()];
    
    public RegisterGUI(){
        setTitle("New User Registration");
        setVisible(true);
        setSize(400,300);
        setLocation(430, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        int i=0;
    for(Wash_Plan w: Admin.washPlans){
        plansList[i++] = w.toString();
    }

        hostel = new JComboBox<String>(hostelList);
        plans = new JComboBox<String>(plansList);
        t1 = new JTextField(20);
        t2 = new JTextField(20);
        b = new JButton("Register");
        b.addActionListener(this);
        l1=new JLabel("Name: ");
        l2=new JLabel("ID: ");

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(new JLabel("Hostel:"));
        add(hostel);
        add(new JLabel("Select Wash Plan:"));
        add(plans);
        add(b);

    }
    public void actionPerformed(ActionEvent ae){
        String id,password="", hostelName,name;
        name = t1.getText();
        id = t2.getText();
        hostelName = hostel.getSelectedItem().toString();

        int washIndex = plans.getSelectedIndex();
        String washPlan = Admin.washPlans.get(washIndex).getName();
        try {
            StudentAuth.registerStudent(name, id, hostelName, washPlan, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisible(false);
        dispose();
        NewJDialog d = new NewJDialog(name, id, hostelName,washPlan);
        };
    }

