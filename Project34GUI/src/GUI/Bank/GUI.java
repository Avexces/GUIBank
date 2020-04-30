 /*
  *
  * Authors: Mischa Quist & Annelot Janssen
  * Group: 5
  * Bank:  SlankBank
  *
  *
  */
 package GUI.Bank;

/* libraries toevoegen */

 import javax.imageio.ImageIO;
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.Font; /* library met font */
 import javax.swing.ImageIcon;
 import java.awt.color.*;

 public class GUI {
     private static JPanel start_Panel = new JPanel(); // maakt een "pagina " aan
     private static JPanel login_Panel = new JPanel();
     private static JPanel main_Panel = new JPanel();
     private static JPanel transaction_Panel = new JPanel();
     private static JPanel customWithdraw_panel = new JPanel();
     private static JFrame frame = new JFrame();
     private static JButton button; // maakt een knop aan
     private static JButton withdrawbutton;
     private static JTextField user_Text; // maakt een tekstvlak aan
     private static JPasswordField pin_Field; // maakt een wachtwoord vlak aan
     private static JLabel message_Label; //

     public class Slankbank extends JFrame {// foto van logo
         private ImageIcon image;
         private JLabel lbl;

         public Slankbank() { // constructur
             setLayout(new FlowLayout());
             image = new ImageIcon(getClass().getResource("slankbank.jpg")); // foto van slankbank
             lbl = new JLabel(image);
             add(lbl);
         }
     }

     public static void main(String[] args)
     {
         start();
     }

     /*Welkom menu*/
     public static void start()
     {
         /*Setup of Empty Frame & Panel*/
         frame = new JFrame(" main");
         /*Makes the frame fullscreen without titlebar*/
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.setUndecorated(true); // zorgt ervoor dat de titelbar weggaat
         frame.setVisible(true);
         login_Panel.remove(login_Panel);
         frame.add(start_Panel);
         start_Panel.revalidate();
         start_Panel.repaint();
         System.out.println("de main wordt geopend");
         System.out.println(" De GUI is opgestart");
         start_Panel.removeAll();
         start_Panel.setSize(frame.getSize());
         start_Panel.setLayout(null);

         Language ln = new Language();
         //ln.setVisible(true);

         start_Panel.setBackground(Color.white); // achtergrond
         start_Panel.setForeground(Color.black); // voorgrond

         // begin menu van de gui vn de bank
         JLabel Welkomtekst = new JLabel("Welkom bij Slankbank");
         Welkomtekst.setBounds(frame.getWidth()/3,frame.getHeight()/2-200,1000,100);
         Welkomtekst.setFont(new Font("TimesRoman", Font.PLAIN, 60));
         Welkomtekst.setForeground(Color.black);
         start_Panel.add(Welkomtekst);



         button = new JButton(new AbstractAction("Login") {

             @Override
             public void actionPerformed(ActionEvent e){
                     Switch(start_Panel, login_Panel);
                     login_Panel();
                 }
         });

         button.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,200,85);
         button.setForeground(new Color(192,27,28));

         start_Panel.add(button);

         button = new JButton(/*"Login"*/ new AbstractAction("Exit") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);
             }
         });
         button.setBounds(0,0,80,25);
         login_Panel.add(button);

         message_Label = new JLabel("");
         message_Label.setBounds(10,110,80 ,25);
         login_Panel.add(message_Label);
         frame.setVisible(true);
         //main_Panel();

         // logo dat op de homepagina staat
        // Slankbank logo = new Slankbank();
         //logo.setVisible(true);

     }

     /*Login menu*/
     private static void login_Panel()
     {


         login_Panel.removeAll();
         login_Panel.setSize(frame.getSize());
         login_Panel.setLayout(null);
         login_Panel.setBackground(Color.white);


         /*UserName Label*/
         JLabel label = new JLabel("Gebruikersnaam");
         label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2,140,25);
         label.setForeground(new Color (192,27,28));
         login_Panel.add(label);

         //UserName veld
         user_Text = new JTextField();
         user_Text.setBounds(frame.getWidth()/2,frame.getHeight()/2/*20*/,140 ,25);
         login_Panel.add(user_Text);

         //Pincode text
         JLabel pin_Label = new JLabel("Wachtwoord");
         pin_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2+30,140,25);
         pin_Label.setForeground(new Color (192,27,28));
         login_Panel.add(pin_Label);

         /*Adds an pin field to the LoginPanel*/
         pin_Field = new JPasswordField();
         pin_Field.setBounds(frame.getWidth()/2,frame.getHeight()/2+30,140 ,25);
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

         /* terug knop */
         button = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                start();
             }
         });
         button.setBounds(frame.getWidth()/2+100,frame.getHeight()/2+60,80,25);
         login_Panel.add(button);



     }

     /*Hoofdmenu voor gebruikers*/
     private static void main_Panel() {
         //main_Panel = new JPanel();

         main_Panel.setLayout(null);
         main_Panel.setBackground(Color.white);

         button = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");

                 Switch(main_Panel, login_Panel);
                 login_Panel();
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setBounds(frame.getWidth()-120,frame.getHeight() - 60,100,50);
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

         JLabel balancetxt_Label = new JLabel(" Balance: ");
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(Color.lightGray);
         balancetxt_Label.setBackground(Color.magenta.darker().darker().darker());
         main_Panel.add(balancetxt_Label);

         JLabel Withdrawaltxt_Label = new JLabel(" Quick withdraw: ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,300,50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(Color.lightGray);
         Withdrawaltxt_Label.setBackground(Color.magenta.darker().darker().darker());
         main_Panel.add(Withdrawaltxt_Label);

         //Withdraw 50
         withdrawbutton = new JButton(new AbstractAction(" $50 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(50);
                 Switch(main_Panel, transaction_Panel);
                 try {
                     transaction_Panel();
                 } catch (InterruptedException ex) {
                     ex.printStackTrace();
                 }
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 50,300,50);
         main_Panel.add(withdrawbutton);

         //Withdraw 100
         withdrawbutton = new JButton(new AbstractAction(" $100 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(100);
                 Switch(main_Panel, transaction_Panel);
                 try {
                     transaction_Panel();
                 } catch (InterruptedException ex) {
                     ex.printStackTrace();
                 }
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 100,300,50);
         main_Panel.add(withdrawbutton);

         //Withdraw 150
         withdrawbutton = new JButton(new AbstractAction(" $150 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(150);
                 Switch(main_Panel, transaction_Panel);
                 try {
                     transaction_Panel();
                 } catch (InterruptedException ex) {
                     ex.printStackTrace();
                 }
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 150,300,50);
         main_Panel.add(withdrawbutton);

     /*   withdrawbutton = new JButton(new AbstractAction(" Custom ") {
             @Override
             public void actionPerformed (ActionEvent e ){
                 System.out.println("Doorverwijzing naar de cutom note pagina");
               //  Switch(customWithdraw_panel);
                 try {
                     //customWithdraw_panel();
                 }
                 catch (InterruptedException ex){

                 }
             }
         });
*/

         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(Color.lightGray);
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 150,300,50);
         main_Panel.add(withdrawbutton);


         button = new JButton(new AbstractAction("FinishTransaction") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");

                 Switch(main_Panel, transaction_Panel);
                 try {
                     transaction_Panel();
                 } catch (InterruptedException ex) {
                     ex.printStackTrace();
                 }
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.lightGray);
         button.setBounds(frame.getWidth()-420,frame.getHeight() - 60,300,50);
         main_Panel.add(button);

         JLabel balance_Label = new JLabel(ServerCommunication.getBalance());
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth()/2 -100 ,frame.getHeight()/2 -50,300,50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray.darker());
         main_Panel.add(balance_Label);
         frame.setVisible(true);

         frame.setVisible(true);
     }



     private static void transaction_Panel() throws InterruptedException {
         transaction_Panel.setLayout(null);
         transaction_Panel.setBackground(Color.white);
         if(ServerCommunication.is_Succes)
         {
             JLabel transaction_Label = new JLabel(" Transaction succesful!");
             transaction_Label.setOpaque(true);
             transaction_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2,300,50);
             transaction_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 28));
             transaction_Label.setForeground(Color.green);
             transaction_Label.setBackground(Color.magenta.darker().darker().darker());
             transaction_Panel.add(transaction_Label);
         }
         else
         {
             JLabel transaction_Label = new JLabel(" Transaction failed!");
             transaction_Label.setOpaque(true);
             transaction_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2,300,50);
             transaction_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 28));
             transaction_Label.setForeground(Color.red);
             transaction_Label.setBackground(Color.magenta.darker().darker().darker());
             transaction_Panel.add(transaction_Label);
         }


         button = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");

                 Switch(transaction_Panel, main_Panel);
                 main_Panel();
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.white);
         button.setBounds(frame.getWidth()-120,frame.getHeight() - 60,100,50);
         transaction_Panel.add(button);


         frame.setVisible(true);

     }

     /*private static void customWithdraw_panel() {
         customWithdraw_panel.removeAll();
         customWithdraw_panel.setSize(frame.getSize());
         customWithdraw_panel.setLayout(null);
         customWithdraw_panel.setBackground(Color.white);

         // fields
         int briefje20 = 20;
         int briefje50 = 50;
         int briefje100 = 100;
         int aantal;

         // knoppen
         button = new JButton(new AbstractAction("homepagina") {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 start();
             }
         });
         button = new JButton(new AbstractAction("back") {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 Switch(transaction_Panel);
             }
         });
         button = new JButton(new AbstractAction("order") {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 ServerCommunication.Withdraw(moneynote);
                 Switch(customWithdraw_panel, transaction_Panel);
                 try {
                     transaction_Panel();
                 } catch (InterruptedException ex) {
                     ex.printStackTrace();
                 }

             }
         });



         // hierin invullen welk briefje geld
         JLabel moneyNote = new JLabel("Money Note");
         moneyNote.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2+30,80,25);
         moneyNote.setForeground(Color.lightGray);
         login_Panel.add(moneyNote);



        // private void moneyNoteTrue(moneyNote) {


        // }


     }

      */


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
         else if(!user.equals("User") || !password.equals("1234") && !ServerCommunication.getBlocked())
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

     // het kiezen voor talen
     public static class Language extends JFrame{
         JButton Nederlands;
         JButton Engels;
         JButton Russisch;

         public void language (){
             ImageIcon iconNederlands = new ImageIcon("Nederlands.jpg");
             ImageIcon iconEngels = new ImageIcon("engels.jpg");
             ImageIcon iconRussisch = new ImageIcon("russisch.jpg");

             Nederlands = new JButton(iconNederlands);
             Engels = new JButton(iconEngels);
             Russisch = new JButton(iconRussisch);

             setLayout(new FlowLayout());
             add(Nederlands);
             add(Engels);
             add(Russisch);

         }
     }

 }
