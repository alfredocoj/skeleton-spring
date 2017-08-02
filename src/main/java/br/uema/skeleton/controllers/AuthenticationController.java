package br.uema.skeleton.controllers;

import br.uema.skeleton.entities.Usuario;
import br.uema.skeleton.models.UsuarioModel;
import br.uema.skeleton.utils.SetSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class AuthenticationController implements AuthenticationProvider {
    /**
     * Injecao de dependencia para a Model Usuario
     */
    @Autowired
    public UsuarioModel usuarioModel;

    /**
     * Injecao de dependencia do HttpServletRequest
     */
    @Autowired
    public HttpServletRequest request;

    /**Método de autentificação personalizado pelo spring security
     * @author Vinicius Oliveira
     * @return UsernamePasswordAuthenticationToken - usuário autenticado
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();

        try {
            String username = authentication.getName();
            String password = (String) authentication.getCredentials();
            Usuario user = usuarioModel.loadUserByUsername(username);

            if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
                throw new BadCredentialsException("Usuário não encontrado.");
            }

            if (!BCrypt.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Senha inválida.");
            }
            String details = authentication.getDetails().toString();
            //verificar esse elemento
            request.getSession().setAttribute("token",details);

            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        } catch (Exception e) {
            SetSession.setErrorSession(e.getMessage());
            throw new BadCredentialsException(e.getMessage(), e);
        }
    }

    /**Método de implementação obrigatoria
     * @author Vinicius Oliveira
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return true;
    }

    /**Retorna a referência para o redirecionamento do Spring Security
     * @author Vinicius Oliveira
     * @return boolean
     */
    private String getRefer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }
}
