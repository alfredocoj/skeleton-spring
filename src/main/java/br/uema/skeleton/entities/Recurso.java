package br.uema.skeleton.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recurso", schema="admin")
public class Recurso extends EntityBase {

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
    private String icone;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    private Boolean ativo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private String ordem;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoriaRecurso categoriaRecurso;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Modulo modulo;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "recurso")
    private Set<Permissao> Permissao = new HashSet<Permissao>();

}
