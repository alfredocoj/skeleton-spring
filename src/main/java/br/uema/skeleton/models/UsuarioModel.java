package br.uema.skeleton.models;

import br.uema.skeleton.entities.EntityBase;
import br.uema.skeleton.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UsuarioModel extends ModelBase<Object> implements UserDetailsService {
    /**
     * Classe responsavel por encriptar e decriptar utilizando o padrao BCrypt
     */
    BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();


    /**
     * Metodo que remove Usuario
     *
     * @param entidade {@link EntityBase}
     * @author Vinicius Oliveira
     * @return boolean
     */
    public boolean remove(EntityBase entidade) {
        String sql = "UPDATE usuario SET ativo = false WHERE id_usuario = :id";
        int flag = manager.createQuery( sql ).setParameter( "id", entidade.getId() ).executeUpdate();

        if (flag != 0)
            return true;

        return false;
    }

    @SuppressWarnings("unused")
    private List<GrantedAuthority> buildUserAuthority(String role) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        setAuths.add( new SimpleGrantedAuthority( role ) );
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(
                setAuths );
        return result;
    }

    public Usuario loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        String jpql = "select u from Usuario u where u.login = :login";
        List<Usuario> users = manager.createNativeQuery( jpql, Usuario.class ).setParameter( "login", username ).getResultList();
        if (users.isEmpty()) {
            throw new UsernameNotFoundException( "O usuario não existe" );
        }
        return users.get( 0 );
    }

    /**
     * Metodo que vincula usuario a um perfil
     *
     * @param id_usuario int
     * @param id_perfil int
     * @author Vinicius Oliveira

     * @return String
     */
    public boolean vincularPerfil(int id_usuario, int id_perfil) {
        try {
            String sql = "insert into admin.usuario_perfil(id_usuario, id_perfil) values (" + id_usuario + "," + id_perfil + ")";
            manager.createNativeQuery(sql).executeUpdate();
            return true;
        } catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    /**
     * Metodo que edita vinculo de usuario com seu perfil
     *
     * @param id_usuario int
     * @param id_perfil int
     * @author Vinicius Oliveira
     * @return Object
     */
    public boolean editarVinculoPerfil(int id_usuario, int id_perfil) {
        try{
            String sql = "UPDATE admin.usuario_perfil SET id_perfil = :id_perfil WHERE id_usuario = :id_usuario";
            manager.createNativeQuery( sql )
                    .setParameter( "id_perfil", id_perfil )
                    .setParameter( "id_usuario", id_usuario )
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }
}
