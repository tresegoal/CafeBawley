package cm.rst.controller;

import cm.rst.dao.ContactRepository;
import cm.rst.entities.Contact;
import cm.rst.mails.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    MailClient mailClient;

    @RequestMapping
    public String newContact(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @RequestMapping("/send")
    public String envoieSimpleMail(Contact contact) throws MessagingException {

        mailClient.sendHtmlEmail("rstngmb@gmail.com", contact.getSubject());
        contactRepository.save(contact);
        return "/contact";
    }

    @RequestMapping("/contact-error")
    public String loginError(Model model) {
        model.addAttribute("contactError", true);
        return "contact";
    }

}
