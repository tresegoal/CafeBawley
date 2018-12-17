package cm.rst.providers;

import cm.rst.autologin.Autologin;
import cm.rst.dao.UtilisateurRepository;
import cm.rst.entities.Utilisateur;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;

@Configuration
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {

    private Facebook facebook;
    private Google google;
    private ConnectionRepository connectionRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    protected Autologin autologin;

    public BaseProvider(Facebook facebook, Google google, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
        this.google = google;
    }

    protected void saveUserDetails(Utilisateur userBean) {
        if (StringUtils.isNotEmpty(userBean.getPassword())) {
            userBean.setPassword(bCryptPasswordEncoder.encode(userBean.getPassword()));
        }
        userRepository.save(userBean);

    }

    public void autoLoginUser(Utilisateur userBean) {
        autologin.setSecuritycontext(userBean);
    }

    public Facebook getFacebook() {
        return facebook;
    }

    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    public ConnectionRepository getConnectionRepository() {
        return connectionRepository;
    }

    public void setConnectionRepository(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Google getGoogle() {
        return google;
    }

    public void setGoogle(Google google) {
        this.google = google;
    }

}
