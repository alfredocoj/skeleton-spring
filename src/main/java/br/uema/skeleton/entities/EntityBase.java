package br.uema.skeleton.entities;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

abstract public class EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_alteracao", insertable = false)
    protected Date dataAlteracao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", updatable = false)
    protected Date dataCriacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public static Map<String, String> validate(ConstraintViolationException erroValidacao){
        Set<ConstraintViolation<?>> erros = erroValidacao.getConstraintViolations();
        Map<String, String> elemento = new HashMap<>();
        for(ConstraintViolation<?> erro : erros){
            elemento.put(erro.getPropertyPath().toString(), erro.getMessageTemplate());
        }
        return elemento;
    }
}
