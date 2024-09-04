package com.example.demo.eap.services;

import com.example.demo.eap.persistence.entities.Demo;

import jakarta.enterprise.context.ApplicationScoped;


import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 *
 * @author ppacheco
 */
@ApplicationScoped
public class DemoService {
   
    
    @Transactional
    public void add(Demo entity) {
        entity.persist();
    }
    
    @Transactional
    public void update(Demo entity) {
        entity.persistAndFlush();
    }
    
    public Demo findById(long id) {
        return Demo.findById(id, LockModeType.READ);
    }
    
    public Demo findByName(String name) {
        return Demo.findByName(name);
    }
    
    public List<Demo> findAll() {
        return Demo.listAll();
    }
    
    @Transactional
    public void delete(Long id, Demo entity) {
        Demo.deleteById(id);
    }
}
