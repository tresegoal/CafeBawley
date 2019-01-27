package cm.rst.restController;

import cm.rst.Utils.SecurityUtility;
import cm.rst.entities.Role;
import cm.rst.entities.UserRole;
import cm.rst.entities.Utilisateur;
import cm.rst.restController.utils.*;
import cm.rst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthRest {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthDTO authDTO) {
        System.out.println(authDTO.getEmail()+"je suis la "+authDTO.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authDTO.getEmail(),
                            authDTO.getPassword()
                    )
            );

            String token = tokenProvider.createToken(authentication);
            System.out.println("Je suis un vrai toto les gars ..."+token);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Utilisateur user) throws Exception {
        if(userService.findByEmail(user.getEmail()) == null) {
            throw new BadRequestException("Email address already in use.");
        }

        user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user,new Role("ROLE_USER")));
        Utilisateur result =userService.createUser(user,userRoles);


        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity
                .created(location)
                .headers(HeaderUtil.createEntityUpdateAlert("Auth", result.getId().toString()))
                .body(result);
    }
}
