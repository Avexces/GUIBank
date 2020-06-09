package Bank;

public class ServerCommunication {
    private static String user_ID = "User";
    private static String pin_Code = "1234";
    private static boolean is_Inserted;
    private static boolean is_Blocked = false;
    private static double balance = 200;
    public static boolean is_Succes = false;
    public static double withdrawal_Amount = 0;

    public static int amount20 = 20;
    public static int amount50 = 50;
    public static int amount100 = 1000;

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

    public static void Withdraw(int i, int b20, int b50, int b100) {
        i = (int) i;
        if(balance < i)
        {
            //returns there is not enough money and nothing went off your account
            is_Succes = false;
            return;
        }
        else if(balance >= i)
        {
            //returns there is enough money and says you can withdraw it form your account
            getBriefje20(b20);
            getBriefje50(b50);
            getBriefje100(b100);
            balance -= i;
            System.out.println(balance);
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

    private static void getBriefje20(int a)
    {
        System.out.println("Spuig uit da geld");
        amount20 -= a;
    }

    private static  void getBriefje50(int a)
    {
        System.out.println("Spuig uit da geld");
        amount50 -= a;
    }

    private static void getBriefje100(int a)
    {
        System.out.println("Spuig uit da geld");
        amount100 -= a;
    }

    public static int getBalanceInt() {
        return (int) balance;
    }
}
