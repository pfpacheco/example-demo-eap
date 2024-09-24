package com.example.demo.eap.services;

import com.example.demo.eap.persistence.entities.Demo;
import com.example.demo.eap.persistence.repositories.DemoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;

import java.util.List;

/**
 *
 * @author ppacheco
 */
@ApplicationScoped
public class DemoService {
    
    @Inject
    private DemoRepository repository;
   
    @Transactional
    public void add(Demo entity) {
        this.repository.persist(entity);
    }
    
    @Transactional
    public void update(Demo entity) {
        this.repository.persistAndFlush(entity);
    }
    
    @Transactional
    public Demo findById(long id) {
        return this.repository.findById(id);
    }

    @Transactional    
    public Demo findByName(String name) {
        return (Demo) this.repository.findByName(name).singleResult();
    }
    
    @Transactional
    public List<Demo> findAll() {
        return this.repository.findAll().list();
    }
    
    @Transactional
    public void delete(Long id, Demo entity) {
        this.repository.delete(entity);
    }
}
