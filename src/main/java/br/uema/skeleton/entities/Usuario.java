package br.uema.skeleton.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Usuario")
@Table(name = "usuario", schema = "admin",
        uniqueConstraints = @UniqueConstraint(columnNames = "login")
)
public class Usuario extends EntityBase  implements UserDetails {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "login", nullable = false, length = 45, unique = true)
    private String login;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "nome", length = 50)
    private String nome;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "senha", nullable = false, length = 255)
    private String senha;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "email",unique = true)
    private String email;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "ativo", nullable = false, columnDefinition = "boolean default true")
    private Boolean ativo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_perfil", schema = "admin", joinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {@JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")})
    private Set<Perfil> perfis = new HashSet<Perfil>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }
}
