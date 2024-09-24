package com.example.demo.eap.persistence.repositories;

import com.example.demo.eap.persistence.entities.Demo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author ppacheco
 */
@ApplicationScoped
public class DemoRepository implements PanacheRepositoryBase<Demo, Long> {
    
    public PanacheQuery<Demo> findByName(String name) {
        return this.find(name, name);
    }
}
