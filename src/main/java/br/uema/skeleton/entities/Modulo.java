package br.uema.skeleton.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "modulo", schema="admin")
public class Modulo extends EntityBase {
    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_modulo")
    private Long id;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @NotNull
    @Column(name = "nome", length = 30)
    private String nome;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "nome", length = 60)
    private String descricao;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @Column(name = "nome", length = 20)
    private String icone;

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
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "modulo")
    private Set<Perfil> perfis = new HashSet<Perfil>();

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    @OneToMany(cascade = { javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST }, fetch = FetchType.LAZY, mappedBy = "modulo")
    private Set<Recurso> recursos = new HashSet<Recurso>();
}
