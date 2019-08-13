/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao.impl;

import glasscat.as1.dao.Dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Li Xuekai<zerlzerl@163.com>
 * @param <T>
 */
public abstract class BaseDaoImpl<T extends Serializable> implements Dao<T> {
    private final static String UNIT_NAME = "GlassCatPU";
    @PersistenceContext(unitName = UNIT_NAME)
    protected EntityManager entityManager;
    protected Class<T> clazz ;
    
    // constructor
    public final void setClazz( Class<T> clazzToSet ){
        this.clazz = clazzToSet;
    }
    
    // implements

    @Override
    public void save(T object) {
        this.entityManager.persist(object);
    }

    @Override
    public void delete(T object) {
        T t = this.entityManager.merge(object);
        this.entityManager.remove(t);
    }

    @Override
    public void deleteById(String id) {
        T object = this.findById(id);
        this.delete(object);
    }

    @Override
    public void update(T object) {
        entityManager.merge(object);
    }

    @Override
    public T findById(String id) {
        return this.entityManager.find(this.clazz, id);
    }

    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery("from " + clazz.getName()).getResultList();
    }
    
    public <T> List<T> executeSQLQuery(Class<T> clazz, String sql) {
        Query qry = entityManager.createNativeQuery(sql, clazz);
        return (List<T>) qry.getResultList(); //Returns an untyped list, part of JPA spec
    }
    
}
