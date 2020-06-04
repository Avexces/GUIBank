package Bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

    public static void main(String[] args) {
                Connection con = null;
                String dbName = "Slankbank";
                String Username = "admin";
                String Password = "SlankBank2020!";


                try {
                    System.out.println(" we gaan nu connecten met de database");
                    con = DriverManager.
                            getConnection("jdbc:mysql://145.24.222.162:3306/" +dbName+ "?useSSL=false", Username, Password);

                    System.out.println("Connection is successful !!!!!");

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
