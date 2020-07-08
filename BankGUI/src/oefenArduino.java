import com.fazecast.jSerialComm.*;

import org.w3c.dom.ls.LSOutput;
import javax.swing.*;

public class oefenArduino {
        public static void main( String [] args) {
            test();
        }
            public static void test(){
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
                                        }
                                    }
            );
        }
    }