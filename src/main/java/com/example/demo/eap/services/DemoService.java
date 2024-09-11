package com.example.demo.eap.services;

import com.example.demo.eap.persistence.entities.Demo;

import jakarta.enterprise.context.ApplicationScoped;

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
    
    @Transactional
    public Demo findById(long id) {
        return Demo.findById(id);
    }

    @Transactional    
    public Demo findByName(String name) {
        return Demo.findByName(name);
    }
    
    @Transactional
    public List<Demo> findAll() {
        return Demo.listAll();
    }
    
    @Transactional
    public void delete(Long id, Demo entity) {
        Demo.deleteById(id);
    }
}
