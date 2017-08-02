package br.uema.skeleton.models;

import br.uema.skeleton.entities.EntityBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Repository
@Transactional
public class ModelBase<EntidadeBase> {

    @PersistenceContext
    protected EntityManager manager;

    protected String schema;

    protected EntityBase entidade;

    protected static final Logger logger = LoggerFactory.getLogger(ModelBase.class);

    public ModelBase() {
        this.schema = "schema_default";
    }

    public ModelBase(EntityManager entityManager) {
        this.schema = "schema_default";
        manager = entityManager;
    }

    public void configEntityManager(String schema) {
        this.schema = schema;
        manager = Persistence.createEntityManagerFactory(this.schema).createEntityManager();
    }

    public EntityBase save(EntityBase entidade) throws Exception {
        try {
            manager.persist(entidade);
            manager.flush();
            return entidade;
        } catch (PersistenceException e) {
            throw new Exception("Erro ao tentar salvar.");
        }
    }

    public EntityBase saveIfNotExist(EntityBase entidade) throws Exception {
        if (getById(entidade) == null) {
            return save(entidade);
        }
        return entidade;
    }

    public EntityBase saveIfNotExist(EntityBase entidade, String attribute, int valor) throws Exception {
        if (getByAttribute(entidade, attribute, valor).size() == 0) {
            return save(entidade);
        }
        return getByAttribute(entidade, attribute, valor).get(0);
    }

    public EntityBase saveIfNotExist(EntityBase entidade, String attribute, String valor) throws Exception {
        if (getByAttribute(entidade, attribute, valor).size() == 0) {
            return save(entidade);
        }
        return getByAttribute(entidade, attribute, valor).get(0);
    }

    public EntityBase update(EntityBase entidade) throws Exception {
        try {
            EntityBase objeto = getById(entidade);
            System.out.println("UPDATE: " + manager.merge(entidade));
            return objeto;
        } catch (PersistenceException e) {
            throw new Exception("Erro ao tentar atualizar.");
        }
    }

    public boolean remove(EntityBase entidade) throws Exception {
        try {
            EntityBase objeto = getById(entidade);
            if (objeto.getId() != null) {
                manager.remove(objeto);
                return true;
            } else
                return false;
        } catch (PersistenceException e) {
            throw new Exception("Erro ao tentar remover.");
        }
    }

    @SuppressWarnings("unchecked")
    public List<EntityBase> getAll(EntityBase entidade, String sql) {
        return (List<EntityBase>) manager.createNativeQuery(sql, entidade.getClass()).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<EntityBase> getAll(EntityBase entidade) {
        return (List<EntityBase>) manager.createNativeQuery("SELECT e FROM " + entidade.getClass().getSimpleName() + " e", entidade.getClass()).getResultList();
    }

    public EntityBase getById(EntityBase entidade) {
        return manager.find(entidade.getClass(), entidade.getId());
    }

    @SuppressWarnings("unchecked")
    public List<EntityBase> getByAttribute(EntityBase entidade, String attribute, String value) {
        Query query = manager.createQuery("SELECT e FROM " + entidade.getClass().getSimpleName() + " e where e." + attribute + " = :valor");
        query.setParameter("valor", value);

        List<EntityBase> lista = query.getResultList();

        return lista;
    }

    public List<EntityBase> getByAttribute(EntityBase entidade, String attribute, int value) {
        Query query = manager.createQuery("SELECT e FROM " + entidade.getClass().getSimpleName() + " e where e." + attribute + " = :valor");
        query.setParameter("valor", value);

        List<EntityBase> lista = query.getResultList();

        return lista;
    }

    public List<EntityBase> getByAttribute(EntityBase entidade, String attribute, EntityBase valor) {
        Query query = manager.createQuery("SELECT e FROM " + entidade.getClass().getSimpleName() + " e where e." + attribute + " = :valor");
        query.setParameter("valor", valor);

        List<EntityBase> lista = query.getResultList();

        return lista;
    }

    public List<Map<String, String>> listarNomePorQuantidade(List<Object[]> lista) {
        List<Map<String, String>> listaNomePorQuantidade = new ArrayList<>();

        for (Object[] linha : lista) {
            Map<String, String> elemento = new HashMap<>();
            elemento.put("nome", linha[0].toString());
            elemento.put("quantidade", linha[1].toString());
            listaNomePorQuantidade.add(elemento);
        }

        return listaNomePorQuantidade;

    }

    public Calendar padronizarData(Calendar data) {

        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.HOUR, 0);
        data.set(Calendar.ZONE_OFFSET, 0);
        data.set(Calendar.DST_OFFSET, 0);
        data.set(Calendar.MINUTE, 0);
        data.set(Calendar.SECOND, 0);
        data.set(Calendar.MILLISECOND, 0);
        data.setTimeZone(TimeZone.getTimeZone("UTC"));
        data.set(Calendar.AM_PM, 0);

        return data;
    }

}
