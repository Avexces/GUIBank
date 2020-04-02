package GUI.Bank;

public class ServerCommunication {
    private static String user_ID = "User";
    private static String pin_Code = "1234";
    private static boolean is_Inserted;
    private static boolean is_Blocked = false;
    private static double balance = 200;
    public static boolean is_Succes = true;

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
    public static void setTrue()
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
            App.Abrupted();
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
}
