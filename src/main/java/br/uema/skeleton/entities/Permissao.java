package br.uema.skeleton.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissao",schema="admin")
public class Permissao extends EntityBase {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Version
    private Integer version;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    private String nome;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String descricao;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Perfil> perfil = new HashSet<Perfil>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Recurso recurso;
}
