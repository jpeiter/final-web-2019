package br.com.trabalhoweb.config;

import br.com.trabalhoweb.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .exceptionHandling().accessDeniedPage("/403")
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=bad_credentials").permitAll()
                .and().logout()
                .logoutSuccessUrl("/login")
                .and().authorizeRequests()
                .antMatchers("/register/**").hasAnyRole("ADMIN")
                .antMatchers("/purchase/**").permitAll()
                .antMatchers("/brand/**").hasAnyRole("ADMIN")
                .antMatchers("/product/**").hasAnyRole("ADMIN")
                .antMatchers("/user/create").hasAnyRole("USER")

                .antMatchers("/**").authenticated()
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
//                .antMatchers("/resources/**");
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/assets/**")
                .antMatchers("/webjars/**");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userServiceImpl;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }
}
