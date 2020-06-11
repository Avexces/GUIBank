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
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.Font; /* library met font */
 import javax.swing.ImageIcon;
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.io.BufferedReader;
 import java.io.*;

 public class GUI {
     // maakt een "pagina " aan
     private static JPanel start_Panel = new JPanel();
     private static JPanel login_Panel = new JPanel();
     private static JPanel main_Panel = new JPanel();
     private static JPanel select_Panel = new JPanel();
     private static JPanel select_CPanel = new JPanel();
     private static JPanel transaction_Panel = new JPanel();
     private static JPanel customWithdraw_panel = new JPanel();

     private static JFrame frame = new JFrame();

     private static JTextField user_Text; // maakt een tekstvlak aan
     private static JPasswordField pin_Field; // maakt een wachtwoord vlak aan
     private static JLabel message_Label; //
     private static JTextField balans;
     private static int amount = 0;
     private static int aantal= 0;

     private static JButton button; // maakt een knop aan
     private static JButton withdrawbutton;
     private static JButton homebutton;
     private static JButton customWithdrawbutton;
     private static JButton loginbutton;
     private static JButton backbutton;
     private static JButton tien;
     private static JButton twintig;
     private static JButton vijtig;
     private static JButton honderd;

     private static String loginkaart;

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
         System.out.println(" De GUI is opgestart");
         start_Panel.removeAll();
         start_Panel.setSize(frame.getSize());
         start_Panel.setLayout(null);

         //ln.setVisible(true);

             start_Panel.setBackground(Color.white); // achtergrond
             start_Panel.setForeground(Color.black); // voorgrond

                  // begin menu van de gui vn de bank
                 JLabel welkom = new JLabel("Welcome to Slankbank");
                 welkom.setBounds(frame.getWidth()/2+575,frame.getHeight()/2+300,1000,80);
                 welkom.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                 welkom.setForeground(Color.black);
                 start_Panel.add(welkom);

                     // ga je naar de login pagina
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
         start_Panel.add(button);

     }

     /*Login menu*/
     private static void login_Panel()
     {
         login_Panel.removeAll();
         login_Panel.setSize(frame.getSize());
         login_Panel.setLayout(null);
         login_Panel.setBackground(Color.white);

             /*UserName Label*/
             JLabel label = new JLabel("Username");
             label.setBounds(frame.getWidth()/2 - 200,frame.getHeight()/2,200,25);
             label.setForeground(new Color (192,27,28));
             label.setFont(new Font("Didact Gothic", Font.PLAIN,22));
             login_Panel.add(label);
             //UserName veld
             user_Text = new JTextField();
             user_Text.setBounds(frame.getWidth()/2,frame.getHeight()/2/*20*/,200 ,30);
             user_Text.setFont(new Font(" Arial", Font.PLAIN,18));
             login_Panel.add(user_Text);

             //Pincode text
             JLabel pin_Label = new JLabel("Password");
             pin_Label.setBounds(frame.getWidth()/2 -200,frame.getHeight()/2+30,200,25);
             pin_Label.setForeground(new Color (192,27,28));
             pin_Label.setFont(new Font("Didact Gothic", Font.PLAIN,22));
             login_Panel.add(pin_Label);
             /*Adds an pin field to the LoginPanel*/
             pin_Field = new JPasswordField();
             pin_Field.setBounds(frame.getWidth()/2,frame.getHeight()/2+30,200 ,30);
             pin_Field.setFont(new Font("Arial",Font.PLAIN,18));
             login_Panel.add(pin_Field);

             /*Login button*/
             button = new JButton(/*"Login"*/ new AbstractAction("Login") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     //FIXME password = pin_Field.getPassword();
                     loginkaart = user_Text.getText();
                     String loginwachtwoord = pin_Field.getText();
                     // oproepen de functie om te kunnen praten met de database
                     loginCommunicatie(loginkaart,loginwachtwoord);

                 }
             });
             button.setBounds(frame.getWidth()/2-75,frame.getHeight()/2+100,250,45);
             login_Panel.add(button);


         /* standaard menu rechtsonder in het scherm */
         /*Exit button*/
         button = new JButton(/*"Login"*/ new AbstractAction("Exit") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);
             }
         });
         button.setBounds(frame.getWidth()/2 + 200 ,frame.getHeight()/2+100,100,45);
         login_Panel.add(button);

         /* terug knop */
         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                start();
             }
         });
         backbutton.setBounds(frame.getWidth()/2-200,frame.getHeight()/2+100,100,45);
         login_Panel.add(backbutton);

         /*Message Label*/
         message_Label = new JLabel("");
         message_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2+250,300,25);
         message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         message_Label.setForeground(new Color(191,26,28));
         login_Panel.add(message_Label);
         frame.setVisible(true);

     }

     /*Hoofdmenu voor gebruikers*/
     private static void main_Panel()
     {
         main_Panel.setLayout(null);
         main_Panel.setBackground(Color.white);

         // menu dat recht onder staat
         // terugknop
         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 Switch(main_Panel, login_Panel);
                 login_Panel();
             }
         });
         backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
         main_Panel.add(backbutton);

         //home knopw
         homebutton = new JButton(new AbstractAction("Home") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");
                 start();
             }
         });
         homebutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         homebutton.setBackground(Color.black.darker().darker().darker().darker());
         homebutton.setBounds(frame.getWidth()/2+500,frame.getHeight()-60,100,50);
         main_Panel.add(homebutton);

         // login knop ga je terug naar login
         loginbutton = new JButton(new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (main_Panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+600,frame.getHeight() - 60,100,50);
         main_Panel.add(loginbutton);

        // textfield met het baland
         JLabel balancetxt_Label = new JLabel(" Balance: ");
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color (192,27,28));
         balancetxt_Label.setBackground(new Color (241,227,12));
         main_Panel.add(balancetxt_Label);

        // laat je balans displayen uit de database
         JLabel balance_Label  = new JLabel("$:  "+balansCommunicatie(loginkaart));
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth()/2 -100 ,frame.getHeight()/2 -50,300,50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray.darker());
         main_Panel.add(balance_Label);
         frame.setVisible(true);
         System.out.println(balansCommunicatie(loginkaart));

         // bovenste kopje voor het menu
         JLabel Withdrawaltxt_Label = new JLabel(" Quick withdraw: ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,300,50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color (192,27,28));
         Withdrawaltxt_Label.setBackground(new Color(241,227,12));
         main_Panel.add(Withdrawaltxt_Label);

         //Withdraw 70
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

         withdrawbutton = new JButton(new AbstractAction(" Adjusted amount ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 //SelectCustom();
                 Switch(main_Panel, select_CPanel);
                 //SelectCustom();

             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 200,300,50);
         main_Panel.add(withdrawbutton);


         frame.setVisible(true);
     }

     //menu om je briefjes te kiezen
     private static void Select_Bills(int selectedmoney)
     {
        select_Panel.setLayout(null);
         select_Panel.setBackground(Color.white);

         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 main_Panel();
                 Switch(select_Panel, main_Panel);

             }
         });
         backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
         select_Panel.add(backbutton);

         homebutton = new JButton(new AbstractAction("Home") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");
                 start();
             }
         });
         homebutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         homebutton.setBackground(Color.black.darker().darker().darker().darker());
         homebutton.setBounds(frame.getWidth()/2+500,frame.getHeight()-60,100,50);
         select_Panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (main_Panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+600,frame.getHeight() - 60,100,50);
         select_Panel.add(loginbutton);

         String geselecteerdbedrag = String.valueOf(selectedmoney);

         JLabel balancetxt_Label = new JLabel("$:"+geselecteerdbedrag);
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color (192,27,28));
         balancetxt_Label.setBackground(new Color (241,227,12));
         select_Panel.add(balancetxt_Label);

         JLabel optiontxt_Label = new JLabel("Select Notes:");
         optiontxt_Label.setOpaque(true);
         optiontxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 50,300,50);
         optiontxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN,18));
         optiontxt_Label.setForeground(Color.lightGray );
         optiontxt_Label.setBackground(Color.darkGray.darker());

         select_Panel.add(optiontxt_Label);

         tien = new JButton(new AbstractAction("$:10") {
             @Override
             public void actionPerformed(ActionEvent e) {
                withdraw(selectedmoney,10);
             }
         });
         tien.setBounds(frame.getWidth()/2-225,frame.getHeight()/2+25,150,50);
         tien.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         tien.setForeground(new Color (192,27,28));
         tien.setBackground(Color.darkGray.darker().darker().darker());
         select_Panel.add(tien);

         twintig = new JButton(new AbstractAction("$:20") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 withdraw(selectedmoney,20);
             }
         });
         twintig.setBounds(frame.getWidth()/2-75,frame.getHeight()/2+25,150,50);
         twintig.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         twintig.setForeground(new Color (192,27,28));
         twintig.setBackground(Color.darkGray.darker().darker().darker());
         select_Panel.add(twintig);

         vijtig = new JButton(new AbstractAction("$50") {
             @Override
             public void actionPerformed(ActionEvent e) {
                withdraw(selectedmoney,50);
             }
         });
         vijtig.setBounds(frame.getWidth()/2+75,frame.getHeight()/2+25,150,50);
         vijtig.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         vijtig.setForeground(new Color (192,27,28));
         vijtig.setBackground(Color.darkGray.darker().darker().darker());
         select_Panel.add(vijtig);

         honderd = new JButton(new AbstractAction("$:100") {
             @Override
             public void actionPerformed(ActionEvent e) {
                withdraw(selectedmoney,100);
             }
         });
         honderd.setBounds(frame.getWidth()/2+225,frame.getHeight()/2+25,150,50);
         honderd.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         honderd.setForeground(new Color (192,27,28));
         honderd.setBackground(Color.darkGray.darker().darker().darker());
         select_Panel.add(honderd);


     }

     // kan je een aangepast bedrag kiezen
     // *private static void SelectCustom()
    /* {
         select_Panel.removeAll();
         select_CPanel.removeAll();
         customWithdraw_panel.removeAll();

         select_CPanel.setLayout(null);
         select_CPanel.setBackground(Color.white);

         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 main_Panel();
                 Switch(select_CPanel, main_Panel);

             }
         });
         backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
         select_CPanel.add(backbutton);

         homebutton = new JButton(new AbstractAction("Home") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");
                 start();
             }
         });
         homebutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         homebutton.setBackground(Color.black.darker().darker().darker().darker());
         homebutton.setBounds(frame.getWidth()/2+500,frame.getHeight()-60,100,50);
         select_CPanel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (select_CPanel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+600,frame.getHeight() - 60,100,50);
         select_CPanel.add(loginbutton);

         //Selecteer veste bedragen
             //TODO weergeef 50 en 20 Notes


             JLabel balancetxt_Label = new JLabel("Total amount: " +amount);
             balancetxt_Label.setOpaque(true);
             balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
             balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             balancetxt_Label.setForeground(new Color (192,27,28));
             balancetxt_Label.setBackground(new Color (241,227,12));
             select_CPanel.add(balancetxt_Label);


             JLabel optiontxt_Label = new JLabel(" Possible note options:");
             optiontxt_Label.setOpaque(true);
             optiontxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 50,300,50);
             optiontxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             optiontxt_Label.setForeground(Color.gray);
             optiontxt_Label.setBackground(Color.darkGray.darker());
             select_CPanel.add(optiontxt_Label);

             JLabel Withdrawaltxt_Label = new JLabel(" 20 Note");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,300,50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color (192,27,28));
             Withdrawaltxt_Label.setBackground(new Color(241,227,12));
             select_CPanel.add(Withdrawaltxt_Label);

         JLabel Withdrawalatxt_Label = new JLabel("     " + tempAmountNotes20);
         Withdrawalatxt_Label.setOpaque(true);
         Withdrawalatxt_Label.setBounds(frame.getWidth()/2+500,frame.getHeight()/2,300,50);
         Withdrawalatxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawalatxt_Label.setForeground(new Color (192,27,28));
         Withdrawalatxt_Label.setBackground(new Color(241,227,12));
         select_CPanel.add(Withdrawalatxt_Label);

         button = new JButton(new AbstractAction("Increase") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Increase");

                 //increase(20);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2+200,frame.getHeight()/2,250,50);
         select_CPanel.add(button);


         button = new JButton(new AbstractAction("Decrease") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Decrease");

                 //decrease(20);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2-350,frame.getHeight()/2,250,50);
         select_CPanel.add(button);

             JLabel Note50_Label = new JLabel(" 50 Note ");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2 + 50,300,50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color (192,27,28));
             Note50_Label.setBackground(new Color(241,227,12));
             select_CPanel.add(Note50_Label);

         JLabel Note50a_Label = new JLabel("     " + tempAmountNotes50);
         Note50a_Label.setOpaque(true);
         Note50a_Label.setBounds(frame.getWidth()/2+500,frame.getHeight()/2 + 50,300,50);
         Note50a_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Note50a_Label.setForeground(new Color (192,27,28));
         Note50a_Label.setBackground(new Color(241,227,12));
         select_CPanel.add(Note50a_Label);

         button = new JButton(new AbstractAction("Increase") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Increase");

                // increase(50);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 +50,250,50);
         select_CPanel.add(button);


         button = new JButton(new AbstractAction("Decrease") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Decrease");

              //   decrease(50);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2-350,frame.getHeight()/2 +50,250,50);
         select_CPanel.add(button);


             JLabel Note100_Label = new JLabel(" 100 Note");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth()/2-100,frame.getHeight()/2 + 100,300,50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color (192,27,28));
             Note100_Label.setBackground(new Color(241,227,12));
             select_CPanel.add(Note100_Label);

         JLabel Note100a_Label = new JLabel("     " + tempAmountNotes100);
         Note100a_Label.setOpaque(true);
         Note100a_Label.setBounds(frame.getWidth()/2+500,frame.getHeight()/2 + 100,300,50);
         Note100a_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Note100a_Label.setForeground(new Color (192,27,28));
         Note100a_Label.setBackground(new Color(241,227,12));
         select_CPanel.add(Note100a_Label);

         button = new JButton(new AbstractAction("Increase") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Increase");

                // increase(100);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2+200,frame.getHeight()/2 +100,250,50);
         select_CPanel.add(button);


         button = new JButton(new AbstractAction("Decrease") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, Decrease");

               //  decrease(100);

             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2-350,frame.getHeight()/2 +100,250,50);
         select_CPanel.add(button);

             button = new JButton(new AbstractAction("FinishTransaction") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Button Clicked, RETREAT!!");
                     try {
                         transaction_Panel(amount,tempAmountNotes20,tempAmountNotes50,tempAmountNotes100);
                     } catch (InterruptedException ex) {
                         ex.printStackTrace();
                     }
                     Switch(select_CPanel, transaction_Panel);

                 }
             });
             button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             button.setBackground(Color.magenta.darker().darker().darker().darker());
             button.setForeground(new Color (192,27,28));
             button.setBounds(frame.getWidth()/2-100,frame.getHeight() /2+175,300,50);
             select_CPanel.add(button);


             frame.setVisible(true);
     }

      */


     private static void transaction_Panel(int a, int b20, int b50, int b100) throws InterruptedException {
         transaction_Panel.setLayout(null);
         transaction_Panel.setBackground(Color.white);
         ServerCommunication.Withdraw(a,b20,b50,b100);
         if(ServerCommunication.is_Succes)
         {
             JLabel transaction_Label = new JLabel(" Transaction succesful!");
             transaction_Label.setOpaque(true);
             transaction_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2,325,50);
             transaction_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 28));
             transaction_Label.setForeground(new Color(19,84,39));
             transaction_Label.setBackground(new Color (195,195,195));
             transaction_Panel.add(transaction_Label);
         }
         else
         {
             JLabel transaction_Label = new JLabel(" Transaction failed!");
             transaction_Label.setOpaque(true);
             transaction_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2,325,50);
             transaction_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 28));
             transaction_Label.setForeground(new Color (192,27,29));
             transaction_Label.setBackground(new Color(195,195,195));
             transaction_Panel.add(transaction_Label);
         }


         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 main_Panel();
                 Switch(transaction_Panel, main_Panel);

             }
         });
         backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
         transaction_Panel.add(backbutton);

         homebutton = new JButton(new AbstractAction("Home") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");
                 start();
             }
         });
         homebutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         homebutton.setBackground(Color.black.darker().darker().darker().darker());
         homebutton.setBounds(frame.getWidth()/2+500,frame.getHeight()-60,100,50);
         transaction_Panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (main_Panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+600,frame.getHeight() - 60,100,50);
         transaction_Panel.add(loginbutton);

         button = new JButton(new AbstractAction("Print receipt") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 //do something with arduino
                 message_Label.setText("Will print receipt!");
             }
         });
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2 -90,frame.getHeight()/2 + 100,300,50);
         transaction_Panel.add(button);


         message_Label = new JLabel("");
         message_Label.setBounds(frame.getWidth()/2 ,frame.getHeight()/2+ +175,300,25);
         message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         message_Label.setForeground(Color.BLACK);
         transaction_Panel.add(message_Label);
         frame.setVisible(true);
         frame.setVisible(true);

     }

     /* communicatie met de server */
     private static void loginCommunicatie(String loginkaart,String loginwachtwoord ){
         System.out.println(loginkaart);
         System.out.println(loginwachtwoord);
        String pincode ="1234";
        String pasnummer = "US-SLBA-02042001";
        String response ="";
        String responsePogingen="";

         URL pogingen = null;
         URL verzenden = null;
         URL url = null;

         String inloggevens = "";
         try {
             url = new URL("http://145.24.222.162/db_connection.php?query=SELECT+*+FROM+Account+WHERE+Pincode+%3D+\""+ loginwachtwoord + "\"+AND+Kaartnummer+%3D+\"" + loginkaart + "\"");
             pogingen = new URL ("http://145.24.222.162/db_connection.php?query=SELECT+Pogingen+FROM+Account+WHERE+Kaartnummer+%3D+\""+loginkaart+"\"");
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(pogingen.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 responsePogingen += line;

             }
             aantal=Integer.parseInt(responsePogingen);

         }
         catch (Exception E){
             System.out.println("exceptie e");
         }

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 response +=line;
             }
         }
         catch (Exception E){
             System.out.println("exceptie e");
         }
         if (aantal <3){
             if (response.equalsIgnoreCase("0 results")){
                 message_Label.setForeground(Color.red);
                 message_Label.setText("Login failled!");
                 System.out.println("login failled");
                 aantal++;
                     try {
                         System.out.println("");
                         String versturen= "http://145.24.222.162/db_connection.php?query=UPDATE+Account+SET+Pogingen+%3D+"+aantal+"+WHERE+Kaartnummer+%3D+\"" +loginkaart+"\"";
                             url = new URL(versturen);
                         System.out.println(versturen);
                     } catch (MalformedURLException e) {
                             e.printStackTrace();
                         }
                         try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                             for (String line; (line = reader.readLine()) != null; ) {
                                 System.out.println(line);
                             }
                         } catch (Exception E){
                             E.printStackTrace();
                         }
                     String resultaatstr = Integer.toString(3-aantal);

                 message_Label.setText("You have "+resultaatstr+" tries left");
                // System.out.println(aantal);
             }
             else{
                 message_Label.setForeground(Color.GREEN);
                 message_Label.setText("Login succesful!");
                 System.out.println("login succes");
                 Switch(login_Panel,main_Panel);
                 main_Panel();
                 try {
                     System.out.println("");
                     String versturen= "http://145.24.222.162/db_connection.php?query=UPDATE+Account+SET+Pogingen+%3D+"+0+"+WHERE+Kaartnummer+%3D+\"" +loginkaart+"\"";
                     url = new URL(versturen);
                     System.out.println(versturen);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                     for (String line; (line = reader.readLine()) != null; ) {
                         System.out.println(line);
                     }
                 } catch (Exception E){
                     E.printStackTrace();
                 }
             }


         }
         else {
             message_Label.setText("Login failled!");
             message_Label.setText("card blocked");
         }



         }

     private static int balansCommunicatie(String loginkaart){
         String balans = "";
         String pasnummer = "US-SLBA-02042001";
         URL url = null;
         try {
             url = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Balans+FROM+Account+WHERE+Kaartnummer+%3D+\"" + loginkaart + "\"");
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 balans += line;
             }
         }

         catch (Exception E){
             System.out.println("exceptie e");
         }

         return Integer.parseInt(balans);

     }

     private static void withdraw(int bedrag,int selecterendbedrag){
         int balans = balansCommunicatie(loginkaart);
         URL briefje10 = null;
         URL briefje20 = null;
         URL briefje50 = null;
         URL briefje100 = null;

         String aantalbriefjetien ="";
         String aantalbriefjetwintig ="";
         String aantalbriefjevijtig ="";
         String aantalbriefjehonderd ="";

         int versturing =0;

         if (balans >= bedrag ) {
                     try {
                         briefje10 = new URL("http://145.222.162/db_connection.php?query==SELECT+Aantal+FROM+%60Biljetten%60+WHERE+soort+%3D+10");
                         briefje20 = new URL("http://145.222.162/db_connection.php?query==SELECT+Aantal+FROM+%60Biljetten%60+WHERE+soort+%3D+20");
                         briefje50 = new URL("http://145.222.162/db_connection.php?query==SELECT+Aantal+FROM+%60Biljetten%60+WHERE+soort+%3D+50");
                         briefje100 = new URL("http://145.222.162/db_connection.php?query==SELECT+Aantal+FROM+%60Biljetten%60+WHERE+soort+%3D+100");
                     } catch (MalformedURLException a) {
                         a.printStackTrace();
                     }
                     //10
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje10.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                             aantalbriefjetien += line;
                         }

                     } catch (Exception E) {
                         System.out.println("exceptie e");
                     }
                     int aantaltien =Integer.parseInt(aantalbriefjetien);


                     // 20
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje20.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                             aantalbriefjetwintig += line;
                         }
                     } catch (Exception E) {
                         System.out.println("exceptie e");
                     }
                          int aantaltwintig =Integer.parseInt(aantalbriefjetwintig);
                     //50
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje50.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                             aantalbriefjevijtig += line;
                         }
                     } catch (Exception E) {
                         System.out.println("exceptie e");
                     }
                     int aantalvijftig =Integer.parseInt(aantalbriefjevijtig);
                     //100
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje100.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                             aantalbriefjehonderd += line;
                         }

                     } catch (Exception E) {
                         System.out.println("exceptie e");
                     }

                        int aantalhonderd = Integer.parseInt(aantalbriefjehonderd);

                     if (bedrag == 0 ){
                         // verstuur naar de server
                         // arduino moet biljetten geven
                         // versturen is het bedrag dat je hebt om van je database enz
                     }

                     while (bedrag > 0 ) {
                         if (selecterendbedrag < bedrag) {
                             System.out.println("Dit biljet is niet mogelijk voor u");
                         }

                         // selecteren van je biljetten
                         if (aantaltien > 0) {
                             if (tien.isSelected()) {
                                 for (int i = 0; i == 10; i++) {
                                     bedrag--;
                                     versturing++;
                                 }

                             }
                         }
                         if (aantaltwintig > 0) {
                             if (twintig.isSelected()) {
                                 for (int i = 0; i == 20; i++) {
                                     bedrag--;
                                     versturing++;
                                 }
                             }
                         }
                         if (aantalvijftig > 0) {
                             if (vijtig.isSelected()) {
                                 for (int i = 0; i == 50; i++) {
                                     bedrag--;
                                     versturing++;
                                 }

                             }
                         }
                         if (aantalhonderd > 0 ) {
                           if (honderd.isSelected()) {
                                 for (int i = 0; i == 100; i++) {
                                     bedrag--;
                                     versturing++;
                                 }

                             }

                         }
                         else{
                             System.out.println("niet beschikbaar");
                         }

                     }


                 }
         else if (balans <= bedrag){
              message_Label  = new  JLabel("Your balance is too low");
              message_Label.setBounds(frame.getWidth()/2,frame.getHeight()/2-900,200,50);
              message_Label.setForeground(new Color(192,27,28));
              message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
              select_Panel.add(message_Label);
         }

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

     protected static void Switch2(JPanel from, JPanel to)
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
         System.out.println("gui is gestopt");
     }


    /* private static void increase(int val)
     {
         if(val == 20 && tempAmountNotes20 <= 5 && amount + 20 <= ServerCommunication.getBalanceInt())
         {
             tempAmountNotes20++;
             amount += 20;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 50 && tempAmountNotes50 <= 5 && amount + 50 <= ServerCommunication.getBalanceInt())
         {
             tempAmountNotes50++;
             amount += 50;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 100 && tempAmountNotes100 <= 5 && amount + 100 <= ServerCommunication.getBalanceInt())
         {
             tempAmountNotes100++;
             amount += 100;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }

         else{return;}
     }

     private static void decrease(int val)
     {
         if(val == 20 && tempAmountNotes20 >=  0 && amount - 20 >= 0)
         {
             tempAmountNotes20--;
             amount -= 20;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 50 && tempAmountNotes50 >=  0 && amount - 50 >= 0)
         {
             tempAmountNotes50--;
             amount -= 50;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }
         else if(val == 100 && tempAmountNotes100 >=  0 && amount - 100 >= 0)
         {
             tempAmountNotes100--;
             amount -= 100;
             Switch(select_CPanel,select_CPanel);
             SelectCustom();
             return;
         }

         else{return;}
     }
*/
 }