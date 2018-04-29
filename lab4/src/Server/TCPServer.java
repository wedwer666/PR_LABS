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
  private static final int PORT = 6788;

    public static void main(String[] args) throws Exception {
        System.out.println("Server pornit");
        ServerSocket serverSocket = new ServerSocket(PORT);
        try {
            while (true) {
                new Conector(serverSocket.accept()).start();
            }
        } finally {
            serverSocket.close();
        }
    }

    private static class Conector extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public Conector(Socket socket) {
            this.clientSocket = socket;
        }
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(
                        clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Comenzi");
                while (true) {
                    String input = in.readLine();
                    out.println("Rezultatul serverului");

                    if (input.equals("date")) {
                        out.println(date());
                    } else if (input.equals("number")) {
                        out.println("Numar random in intervalul 0.0. si 1.0 este: " + number());
                    } else if (input.equals("help")) {
                        out.println(help());
                    } else if (input.startsWith("hello")) {
                        out.println(hello(input.split(" ", 2)[1]));
                    } else if (input.startsWith("flip")) {
                        out.println(flip());
                    } else if (input.startsWith("exit")) {
                        out.println("Exit");
                        e();
                    } else {
                        out.println("Comanda invalida. Lista de comenzi valide - help");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                }
            }
        }
        private String date() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            return dateFormat.format(date);
        }
        private static double number() {
            double x = Math.random();
            return x;
        }
        private String flip(){
            double y = Math.random();
            if (y < 0.5)
                return ("Cap");
            else
                return ("Pajura");
        }
        private int file() throws IOException {
            return (1);
        }

        private String hello(String str){
            return str;
        }

        private String help() {
            String comands = "Lista comenzilor:" +
                    " /date - data curenta, /hello Text - text input, /number - numar random, flip - aruncarea cu mondede, " +
                    ", file - trimitera de fisier, /help - informatie, /exit - inchide";
            return comands;
        }

        private void e(){
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
