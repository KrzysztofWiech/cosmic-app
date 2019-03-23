package exampleApp.commons.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserService customUserService;
    private PasswordEncoder passwordEncoder;

    //bean tworzony w CosmicAppApplication - PasswordEncoder
    public SecurityConfig(CustomUserService customUserService, PasswordEncoder passwordEncoder) {
        this.customUserService = customUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//włączam autoryzację
//                .antMatchers("/login**", "/").permitAll()
                .antMatchers("/login**").permitAll()
                .and()
                .formLogin()//włączanie konfiguracji formularza
                .loginPage("/login")//ustawianie formularza
                //config form in login page
                .loginProcessingUrl("/signin")//ustawiamy and pointa/ form action link
                .usernameParameter("username") // input name
                .passwordParameter("password") // input password
                .successHandler(
                        (req, res, auth) -> {
                            for (GrantedAuthority g : auth.getAuthorities()) {
                                System.out.println(g.getAuthority());
                            }
                            res.sendRedirect("/");
                        })
                .failureHandler(
                        (req, res, exp) -> {
                            String errorMessege;
                            if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                                errorMessege = "Invalid username or password";
                            } else {
                                errorMessege = "unknow error" + exp.getMessage();
                            }
                            req.getSession().setAttribute("message", errorMessege);
                            res.sendRedirect("/login");
                        }
                )
                .permitAll()
                .and()
                //wylogowywanie
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(
                        (req, res, auth) -> {
                            res.sendRedirect("/");
                        }
                ).permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/"); // 403 error handler


        http

                .csrf().disable(); // odporność na ataki DDoS / wyłączona

        http.headers().frameOptions().disable(); // for H2 memory database

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder);
    }
}
