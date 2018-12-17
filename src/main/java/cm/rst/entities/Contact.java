package cm.rst.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Fabrice
 *
 */
@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String subject;

    public Contact() {
    }

    public Contact(String email, String subject) {
        this.email = email;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
