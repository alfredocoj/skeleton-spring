package br.uema.skeleton.config;

import br.uema.skeleton.controllers.AuthenticationController;
import br.uema.skeleton.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationController authenticationController;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers("/").permitAll()


                /**
                 * Parte de autentificação e lindando com erros
                 */
                .anyRequest().authenticated()
                .and().authenticationProvider(authenticationController)
                .formLogin()
                .loginPage("/auth").failureForwardUrl("/loginerro").successForwardUrl("/loginsucess")
                .and()
                .exceptionHandling().accessDeniedPage("/index")
                .and()
                .logout().logoutSuccessUrl("/index")
                .permitAll();
        //verificar elemento
        http.csrf().disable();
    }

    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        //repository.setHeaderName("X-XSRF-TOKEN");
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

    @Autowired
    private UsuarioModel usuarioModel;


    //verificar elemento
    @Bean
    public BCryptPasswordEncoder passwordEncoder2() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioModel).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
