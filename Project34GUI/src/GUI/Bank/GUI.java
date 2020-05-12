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

 import Bank.ServerCommunication;

 import javax.imageio.ImageIO;
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.Font; /* library met font */
 import javax.swing.ImageIcon;
 import java.awt.color.*;
 import java.awt.event.ActionListener;
 import java.sql.SQLOutput;

 public class GUI {
     private static JPanel start_Panel = new JPanel(); // maakt een "pagina " aan
     private static JPanel login_Panel = new JPanel();
     private static JPanel main_Panel = new JPanel();
     private static JPanel transaction_Panel = new JPanel();
     private static JPanel customWithdraw_Panel = new JPanel();
     private static JPanel customNote_Panel = new JPanel();
     private static JFrame frame = new JFrame();

     private static JButton button; // maakt een knop aan
     private static JButton withdrawbutton;
     private static JButton homebutton;
     private static JButton customWithdrawbutton;
     private static JButton finnishtansaction;
     private static JButton loginbutton;
     private static JButton backbutton;


     private static JTextField user_Text; // maakt een tekstvlak aan
     private static JPasswordField pin_Field; // maakt een wachtwoord vlak aan
     private static JLabel message_Label; //
     private static JLabel thankYou_Label;
     private static JTextField Amount_Text;

     /* geld briefjes
     private static JButton briefje20;
     private static JButton briefje50;
     private static JButton briefjes100;
     private static JButton increaseButton;
     private static JButton decreaseButton;*/

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
        // button.setBackground(Color.black.darker().darker());
         button.setForeground(new Color(192,27,28));
         button.setOpaque(true);
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
         main_Panel();

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

         /*Message Label*/
         message_Label = new JLabel("");
         message_Label.setBounds(frame.getWidth()/2 - 100,frame.getHeight()/2+250,300,25);
         message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         message_Label.setForeground(new Color(191,26,28));
         login_Panel.add(message_Label);
         frame.setVisible(true);


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
         button.setBounds(frame.getWidth()/2-75,frame.getHeight()/2+100,250,45);
         login_Panel.add(button);

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


     }

     /*Hoofdmenu voor gebruikers*/
     private static void main_Panel() {
         //main_Panel = new JPanel();

         main_Panel.setLayout(null);
         main_Panel.setBackground(Color.white);

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


         homebutton = new JButton(new AbstractAction("Home") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");
                start();
             }
         });
         homebutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         homebutton.setBackground(Color.black.darker().darker().darker().darker());
         homebutton.setBounds(frame.getWidth()/2+200,frame.getHeight()-60,100,50);
         main_Panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (main_Panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+300,frame.getHeight() - 60,100,50);
         main_Panel.add(loginbutton);

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
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2+400,frame.getHeight() - 60,300,50);
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
         withdrawbutton = new JButton(new AbstractAction(" $50 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(50);
                 Switch(main_Panel, customNote_Panel);
                 //try {
                     customNote_Panel();
                // } catch (InterruptedException ex) {
                  //   ex.printStackTrace();
            //     }
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(new Color(241,227,12));
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 50,300,50);
         main_Panel.add(withdrawbutton);

         withdrawbutton = new JButton(new AbstractAction(" $70 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(50);
                 Switch(main_Panel, customNote_Panel);
                 //try {
                 customNote_Panel();
                 // } catch (InterruptedException ex) {
                 //   ex.printStackTrace();
                 //     }
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(new Color(241,227,12));
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 100,300,50);
         main_Panel.add(withdrawbutton);

         //Withdraw 100
         withdrawbutton = new JButton(new AbstractAction(" $100 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(100);
                 Switch(main_Panel, customNote_Panel);
              //   try {
                     customNote_Panel();
             //    } catch (InterruptedException ex) {
             //        ex.printStackTrace();
             //    }
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 150,300,50);
         main_Panel.add(withdrawbutton);

         //Withdraw 150
         withdrawbutton = new JButton(new AbstractAction(" $150 ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(150);
                 Switch(main_Panel, customNote_Panel);
              //   try {
                     customNote_Panel();
              //   } catch (InterruptedException ex) {
             //        ex.printStackTrace();
            //     }
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 200,300,50);
         main_Panel.add(withdrawbutton);

        // custom geld
         customWithdrawbutton = new JButton(new AbstractAction(" Custom ") {
             @Override
             public void actionPerformed (ActionEvent e ){
                 System.out.println("Doorverwijzing naar de cutom note pagina");
                 Switch( main_Panel,customWithdraw_Panel);
                 customWithdraw_panel();

                 }
         });
         customWithdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         customWithdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         customWithdrawbutton.setForeground(new Color (192,27,28));
         customWithdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 +250,300,50);
         main_Panel.add(customWithdrawbutton);


         JLabel balance_Label = new JLabel(ServerCommunication.getBalance());
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth()/2 -100 ,frame.getHeight()/2 -50,300,50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray);
         main_Panel.add(balance_Label);
         frame.setVisible(true);

     }

     private static void transaction_Panel() throws InterruptedException {
         transaction_Panel.setLayout(null);
         transaction_Panel.setBackground(Color.white);
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
         JLabel thankYou_Label = new JLabel("Thank you for choosing Slankbank");
         thankYou_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 28));
         thankYou_Label.setBounds(frame.getWidth()/2-150,frame.getHeight()/2+100,475,50);
         thankYou_Label.setOpaque(true);
         transaction_Panel.add(thankYou_Label);

         button = new JButton(new AbstractAction("Done") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 start();
             }
         });
         button.setOpaque(true);
         button.setBounds(frame.getWidth()/2+10,frame.getHeight()/2+200,150,75);
         thankYou_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 28));
         transaction_Panel.add(button);

         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 Switch(transaction_Panel, main_Panel);
                 main_Panel();
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
         homebutton.setBounds(frame.getWidth()/2+200,frame.getHeight()-60,100,50);
         transaction_Panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (transaction_Panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+300,frame.getHeight() - 60,100,50);
         transaction_Panel.add(loginbutton);

         finnishtansaction = new JButton(new AbstractAction("FinishTransaction") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");

                 Switch(transaction_Panel, transaction_Panel);
                 try {
                     transaction_Panel();
                 } catch (InterruptedException ex) {
                     ex.printStackTrace();
                 }
             }
         });
         finnishtansaction.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         finnishtansaction.setBackground(Color.magenta.darker().darker().darker().darker());
         finnishtansaction.setForeground(Color.black);
         finnishtansaction.setBounds(frame.getWidth()/2+400,frame.getHeight() - 60,300,50);
         transaction_Panel.add(finnishtansaction);
     }

        // nieuwe pagina
     private static void customWithdraw_panel() {
         customWithdraw_Panel.removeAll();
         customWithdraw_Panel.setSize(frame.getSize());
         customWithdraw_Panel.setLayout(null);
         customWithdraw_Panel.setBackground(Color.white);

         int waarde;

         // knoppen
         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 Switch(customWithdraw_Panel, main_Panel);
                 main_Panel();
             }
         });
         backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
         customWithdraw_Panel.add(backbutton);


         homebutton = new JButton(new AbstractAction("Home") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, chipRemoved");
                 start();
             }
         });
         homebutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         homebutton.setBackground(Color.black.darker().darker().darker().darker());
         homebutton.setBounds(frame.getWidth()/2+200,frame.getHeight()-60,100,50);
         customWithdraw_Panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (customWithdraw_Panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+300,frame.getHeight() - 60,100,50);
         customWithdraw_Panel.add(loginbutton);

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
         button.setForeground(Color.black);
         button.setBounds(frame.getWidth()/2+400,frame.getHeight() - 60,300,50);
         customWithdraw_Panel.add(button);



         // balance op de pagina
         JLabel balancetxt_Label = new JLabel(" Balance: ");
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 - 100,300,50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color (192,27,28));
         balancetxt_Label.setBackground(new Color (241,227,12));
         customWithdraw_Panel.add(balancetxt_Label);

         JLabel balance_Label = new JLabel(ServerCommunication.getBalance());
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth()/2 -100 ,frame.getHeight()/2 -50,300,50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray);
         customWithdraw_Panel.add(balance_Label);
         frame.setVisible(true);





        // zorgt voor het kunnen invullen en versturen van bedragen
         JLabel label = new JLabel("Enter your amount:");
         label.setBounds(frame.getWidth()/2-50,frame.getHeight()/2+50,400,50);
         label.setForeground(Color.black);
         label.setFont(new Font("Didact Gothic", Font.PLAIN,22));
         customWithdraw_Panel.add(label);


         Amount_Text = new JTextField();
         Amount_Text.setBounds(frame.getWidth()/2-50,frame.getHeight()/2+100/*20*/,200 ,30);
         Amount_Text.setFont(new Font(" Arial", Font.PLAIN,18));
         customWithdraw_Panel.add(Amount_Text);

         withdrawbutton = new JButton(new AbstractAction(" Send ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 ServerCommunication.Withdraw(100);
                 Switch(customWithdraw_Panel, customNote_Panel);
                     customNote_Panel();
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color (192,27,28));
         withdrawbutton.setBounds(frame.getWidth()/2 -100,frame.getHeight()/2 + 150,300,50);
         customWithdraw_Panel.add(withdrawbutton);


     }

        private static void customNote_Panel () {
            customNote_Panel.removeAll();
            customNote_Panel.setSize(frame.getSize());
            customNote_Panel.setLayout(null);
            customNote_Panel.setBackground(Color.white);

            backbutton = new JButton(new AbstractAction("Back") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button Clicked, RETREAT!!");
                    Switch(customNote_Panel, main_Panel);
                    main_Panel();
                }
            });
            backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
            backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
            backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
            customNote_Panel.add(backbutton);


            homebutton = new JButton(new AbstractAction("Home") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button Clicked, chipRemoved");
                    start();
                }
            });
            homebutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
            homebutton.setBackground(Color.black.darker().darker().darker().darker());
            homebutton.setBounds(frame.getWidth()/2+200,frame.getHeight()-60,100,50);
            customNote_Panel.add(homebutton);

            loginbutton = new JButton(new AbstractAction("login") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Switch (customNote_Panel,login_Panel);
                    login_Panel();
                }
            });
            loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
            loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
            loginbutton.setBounds(frame.getWidth()/2+300,frame.getHeight() - 60,100,50);
            customNote_Panel.add(loginbutton);

            finnishtansaction = new JButton(new AbstractAction("FinishTransaction") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Button Clicked, RETREAT!!");

                    Switch(customNote_Panel, transaction_Panel);
                    try {
                        transaction_Panel();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            finnishtansaction.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
            finnishtansaction.setBackground(Color.magenta.darker().darker().darker().darker());
            finnishtansaction.setForeground(Color.black);
            finnishtansaction.setBounds(frame.getWidth()/2+400,frame.getHeight() - 60,300,50);
            customNote_Panel.add(finnishtansaction);

            button = new JButton(new AbstractAction("Send") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("doorgeleid naar de volgende pagina met de transactie");
                    Switch(customNote_Panel,transaction_Panel);
                    try {
                        transaction_Panel();
                    }
                    catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            });
            button.setBounds(frame.getWidth()/2,frame.getHeight()/2+50,100,50);
            button.setForeground(new Color(191,26,28));
            button.setBackground(Color.black.darker().darker().darker().darker());
            customNote_Panel.add(button);

            // button voor aantal briefjes

         /*   briefje20 = new JButton(new AbstractAction("Note 20$") {
                @Override
                public void actionPerformed(ActionEvent e) {
                        ServerCommunication.setBriefjes20(1);
                }
            });
            briefje20.setBounds(frame.getWidth()/2-100,frame.getHeight()/2,100,50);
            briefje20.setForeground(new Color(191,26,28));
            customNote_Panel.add(briefje20);


            briefje50 = new JButton(new AbstractAction("Note 50$") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ServerCommunication.setBriefjes50(1);
                }
            });
            briefje50.setBounds(frame.getWidth()/2,frame.getHeight()/2,100,50);
            briefje50.setForeground(new Color(191,26,28));
            customNote_Panel.add(briefje50);

            briefjes100 = new JButton(new AbstractAction("Note 100$") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ServerCommunication.setBriefjes100(1);
                }
            });
            briefjes100.setBounds(frame.getWidth()/2+100,frame.getHeight()/2,100,50);
            briefjes100.setForeground(new Color(191,26,28));
            customNote_Panel.add(briefjes100);


            increaseButton = new JButton(new AbstractAction("increase") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ServerCommunication.setBriefjes100(1);
                }
            });
            increaseButton.setBounds(frame.getWidth()/2+350,frame.getHeight()/2,500,500);
            increaseButton.setForeground(new Color(191,26,28));
            customNote_Panel.add(increaseButton); */

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
     public static void Abrupted()
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
