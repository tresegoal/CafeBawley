package cm.rst.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Fabrice
 */
//@formatter:off
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String[] PUBLIC_MATCHES = {
            "/css/**",
            "/js/**",
            "/img/**",
            "/assets/**",
            "/vendor/**",
            "/fonts/**",
            "/connect/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       /* http
                .authorizeRequests()
                    .anyRequest()
                        .authenticated()
                            .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/secure/user")
                    .failureUrl("/login-error")
                        .and()
                .exceptionHandling().accessDeniedPage("/403")
                    .and()
                .authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll()
                .antMatchers("/secure/**")
                .authenticated();*/

            http.exceptionHandling().accessDeniedPage("/403")
                .and()
                .authorizeRequests().antMatchers(PUBLIC_MATCHES).permitAll()
                .antMatchers("/secure/**")
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/secure/user")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests().antMatchers("/admincafe/**").hasRole("ADMIN");
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.GET, "/client/**").hasRole("CLIENT");
               /* .and()
                .authorizeRequests()
                .antMatchers("/admincafe/**").authenticated()
                .antMatchers(HttpMethod.POST, "/utilisateur/").hasAuthority("ADMIN");*/
//               http.authorizeRequests().antMatchers("/client/**").hasRole("CLIENT");
//               http.authorizeRequests().antMatchers("/client/**","/commande/**","/produit/**").hasRole("PUBLISHER");
//               http.authorizeRequests().antMatchers("/client/**","/commande/**","/admincafe/**","/produit/**").hasRole("ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}

// @formatter:on