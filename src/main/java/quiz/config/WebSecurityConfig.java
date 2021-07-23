package quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import quiz.service.UserService;

import java.util.concurrent.TimeUnit;

import static quiz.entity.Role.ADMIN;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private static final String rememberMeKey = "somethingVerySecured";

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MessageDigestPasswordEncoder("MD5");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/api/quiz/quiz").hasAuthority(ADMIN.name())
                    .antMatchers(HttpMethod.POST,"/api/quiz/quiz").hasAuthority(ADMIN.name())
                    .antMatchers(HttpMethod.PUT,"/api/quiz/quiz/**").hasAuthority(ADMIN.name())
                    .antMatchers(HttpMethod.DELETE,"/api/quiz/quiz/**").hasAuthority(ADMIN.name())
                    .antMatchers(HttpMethod.POST,"/api/quiz/question/**").hasAuthority(ADMIN.name())
                    .antMatchers(HttpMethod.PUT,"/api/quiz/question/**").hasAuthority(ADMIN.name())
                    .antMatchers(HttpMethod.DELETE,"/api/quiz/question/**").hasAuthority(ADMIN.name())
                        .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl( "/dashboard", false)
                    .passwordParameter("password")
                    .usernameParameter("username")
                    .failureUrl("/login/login-error")
                    .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                    .key(rememberMeKey)
                    .rememberMeParameter("remember-me")
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.GET.name()))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login")
                    .and()
                .authorizeRequests()
                    .anyRequest().permitAll();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
