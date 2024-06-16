/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;
import ec.edu.espe.medicalappointmentsystem.util.EmailConfig;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Alexis Viteri DCO-ESPE
 */
public class EmailSender {

    private EmailConfig emailConfig;

    public EmailSender(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }

    public void sendMail(String destinatario, String asunto, String cuerpo) {
        Properties props = emailConfig.getProperties();

        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport.send(message);
            System.out.println("Correo enviado correctamente");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}