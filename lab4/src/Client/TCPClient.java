package Client;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


class TCPClient {
    public static void main(String argv[]) throws Exception{
        Socket client_socket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(client_socket.getOutputStream());

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String sentence = inFromUser.readLine();
        outToServer.writeBytes(sentence + '\n');
        client_socket.close();
    }
}
