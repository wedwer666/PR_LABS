import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Maria on 31.03.2018.
 */
public class ClientApp extends Component {

    public static void main(String args[]) {
        String input_username;
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce email credentials for authentification");
        System.out.println("Username: ");
        input_username = input.next();

        String input_password;
        Scanner input2 = new Scanner(System.in);
        System.out.println("Password: ");
        input_password = input2.next();
        if (input_username.equals(input_username) && input_password.equals(input_password))
            System.out.println("You are logged in Email");
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            new IntroduceCredentials();
            store.connect("imap.gmail.com", input_username, input_password);
            System.out.println(store);
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);
                    BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Enter:" + "\n" +
                            " A - to see all messages" + "\n" +
                            " U - to see all unread messages" + "\n" +
                            " T - to send a message");
                    try {
                        char answer = (char) optionReader.read();
                        if (answer == 'A' || answer == 'a') {
                            showAll(inbox);
                } else if (answer == 'U' || answer == 'u') {
                    showUnread(inbox);
                } else if (answer == 'T' || answer == 't') {
                    sendMail();
                }
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

    static public void showAll(Folder inbox) {
        try {
            Message msg[] = inbox.getMessages();
            System.out.println("Total number of messages : " + msg.length);
            String myMail = "";
            for (Message message : msg) {
                try {
                    System.out.println("SUBJECT: " + message.getSubject().toString());
                    System.out.println("DATE: " + message.getSentDate().toString());
                    System.out.println("FROM: " + message.getFrom()[0].toString());
                    System.out.println("CONTENT: " + message.getContent().toString());
                    Multipart multipart = (Multipart) message.getContent();
                    int attachmentCount = multipart.getCount();
                    System.out.println("Number of attachments: " + attachmentCount);

                    Part messagePart = message;
                    Object content = messagePart.getContent();
                    // -- or its first body part if it is a multipart message --
                    if (content instanceof Multipart) {
                        messagePart = ((Multipart) content).getBodyPart(0);
                        System.out.println("[ Multipart Message ]");
                    }
                    // -- Get the content type --
                    String contentType = messagePart.getContentType();
                    //  If the content is plain text, we can print it
                    System.out.println("CONTENT:" + contentType);
                    if (contentType.startsWith("TEXT/PLAIN")
                            || contentType.startsWith("TEXT/HTML")) {
                        InputStream is = messagePart.getInputStream();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(is));
                        String thisLine = reader.readLine();
                        while (thisLine != null) {
                            System.out.println(thisLine);
                            myMail = myMail + thisLine;
                            thisLine = reader.readLine();
                        }
                    }
                    System.out.println("                                           ");
                } catch (Exception e) {
                    System.out.println("There is no information in email");
                }
            }
        } catch (MessagingException e) {
            System.out.println(e.toString());
        }
    }


    static public void showUnread(Folder inbox) {
        try {
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            Message msg[] = inbox.search(ft);
            String myunreadMail = "";
            System.out.println("Number of unseen messages : " + msg.length);
            for (Message message : msg) {
                try {
                    System.out.println("SUBJECT: " + message.getSubject().toString());
                    System.out.println("DATE: " + message.getSentDate().toString());
                    System.out.println("FROM: " + message.getFrom()[0].toString());
                    Part messagePart = message;
                    Object content = messagePart.getContent();
                    if (content instanceof Multipart) {
                        messagePart = ((Multipart) content).getBodyPart(0);
                        System.out.println("[ Multipart Message ]");
                    }
                    String contentType = messagePart.getContentType();
                    System.out.println("CONTENT:" + contentType);
                    if (contentType.startsWith("TEXT/PLAIN")
                            || contentType.startsWith("TEXT/HTML")) {
                        InputStream is = messagePart.getInputStream();
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(is));
                        String thisLine = reader.readLine();
                        while (thisLine != null) {
                            System.out.println(thisLine);
                            myunreadMail = myunreadMail + thisLine;
                            thisLine = reader.readLine();
                        }
                    }
                    System.out.println("                                           ");
                } catch (Exception e) {
                    System.out.println("Nu este informatie in email");
                }
            }
        } catch (MessagingException e) {
            System.out.println(e.toString());
        }
    }

    static void sendMail() throws MessagingException, IOException {
        String send_input_username;
        Scanner input = new Scanner(System.in);
        System.out.println("Introduce email credentials to send an email");
        System.out.println("Username: ");
        send_input_username = input.next();

        String send_input_password;
        Scanner input2 = new Scanner(System.in);
        System.out.println("Password: ");
        send_input_password = input2.next();
        if (send_input_username.equals(send_input_username) && send_input_password.equals(send_input_password))
            System.out.println("You are gonna send an email");
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(send_input_username, send_input_password);
                    }
                });
        try {
            String email_to_send;
            Scanner input3 = new Scanner(System.in);
            System.out.println("Email of the receiver: ");
            email_to_send = input3.next();

            String set_subject;
            Scanner input4 = new Scanner(System.in);
            System.out.println("Subject: ");
            set_subject = input4.next();

            String set_body;
            Scanner input5 = new Scanner(System.in);
            System.out.println("Body: ");
            set_body = input5.next();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(send_input_username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_to_send));
            message.setSubject(set_subject);
            message.setText(set_body);
            System.out.println("you are gonna to choose file to upload");
            // attachment with simple format or text/html format
            BufferedReader optionReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter: " + "\n" +
                    " S - to attach file " + "\n" +
                    " H - to send email in TXT/HTML format " + "\n" +
                    " D - create draft ");
            try {
                char answer = (char) optionReader.read();
                if (answer == 'S' || answer == 's') {
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    Multipart multipart = new MimeMultipart();
                    messageBodyPart = new MimeBodyPart();
                    String attachmentPath = "C:/Users/Maria/Desktop/Anul3_II";
                    String attachmentName = "LogResults.txt";

                    File att = new File(new File(attachmentPath), attachmentName);
                    messageBodyPart.attachFile(att);

                    DataSource source = new FileDataSource(att);
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(attachmentName);
                    multipart.addBodyPart(messageBodyPart);
                    message.setContent(multipart);
                    System.out.println("Sending message with simple attachment");
                    Transport.send(message);
                    System.out.println("Your message was successfully send to " + email_to_send);

                } else if (answer == 'H' || answer == 'h') {
                    BodyPart messageBodyPart = new MimeBodyPart();
                    message.setSubject(set_subject);
                    // the format of the attachment to be send
                    message.setContent("<h1>E-mail client app with IMAP and SMTP protocols</h1>",
                            "text/html");
                    System.out.println("Sending message with text/html attachment");
                    Transport.send(message);
                    System.out.println("Your message was successfully send to " + email_to_send);
                } else if (answer == 'D' || answer == 'd') {
                    // save file as draft
                    System.out.println("You are gonna save message as draft");
//                    create_draft(service, ME, emailContent);
                }
            }
                catch (MessagingException e)
                {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                optionReader.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    private static void create_draft(){
    }

}

