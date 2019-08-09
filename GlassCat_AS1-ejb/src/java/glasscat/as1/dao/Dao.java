/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glasscat.as1.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zerlz
 * @param <T>
 */
public interface Dao<T extends Serializable>{
    /**
     * add
     * @param object 
     */
    public void save(T object);   
    /**
     * delete
     * @param object 
     */
    public void delete(T object);
    /**
     * delete by id
     * @param id 
     */
    public void deleteById(Long id);
    /**
     * update
     * @param object 
     */
    public void update(T object);
    /**
     * find by id
     * @param id
     * @return 
     */
    public T findById(Long id);
    /**
     * find all records
     * @return 
     */
    public List<T> findAll();
    /**
     * execute original sql query
     * @param <T>
     * @param clazz<T>
     * @param sql
     * @return 
     */
    public <T> List<T> executeSQLQuery(Class<T> clazz,String sql);    
}
