package Bank;

public class ServerCommunication {
    private static String user_ID = "User";
    private static String pin_Code = "1234";
    private static boolean is_Inserted;
    private static boolean is_Blocked = false;
    private static double balance = 200;
    public static boolean is_Succes = false;
    public static double withdrawal_Amount = 0;

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
            GUI.Abrupted();
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

    public static void get10(int amount)
    {
        withdrawal_Amount += 10 * amount;

        //TODO laat de arduino $10 biljetten uitspugen
    }

    public static void get20(int amount)
    {
        withdrawal_Amount += 20 * amount;

        //TODO laat de arduino $20 biljetten uitspugen
    }
    public static void get50(int amount)
    {
        withdrawal_Amount += 20 * amount;

        //TODO laat de arduino $50 biljetten uitspugen
    }
}
