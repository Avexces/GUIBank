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
            url = new URL("http://145.24.222.162/db_connection.php?query=SELECT%20*%20FROM%20Account");
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