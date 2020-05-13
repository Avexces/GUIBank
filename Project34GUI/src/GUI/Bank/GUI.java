 /*
  *
  * Authors: Mischa Quist & Annelot Janssen
  * Group: 5
  * Bank:  SlankBank
  *
  *
  */
 package Bank;

/* libraries toevoegen */

 import javax.imageio.ImageIO;
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.Font; /* library met font */
 import javax.swing.ImageIcon;
 import java.awt.color.*;
 import java.util.concurrent.TimeUnit;

 public class GUI {
     private static JPanel start_Panel = new JPanel(); // maakt een "pagina " aan
     private static JPanel login_Panel = new JPanel();
     private static JPanel main_Panel = new JPanel();
     private static JPanel select_Panel = new JPanel();
     private static JPanel select_CPanel = new JPanel();
     private static JPanel transaction_Panel = new JPanel();
     private static JPanel customWithdraw_panel = new JPanel();
     private static JFrame frame = new JFrame();
     private static JButton button; // maakt een knop aan
     private static JButton withdrawbutton;
     private static JTextField user_Text; // maakt een tekstvlak aan
     private static JPasswordField pin_Field; // maakt een wachtwoord vlak aan
     private static JLabel message_Label; //
     private static int tempAmountBriefjes20 =0;
     private static int tempAmountBriefjes50 = 0;
     private static int tempAmountBriefjes100 = 0;
     private static int amount = 0;

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
         balancetxt_Label.setForeground(new Color (192,27,28));
         balancetxt_Label.setBackground(new Color (241,227,12));
         main_Panel.add(balancetxt_Label);

         JLabel Withdrawaltxt_Label = new JLabel(" Quick withdraw: ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,300,50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color (192,27,28));
         Withdrawaltxt_Label.setBackground(new Color(241,227,12));
         main_Panel.add(Withdrawaltxt_Label);

         //Withdraw 50
         withdrawbutton = new JButton(new AbstractAction(" $70 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 Select_Bills(70);
                 Switch(main_Panel, select_Panel);
                 Select_Bills(70);
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(new Color(241,227,12));
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 50,300,50);
         main_Panel.add(withdrawbutton);

         //Withdraw 100
         withdrawbutton = new JButton(new AbstractAction(" $100 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 Select_Bills(100);
                 Switch(main_Panel, select_Panel);

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
                 Select_Bills(150);
                 Switch(main_Panel, select_Panel);
                 Select_Bills(150);

             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 150,300,50);
         main_Panel.add(withdrawbutton);

         withdrawbutton = new JButton(new AbstractAction(" Aangepast bedrag ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 SelectCustom();
                 Switch(main_Panel, select_CPanel);
                 SelectCustom();

             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 200,300,50);
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



     private static void Select_Bills(int selectedMoney)
     {
        select_Panel.setLayout(null);
         select_Panel.setBackground(Color.white);

         button = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");

                 Switch(select_Panel, main_Panel);
                 main_Panel();
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setBounds(frame.getWidth()-120,frame.getHeight() - 60,100,50);
         select_Panel.add(button);

         button = new JButton(new AbstractAction("Abrupt") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");

                 ServerCommunication.setFalse();
                 login_Panel();
             }
         });
         button.setBounds((frame.getWidth()/2)+ 40,80,80,25);
         select_Panel.add(button);

         //Selecteer veste bedragen
         if(selectedMoney == 70)
         {
             //TODO weergeef 50 en 20 briefjes
             JLabel balancetxt_Label = new JLabel(" Gekozen bedrag: 70 euro");
             balancetxt_Label.setOpaque(true);
             balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
             balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             balancetxt_Label.setForeground(new Color (192,27,28));
             balancetxt_Label.setBackground(new Color (241,227,12));
             select_Panel.add(balancetxt_Label);

             JLabel optiontxt_Label = new JLabel(" Mogelijke biljet opties:");
             optiontxt_Label.setOpaque(true);
             optiontxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 50,300,50);
             optiontxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             optiontxt_Label.setForeground(new Color (192,27,28));
             optiontxt_Label.setBackground(new Color (241,227,12).darker());
             select_Panel.add(optiontxt_Label);

             JLabel Withdrawaltxt_Label = new JLabel(" 20 briefje       Aantal: 1");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_Panel.add(Withdrawaltxt_Label);

             JLabel briefje50_Label = new JLabel(" 50 briefje       Aantal: 1");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje50_Label);

             JLabel briefje100_Label = new JLabel(" 100 briefje       Aantal: 0");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje100_Label);

             //Withdraw 50
             withdrawbutton = new JButton(new AbstractAction(" Kies optie ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Bedrag optie gekozen");
                     Switch(select_Panel, transaction_Panel);
                     try {
                         transaction_Panel(70,1,1,0);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241,227,12));
             withdrawbutton.setForeground(new Color (192,27,28));
             withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 150,300,50);
             select_Panel.add(withdrawbutton);

             button = new JButton(new AbstractAction("FinishTransaction") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Button Clicked, RETREAT!!");

                     Switch(main_Panel, transaction_Panel);
                     try {
                         transaction_Panel(70,1,2,0);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             button.setBackground(Color.magenta.darker().darker().darker().darker());
             button.setForeground(Color.lightGray);
             button.setBounds(frame.getWidth()-420,frame.getHeight() - 60,300,50);
             select_Panel.add(button);


             frame.setVisible(true);
         }
         else if(selectedMoney == 100)
         {
             ////TODO weergeef 50 en 20 briefjes
             JLabel balancetxt_Label = new JLabel(" Gekozen bedrag: 100 euro");
             balancetxt_Label.setOpaque(true);
             balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
             balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             balancetxt_Label.setForeground(new Color (192,27,28));
             balancetxt_Label.setBackground(new Color (241,227,12));
             select_Panel.add(balancetxt_Label);

             JLabel optiontxt_Label = new JLabel(" Mogelijke biljet opties:");
             optiontxt_Label.setOpaque(true);
             optiontxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 50,300,50);
             optiontxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             optiontxt_Label.setForeground(new Color (192,27,28));
             optiontxt_Label.setBackground(new Color (241,227,12).darker());
             select_Panel.add(optiontxt_Label);
             //3
             JLabel Withdrawaltxt_Label = new JLabel(" 20 briefje       Aantal: 5");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2+200,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_Panel.add(Withdrawaltxt_Label);

             JLabel briefje50_Label = new JLabel(" 50 briefje       Aantal: 0");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje50_Label);

             JLabel briefje100_Label = new JLabel(" 100 briefje       Aantal: 0");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Kies optie ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Bedrag optie gekozen");
                     Switch(select_Panel, transaction_Panel);
                     try {
                         transaction_Panel(100,5,0,0);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241,227,12));
             withdrawbutton.setForeground(new Color (192,27,28));
             withdrawbutton.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 + 150,300,50);
             select_Panel.add(withdrawbutton);

        //2
             Withdrawaltxt_Label = new JLabel(" 20 briefje       Aantal: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2- 100,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_Panel.add(Withdrawaltxt_Label);

             briefje50_Label = new JLabel(" 50 briefje       Aantal: 2");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2- 100,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje50_Label);

             briefje100_Label = new JLabel(" 100 briefje       Aantal: 0");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Kies optie ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Bedrag optie gekozen");
                     Switch(select_Panel, transaction_Panel);
                     try {
                         transaction_Panel(100,0,2,0);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241,227,12));
             withdrawbutton.setForeground(new Color (192,27,28));
             withdrawbutton.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2 + 150,300,50);
             select_Panel.add(withdrawbutton);

             //1
             Withdrawaltxt_Label = new JLabel(" 20 briefje       Aantal: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2- 400,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_Panel.add(Withdrawaltxt_Label);

             briefje50_Label = new JLabel(" 50 briefje       Aantal: 0");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2- 400,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje50_Label);

             briefje100_Label = new JLabel(" 100 briefje       Aantal: 1");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2 - 400,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Kies optie ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Bedrag optie gekozen");
                     Switch(select_Panel, transaction_Panel);
                     try {
                         transaction_Panel(100,0,0,1);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241,227,12));
             withdrawbutton.setForeground(new Color (192,27,28));
             withdrawbutton.setBounds(frame.getWidth()/2 - 400,frame.getHeight()/2 + 150,300,50);
             select_Panel.add(withdrawbutton);

             button = new JButton(new AbstractAction("FinishTransaction") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Button Clicked, RETREAT!!");

                     Switch(main_Panel, transaction_Panel);
                     try {
                         transaction_Panel(100,0,0,1);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             button.setBackground(Color.magenta.darker().darker().darker().darker());
             button.setForeground(Color.lightGray);
             button.setBounds(frame.getWidth()-420,frame.getHeight() - 60,300,50);
             select_Panel.add(button);




             frame.setVisible(true);
         }
         else if(selectedMoney == 150)
         {
             ////TODO weergeef 50 en 20 briefjes
             JLabel balancetxt_Label = new JLabel(" Gekozen bedrag: 150 euro");
             balancetxt_Label.setOpaque(true);
             balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
             balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             balancetxt_Label.setForeground(new Color (192,27,28));
             balancetxt_Label.setBackground(new Color (241,227,12));
             select_Panel.add(balancetxt_Label);

             JLabel optiontxt_Label = new JLabel(" Mogelijke biljet opties:");
             optiontxt_Label.setOpaque(true);
             optiontxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 50,300,50);
             optiontxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             optiontxt_Label.setForeground(new Color (192,27,28));
             optiontxt_Label.setBackground(new Color (241,227,12).darker());
             select_Panel.add(optiontxt_Label);
             //3
             JLabel Withdrawaltxt_Label = new JLabel(" 20 briefje       Aantal: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2+200,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_Panel.add(Withdrawaltxt_Label);

             JLabel briefje50_Label = new JLabel(" 50 briefje       Aantal: 3");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje50_Label);

             JLabel briefje100_Label = new JLabel(" 100 briefje       Aantal: 0");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Kies optie ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Bedrag optie gekozen");
                     Switch(select_Panel, transaction_Panel);
                     try {
                         transaction_Panel(150,0,3,0);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241,227,12));
             withdrawbutton.setForeground(new Color (192,27,28));
             withdrawbutton.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 + 150,300,50);
             select_Panel.add(withdrawbutton);

             //2
             Withdrawaltxt_Label = new JLabel(" 20 briefje       Aantal: 5");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2- 100,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_Panel.add(Withdrawaltxt_Label);

             briefje50_Label = new JLabel(" 50 briefje       Aantal: 2");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2- 100,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje50_Label);

             briefje100_Label = new JLabel(" 100 briefje       Aantal: 0");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Kies optie ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Bedrag optie gekozen");
                     Switch(select_Panel, transaction_Panel);
                     try {
                         transaction_Panel(150,5,2,0);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241,227,12));
             withdrawbutton.setForeground(new Color (192,27,28));
             withdrawbutton.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2 + 150,300,50);
             select_Panel.add(withdrawbutton);

             //1
             Withdrawaltxt_Label = new JLabel(" 20 briefje       Aantal: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2- 400,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_Panel.add(Withdrawaltxt_Label);

             briefje50_Label = new JLabel(" 50 briefje       Aantal: 1");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2- 400,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje50_Label);

             briefje100_Label = new JLabel(" 100 briefje       Aantal: 1");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2 - 400,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_Panel.add(briefje100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Kies optie ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Bedrag optie gekozen");
                    // ServerCommunication.Withdraw(50);
                     Switch(select_Panel, transaction_Panel);
                     try {
                         transaction_Panel(150,0,1,1);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241,227,12));
             withdrawbutton.setForeground(new Color (192,27,28));
             withdrawbutton.setBounds(frame.getWidth()/2 - 400,frame.getHeight()/2 + 150,300,50);
             select_Panel.add(withdrawbutton);

             button = new JButton(new AbstractAction("FinishTransaction") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Button Clicked, RETREAT!!");

                     Switch(main_Panel, transaction_Panel);
                     try {
                         transaction_Panel(150,0,1,1);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             button.setBackground(Color.magenta.darker().darker().darker().darker());
             button.setForeground(Color.lightGray);
             button.setBounds(frame.getWidth()-420,frame.getHeight() - 60,300,50);
             select_Panel.add(button);




             frame.setVisible(true);
         }

         frame.setVisible(true);
     }

     private static void SelectCustom()
     {

         select_CPanel.setLayout(null);
         select_CPanel.setBackground(Color.white);

         button = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");

                 Switch(select_CPanel, main_Panel);
                 main_Panel();
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setBounds(frame.getWidth()-120,frame.getHeight() - 60,100,50);
         select_CPanel.add(button);

         button = new JButton(new AbstractAction("Abrupt") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");

                 ServerCommunication.setFalse();
                 login_Panel();
             }
         });
         button.setBounds((frame.getWidth()/2)+ 40,80,80,25);
         select_CPanel.add(button);

         //Selecteer veste bedragen
             //TODO weergeef 50 en 20 briefjes
             JLabel balancetxt_Label = new JLabel(" Gekozen bedrag: 70 euro");
             balancetxt_Label.setOpaque(true);
             balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
             balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             balancetxt_Label.setForeground(new Color (192,27,28));
             balancetxt_Label.setBackground(new Color (241,227,12));
             select_CPanel.add(balancetxt_Label);

             JLabel optiontxt_Label = new JLabel(" Mogelijke biljet opties:");
             optiontxt_Label.setOpaque(true);
             optiontxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 50,300,50);
             optiontxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             optiontxt_Label.setForeground(new Color (192,27,28));
             optiontxt_Label.setBackground(new Color (241,227,12).darker());
             select_CPanel.add(optiontxt_Label);

             JLabel Withdrawaltxt_Label = new JLabel(" 20 briefje");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_CPanel.add(Withdrawaltxt_Label);

         JLabel Withdrawalatxt_Label = new JLabel("     " + tempAmountBriefjes20);
         Withdrawalatxt_Label.setOpaque(true);
         Withdrawalatxt_Label.setBounds(frame.getWidth()/2+500,frame.getHeight()/2,300,50);
         Withdrawalatxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawalatxt_Label.setForeground(new Color (192,27,28));
         Withdrawalatxt_Label.setBackground(new Color(241,227,12));
         select_CPanel.add(Withdrawalatxt_Label);

         JLabel briefjeamount_Label = new JLabel(String.valueOf(amount));
         briefjeamount_Label.setOpaque(true);
         briefjeamount_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2 + 300,300,50);
         briefjeamount_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         briefjeamount_Label.setForeground(new Color (192,27,28));
         briefjeamount_Label.setBackground(new Color(241,227,12));
         select_CPanel.add(briefjeamount_Label);

         button = new JButton(new AbstractAction("Increase") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Increase");

                 increase(20);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.lightGray);
         button.setBounds(frame.getWidth()/2+200,frame.getHeight()/2,250,50);
         select_CPanel.add(button);


         button = new JButton(new AbstractAction("Decrease") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Decrease");

                 decrease(20);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.lightGray);
         button.setBounds(frame.getWidth()/2-350,frame.getHeight()/2,250,50);
         select_CPanel.add(button);

             JLabel briefje50_Label = new JLabel(" 50 briefje ");
             briefje50_Label.setOpaque(true);
             briefje50_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2 + 50,300,50);
             briefje50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje50_Label.setForeground(new Color (192,27,28));
             briefje50_Label.setBackground(new Color(241,227,12));
             select_CPanel.add(briefje50_Label);

         JLabel briefje50a_Label = new JLabel("     " + tempAmountBriefjes50);
         briefje50a_Label.setOpaque(true);
         briefje50a_Label.setBounds(frame.getWidth()/2+500,frame.getHeight()/2 + 50,300,50);
         briefje50a_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         briefje50a_Label.setForeground(new Color (192,27,28));
         briefje50a_Label.setBackground(new Color(241,227,12));
         select_CPanel.add(briefje50a_Label);

         button = new JButton(new AbstractAction("Increase") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Increase");

                 increase(50);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.lightGray);
         button.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 +50,250,50);
         select_CPanel.add(button);


         button = new JButton(new AbstractAction("Decrease") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Decrease");

                 decrease(50);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.lightGray);
         button.setBounds(frame.getWidth()/2-350,frame.getHeight()/2 +50,250,50);
         select_CPanel.add(button);


             JLabel briefje100_Label = new JLabel(" 100 briefje");
             briefje100_Label.setOpaque(true);
             briefje100_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2 + 100,300,50);
             briefje100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             briefje100_Label.setForeground(new Color (192,27,28));
             briefje100_Label.setBackground(new Color(241,227,12));
             select_CPanel.add(briefje100_Label);

         JLabel briefje100a_Label = new JLabel("     " + tempAmountBriefjes100);
         briefje100a_Label.setOpaque(true);
         briefje100a_Label.setBounds(frame.getWidth()/2+500,frame.getHeight()/2 + 100,300,50);
         briefje100a_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         briefje100a_Label.setForeground(new Color (192,27,28));
         briefje100a_Label.setBackground(new Color(241,227,12));
         select_CPanel.add(briefje100a_Label);
         button = new JButton(new AbstractAction("Increase") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Increase");

                 increase(100);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.lightGray);
         button.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 +100,250,50);
         select_CPanel.add(button);


         button = new JButton(new AbstractAction("Decrease") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Decrease");

                 decrease(100);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.lightGray);
         button.setBounds(frame.getWidth()/2-350,frame.getHeight()/2 +100,250,50);
         select_CPanel.add(button);

             button = new JButton(new AbstractAction("FinishTransaction") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Button Clicked, RETREAT!!");

                     Switch(main_Panel, transaction_Panel);
                     try {
                         transaction_Panel(amount,tempAmountBriefjes20,tempAmountBriefjes50,tempAmountBriefjes100);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                 }
             });
             button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             button.setBackground(Color.magenta.darker().darker().darker().darker());
             button.setForeground(Color.lightGray);
             button.setBounds(frame.getWidth()-420,frame.getHeight() - 60,300,50);
             select_CPanel.add(button);


             frame.setVisible(true);
     }
     private static void transaction_Panel(int a, int b20, int b50, int b100) throws InterruptedException {
         transaction_Panel.setLayout(null);
         transaction_Panel.setBackground(Color.white);
         ServerCommunication.Withdraw(a,b20,b50,b100);
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
                 main_Panel();
                 Switch(transaction_Panel, main_Panel);
                 
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.white);
         button.setBounds(frame.getWidth()-120,frame.getHeight() - 60,100,50);
         transaction_Panel.add(button);

         button = new JButton(new AbstractAction("Print receipt") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 //do something with arduino
                 message_Label.setText("Will print receipt!");
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.white);
         button.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 120,250,50);
         transaction_Panel.add(button);

         message_Label = new JLabel("");
         message_Label.setBounds(frame.getWidth()/2 - 120,frame.getHeight()/2+90,300,25);
         message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         transaction_Panel.add(message_Label);
         frame.setVisible(true);
         frame.setVisible(true);

     }

     /*private static void customWithdraw_panel() {
         customWithdraw_panel.removeAll();
         customWithdraw_panel.setSize(frame.getSize());
         customWithdraw_panel.setLayout(null);
         customWithdraw_panel.setBackground(Color.white);

         // fields
         int briefje20;
         int briefje50;
         int briefje100;
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

     protected static void Switch2(JPanel from, JPanel to)
     {
         from.removeAll();
         frame.remove(from);
         frame.add(to);
         to.revalidate();
         to.repaint();
         wait(5000);
         return;
     }
     protected static void Abrupted()
     {
         frame.removeAll();
         start();
         System.out.println("gui is gestopt");
     }

     private static void Login(String user, String password)
     {
         /*
         int aantalPogingen = 3;

        public int aantalpogingen(aantalPogingen){
        return aantalPogingen;
        }

         */


         if(user.equals("User") && password.equals("1234") && !ServerCommunication.getBlocked())
         {
             message_Label.setForeground(Color.GREEN);
             message_Label.setText("Login succesful!");
             main_Panel();
             Switch(login_Panel, main_Panel);

         }
         else if(!user.equals("User") || !password.equals("1234") && !ServerCommunication.getBlocked())
         {
             // aantalPogingen--
             message_Label.setForeground(Color.RED.darker().darker());
             message_Label.setText("Login Failed please try again!");
             // message_label.setText("u heeft nog zoveel pogingen"+aantalPogingen");
         }
         else if(ServerCommunication.getBlocked())
         {
             message_Label.setForeground(Color.RED);
             message_Label.setText("Account locked please go to your bank!");
         }
         return;
     }


     private static void increase(int val)
     {
         if(val == 20 && tempAmountBriefjes20 <= 5 && amount + 20 <= ServerCommunication.getBalanceInt())
         {
             tempAmountBriefjes20++;
             amount += 20;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 50 && tempAmountBriefjes50 <= 5 && amount + 50 <= ServerCommunication.getBalanceInt())
         {
             tempAmountBriefjes50++;
             amount += 50;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 100 && tempAmountBriefjes100 <= 5 && amount + 100 <= ServerCommunication.getBalanceInt())
         {
             tempAmountBriefjes100++;
             amount += 100;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }

         else{return;}
     }

     private static void decrease(int val)
     {
         if(val == 20 && tempAmountBriefjes20 >=  0 && amount - 20 >= 0)
         {
             tempAmountBriefjes20--;
             amount -= 20;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 50 && tempAmountBriefjes50 >=  0 && amount - 50 >= 0)
         {
             tempAmountBriefjes50--;
             amount -= 50;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 100 && tempAmountBriefjes100 >=  0 && amount - 100 >= 0)
         {
             tempAmountBriefjes100--;
             amount -= 100;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }

         else{return;}
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
     public static void wait(int ms){
         try
         {
             Thread.sleep(ms);
         }
         catch(InterruptedException ex)
         {
             Thread.currentThread().interrupt();
         }
     }
 }
