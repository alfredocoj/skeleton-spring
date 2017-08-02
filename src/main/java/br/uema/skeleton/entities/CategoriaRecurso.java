package br.uema.skeleton.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria_recurso",schema="admin")
public class CategoriaRecurso extends EntityBase {
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
    private String icone;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    private String ordem;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    private Boolean visivel;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "categoriaRecurso")
    private Set<Recurso> recursos = new HashSet<Recurso>();
}
