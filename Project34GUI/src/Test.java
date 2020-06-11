import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        connect();
    }

    protected static void connect() {
        URL url = null;
        try {
            url = new URL("http://145.24.222.162/db_connection.php?query==UPDATE+%60Account%60+SET+%60Balans%60+%3D+%27100%27+WHERE+%60Account%60.%60Kaartnummer%60+%3D+%27US-SLBA-02042001%27%3B");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null; ) {
                System.out.println(line);
            }
        }
        catch (Exception E){
            System.out.println("exceptio e is hier gaande");
        }
    }
}