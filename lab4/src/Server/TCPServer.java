package Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

//Comenzile obligatorii care trebuie să le implementeze serverul:
//
//        /help - răspunde cu o listă a comenzilor suportate și o descriere a fiecărei comenzi;
//        /hello Text - raspunde cu textul care a fost expediat ca paremetru<
//        alte 3 comenzi cu funcțional diferit (e.g. timpul curent, generator de cifre, flip the coin etc)
//Dacă serverul primește o comandă invalidă - se răspunde cu un mesaj informativ.
public class TCPServer {
    public static void main(String argv[]) throws Exception{
        ServerSocket first_socket = new ServerSocket(6789);
        while(true)
        {
            Socket connectionSocket = first_socket.accept();
            BufferedReader messagefromclient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            String clientSentence = messagefromclient.readLine();
            System.out.println("Received: " + clientSentence);
        }
    }
}
