import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Main {

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://evil-legacy-service.herokuapp.com/api/v101/orders/?start=2000-02-22&end=2010-02-24");
        URLConnection connection = url.openConnection();
        connection.addRequestProperty("x-api-key","55193451-1409-4729-9cd4-7c65d63b8e76");
        connection.addRequestProperty("ACCEPT","text/csv");
        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        BufferedReader buffer = null;
        String line = "";
        String csvSplitBy = ",";

        try {

            buffer = new BufferedReader(input);
            while ((line = buffer.readLine()) != null) {
                String[] room = line.split(csvSplitBy);
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}