package cm.rst.providers;

import cm.rst.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FacebookProvider {

    private static final String FACEBOOK = "facebook";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    @Autowired
    BaseProvider baseProvider;


    public String getFacebookUserData(Model model, Utilisateur userForm) {

        ConnectionRepository connectionRepository = baseProvider.getConnectionRepository();
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return REDIRECT_LOGIN;
        }
        //Populate the Bean
        populateUserDetailsFromFacebook(userForm);
        //Save the details in DB
        baseProvider.saveUserDetails(userForm);
        //Login the User
        baseProvider.autoLoginUser(userForm);
        model.addAttribute("loggedInUser", userForm);
        return "secure/user";
    }

    protected void populateUserDetailsFromFacebook(Utilisateur userForm) {
        Facebook facebook = baseProvider.getFacebook();
        User user = facebook.userOperations().getUserProfile();
        userForm.setEmail(user.getEmail());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setImage(user.getCover().getSource());
        userForm.setProvider(FACEBOOK);
    }


}
