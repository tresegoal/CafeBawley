package cm.rst.security;

import cm.rst.Utils.SecurityUtility;
import cm.rst.serviceImpl.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PUBLIC_MATCHERS = {
            "/css/**",
            "/js/**",
            "/img/**",
            "/image/**",
            "/",
            "/newUser",
            "/forgetPassword",
            "/login",
            "/fonts/**",
            "/assets/**",
            "/vendor/**",
            "/produit",
            "/searchProduct",
            "/produitDetails/{id}",
            "/produitParPrix",
            "/livraison",
            "/panier/**",
            "/contact/**",
            "/facebook/**",
            "/google/**",
            "/connect/**"
    };
    @Autowired
    private Environment env;
    @Autowired
    private UserSecurityService userSecurityService;

    private BCryptPasswordEncoder passwordEncoder() {
        return SecurityUtility.passwordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().
                /*	antMatchers("/**").*/
                        antMatchers(PUBLIC_MATCHERS).
                permitAll().anyRequest().authenticated();

        http
                .csrf().disable().cors().disable()
                .formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }

}




/*
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


 http
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
                .authenticated();*//*


 */
/*http.exceptionHandling().accessDeniedPage("/403")
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
                .antMatchers(HttpMethod.POST, "/utilisateur/").hasAuthority("ADMIN");*//*

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

// @formatter:on*/
