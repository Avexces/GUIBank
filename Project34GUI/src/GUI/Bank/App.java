 /*
  *
  * Author: Mischa Quist
  * Group: 5
  * Bank:  SlankBank
  *
  *
  */
 package GUI.Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.lang.reflect.Method;

 public class App {
    private static JPanel start_Panel = new JPanel();
    private static JPanel login_Panel = new JPanel();
    private static JPanel main_Panel = new JPanel();
    private static JFrame frame = new JFrame();
    private static JButton button;
    private static JTextField user_Text;
    private static JPasswordField pin_Field;
    private static JLabel message_Label;

    public static void main(String[] args)
    {
        start();
    }

    /*Welkom menu*/
    public static void start()
    {
        /*Setup of Empty Frame & Panel*/
        frame = new JFrame();
        /*Makes the frame fullscreen without titlebar*/
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        login_Panel.remove(login_Panel);
        frame.add(start_Panel);
        start_Panel.revalidate();
        start_Panel.repaint();
        System.out.println("Main");
        start_Panel.removeAll();
        start_Panel.setSize(frame.getSize());
        start_Panel.setLayout(null);
        start_Panel.setBackground(Color.darkGray);
        start_Panel.setForeground(Color.lightGray);
        JLabel label = new JLabel("Welkom bij slankbank");
        label.setBounds(frame.getWidth()/2 - 100,10,500,50);
        label.setFont(new Font("Consolas", Font.PLAIN, 28));
        label.setForeground(Color.lightGray);
        start_Panel.add(label);
        button = new JButton(new AbstractAction("Login") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Switch(start_Panel, login_Panel);
                login_Panel();
            }
        });
        button.setBounds(frame.getWidth()/2,80,80,25);
        start_Panel.add(button);

        button = new JButton(/*"Login"*/ new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        button.setBounds(100,80,80,25);
        login_Panel.add(button);

        message_Label = new JLabel("");
        message_Label.setBounds(10,110,300,25);
        login_Panel.add(message_Label);
        frame.setVisible(true);
        //main_Panel();

    }

    /*Login menu*/
    private static void login_Panel()
    {
        login_Panel.removeAll();
        login_Panel.setSize(frame.getSize());
        login_Panel.setLayout(null);
        login_Panel.setBackground(Color.darkGray);

        /*UserName Label*/
        JLabel label = new JLabel("User");
        label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2,80,25);
        label.setForeground(Color.lightGray);
        login_Panel.add(label);

        //UserName veld
        user_Text = new JTextField();
        user_Text.setBounds(frame.getWidth()/2,frame.getHeight()/2/*20*/,165,25);
        login_Panel.add(user_Text);

        //Pincode text
        JLabel pin_Label = new JLabel("Pincode");
        pin_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2+30,80,25);
        pin_Label.setForeground(Color.lightGray);
        login_Panel.add(pin_Label);

        /*Adds an pin field to the LoginPanel*/
        pin_Field = new JPasswordField();
        pin_Field.setBounds(frame.getWidth()/2,frame.getHeight()/2+30,165,25);
        login_Panel.add(pin_Field);

        /*Login button*/
        button = new JButton(/*"Login"*/ new AbstractAction("Login") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = user_Text.getText();
                String password = pin_Field.getText();
                //FIXME password = pin_Field.getPassword();
                System.out.println("User: " + user + " PinCode: " + password);
                Login(user,password);
            }
        });
        button.setBounds(frame.getWidth()/2,frame.getHeight()/2+60,80,25);
        login_Panel.add(button);

        /*Exit button*/
        button = new JButton(/*"Login"*/ new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        button.setBounds(frame.getWidth()/2 - 100 ,frame.getHeight()/2+60,80,25);
        login_Panel.add(button);

        /*Message Label*/
        message_Label = new JLabel("");
        message_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2+90,300,25);
        message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
        login_Panel.add(message_Label);
        frame.setVisible(true);
    }

    /*Hoofdmenu voor gebruikers*/
    private static void main_Panel() {
        //main_Panel = new JPanel();

        main_Panel.setLayout(null);
        main_Panel.setBackground(Color.darkGray);

        button = new JButton(new AbstractAction("Back") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked, RETREAT!!");

                Switch(main_Panel, login_Panel);
               login_Panel();
            }
        });
        button.setBounds((frame.getWidth()/2)-40,80,80,25);
        main_Panel.add(button);

        button = new JButton(new AbstractAction("Abrupt") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked, chipRemoved");

                ServerCommunication.setFalse();
                login_Panel();
            }
        });
        button.setBounds((frame.getWidth()/2)+ 40,80,80,25);
        main_Panel.add(button);

        JLabel balance_Label = new JLabel(ServerCommunication.getBalance());
        balance_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2+90,300,25);
        balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
        balance_Label.setForeground(Color.lightGray);
        balance_Label.setBackground(Color.darkGray.darker());
        main_Panel.add(balance_Label);
        frame.setVisible(true);

        frame.setVisible(true);
    }

    /*Usable Methods*/
    protected static void Switch(JPanel from, JPanel to)
    {
        from.removeAll();
        frame.remove(from);
        frame.add(to);
        to.revalidate();
        to.repaint();

        return;
    }
     protected static void Abrupted()
     {
         frame.removeAll();
         start();
        // frame.remove(main_Panel);
        // frame.add(login_Panel);
        // login_Panel.revalidate();
       //  login_Panel.repaint();
        // login_Panel();
        // return;
     }
    private static void Login(String user, String password)
    {
        if(user.equals("User") && password.equals("1234") && !ServerCommunication.getBlocked())
        {
            message_Label.setForeground(Color.GREEN);
            message_Label.setText("Login succesful!");
            main_Panel();
            Switch(login_Panel, main_Panel);


        }
        else if(!user.equals("User") && !password.equals("1234") && !ServerCommunication.getBlocked())
        {
            message_Label.setForeground(Color.RED.darker().darker());
            message_Label.setText("Login Failed please try again!");
        }
        else if(ServerCommunication.getBlocked())
        {
            message_Label.setForeground(Color.RED);
            message_Label.setText("Account locked please go to your bank!");
        }
        return;
    }
}
