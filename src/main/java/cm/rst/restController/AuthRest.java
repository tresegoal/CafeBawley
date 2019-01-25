package cm.rst.restController;

import cm.rst.Utils.SecurityUtility;
import cm.rst.entities.Role;
import cm.rst.entities.UserRole;
import cm.rst.entities.Utilisateur;
import cm.rst.restController.utils.*;
import cm.rst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthDTO authDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDTO.getEmail(),
                        authDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
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
