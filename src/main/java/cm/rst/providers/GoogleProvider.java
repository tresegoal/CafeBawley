package cm.rst.providers;

import cm.rst.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GoogleProvider {

    private static final String REDIRECT_CONNECT_GOOGLE = "redirect:/login";
    private static final String GOOGLE = "google";

    @Autowired
    BaseProvider baseProvider;


    public String getGoogleUserData(Model model, Utilisateur userForm) {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Google.class) == null) {
            return REDIRECT_CONNECT_GOOGLE;
        }

        populateUserDetailsFromGoogle(userForm);
        //Save the details in DB
        baseProvider.saveUserDetails(userForm);

        //Login the User
        baseProvider.autoLoginUser(userForm);

        model.addAttribute("loggedInUser", userForm);
        return "secure/user";
    }


    protected void populateUserDetailsFromGoogle(Utilisateur userform) {
        Google google = baseProvider.getGoogle();
        Person googleUser = google.plusOperations().getGoogleProfile();
        userform.setEmail(googleUser.getAccountEmail());
        userform.setPrenom(googleUser.getGivenName());
        userform.setNom(googleUser.getFamilyName());
        userform.setImage(googleUser.getImageUrl());
        userform.setProvider(GOOGLE);
    }

}
