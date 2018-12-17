package cm.rst.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Fabrice
 * Adresse de l'utilisateur
 */
@Entity
public class Adresse implements Serializable {

    /**
     * Identifiant de l'adresse
     */
    @Id
    @GeneratedValue
    private Long id;
    private String pays;

    //@Column(nullable=false)
    //@NotBlank(message = "Indiquer la ville")
    //@NotNull(message = "La ville est obligatoire")
    private String ville;

    //@Column(nullable=false)
    //@NotBlank(message = "Indiquer la rue")
    //@NotNull(message = "Le champ rue est obligatoire")
    private String rue;

    private String codePostal;
    private String complement;

    @ManyToOne
    @JoinColumn(name = "utilis_id")
    private Utilisateur user;

    public Adresse() {
        super();
    }

    public Adresse(String pays, String ville, String rue, String codePostal, String complement) {
        this.pays = pays;
        this.ville = ville;
        this.rue = rue;
        this.codePostal = codePostal;
        this.complement = complement;
    }

    public Long getIdAd() {
        return id;
    }

    public void setIdAd(Long idAd) {
        this.id = idAd;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
