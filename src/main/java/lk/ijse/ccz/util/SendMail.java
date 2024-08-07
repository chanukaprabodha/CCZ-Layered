package lk.ijse.ccz.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;


import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static javax.mail.Message.RecipientType.TO;

public class SendMail {

    public static String customerMailAddress;
    public static String customerName;
    private static final String cczMail = "ckprabodha@gmail.com";
    private static Gmail service;

    public SendMail() throws Exception {

        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jasonFactory = GsonFactory.getDefaultInstance();
         service = new Gmail.Builder(httpTransport, jasonFactory, getCredentials(httpTransport, jasonFactory))
                .setApplicationName("CCZManagement")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory) throws IOException {
        // Load client secrets.
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(SendMail.class.getResourceAsStream("/client_secret_541077520352-0ev44c6jvkod3puleso62oh6sun3rtgr.apps.googleusercontent.com.json")));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get("token").toFile()))
                .setAccessType("offline")
                .build();

        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public  static void sendMail(String subject, String message) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(cczMail));
        email.addRecipient(TO, new InternetAddress("chanugamage02@gmail.com"));
        email.setSubject(subject);
        email.setText(message);

        // Encode and wrap the MIME message into a gmail message
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            // Create send message
            msg = service.users().messages().send("me", msg).execute();
//            System.out.println("Message id: " + msg.getId());
//            System.out.println(msg.toPrettyString());
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("Unable to send message: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }

    public static void sending(Date date,String orderId,Double totalAmount) throws Exception {
        sendMail("Chamu Cake Zone Order Confirmation", "Hi " + customerName + ",\n\n" +
                "\tThank you for shopping with us. " +
                "Your order is confirmed. " +
                "\n\tWe'll let you know when your order is ready 😊\n" +
                "\n\tOrder Details:\n\n" +
                "\t\t✅  Placed on :  " + date + "\n\n" +
                "\t\t✅  Order ID :  " + orderId + "\n\n" +
                "\t\t✅  Total Amount :  " + totalAmount + "\n" +
                "\n\n\tSent with ❤️ from CCZ.\n\n");
    }
}
