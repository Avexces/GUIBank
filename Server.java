package Bank;

import java.sql.*;


public class Server {
    public static void main(String[] args) {
        try {
            String host = "http://localhost:60000/phpmyadmin/sql.php?server=1&db=Slankbank&table=Account&pos=0&token=2f4accdd229fcaa18b559d7af44c1a1a";
            String Username = "root";
            String Password = "SlankBank2020!";
            Connection myConn = DriverManager.getConnection(host,Username,Password);

            System.out.println(" er is verbinding met de server");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
