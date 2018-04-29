package Client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPClient {

    BufferedReader in;
    PrintWriter out;

    public static void main(String[] args) throws Exception {
        TCPClient client = new TCPClient();
        client.run();
    }

    private void run() throws IOException {

        String serverAddress = "127.0.0.1";
        Socket socket = new Socket(serverAddress, 6788);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("Comenzi")) {
                System.out.println("Comanda: ");
                out.println(getClientInput());
            } else if (line.startsWith("Rezultatul serverului")) {
                String answer = in.readLine();
                System.out.println(line + ": " + answer);
                if (answer.equals("Exit")) {
                    System.out.println("Sesiunea inchisa");
                    break;
                } else {
                    System.out.println("Comanda: ");
                    out.println(getClientInput());
                }
            }
        }
    }

    private String getClientInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

}
