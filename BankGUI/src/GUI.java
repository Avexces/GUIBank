 /*
  *
  * Authors: Mischa Quist & Annelot Janssen
  * Group: 5
  * Bank:  SlankBank
  *
  *
  */

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
 import com.fazecast.jSerialComm.*;
 import org.w3c.dom.ls.LSOutput;
 import javax.swing.*;


 public class GUI {
     // maakt een "pagina " aan
     private static JPanel start_Panel = new JPanel();
     private static JPanel login_Panel = new JPanel();
     private static JPanel main_Panel = new JPanel();
     private static JPanel select_Panel = new JPanel();
     private static JPanel select_CPanel = new JPanel();
     private static JPanel transaction_Panel= new JPanel();
     private static JPanel customWithdraw_panel = new JPanel();
     private static JPanel selectBill_panel = new JPanel();
     private static JPanel pasword_panel = new JPanel();


     private static JFrame frame = new JFrame();

     private static JTextField user_Text; // maakt een tekstvlak aan
     private static JPasswordField pin_Field; // maakt een wachtwoord vlak aan
     private static JTextField custom_amount;
     private static JLabel message_Label; //
     private static JTextField balans;
     private static int amount = 0;
     private static int aantal = 0;
     private static JLabel opties;

     private static JButton button; // maakt een knop aan
     private static JButton withdrawbutton;
     private static JButton homebutton;
     private static JButton customWithdrawbutton;
     private static JButton loginbutton;
     private static JButton backbutton;

     private static String loginkaart;
     private static String doorStuurbedrag;
     private static int briefjekeuze [][];
     private static double amounts;



     public static void main(String[] args) {
         int[][] otherVar = new int[0][0];
         briefjekeuze = otherVar;
         start();
     }

     /*Welkom menu*/
     public static void start() {
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
         welkom.setBounds(frame.getWidth() / 2 + 575, frame.getHeight() / 2 + 300, 1000, 80);
         welkom.setFont(new Font("TimesRoman", Font.PLAIN, 60));
         welkom.setForeground(Color.black);
         start_Panel.add(welkom);

         // ga je naar de login pagina
         button = new JButton(new AbstractAction("Login") {

             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch(start_Panel, login_Panel);
                 login_Panel();
             }
         });
         button.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2, 200, 85);
         button.setForeground(new Color(192, 27, 28));
         start_Panel.add(button);

         button = new JButton(/*"Login"*/ new AbstractAction("Exit") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);
             }
         });
         button.setBounds(0, 0, 80, 25);
         start_Panel.add(button);

     }


     /*Login menu*/
     private static void login_Panel() {
         login_Panel.removeAll();
         login_Panel.setSize(frame.getSize());
         login_Panel.setLayout(null);
         login_Panel.setBackground(Color.white);
         final String passUID = "";
         /*
          * Change "COM4" to your USB port connected to the Arduino
          * You can find the right port using the ArduinIDE
          *
          * PS: Unix based operating systems use "/dev/ttyUSB"
          */
         SerialPort comPort = SerialPort.getCommPort("/dev/cu.usbserial-1430");

         //set the baud rate to 9600 (same as the Arduino)
         comPort.setBaudRate(9600);

         //open the port
         comPort.openPort();

         //create a listener and start listening
         comPort.addDataListener(new SerialPortDataListener() {
                                     @Override
                                     public int getListeningEvents() {
                                         return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                                     }

                                     @Override
                                     public void serialEvent(SerialPortEvent event) {
                                         if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                                             return; //wait until we receive data

                                         byte[] newData = new byte[comPort.bytesAvailable()]; //receive incoming bytes
                                         comPort.readBytes(newData, newData.length); //read incoming bytes
                                         String serialData = new String(newData); //convert bytes to string
                                         //print string received from the Arduino
                                         System.out.print(serialData);
                                         System.out.println("1");
                                         loginkaart += serialData;
                                         try {
                                             wait();
                                         } catch (InterruptedException e) {
                                             e.printStackTrace();
                                         }
                                     }
                                 }
         );

         System.out.println("2");
         System.out.println(loginkaart);

         //Pincode text
         JLabel pin_Label = new JLabel("Password");
         pin_Label.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 + 30, 200, 25);
         pin_Label.setForeground(new Color(192, 27, 28));
         pin_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 22));
         login_Panel.add(pin_Label);

         /*Adds an pin field to the LoginPanel*/
         pin_Field = new JPasswordField();
         pin_Field.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 + 30, 200, 30);
         pin_Field.setFont(new Font("Arial", Font.PLAIN, 18));
         login_Panel.add(pin_Field);

         /*Login button*/
         button = new JButton(/*"Login"*/ new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 //FIXME password = pin_Field.getPassword();
                 loginkaart = user_Text.getText();
                 String loginwachtwoord = pin_Field.getText();
                 // oproepen de functie om te kunnen praten met de database
                 loginCommunicatie(loginkaart, loginwachtwoord);

             }
         });
         button.setBounds(frame.getWidth() / 2 - 75, frame.getHeight() / 2 + 100, 250, 45);
         login_Panel.add(button);


         /* standaard menu rechtsonder in het scherm */
         /*Exit button*/
         button = new JButton(/*"Login"*/ new AbstractAction("Exit") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.exit(0);
             }
         });
         button.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2 + 100, 100, 45);
         login_Panel.add(button);

         /* terug knop */
         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent actionEvent) {
                 start();
             }
         });
         backbutton.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 + 100, 100, 45);
         login_Panel.add(backbutton);

         /*Message Label*/
         message_Label = new JLabel("");
         message_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 250, 300, 25);
         message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         message_Label.setForeground(new Color(191, 26, 28));
         login_Panel.add(message_Label);
         frame.setVisible(true);

     }





     /*Hoofdmenu voor gebruikers*/
     private static void main_Panel() {
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
         backbutton.setBounds(frame.getWidth() / 2 + 700, frame.getHeight() - 60, 100, 50);
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
         homebutton.setBounds(frame.getWidth() / 2 + 500, frame.getHeight() - 60, 100, 50);
         main_Panel.add(homebutton);

         // login knop ga je terug naar login
         loginbutton = new JButton(new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch(main_Panel, login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth() / 2 + 600, frame.getHeight() - 60, 100, 50);
         main_Panel.add(loginbutton);

         // textfield met het baland
         JLabel balancetxt_Label = new JLabel(" Balance: ");
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 100, 300, 50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color(192, 27, 28));
         balancetxt_Label.setBackground(new Color(241, 227, 12));
         main_Panel.add(balancetxt_Label);

         // laat je balans displayen uit de database
         JLabel balance_Label = new JLabel("$:  " + balansCommunicatie(loginkaart));
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 50, 300, 50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray.darker());
         main_Panel.add(balance_Label);
         frame.setVisible(true);
         System.out.println(balansCommunicatie(loginkaart));

         // bovenste kopje voor het menu
         JLabel Withdrawaltxt_Label = new JLabel(" Quick withdraw: ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
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
         withdrawbutton.setBackground(new Color(241, 227, 12));
         withdrawbutton.setForeground(new Color(192, 27, 28));
         withdrawbutton.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 50, 300, 50);
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
         withdrawbutton.setForeground(new Color(192, 27, 28));
         withdrawbutton.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 100, 300, 50);
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
         withdrawbutton.setForeground(new Color(192, 27, 28));
         withdrawbutton.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 150, 300, 50);
         main_Panel.add(withdrawbutton);

         withdrawbutton = new JButton(new AbstractAction(" Adjusted amount ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 Switch(main_Panel, select_CPanel);
                 SelectCustom();
             }
         });
         withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         withdrawbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         withdrawbutton.setForeground(new Color(192, 27, 28));
         withdrawbutton.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 200, 300, 50);
         main_Panel.add(withdrawbutton);


         frame.setVisible(true);
     }

     //menu om je briefjes te kiezen
     private static void Select_Bills(int selectedmoney) {
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
         backbutton.setBounds(frame.getWidth() / 2 + 700, frame.getHeight() - 60, 100, 50);
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
         homebutton.setBounds(frame.getWidth() / 2 + 500, frame.getHeight() - 60, 100, 50);
         select_Panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch(main_Panel, login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth() / 2 + 600, frame.getHeight() - 60, 100, 50);
         select_Panel.add(loginbutton);


         JLabel balancetxt_Label = new JLabel("$:" + selectedmoney);
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 100, 300, 50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color(192, 27, 28));
         balancetxt_Label.setBackground(new Color(241, 227, 12));
         select_Panel.add(balancetxt_Label);

         JLabel optiontxt_Label = new JLabel("Select option:");
         optiontxt_Label.setOpaque(true);
         optiontxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 50, 300, 50);
         optiontxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         optiontxt_Label.setForeground(Color.lightGray);
         optiontxt_Label.setBackground(Color.darkGray.darker());

         select_Panel.add(optiontxt_Label);
         //Selecteer veste bedragen
         if (selectedmoney == 70) {
             //TODO weergeef 50 en 20 Notes
             JLabel Withdrawaltxt_Label = new JLabel(" 10$ Note       Number: 2");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2, 300, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             Withdrawaltxt_Label = new JLabel(" 20$ Note       Number: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 + 50, 300, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             JLabel Note50_Label = new JLabel(" 50$ Note       Number: 1");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 + 100, 300, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             JLabel Note100_Label = new JLabel(" 100$ Note     Number: 0");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 + 150, 300, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);

             //Withdraw 50
             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(70, 2, 0, 1, 0);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 - 250, frame.getHeight() / 2 + 200, 300, 50);
             select_Panel.add(withdrawbutton);

             //2de optie
             JLabel biljet10 = new JLabel(" 10$ Note       Number: 0");
             biljet10.setOpaque(true);
             biljet10.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2, 300, 50);
             biljet10.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet10.setForeground(new Color(192, 27, 28));
             biljet10.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet10);

             JLabel biljet20 = new JLabel(" 20$ Note       Number: 1");
             biljet20.setOpaque(true);
             biljet20.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 + 50, 300, 50);
             biljet20.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet20.setForeground(new Color(192, 27, 28));
             biljet20.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet20);

             JLabel biljet50 = new JLabel(" 50$ Note       Number: 1");
             biljet50.setOpaque(true);
             biljet50.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 + 100, 300, 50);
             biljet50.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet50.setForeground(new Color(192, 27, 28));
             biljet50.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet50);

             JLabel biljet100 = new JLabel(" 100$ Note     Number: 0");
             biljet100.setOpaque(true);
             biljet100.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 + 150, 300, 50);
             biljet100.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet100.setForeground(new Color(192, 27, 28));
             biljet100.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet100);

             //Withdraw 50
             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(70, 0, 1, 1, 0);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 + 50, frame.getHeight() / 2 + 200, 300, 50);
             select_Panel.add(withdrawbutton);

             frame.setVisible(true);
         }
         // 100 dollaar pinnen
         else if (selectedmoney == 100) {
             //mogelijkheid 1
             JLabel biljet10 = new JLabel(" 10$ Note      Number: 0");
             biljet10.setOpaque(true);
             biljet10.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2, 250, 50);
             biljet10.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet10.setForeground(new Color(192, 27, 28));
             biljet10.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet10);

             JLabel Withdrawaltxt_Label = new JLabel(" 20$ Note        Number: 5");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             JLabel Note50_Label = new JLabel(" 50$ Note        Number: 0");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             JLabel Note100_Label = new JLabel(" 100$ Note      Number: 0");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(100, 0, 5, 0, 0);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);

             //mogelijkheid 2

             JLabel biljet102 = new JLabel(" 10$ Note      Number: 0");
             biljet102.setOpaque(true);
             biljet102.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2, 250, 50);
             biljet102.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet102.setForeground(new Color(192, 27, 28));
             biljet102.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet102);

             Withdrawaltxt_Label = new JLabel(" 20$ Note       Number: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             Note50_Label = new JLabel(" 50$ Note       Number: 2");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             Note100_Label = new JLabel(" 100$ Note     Number: 0");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(100, 0, 0, 2, 0);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);

             //mogelijkheid 3
             JLabel biljet103 = new JLabel(" 10$ Note      Number: 0");
             biljet103.setOpaque(true);
             biljet103.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2, 250, 50);
             biljet103.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet103.setForeground(new Color(192, 27, 28));
             biljet103.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet103);

             Withdrawaltxt_Label = new JLabel(" 20$ Note       Number: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             Note50_Label = new JLabel(" 50$ Note       Number: 0");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             Note100_Label = new JLabel(" 100$ Note     Number: 1");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(100, 0, 0, 0, 1);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);

             //mogelijkheid 4

             JLabel biljet104 = new JLabel(" 10$ Note      Number: 1");
             biljet104.setOpaque(true);
             biljet104.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2, 250, 50);
             biljet104.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet104.setForeground(new Color(192, 27, 28));
             biljet104.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet104);

             Withdrawaltxt_Label = new JLabel(" 20$ Note       Number: 2");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             Note50_Label = new JLabel(" 50$ Note       Number: 1");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             Note100_Label = new JLabel(" 100$ Note     Number: 0");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(100, 1, 2, 1, 0);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);


             frame.setVisible(true);
         }
         // 150 dollaar pinen
         else if (selectedmoney == 150) {
             //mogelijkheid 1
             JLabel biljet10 = new JLabel(" 10$ Note      Number: 3");
             biljet10.setOpaque(true);
             biljet10.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2, 250, 50);
             biljet10.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet10.setForeground(new Color(192, 27, 28));
             biljet10.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet10);

             JLabel Withdrawaltxt_Label = new JLabel(" 20$ Note        Number: 1");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             JLabel Note50_Label = new JLabel(" 50$ Note        Number: 2");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             JLabel Note100_Label = new JLabel(" 100$ Note      Number: 0");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(150, 3, 2, 1, 0);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 - 425, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);

             // mogelijkheid 2
             JLabel biljet102 = new JLabel(" 10$ Note      Number: 1");
             biljet102.setOpaque(true);
             biljet102.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2, 250, 50);
             biljet102.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet102.setForeground(new Color(192, 27, 28));
             biljet102.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet102);

             Withdrawaltxt_Label = new JLabel(" 20$ Note       Number: 2");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             Note50_Label = new JLabel(" 50$ Note       Number: 2");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             Note100_Label = new JLabel(" 100$ Note     Number: 0");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(150, 1, 2, 2, 0);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 - 175, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);

             //mogelijkheid 3

             JLabel biljet103 = new JLabel(" 10$ Note      Number: 1");
             biljet103.setOpaque(true);
             biljet103.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2, 250, 50);
             biljet103.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet103.setForeground(new Color(192, 27, 28));
             biljet103.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet103);

             Withdrawaltxt_Label = new JLabel(" 20$ Note       Number: 2");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             Note50_Label = new JLabel(" 50$ Note       Number: 0");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             Note100_Label = new JLabel(" 100$ Note     Number: 1");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);
//

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(150, 1, 2, 0, 1);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 + 75, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);

             //mogelijkheid 4
             JLabel biljet104 = new JLabel(" 10$ Note      Number: 0");
             biljet104.setOpaque(true);
             biljet104.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2, 250, 50);
             biljet104.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             biljet104.setForeground(new Color(192, 27, 28));
             biljet104.setBackground(new Color(241, 227, 12));
             select_Panel.add(biljet104);

             Withdrawaltxt_Label = new JLabel(" 20$ Note       Number: 0");
             Withdrawaltxt_Label.setOpaque(true);
             Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 50, 250, 50);
             Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
             Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Withdrawaltxt_Label);

             Note50_Label = new JLabel(" 50$ Note       Number: 1");
             Note50_Label.setOpaque(true);
             Note50_Label.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 100, 250, 50);
             Note50_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note50_Label.setForeground(new Color(192, 27, 28));
             Note50_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note50_Label);

             Note100_Label = new JLabel(" 100$ Note     Number: 1");
             Note100_Label.setOpaque(true);
             Note100_Label.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 150, 250, 50);
             Note100_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             Note100_Label.setForeground(new Color(192, 27, 28));
             Note100_Label.setBackground(new Color(241, 227, 12));
             select_Panel.add(Note100_Label);

             withdrawbutton = new JButton(new AbstractAction(" Choose option ") {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     System.out.println("Amount option chosen");
                     withdraw(150, 0, 0, 1, 1);
                 }
             });
             withdrawbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             withdrawbutton.setBackground(new Color(241, 227, 12));
             withdrawbutton.setForeground(new Color(192, 27, 28));
             withdrawbutton.setBounds(frame.getWidth() / 2 + 325, frame.getHeight() / 2 + 200, 250, 50);
             select_Panel.add(withdrawbutton);

             message_Label = new JLabel("");
             message_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 +400, 300, 25);
             message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
             message_Label.setForeground(new Color(191, 26, 28));
             select_Panel.add(message_Label);
             frame.setVisible(true);

             frame.setVisible(true);


         }

         frame.setVisible(true);
     }

     // kan je een aangepast bedrag kiezen
     private static void SelectCustom() {
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

         //Selecteer een bedrag
         JLabel label = new JLabel("Enter your amount:");
         label.setBounds(frame.getWidth() / 2-75 , frame.getHeight() / 2-70, 500, 25);
         label.setForeground(new Color(192, 27, 28));
         label.setFont(new Font("Didact Gothic", Font.PLAIN, 22));
         select_CPanel.add(label);

         custom_amount = new JTextField();
         custom_amount.setBounds(frame.getWidth() / 2 - 130, frame.getHeight() / 2, 300, 50);
         custom_amount.setFont(new Font(" Arial", Font.PLAIN, 18));
         custom_amount.setForeground(Color.magenta.darker().darker().darker().darker());
         custom_amount.setOpaque(true);
         select_CPanel.add(custom_amount);



         button = new JButton(new AbstractAction("Select bills") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 doorStuurbedrag = custom_amount.getText();
                 amounts = Double.parseDouble(custom_amount.getText());
                 Switch(select_CPanel,selectBill_panel);
                 selectBill_panel(amounts);

             }
         });
         button.setBounds(frame.getWidth()/2-100,frame.getHeight()/2+70,250,25);
         button.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         select_CPanel.add(button);


         frame.setVisible(true);
     }

     private static void selectBill_panel(double selecterendBedrag) {

         System.out.println("JOEHOE");
         selectBill_panel.removeAll();
         selectBill_panel.setSize(frame.getSize());
         selectBill_panel.setLayout(null);
         selectBill_panel.setBackground(Color.white);

         double tempBedrag = 50;

         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 SelectCustom();
                 Switch(selectBill_panel, select_CPanel);

             }
         });
         backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
         selectBill_panel.add(backbutton);

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
         selectBill_panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+600,frame.getHeight() - 60,100,50);
         selectBill_panel.add(loginbutton);

         System.out.println(tempBedrag);

         JLabel balancetxt_Label = new JLabel(" Chosen amount: ");
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 100, 300, 50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color(192, 27, 28));
         balancetxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(balancetxt_Label);

         JLabel balance_Label = new JLabel("$:  " +tempBedrag);
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 50, 300, 50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray.darker());
         selectBill_panel.add(balance_Label);

         JLabel Withdrawaltxt_Label = new JLabel(" Options: ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         //selectBill_panel.add(Withdrawaltxt_Label);

         System.out.println("Helloow");
         selectBill_panel.setVisible(true);


         //fixme change this to other values
         // kleinste aantal biljetten
         int aantalBriefje10 = opvragenBriefje10();
         int aantalBriefje20 = opvragenBriefje20();
         int aantalBriefje50 = opvragenBriefje50();
         int aantalBriefje100 = opvragenBriefje100();

         while (tempBedrag > 0){
             if(aantalBriefje10 > 0 && tempBedrag >= 10){
                 tempBedrag -= 10;
//                 briefjekeuze[1][1] ++;//173
                 aantalBriefje10 --;
             }
             else if(aantalBriefje20 > 0 && tempBedrag >= 20){
                 tempBedrag -=20;
                 briefjekeuze[1][2] ++;
                 aantalBriefje20 --;
             }
             else if(aantalBriefje50 > 0 && tempBedrag >= 50){
                 tempBedrag -=50;
                 briefjekeuze[1][3] ++;
                 aantalBriefje50 --;
             }
             else if(aantalBriefje100 > 0 && tempBedrag >= 100){
                 tempBedrag -=100;
                 briefjekeuze[1][4] ++;
                 aantalBriefje100 --;
                 break;
             }
             else {
                 // gebruiker kan niet dit bedrag pinnen
                 System.out.println("zelfmoord plegen");
             }
             System.out.println(briefjekeuze);
             break;
         }

         Withdrawaltxt_Label = new JLabel(" Option 1 ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje10));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2 +50, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje20));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2 +100, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje50));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2+150, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje100));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2+200, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);


         final  int finalAantalBriefje1 = aantalBriefje10;
         final   int finalAantalBriefje2 = aantalBriefje20;
         final int finalAantalBriefje5 = aantalBriefje50;
         final int finalAantalBriefje10 = aantalBriefje100;
         loginbutton = new JButton(new AbstractAction("Kies optie ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,transaction_Panel);
                 transaction_Panel((int) amounts);
                 withdraw((int) amounts, finalAantalBriefje1, finalAantalBriefje2, finalAantalBriefje5, finalAantalBriefje10);
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2 - 400,frame.getHeight() / 2 + 250,300,50);
         selectBill_panel.add(loginbutton);

         System.out.println("Helloow");
         selectBill_panel.setVisible(true);

         // random
         for (int i = 2; i <4; i++) {
             aantalBriefje10 = opvragenBriefje10();
             aantalBriefje20 = opvragenBriefje20();
             aantalBriefje50 = opvragenBriefje50();
             aantalBriefje100 = opvragenBriefje100();
             tempBedrag = amounts;
             while (tempBedrag > 0) {
                 int randomGetal = random(1, 4);
                 if (randomGetal == 1 && aantalBriefje10 > 0 && tempBedrag >= 10) {
                     tempBedrag -= 10;
                     briefjekeuze[2][1]++;
                     aantalBriefje10--;
                 } else if (randomGetal == 2 && aantalBriefje20 > 0 && tempBedrag >= 20) {
                     tempBedrag -= 20;
                     briefjekeuze[2][2]++;
                     aantalBriefje20--;
                 } else if (randomGetal == 3 && aantalBriefje50 > 0 && tempBedrag >= 50) {
                     tempBedrag -= 50;
                     briefjekeuze[2][3]++;
                     aantalBriefje50--;
                 } else if (randomGetal == 4 && aantalBriefje100 > 0 && tempBedrag >= 100) {
                     tempBedrag -= 100;
                     briefjekeuze[2][4]++;
                     aantalBriefje100--;
                 } else {
                     // gebruiker kan niet dit bedrag pinnen
                     System.out.println("zelfmoord plegen");
                 }
             }
             System.out.println(briefjekeuze);
         }
         Withdrawaltxt_Label = new JLabel(" Option 2 ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje10));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 +50, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje20));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 +100, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje50));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2+150, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje100));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2+ 200, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);


         int finalAantalBriefje = finalAantalBriefje1;
         int finalAantalBriefje3 = finalAantalBriefje2;
         int finalAantalBriefje4 = finalAantalBriefje5;
         int finalAantalBriefje11 = finalAantalBriefje10;
         loginbutton = new JButton(new AbstractAction("Kies optie ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,transaction_Panel);
                 transaction_Panel((int) amounts);
                 withdraw((int) amounts, finalAantalBriefje, finalAantalBriefje3, finalAantalBriefje4, finalAantalBriefje11);
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2 - 100,frame.getHeight() / 2 +250,300,50);
         selectBill_panel.add(loginbutton);

         // grootste aantal biljetten
         aantalBriefje10 = opvragenBriefje10();
         aantalBriefje20 = opvragenBriefje20();
         aantalBriefje50 = opvragenBriefje50();
         aantalBriefje100 = opvragenBriefje100();
         tempBedrag = amounts;
         while (tempBedrag > 0){
             if (aantalBriefje100 > 0 && tempBedrag >= 100){
                 tempBedrag -=100;
                 briefjekeuze[1][4] ++;
                 aantalBriefje100 --;
             }
             else if(aantalBriefje50 > 0 && tempBedrag >= 50){
                 tempBedrag -=50;
                 briefjekeuze[1][3] ++;
                 aantalBriefje50 --;
             }
             else if(aantalBriefje20 > 0 && tempBedrag >= 20){
                 tempBedrag -=20;
                 briefjekeuze[1][2] ++;
                 aantalBriefje20 --;
             }
             else if(aantalBriefje10 > 0 && tempBedrag >= 10){
                 tempBedrag -=10;
                 briefjekeuze[1][1] ++;
                 aantalBriefje10 --;
             }

             else {
                 // gebruiker kan niet pinnen
             }
             System.out.println(briefjekeuze);
         }

         Withdrawaltxt_Label = new JLabel(" Option 3 ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje10));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2 +50, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje20));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2 +100, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje50));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2+150, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje100));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2+200, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);


         int finalAantalBriefje6 = aantalBriefje10;
         int finalAantalBriefje7 = aantalBriefje20;
         int finalAantalBriefje8 = aantalBriefje50;
         int finalAantalBriefje12 = aantalBriefje100;
         loginbutton = new JButton(new AbstractAction("Kies optie ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,transaction_Panel);
                 transaction_Panel((int) amounts);
                 withdraw((int) amounts, finalAantalBriefje6, finalAantalBriefje7, finalAantalBriefje8, finalAantalBriefje12);
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2 + 200,frame.getHeight() / 2 + 250,300,50);
         selectBill_panel.add(loginbutton);
         System.out.println("Helloow");

         selectBill_panel.setVisible(true);
         selectBill_panel.revalidate();
         selectBill_panel.repaint();
         selectBill_panel.setVisible(true);
         
         /*selectBill_panel.removeAll();
         selectBill_panel.setSize(frame.getSize());

         selectBill_panel.setLayout(null);
         selectBill_panel.setBackground(Color.white);

         double tempBedrag = selecterendBedrag;

         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 SelectCustom();
                 Switch(selectBill_panel, select_CPanel);

             }
         });
         backbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         backbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         backbutton.setBounds(frame.getWidth()/2+700,frame.getHeight() - 60,100,50);
         selectBill_panel.add(backbutton);

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
         selectBill_panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2+600,frame.getHeight() - 60,100,50);
         selectBill_panel.add(loginbutton);

         System.out.println(tempBedrag);

         JLabel balancetxt_Label = new JLabel(" Chosen amount: ");
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 100, 300, 50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color(192, 27, 28));
         balancetxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(balancetxt_Label);

         JLabel balance_Label = new JLabel("$:  " +tempBedrag);
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 50, 300, 50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray.darker());
         selectBill_panel.add(balance_Label);

         JLabel Withdrawaltxt_Label = new JLabel(" Options: ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         // kleinste aantal biljetten
         int aantalBriefje10 = opvragenBriefje10();
         int aantalBriefje20 = opvragenBriefje20();
         int aantalBriefje50 = opvragenBriefje50();
         int aantalBriefje100 = opvragenBriefje100();
         while (tempBedrag > 0){
             if(aantalBriefje10 > 0 && tempBedrag >= 10){
                 tempBedrag -=10;
                 briefjekeuze[1][1] ++;
                 aantalBriefje10 --;
             }
             else if(aantalBriefje20 > 0 && tempBedrag >= 20){
                 tempBedrag -=20;
                 briefjekeuze[1][2] ++;
                 aantalBriefje20 --;
             }
             else if(aantalBriefje50 > 0 && tempBedrag >= 50){
                 tempBedrag -=50;
                 briefjekeuze[1][3] ++;
                 aantalBriefje50 --;
             }
             else if(aantalBriefje100 > 0 && tempBedrag >= 100){
                 tempBedrag -=100;
                 briefjekeuze[1][4] ++;
                 aantalBriefje100 --;
             }
             else {
                 // gebruiker kan niet dit bedrag pinnen
                 System.out.println("zelfmoord plegen");
             }
             System.out.println(briefjekeuze);

         }

         Withdrawaltxt_Label = new JLabel(" Option 1 ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje10));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2 -50, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje20));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2 -100, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje50));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2-150, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje100));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 400, frame.getHeight() / 2- 200, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         int finalAantalBriefje1 = aantalBriefje10;
         int finalAantalBriefje2 = aantalBriefje20;
         int finalAantalBriefje5 = aantalBriefje50;
         int finalAantalBriefje10 = aantalBriefje100;
         int finalAantalBriefje13 = finalAantalBriefje10;
         int finalAantalBriefje9 = finalAantalBriefje5;
         int finalAantalBriefje14 = finalAantalBriefje2;
         int finalAantalBriefje15 = finalAantalBriefje1;
         loginbutton = new JButton(new AbstractAction("Kies optie ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,transaction_Panel);
                 transaction_Panel((int) amounts);
                 withdraw((int) amounts, finalAantalBriefje15, finalAantalBriefje14, finalAantalBriefje9, finalAantalBriefje13);
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2 - 400,frame.getHeight() / 2 - 250,300,50);
         selectBill_panel.add(loginbutton);
         // random
         for (int i = 2; i <4; i++) {
             aantalBriefje10 = opvragenBriefje10();
             aantalBriefje20 = opvragenBriefje20();
             aantalBriefje50 = opvragenBriefje50();
             aantalBriefje100 = opvragenBriefje100();
             tempBedrag = amounts;
             while (tempBedrag > 0) {
                 int randomGetal = random(1, 4);
                 if (randomGetal == 1 && aantalBriefje10 > 0 && tempBedrag >= 10) {
                     tempBedrag -= 10;
                     briefjekeuze[2][1]++;
                     aantalBriefje10--;
                 } else if (randomGetal == 2 && aantalBriefje20 > 0 && tempBedrag >= 20) {
                     tempBedrag -= 20;
                     briefjekeuze[2][2]++;
                     aantalBriefje20--;
                 } else if (randomGetal == 3 && aantalBriefje50 > 0 && tempBedrag >= 50) {
                     tempBedrag -= 50;
                     briefjekeuze[2][3]++;
                     aantalBriefje50--;
                 } else if (randomGetal == 4 && aantalBriefje100 > 0 && tempBedrag >= 100) {
                     tempBedrag -= 100;
                     briefjekeuze[2][4]++;
                     aantalBriefje100--;
                 } else {
                     // gebruiker kan niet dit bedrag pinnen
                     System.out.println("zelfmoord plegen");
                 }
             }
             System.out.println(briefjekeuze);
         }
         Withdrawaltxt_Label = new JLabel(" Option 2 ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje10));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 -50, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje20));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 -100, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje50));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2-150, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje100));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2- 200, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         finalAantalBriefje1 = aantalBriefje10;
         finalAantalBriefje2 = aantalBriefje20;
         finalAantalBriefje5 = aantalBriefje50;
         finalAantalBriefje10 = aantalBriefje100;
         int finalAantalBriefje = finalAantalBriefje1;
         int finalAantalBriefje3 = finalAantalBriefje2;
         int finalAantalBriefje4 = finalAantalBriefje5;
         int finalAantalBriefje11 = finalAantalBriefje10;
         loginbutton = new JButton(new AbstractAction("Kies optie ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,transaction_Panel);
                 transaction_Panel((int) amounts);
                 withdraw((int) amounts, finalAantalBriefje, finalAantalBriefje3, finalAantalBriefje4, finalAantalBriefje11);
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2 - 100,frame.getHeight() / 2 - 250,300,50);
         selectBill_panel.add(loginbutton);

         // grootste aantal biljetten
         aantalBriefje10 = opvragenBriefje10();
         aantalBriefje20 = opvragenBriefje20();
         aantalBriefje50 = opvragenBriefje50();
         aantalBriefje100 = opvragenBriefje100();
         tempBedrag = amounts;
         while (tempBedrag > 0){
             if (aantalBriefje100 > 0 && tempBedrag >= 100){
                 tempBedrag -=100;
                 briefjekeuze[1][4] ++;
                 aantalBriefje100 --;
             }
             else if(aantalBriefje50 > 0 && tempBedrag >= 50){
                 tempBedrag -=50;
                 briefjekeuze[1][3] ++;
                 aantalBriefje50 --;
             }
             else if(aantalBriefje20 > 0 && tempBedrag >= 20){
                 tempBedrag -=20;
                 briefjekeuze[1][2] ++;
                 aantalBriefje20 --;
             }
             else if(aantalBriefje10 > 0 && tempBedrag >= 10){
                 tempBedrag -=10;
                 briefjekeuze[1][1] ++;
                 aantalBriefje10 --;
             }

             else {
                 // gebruiker kan niet pinnen
             }
             System.out.println(briefjekeuze);
         }

         Withdrawaltxt_Label = new JLabel(" Option 3 ");
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje10));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2 -50, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje20));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2 -100, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);
         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje50));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2-150, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         Withdrawaltxt_Label = new JLabel(String.valueOf(aantalBriefje100));
         Withdrawaltxt_Label.setOpaque(true);
         Withdrawaltxt_Label.setBounds(frame.getWidth() / 2 + 200, frame.getHeight() / 2- 200, 300, 50);
         Withdrawaltxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         Withdrawaltxt_Label.setForeground(new Color(192, 27, 28));
         Withdrawaltxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(Withdrawaltxt_Label);

         finalAantalBriefje1 = aantalBriefje10;
         finalAantalBriefje2 = aantalBriefje20;
         finalAantalBriefje5 = aantalBriefje50;
         finalAantalBriefje10 = aantalBriefje100;
         int finalAantalBriefje6 = finalAantalBriefje1;
         int finalAantalBriefje7 = finalAantalBriefje2;
         int finalAantalBriefje8 = finalAantalBriefje5;
         int finalAantalBriefje12 = finalAantalBriefje10;
         loginbutton = new JButton(new AbstractAction("Kies optie ") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch (selectBill_panel,transaction_Panel);
                 transaction_Panel((int) amounts);
                 withdraw((int) amounts, finalAantalBriefje6, finalAantalBriefje7, finalAantalBriefje8, finalAantalBriefje12);
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth()/2 - 400,frame.getHeight() / 2 - 250,300,50);
         selectBill_panel.add(loginbutton); */
     }

     private static int random(int min, int max){
         int random = (int) ((Math.random() * ((max - min) + 1)) + min);
         return random;
     }

     private static int opvragenBriefje10(){
         URL briefje10 = null;

         String aantalbriefjetien = "";

         try {
             briefje10 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D10");
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
         int aantaltien = Integer.parseInt(aantalbriefjetien);
         return aantaltien;
     }

     private static int opvragenBriefje20(){
         URL briefje20 = null;

         String aantalbriefjetwintig = "";

         try {
             briefje20 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D20");
         } catch (MalformedURLException a) {
             a.printStackTrace();
         }
         //10
         try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje20.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 aantalbriefjetwintig += line;
             }

         } catch (Exception E) {
             System.out.println("exceptie e");
         }
         int aantaltwintig = Integer.parseInt(aantalbriefjetwintig);
         return aantaltwintig;
     }

     private static int opvragenBriefje50(){
         URL briefje50 = null;

         String aantalbriefjevijftig= "";

         try {
             briefje50 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D50");
         } catch (MalformedURLException a) {
             a.printStackTrace();
         }
         //10
         try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje50.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 aantalbriefjevijftig += line;
             }

         } catch (Exception E) {
             System.out.println("exceptie e");
         }
         int aantalvijftig = Integer.parseInt(aantalbriefjevijftig);
         return aantalvijftig;
     }

     private static int opvragenBriefje100(){
         URL briefje100 = null;

         String aantalbriefjehonderd= "";

         try {
             briefje100 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D100");
         } catch (MalformedURLException a) {
             a.printStackTrace();
         }
         //10
         try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje100.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 aantalbriefjehonderd += line;
             }

         } catch (Exception E) {
             System.out.println("exceptie e");
         }
         int aantalhonderd = Integer.parseInt(aantalbriefjehonderd);
         return aantalhonderd;
     }

     private static void transaction_Panel(int bedragDb){

         select_Panel.removeAll();
         select_CPanel.removeAll();
         customWithdraw_panel.removeAll();

         String bedragvoorDB=String.valueOf(bedragDb);

         //versturen van de transactie
         URL send = null;
         try {
             send = new URL("http://145.24.222.162/db_connection.php?query=INSERT+INTO+Transactie+(+Kaartnummer%2C+Bedrag%2C+Tijd%2C+Datum)+VALUES+(+\"" + loginkaart + "\"%2C+\""+ bedragvoorDB+"\"%2C+CURRENT_TIME%2C+CURRENT_DATE)");
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(send.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
             }
         } catch (Exception E) {
             System.out.println("exceptie e");
         }


         transaction_Panel.setLayout(null);
         transaction_Panel.setBackground(Color.white);

         JLabel transaction_Label = new JLabel(" Transaction succesful!");
         transaction_Label.setOpaque(true);
         transaction_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2, 325, 50);
         transaction_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 28));
         transaction_Label.setForeground(new Color(19, 84, 39));
         transaction_Label.setBackground(new Color(195, 195, 195));
         transaction_Panel.add(transaction_Label);


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
         backbutton.setBounds(frame.getWidth() / 2 + 700, frame.getHeight() - 60, 100, 50);
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
         homebutton.setBounds(frame.getWidth() / 2 + 500, frame.getHeight() - 60, 100, 50);
         transaction_Panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Switch(main_Panel, login_Panel);
                 login_Panel();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth() / 2 + 600, frame.getHeight() - 60, 100, 50);
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
         button.setBounds(frame.getWidth() / 2 - 90, frame.getHeight() / 2 + 100, 300, 50);
         transaction_Panel.add(button);


         message_Label = new JLabel("");
         message_Label.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 + +175, 300, 25);
         message_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         message_Label.setForeground(Color.BLACK);
         transaction_Panel.add(message_Label);
         frame.setVisible(true);
         frame.setVisible(true);




     }

     /* communicatie met de server */
     private static void loginCommunicatie(String loginkaart, String loginwachtwoord) {
         System.out.println(loginkaart);
         System.out.println(loginwachtwoord);
         String pincode = "1234";
         String pasnummer = "US-SLBA-02042001";
         String response = "";
         String responsePogingen = "";

         URL pogingen = null;
         URL verzenden = null;
         URL url = null;

         String inloggevens = "";
         try {
             url = new URL("http://145.24.222.162/db_connection.php?query=SELECT+*+FROM+Account+WHERE+Pincode+%3D+\"" + loginwachtwoord + "\"+AND+Kaartnummer+%3D+\"" + loginkaart + "\"");
             pogingen = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Pogingen+FROM+Account+WHERE+Kaartnummer+%3D+\"" + loginkaart + "\"");
         } catch (MalformedURLException e) {
             e.printStackTrace();
         }

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(pogingen.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 responsePogingen += line;

             }
             aantal = Integer.parseInt(responsePogingen);

         } catch (Exception E) {
             System.out.println("exceptie e");
         }

         try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
                 response += line;
             }
         } catch (Exception E) {
             System.out.println("exceptie e");
         }
         if (aantal < 3) {
             if (response.equalsIgnoreCase("0 results")) {
                 message_Label.setForeground(Color.red);
                 message_Label.setText("Login failled!");
                 System.out.println("login failled");
                 aantal++;
                 try {
                     System.out.println("");
                     String versturen = "http://145.24.222.162/db_connection.php?query=UPDATE+Account+SET+Pogingen+%3D+" + aantal + "+WHERE+Kaartnummer+%3D+\"" + loginkaart + "\"";
                     url = new URL(versturen);
                     System.out.println(versturen);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                     for (String line; (line = reader.readLine()) != null; ) {
                         System.out.println(line);
                     }
                 } catch (Exception E) {
                     E.printStackTrace();
                 }
                 String resultaatstr = Integer.toString(3 - aantal);

                 message_Label.setText("You have " + resultaatstr + " tries left");
                 message_Label.setBounds(frame.getWidth()/2-50,frame.getHeight()/2+250,200,50);

                 // System.out.println(aantal);
             } else {
                 message_Label.setForeground(Color.GREEN);
                 message_Label.setText("Login succesful!");
                 System.out.println("login succes");
                 Switch(login_Panel, main_Panel);
                 main_Panel();
                 try {
                     System.out.println("");
                     String versturen = "http://145.24.222.162/db_connection.php?query=UPDATE+Account+SET+Pogingen+%3D+" + 0 + "+WHERE+Kaartnummer+%3D+\"" + loginkaart + "\"";
                     url = new URL(versturen);
                     System.out.println(versturen);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 }
                 try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                     for (String line; (line = reader.readLine()) != null; ) {
                         System.out.println(line);
                     }
                 } catch (Exception E) {
                     E.printStackTrace();
                 }
             }


         } else {
             message_Label.setText("Login failled!");
             message_Label.setText("card blocked");
             message_Label.setBounds(frame.getWidth()/2-10,frame.getHeight()/2+250,200,50);
         }


     }

     private static int balansCommunicatie(String loginkaart) {
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
         } catch (Exception E) {
             System.out.println("exceptie e");
         }

         return Integer.parseInt(balans);

     }

     private static void withdraw(int bedrag, int brief10, int brief20, int brief50, int brief100) {
         int balans = balansCommunicatie(loginkaart);
         int nieuwSaldo = 0;

         int bedragDB =0;

         URL url = null;
         URL briefje10 = null;
         URL briefje20 = null;
         URL briefje50 = null;
         URL briefje100 = null;

         URL geld10 = null;
         URL geld20 = null;
         URL geld50 = null;
         URL geld100 = null;

         String aantalbriefjetien = "";
         String aantalbriefjetwintig = "";
         String aantalbriefjevijtig = "";
         String aantalbriefjehonderd = "";
         String updaten;
         System.out.println(amount);

         if (balans >= bedrag) { // kijken of er genoeg balans is
             System.out.println("uw balans is goed");
             try {
                 briefje10 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D10");
                 briefje20 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D20");
                 briefje50 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D50");
                 briefje100 = new URL("http://145.24.222.162/db_connection.php?query=SELECT+Aantal+FROM+%60Biljetten%60+WHERE+Soort+%3D100");
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
             int aantaltien = Integer.parseInt(aantalbriefjetien);
             System.out.println("briefje 10 is uitegelzen ");


             // 20
             try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje20.openStream(), "UTF-8"))) {
                 for (String line; (line = reader.readLine()) != null; ) {
                     System.out.println(line);
                     aantalbriefjetwintig += line;
                 }
             } catch (Exception E) {
                 System.out.println("exceptie e");
             }
             int aantaltwintig = Integer.parseInt(aantalbriefjetwintig);
             System.out.println("briefje 20 is uitegelzen ");
             //50
             try (BufferedReader reader = new BufferedReader(new InputStreamReader(briefje50.openStream(), "UTF-8"))) {
                 for (String line; (line = reader.readLine()) != null; ) {
                     System.out.println(line);
                     aantalbriefjevijtig += line;
                 }
             } catch (Exception E) {
                 System.out.println("exceptie e");
             }
             int aantalvijftig = Integer.parseInt(aantalbriefjevijtig);
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

             if (bedrag == 70) {
                 int check = 0;
                 System.out.println("70 geselecteerd");
                 if (brief10 == 2 && aantaltien - 2 > 0 && aantalvijftig - 1 > 0) {
                     // arduino 2 briefje van 10
                     // arduino 1 briefje van 5
                     System.out.println("we gaan nu verzenden");
                     int a = 1;
                     int tienver = aantaltien - 2;
                     int vijver = aantalvijftig - a;

                     String nieuwaantal10 = String.valueOf(tienver);
                     String nieuwaantal50 = String.valueOf(vijver);
                     check++;

                     try {
                         geld10 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal10 + "\"+WHERE+Soort+%3D+10");
                         geld50 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal50 + "\"+WHERE+Soort+%3D+50");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException b) {
                         b.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld10.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld50.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();

                     }


                 } else if (brief20 == 1 && aantaltwintig - 1 > 0 && aantalvijftig - 1 > 0) {
                     System.out.println("we gaan nu verzenden");
                     int a = 1;
                     int twinver = aantaltwintig - a;
                     int vijver = aantalvijftig - a;

                     String nieuwaantal20 = String.valueOf(twinver);
                     String nieuwaantal50 = String.valueOf(vijver);

                     try {
                         geld20 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal20 + "\"+WHERE+Soort+%3D+20");
                         geld50 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal50 + "\"+WHERE+Soort+%3D+50");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException b) {
                         b.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld20.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld50.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     check++;
                 }
                 if (check > 0) {
                     bedragDB +=70;
                     nieuwSaldo = balans - 70;
                     String saldoDatabalans70 = String.valueOf(nieuwSaldo);
                     stuurDataBase(saldoDatabalans70,"nee",bedragDB);
                 }
                 else if (check == 0){
                     message_Label.setText("Were out of bill \n Please contact Slankbank\n your balance is the same\n Please press any of the button at the bottom right");
                 }
             }

             if (bedrag == 100) {
                 int check = 0;
                 System.out.println("100 geselecteerd");
                 if (brief20 == 5 && aantaltwintig - 5 > 0) {
                     // arduino 2 briefje van 10
                     // arduino 1 briefje van 5
                     System.out.println("we gaan nu verzenden");
                     int a = 5;
                     int twinver = aantaltwintig - a;

                     String nieuwaantal20 = String.valueOf(twinver);

                     check++;

                     try {
                         geld20 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal20 + "\"+WHERE+Soort+%3D+20");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException b) {
                         b.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld20.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                 } else if (brief50 == 2 && aantalvijftig - 2 > 0) {

                     // arduino 2 briefje van 10
                     // arduino 1 briefje van 5
                     System.out.println("we gaan nu verzenden");
                     int a = 2;
                     int vijfer = aantalvijftig - a;
                     System.out.println("hoi");

                     String nieuwaantal50 = String.valueOf(vijfer);
                     check++;
                     try {
                         geld50 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal50 + "\"+WHERE+Soort+%3D+50");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException b) {
                         b.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld50.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }

                 } else if (brief100 == 1 && aantalhonderd - 1 > 0) {
                     // arduino 2 briefje van 10
                     // arduino 1 briefje van 5
                     System.out.println("we gaan nu verzenden");
                     int a = 1;
                     int honver = aantalhonderd - a;
                     check++;
                     String nieuwaantal100 = String.valueOf(honver);

                     try {
                         geld100 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal100 + "\"+WHERE+Soort+%3D+100");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException b) {
                         b.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld100.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                 } else if (brief10 == 1 && aantaltien - 1 > 0 && aantaltwintig - 5 > 0 && aantalhonderd - 1 > 0) {

                     // arduino 2 briefje van 10
                     // arduino 1 briefje van 5
                     System.out.println("we gaan nu verzenden");
                     int a = 5;
                     int b = 1;
                     int tienver = aantaltien - b;
                     int twinver = aantaltwintig - a;
                     int honver = aantalhonderd - b;

                     String nieuwaantal10 = String.valueOf(tienver);
                     String nieuwaantal20 = String.valueOf(twinver);
                     String nieuwaantal100 = String.valueOf(honver);

                     check++;

                     try {
                         geld10 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal10 + "\"+WHERE+Soort+%3D+10");
                         geld20 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal20 + "\"+WHERE+Soort+%3D+20");
                         geld100 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal100 + "\"+WHERE+Soort+%3D+100");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException j) {
                         j.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld10.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld20.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld100.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                 }
                 if (check > 0) {
                     nieuwSaldo = balans - 100;
                     bedragDB +=100;
                     String saldoDatabalans100 = String.valueOf(nieuwSaldo);
                     stuurDataBase(saldoDatabalans100,"nee",bedragDB);
                 }
                 else{
                     message_Label.setText("Were out of bill \n Please contact Slankbank\n your balance is the same\n Please press any of the button at the bottom right");
                 }
             }

             if (bedrag == 150) {
                 int check = 0;
                 // mogelijkheid 1
                 System.out.println("150 geselecteerd");
                 if (brief10 == 3 && aantaltien - 3 > 0 && aantaltwintig - 1 > 0 && aantalvijftig - 2 > 0) {
                     // arduino 2 briefje van 10
                     // arduino 1 briefje van 5
                     int a = 3;
                     int b = 1;
                     int c = 2;
                     int tienver = aantaltien - a;
                     int twinver = aantaltwintig - b;
                     int vijfer = aantalvijftig - c;

                     String nieuwaantal10 = String.valueOf(tienver);
                     String nieuwaantal20 = String.valueOf(twinver);
                     String nieuwaantal50 = String.valueOf(vijfer);
                     System.out.println("interger zijn string ");

                     check++;

                     try {
                         System.out.println("we gaan nu querys versturen");
                         geld10 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal10 + "\"+WHERE+Soort+%3D+10");
                         geld20 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal20 + "\"+WHERE+Soort+%3D+20");
                         geld50 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal50 + "\"+WHERE+Soort+%3D+50");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException p) {
                         p.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld10.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld20.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld50.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                 }
                 // mogelijkheid 2
                 else if (brief50 == 2 && aantaltien - 1 > 0 && aantaltwintig - 2 > 0 && aantalvijftig - 2 > 0) {
                     // arduino 2 briefje van 10
                     // arduino 1 briefje van 5
                     System.out.println("we gaan nu verzenden");
                     int a = 1;
                     int b = 2;
                     int tienver = aantaltien - a;
                     int twinver = aantaltwintig - b;
                     int vijver = aantalvijftig - b;

                     String nieuwaantal10 = String.valueOf(tienver);
                     String nieuwaantal20 = String.valueOf(twinver);
                     String nieuwaantal50 = String.valueOf(vijver);
                     check++;
                     try {
                         geld10 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal10 + "\"+WHERE+Soort+%3D+10");
                         geld20 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal20 + "\"+WHERE+Soort+%3D+20");
                         geld50 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal50 + "\"+WHERE+Soort+%3D+50");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException L) {
                         L.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld10.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld20.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld50.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                 }
                 // mogelijkheid 3
                 else if (brief20 == 2 && aantaltien - 1 > 0 && aantaltwintig - 2 > 0 && aantalhonderd - 1 > 0) {
                     int a = 1;
                     int b = 2;
                     int tiever = aantaltien - a;
                     int twiever = aantaltwintig - b;
                     int honver = aantalhonderd - a;
                     check++;
                     String nieuwaantal10 = String.valueOf(tiever);
                     String nieuwaantal20 = String.valueOf(twiever);
                     String nieuwaantal100 = String.valueOf(honver);

                     try {
                         geld10 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal10 + "\"+WHERE+Soort+%3D+10");
                         geld20 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal20 + "\"+WHERE+Soort+%3D+20");
                         geld100 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal100 + "\"+WHERE+Soort+%3D+100");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException q) {
                         q.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld10.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld20.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld100.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }

                 }
                 // mogelijkheid4
                 else if (brief100 == 1 && aantalhonderd - 1 > 0 && aantalvijftig - 1 > 0) {
                     int a = 1;
                     int hover = aantalhonderd - a;
                     int vijver = aantalvijftig - a;

                     String nieuwaantal100 = String.valueOf(hover);
                     String nieuwaantal50 = String.valueOf(vijver);
                     check++;
                     try {
                         geld100 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal100 + "\"+WHERE+Soort+%3D+100");
                         geld50 = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Biljetten+SET+Aantal+%3D+\"" + nieuwaantal50 + "\"+WHERE+Soort+%3D+50");
                         System.out.println("1briefje minder");

                     } catch (MalformedURLException b) {
                         b.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld100.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }
                     try (BufferedReader reader = new BufferedReader(new InputStreamReader(geld50.openStream(), "UTF-8"))) {
                         for (String line; (line = reader.readLine()) != null; ) {
                             System.out.println(line);
                         }
                     } catch (Exception E) {
                         E.printStackTrace();
                     }

                 }
                 if (check > 0) {
                     nieuwSaldo = balans - 150;
                     bedragDB +=150;
                     String saldoDatabalans150 = String.valueOf(nieuwSaldo);
                     stuurDataBase(saldoDatabalans150,"nee",bedragDB);
                 } else {
                     message_Label.setText("Were out of bill \n Please contact Slankbank\n your balance is the same\n Please press any of the button at the bottom right");
                 }
             }


         } else if (balans <= bedrag) {
             message_Label.setText("Balance to low ");
             message_Label.setForeground(Color.red);
             message_Label.setBounds(frame.getWidth()/2,frame.getHeight()/2+300,250,100);
         }

     }

     private static void stuurDataBase(String nieuwsaldo, String welkePaneel, int bedragDB) {
         URL url = null;
         try {
             url = new URL("http://145.24.222.162/db_connection.php?query=UPDATE+Account+SET+Balans+%3D+\"" + nieuwsaldo + "\"WHERE+Kaartnummer+%3D+\"" + loginkaart + "\"");
         } catch (MalformedURLException a) {
             a.printStackTrace();
         }
         try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
             for (String line; (line = reader.readLine()) != null; ) {
                 System.out.println(line);
             }

         } catch (Exception E) {
             E.printStackTrace();

         }
         if (welkePaneel == "ja") {
             Switch(select_CPanel, transaction_Panel);
             transaction_Panel(bedragDB);
         } else {
             Switch(select_Panel, transaction_Panel);
             transaction_Panel(bedragDB);
         }
     }



     /*Usable Methods*/
     public static void Switch(JPanel from, JPanel to)
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




 }



