package br.uema.skeleton.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="perfil", schema="admin")
public class Perfil  extends EntityBase  implements GrantedAuthority {
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_perfil")
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_modulo", nullable=false)
    private Modulo modulo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Usuario> usuario = new HashSet<Usuario>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "perfil")
    private Set<Permissao> permissao = new HashSet<Permissao>();

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
