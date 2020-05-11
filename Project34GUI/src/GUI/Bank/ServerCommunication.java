package Bank;

import java.sql.Connection;
import java.sql.DriverManager;

public class ServerCommunication {
    private static String user_ID = "User";
    private static String pin_Code = "1234";
    private static boolean is_Inserted;
    private static boolean is_Blocked = false;
    private static double balance = 200;
    public static boolean is_Succes = false;
    public static double withdrawal_Amount = 0;
    public static int briefjes50 = 0;
    public static int briefjes100 = 0;
    public static int briefjes20 = 0;

    public static void main(String[] argv) throws Exception {
        String driverName = "org.gjt.mm.mysql.Driver";
        Class.forName(driverName);

        String serverName = "145.24.222.157";
        String mydatabase = "mydatabase";
        String url = "jdbc:mysql :// " + serverName + "/" + mydatabase;

        String username = "ubuntu-0985583";
        String password = "7n95W2";
        Connection connection = DriverManager.getConnection(url, username, password);
    }

    public static String getPincode()
    {
        return user_ID;
    }

    public static String get_user_ID()
    {
        return user_ID;
    }

    public static void setFalse()
    {
        is_Inserted = false;
        bool();
        return;
    }
    public static void seatTrue()
    {
        is_Inserted = true;
        bool();
        return;
    }
    public static boolean getBlocked()
    {
        return is_Blocked;
    }

    public static void bool()
    {
        if(!is_Inserted)
        {
            BANK.GUI.Abrupted();
        }
        return;
    }

    public static String getBalance() {
        return" $: "+Double.toString(balance);
    }

    public static boolean TransactionStatus()
    {
        return is_Succes;
    }

    public static void Withdraw(double i) {
        i = withdrawal_Amount;
        if(balance < withdrawal_Amount)
        {
            //returns there is not enough money and nothing went off your account
            is_Succes = false;
            return;
        }
        else if(balance >= withdrawal_Amount)
        {
            //returns there is enough money and says you can withdraw it form your account
            is_Succes = true;
            return;
        }
        else
        {
            //returns something went wrong and nothing went off your account
            is_Succes = false;
            return;
        }


    }

    public static int setBriefjes20(int i)
    {
        int aantal = 0;

        briefjes20 += i;
        aantal = briefjes20;

        return aantal;
    }

    public static int setBriefjes50(int i)
    {
        int aantal = 0;

        briefjes50 += i;

        aantal = briefjes50;

        return aantal;
    }

    public static int setBriefjes100(int i)
    {
        int aantal = 0;

        briefjes100 += i;

        aantal = briefjes100;

        return aantal;
    }
}
