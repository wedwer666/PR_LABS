import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Maria on 31.03.2018.
 */
public class ClientApp {
        public static void main(String args[]) {

            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");
            try {
                Session session = Session.getDefaultInstance(props, null);
                Store store = session.getStore("imaps");

                store.connect("imap.gmail.com", "***********", "***********");
                System.out.println(store);
                Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_ONLY);
                BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Apasati " +
                        " N - pentru a vedea mesajele necitite, " +
                        " C - pentru a vedea toate mesajele: " +
                        " T - pentru a trimite mesaj");
                try {
                    char answer = (char) optionReader.read();
                    if(answer=='N' || answer=='n'){
                        showAll(inbox);
                    }else if(answer=='C' || answer=='c'){
                        showUnread(inbox);
                    }
                    else if (answer == 'T' || answer == 't')

                    optionReader.close();
                } catch (IOException e) {
                    System.out.println(e);
                }

            } catch (NoSuchProviderException e) {
                System.out.println(e.toString());
                System.exit(1);
            } catch (MessagingException e) {
                System.out.println(e.toString());
                System.exit(2);
            }

        }


    }
