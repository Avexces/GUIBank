 /*
  *
  * Authors: Mischa Quist & Annelot Janssen
  * Group: 5
  * Bank:  SlankBank
  *
  *
  */

 /* libraries toevoegen */
 import com.fazecast.jSerialComm.SerialPort;
 import com.fazecast.jSerialComm.SerialPortDataListener;
 import com.fazecast.jSerialComm.SerialPortEvent;

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.Font; /* library met font */
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.io.BufferedReader;
 import java.io.*;
 import java.util.*;
 import java.util.concurrent.TimeUnit;

 class ScreenElement {
     public int x, y, w, h, fs, data;
     public String Value, TriggerButton, EType;
     public Runnable cb;

     private JButton createButton() {
         JButton button = new JButton(new AbstractAction(Value) {
             @Override
             public void actionPerformed(ActionEvent e) { cb.run(); }
         });

         button.setFont(new Font("Didact Gothic", Font.PLAIN, fs));
         button.setBackground(Color.magenta.darker().darker().darker().darker());
         button.setBounds(x,y,w,h);
         return button;
     }

     private JLabel createText() {
         JLabel label = new JLabel(Value);
         label.setBounds(x,y,w,h);
         label.setFont(new Font("TimesRoman", Font.PLAIN, fs));
         label.setForeground(Color.black);
         return label;
     }

     public ScreenElement(int x, int y, int w, int h, int fs, String value, String triggerButton, String Etype, Runnable cb) {
         this.x = x;
         this.y = y;
         this.w = w;
         this.h = h;
         this.data = Integer.MAX_VALUE;
         this.fs = fs;
         this.Value = value;
         this.TriggerButton = triggerButton;
         this.EType = Etype;
         this.cb = cb;
     }

     public Component getElementObject() {
         System.out.println(EType);
         switch(EType){
             case "Button":
                 return createButton();
             case "Text":
                 return createText();
             case "Input":
                 break;
         }

         return null; //lmao yeet
     }
 }


 class Screen {
     public ArrayList<ScreenElement> elements;
     public JPanel Panel;
     public ArrayList<Integer> screenData;
     public String name;

     public Screen(JPanel panel, String name) {
         Panel = panel;
         this.name = name;
         elements = new ArrayList<ScreenElement>();
     }

     public void AddElement(ScreenElement element) {
         elements.add(element);
     }

     public void Render() {
         Panel.removeAll();
         for(ScreenElement e : elements) {
             Panel.add(e.getElementObject());
         }
         Panel.setLayout(null);
     }

     //dont worry about this one chief
     public void getElementData() {
         for(ScreenElement e : elements) {
             if(e.data != Integer.MAX_VALUE) {
                 screenData.add(e.data);
                 e.data = Integer.MAX_VALUE;
             }
         }
     }

     public void HandleInput(String input) {
         for(ScreenElement e : elements) {
             if(e.TriggerButton == input) {
                 e.cb.run();
                 getElementData();
             }
         }
     }
 }

 public class GUI {
     // maakt een "pagina " aan
     private static JPanel start_Panel = new JPanel();
     private static JPanel login_Panel = new JPanel();
     private static JPanel main_Panel = new JPanel();
     private static JPanel select_Panel = new JPanel();
     private static JPanel select_CPanel = new JPanel();
     private static JPanel transaction_Panel = new JPanel();
     private static JPanel customWithdraw_panel = new JPanel();
     private static JPanel selectBill_panel = new JPanel();

     private static JFrame frame = new JFrame();


     private static JTextField custom_amount;
     private static JLabel message_Label;
     private static JTextField balans;
     private static int amount = 0;
     private static int aantal = 0;
     private static JLabel opties;

     private static JButton button; // maakt een knop aan
     private static JButton withdrawbutton;
     private static JButton homebutton;
     private static JButton loginbutton;
     private static JButton backbutton;

     private static String attemptedPass = "";
     private static String loginkaart;
     private static String doorStuurbedrag;
     private static int briefjekeuze[][];
     private static double amounts;
     private String pincode;
     private static Screen currentScreen;


     public static void main(String[] args) {
         int[][] otherVar = new int[0][0];
         briefjekeuze = otherVar;
         start();
     }

     public static void setScreen(Screen screen) {
//         frame.remove(currentScreen.Panel);
         currentScreen = screen;
         currentScreen.Render();
         frame.getContentPane().removeAll();
         frame.add(currentScreen.Panel);
         frame.pack();
         currentScreen.Panel.revalidate();
         currentScreen.Panel.repaint();
     }

     /*Welkom menu*/
     public static void start() {
         frame = new JFrame(" main");
         // frame.setLayout(new FlowLayout());
         frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         frame.setUndecorated(true);
         frame.setVisible(true);


         int centerX = frame.getWidth() / 2;
         int centerY = frame.getHeight() / 2;


         start_Panel.setBackground(Color.white);
         /* BEGIN TEMPLATE */
         Screen homeScreen = new Screen(start_Panel, "start");

         ScreenElement welcomeText = new ScreenElement(centerX+550,  centerY+200, 600, 80, 60, "Welcome to SlankBank", "l", "Text", () -> {
             return;
         });
         ScreenElement scanText = new ScreenElement(centerX + 800, centerY + 600, 200, 80, 20, "please scan card", "l", "Text",  () -> {
             return;
         });
         ScreenElement exitButton = new ScreenElement(frame.getWidth() - 100, frame.getHeight() - 100, 80, 25, 10, "exit", "A", "Button", () -> {
             System.exit(0);
         });
            arduino();

         homeScreen.AddElement(welcomeText);
         homeScreen.AddElement(scanText);
         homeScreen.AddElement(exitButton);

         setScreen(homeScreen);
         /* END TEMPLATE */


     }


     private static boolean handleScreenResponse(String pressValue) {
         return true;
     }

     private static void login_Panel(){

         login_Panel.setBackground(Color.white);
         int centerX = frame.getWidth() / 2;
         int centerY = frame.getHeight() / 2;
         Screen loginScreen = new Screen(login_Panel, "login");

         /* BEGIN TEMPLETE */
         ScreenElement pincodeVraag = new ScreenElement(centerX-75  ,centerY-100, 250, 80, 30, "Pincode please ", "l", "Text", () -> {
             return;
            });
             ScreenElement terugknop = new ScreenElement(centerX-75  ,centerY-100, 250, 80, 30, "back ", "l", "Text", () -> {
             start();
         });

         arduino();

         loginScreen.AddElement(pincodeVraag);
        
         loginScreen.AddElement(terugknow);

         setScreen(loginScreen);

        


     }



     private static void arduino() {

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
                                         byte[] newData = new byte[comPort.bytesAvailable()]; //receive incoming bytes
                                         comPort.readBytes(newData, newData.length); //read incoming bytes
                                         String serialData = new String(newData); //convert bytes to string
                                         System.out.println(currentScreen.name);
                                             if(currentScreen.name== "start"){
                                                 System.out.println("hell0");
                                                 try {Thread.sleep(6718);} catch(Exception e){}
                                                     System.out.println("hello1");
                                                     Switch(start_Panel, login_Panel);
                                                     System.out.println("hello2");
                                                     login_Panel();

                                         }
                                         if(currentScreen.name == "login") {
                                             System.out.println("ahbkfbaf");
                                             if (serialData.length() == 4) {
                                                 loginCommunicatie("US-SLBA-02042001", attemptedPass);
                                             } else {
                                                 System.out.println("afnjnewjorw");
                                                 System.out.println(attemptedPass.length());
                                                 ScreenElement star = new ScreenElement(0,0,10,10, 10, "*", "l", "Text", () -> { return; }); //finish
                                                 currentScreen.AddElement(star);
                                                 currentScreen.Render();
                                                 currentScreen.Panel.revalidate();
                                                 currentScreen.Panel.repaint();
                                                 attemptedPass += serialData;
                                             }
                                         }
                                         currentScreen.HandleInput(serialData);
                                     }
                                 }
         );
     }


     /*Hoofdmenu voor gebruikers*/
     private static void main_Panel() {
         main_Panel.setLayout(null);
         main_Panel.setBackground(Color.white);

         int centerX = frame.getWidth() / 2;
         int centerY = frame.getHeight() / 2;

         Screen mainScreen = new Screen(main_Panel, "main");

         /* BEGIN TEMPLET */
         ScreenElement exitButton = new ScreenElement(centerX - 100, centerY - 400, 1000, 80, 60, "Back", "1", "button", () -> {
             start();
         });

         ScreenElement homeButton = new ScreenElement(centerX - 100, centerY - 400, 1000, 80, 60, "Homebutton", "2", "Text", () -> {
             return;
         });
         ScreenElement balans = new ScreenElement(centerX + 800, centerY + 600, 200, 80, 20, "Balance", "l", "Text", () -> {
             return;
         });
         ScreenElement scanText = new ScreenElement(centerX + 800, centerY + 600, 200, 80, 20, "Quick withdraw", "l", "Text", () -> {
             return;
         });
         ScreenElement seventy = new ScreenElement(centerX - 100, centerY - 400, 1000, 80, 60, "$70", "A", "button", () -> {

         });
         ScreenElement hundred = new ScreenElement(centerX - 100, centerY - 400, 1000, 80, 60, "100", "B", "button", () -> {
             start();
         });
         ScreenElement hundredfifthy = new ScreenElement(centerX - 100, centerY - 400, 1000, 80, 60, "150", "C", "button", () -> {
             start();
         });
         ScreenElement adjustedAmount = new ScreenElement(centerX - 100, centerY - 400, 1000, 80, 60, "Adjusted amount", "D", "button", () -> {
             start();
         });

         JLabel balance_Label = new JLabel("$:  " + balansCommunicatie(loginkaart));
         balance_Label.setOpaque(true);
         balance_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 50, 300, 50);
         balance_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balance_Label.setForeground(Color.lightGray);
         balance_Label.setBackground(Color.darkGray.darker());
         main_Panel.add(balance_Label);
     }



     // menu dat recht onder staat
     // terugknop
         /*
         backbutton = new JButton(new AbstractAction("Back") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Button Clicked, RETREAT!!");
                 start();
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
                 start();
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
     */

     //menu om je briefjes te kiezen
     private static void Select_Bills(int selectedmoney) {
         select_Panel.setLayout(null);
         select_Panel.setBackground(Color.white);

         int centerX = frame.getWidth() / 2;
         int centerY = frame.getHeight() / 2;

         ScreenElement exitButton = new ScreenElement(centerX - 100, centerY - 400, 1000, 80, 60, "Back", "1", "button", () -> {
             start();
         });

         ScreenElement homeButton = new ScreenElement(centerX + 500, centerY - 400, 1000, 80, 60, "Home", "2", "button", () -> {
             return;
         });


         loginbutton = new JButton(new AbstractAction("Login") {
             @Override
             public void actionPerformed(ActionEvent e) {
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth() / 2 + 600, frame.getHeight() - 60, 100, 50);
         select_Panel.add(loginbutton);

         ScreenElement w100with102 = new ScreenElement(centerX - 100,centerY-100,300,50,18,"$: " + selectedmoney,"2", "Text", () -> {    return; });
         ScreenElement w100with1022 = new ScreenElement(centerX - 100,centerY-50,300,50,18," Select option: ","2", "Text", () -> {    return; });


         select_Panel.add(optiontxt_Label);
         //Selecteer veste bedragen
         if (selectedmoney == 70) {
             ScreenElement w70with10 = new ScreenElement(centerX -250,centerY,300,50,18," 10$ Note       Number: 2","2", "Text", () -> {    return; });
             ScreenElement w70with20 = new ScreenElement(centerX -250,centerY +50,300,50,18," 20$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w70with50 = new ScreenElement(centerX -250,centerY+ 100,300,50,18," 50$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w70with100 = new ScreenElement(centerX -250,centerY+150,300,50,18," 100$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w70OptionButton = new ScreenElement(centerX -250,centerY +200,300,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(70, 2, 0, 1, 0); });
             //TODO weergeef 50 en 20 Notes
    
             ScreenElement w70with102 = new ScreenElement(centerX -250+ 50,centerY,300,50,18," 10$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w70with202 = new ScreenElement(centerX -250+ 50,centerY +50,300,50,18," 20$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w70with502 = new ScreenElement(centerX -250+ 50,centerY+ 100,300,50,18," 50$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w70with1002 = new ScreenElement(centerX -250+ 50,centerY+150,300,50,18," 100$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w70OptionButton2 = new ScreenElement(centerX -250,centerY +200,300,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(70, 0, 1, 1, 0); });
             frame.setVisible(true);
         }
         // 100 dollaar pinnen
         else if (selectedmoney == 100) {

            ScreenElement w100with10a2 = new ScreenElement(centerX - 425,centerY,300,50,18," 10$ Note       Number: 0","2", "Text", () -> {    return; });
            ScreenElement w100with202 = new ScreenElement(centerX - 425,centerY +50,250,50,18," 20$ Note       Number: 5","2", "Text", () -> {    return; });
            ScreenElement w100with502 = new ScreenElement(centerX - 425,centerY+ 100,250,50,18," 50$ Note       Number: 0","2", "Text", () -> {    return; });
            ScreenElement w100with1002 = new ScreenElement(centerX  - 425,centerY+150,250,50,18," 100$ Note       Number: 0","2", "Text", () -> {    return; });
            ScreenElement w100OptionButton2 = new ScreenElement(centerX  - 425,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(100, 0, 5, 0, 0); });
            
             //mogelijkheid 2
             ScreenElement w100with101 = new ScreenElement(centerX - 175,centerY,300,50,18," 10$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with201 = new ScreenElement(centerX - 175,centerY +50,250,50,18," 20$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with501 = new ScreenElement(centerX - 175,centerY+ 100,250,50,18," 50$ Note       Number: 2","2", "Text", () -> {    return; });
             ScreenElement w100with1001 = new ScreenElement(centerX  - 175,centerY+150,250,50,18," 100$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100OptionButton1 = new ScreenElement(centerX  - 175,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(100, 0, 0, 2, 0); });
        

             //mogelijkheid 3
             ScreenElement w100with103 = new ScreenElement(centerX +75,centerY,300,50,18," 10$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with203 = new ScreenElement(centerX +75,centerY +50,250,50,18," 20$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with503 = new ScreenElement(centerX +75,centerY+ 100,250,50,18," 50$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with1003 = new ScreenElement(centerX  +75,centerY+150,250,50,18," 100$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100OptionButto3 = new ScreenElement(centerX  +75,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(100, 0, 0, 0, 1); });

             //mogelijkheid 4
             ScreenElement w100with104 = new ScreenElement(centerX +325,centerY,300,50,18," 10$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100with204 = new ScreenElement(centerX +325,centerY +50,250,50,18," 20$ Note       Number: 2","2", "Text", () -> {    return; });
             ScreenElement w100with504 = new ScreenElement(centerX +325,centerY+ 100,250,50,18," 50$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100with1004 = new ScreenElement(centerX  +325,centerY+150,250,50,18," 100$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100OptionButton4 = new ScreenElement(centerX  +325,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(100, 1, 2, 1, 0); });


             frame.setVisible(true);
         }
         // 150 dollaar pinen
         else if (selectedmoney == 150) {
             
             //mogelijkheid 1
             ScreenElement w100with104 = new ScreenElement(centerX - 425,centerY,300,50,18," 10$ Note       Number: 3","2", "Text", () -> {    return; });
             ScreenElement w100with204 = new ScreenElement(centerX - 425,centerY +50,250,50,18," 20$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100with504 = new ScreenElement(centerX - 425,centerY+ 100,250,50,18," 50$ Note       Number: 2","2", "Text", () -> {    return; });
             ScreenElement w100with1004 = new ScreenElement(centerX  - 425,centerY+150,250,50,18," 100$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100OptionButton4 = new ScreenElement(centerX  - 425,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(150, 3, 1, 2, 0); });

             // mogelijkheid 2
             ScreenElement w100with103 = new ScreenElement(centerX - 175,centerY,300,50,18," 10$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100with203 = new ScreenElement(centerX - 175,centerY +50,250,50,18," 20$ Note       Number: 2","2", "Text", () -> {    return; });
             ScreenElement w100with503 = new ScreenElement(centerX - 175,centerY+ 100,250,50,18," 50$ Note       Number: 2","2", "Text", () -> {    return; });
             ScreenElement w100with1003 = new ScreenElement(centerX  - 175,centerY+150,250,50,18," 100$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100OptionButton3 = new ScreenElement(centerX  - 175,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(150, 1, 2, 2, 0); });
            
             //mogelijkheid 3
             ScreenElement w100with1023 = new ScreenElement(centerX +75,centerY,300,50,18," 10$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100with2023 = new ScreenElement(centerX +75,centerY +50,250,50,18," 20$ Note       Number: 2","2", "Text", () -> {    return; });
             ScreenElement w100with5023 = new ScreenElement(centerX +75,centerY+ 100,250,50,18," 50$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with10023 = new ScreenElement(centerX  +75,centerY+150,250,50,18," 100$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100OptionButton23 = new ScreenElement(centerX  +75,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(150, 1, 2, 0, 1); });
             
             //mogelijkheid 4
             ScreenElement w100with1033 = new ScreenElement(centerX + 325,centerY,300,50,18," 10$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with2033 = new ScreenElement(centerX + 325,centerY +50,250,50,18," 20$ Note       Number: 0","2", "Text", () -> {    return; });
             ScreenElement w100with5033 = new ScreenElement(centerX + 325,centerY+ 100,250,50,18," 50$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100with10033 = new ScreenElement(centerX  + 325,centerY+150,250,50,18," 100$ Note       Number: 1","2", "Text", () -> {    return; });
             ScreenElement w100OptionButton33 = new ScreenElement(centerX  + 325,centerY +200,250,50,18," Choose option ","2", "Button", () -> {    System.out.println("Amount option chosen"); withdraw(150, 0, 0, 1, 1); });
             
             message_Label = new JLabel("");
             message_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 + 400, 300, 25);
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

         ScreenElement backButton = new ScreenElement(centerX +700, centerY - 60, 100, 50, 18, "Back", "1", "button", () -> {
            main_Panel();
                 Switch(select_CPanel, main_Panel);
        });

        ScreenElement homeButton = new ScreenElement(centerX +500, centerY - 60, 100, 50, 18, "Home", "1", "button", () -> {
            start();
        });
         

         //Selecteer een bedrag
         ScreenElement txt = new ScreenElement(centerX -75, centerY - 70, 500, 25, 22, "Enter your amount: ", "1", "text", () -> {return;
        });

         custom_amount = new JTextField();
         custom_amount.setBounds(frame.getWidth() / 2 - 130, frame.getHeight() / 2, 300, 50);
         custom_amount.setFont(new Font(" Arial", Font.PLAIN, 18));
         custom_amount.setForeground(Color.magenta.darker().darker().darker().darker());
         custom_amount.setOpaque(true);
         select_CPanel.add(custom_amount);

         ScreenElement abc = new ScreenElement(centerX -100, centerY + 70, 250, 25, 22, "Select bills", "1", "button", () -> {doorStuurbedrag = custom_amount.getText();
            amounts = Double.parseDouble(custom_amount.getText());
            Switch(select_CPanel, selectBill_panel);
            selectBill_panel(amounts);
         });

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
         backbutton.setBounds(frame.getWidth() / 2 + 700, frame.getHeight() - 60, 100, 50);
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
         homebutton.setBounds(frame.getWidth() / 2 + 500, frame.getHeight() - 60, 100, 50);
         selectBill_panel.add(homebutton);

         loginbutton = new JButton(new AbstractAction("login") {
             @Override
             public void actionPerformed(ActionEvent e) {
                 start();
             }
         });
         loginbutton.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         loginbutton.setBackground(Color.magenta.darker().darker().darker().darker());
         loginbutton.setBounds(frame.getWidth() / 2 + 600, frame.getHeight() - 60, 100, 50);
         selectBill_panel.add(loginbutton);

         System.out.println(tempBedrag);

         JLabel balancetxt_Label = new JLabel(" Chosen amount: ");
         balancetxt_Label.setOpaque(true);
         balancetxt_Label.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 100, 300, 50);
         balancetxt_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 18));
         balancetxt_Label.setForeground(new Color(192, 27, 28));
         balancetxt_Label.setBackground(new Color(241, 227, 12));
         selectBill_panel.add(balancetxt_Label);

         JLabel balance_Label = new JLabel("$:  " + tempBedrag);
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

         while (tempBedrag > 0) {
             if (aantalBriefje10 > 0 && tempBedrag >= 10) {
                 tempBedrag -= 10;
//                 briefjekeuze[1][1] ++;//173
