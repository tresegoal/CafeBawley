package cm.rst.mails;

import cm.rst.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MailConstructor {
    @Autowired
    private Environment env;

    public SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, Utilisateur user, String password
    ) {

        String url = contextPath + "/newUser?token=" + token;
        String message = "\nCliquer sur ce lien pour la vérification de votre email et la mise à jour de informations personnelles. Votre mot de passe est :\n" + password;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Café Bawley - Nouveau Utilisateur");
        email.setText(url + message);
        email.setFrom(env.getProperty("support.email"));
        return email;

    }
}
