import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class keypad {

        // maakt een "pagina " aan
        private static JPanel start_Panel = new JPanel();
        private static JPanel login_Panel = new JPanel();


        private static JFrame frame = new JFrame();
        private static JPasswordField pin_Field; // maakt een wachtwoord vlak aan
        private static JLabel message_Label; //
        private  static String pincodecijfer;


        private static JButton button; // maakt een knop aan

        private static JButton backbutton;


        private static int invoerpincode;


        public static void main(String[] args) {
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

            button = new JButton(/*"Login"*/ new AbstractAction("Exit") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            button.setBounds(0, 0, 80, 25);
            start_Panel.add(button);

            backbutton = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Switch(start_Panel,login_Panel);
                    login_Panel();

                }
            });
            backbutton.setBounds(frame.getWidth()/2,frame.getHeight()/2,100,50);
            start_Panel.add(backbutton);


        }



        /*Login menu*/
        private static void login_Panel() {
            login_Panel.removeAll();
            login_Panel.setSize(frame.getSize());
            login_Panel.setLayout(null);
            login_Panel.setBackground(Color.white);

            keypad();




            //Pincode text
            JLabel pin_Label = new JLabel("Password");
            pin_Label.setBounds(frame.getWidth() / 2 - 200, frame.getHeight() / 2 + 30, 200, 25);
            pin_Label.setForeground(new Color(192, 27, 28));
            pin_Label.setFont(new Font("Didact Gothic", Font.PLAIN, 22));
            login_Panel.add(pin_Label);

            pin_Field = new JPasswordField();
            pin_Field.setBounds(frame.getWidth() / 2, frame.getHeight() / 2 + 30, 200, 30);
            pin_Field.setFont(new Font("Arial", Font.PLAIN, 18));
            login_Panel.add(pin_Field);



    /*Login button*/
            button = new JButton(/*"Login"*/ new AbstractAction("Login") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //FIXME password = pin_Field.getPassword();
                    String loginwachtwoord = pin_Field.getText();
                    // oproepen de functie om te kunnen praten met de database

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

    public static void keypad() {
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
                                        invoerpincode= Integer.parseInt(serialData);

                                    }
                                }
        );
    }
    public static void Switch(JPanel from, JPanel to)
    {
        from.removeAll();
        frame.remove(from);
        frame.add(to);
        to.revalidate();
        to.repaint();
        return;
    }
}