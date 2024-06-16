/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.medicalappointmentsystem.util;
import java.util.Properties;
/**
 *
 * @author Alexis Viteri DCO-ESPE
 */
public class EmailConfig {
    private String smtpHost;
    private int smtpPort;
    private String username;
    private String password;
    private Properties properties;

    public EmailConfig(String smtpHost, int smtpPort, String username, String password) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.username = username;
        this.password = password;
        this.properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
    }

    public Properties getProperties() {
        return properties;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}