package cm.rst.mails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailClient {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @ResponseBody
    public void sendSimpleEmail(String sendTo, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendTo);
        message.setText(msg);

        mailSender.send(message);
    }

    @ResponseBody
    public void sendSimpleEmail(String sendTo, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(msg);

        mailSender.send(message);
    }

//    Mail html

    @ResponseBody
    public String sendHtmlEmail(String sendTo, String msg) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<img src='http://www.cafebawley.com/img/logo-header.png'>" +
                "<h3>Envoyer par Café Bawley</h3> <br/><br/>" + msg + "<br/><br/><br/><br/>" +
                "<div bgcolor='#b97915' style=\"padding: 30px 30px 30px 30px;\">\n" +
                "    <p> Café Bawley </p> <p>Cameroun </p> <span>http://www.cafebawley.com</span></div>";

        message.setSubject("Envoyer depuis la page contact");
        message.setContent(htmlMsg, "text/html");

        helper.setTo(sendTo);

        mailSender.send(message);

        Context context = new Context();
        context.setVariable("message", htmlMsg);
        return templateEngine.process("mailTemplate", context);
    }

}
