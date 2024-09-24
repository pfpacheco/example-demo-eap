package com.example.demo.eap.persistence.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ppacheco
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@Entity
@Table(name = "t_demo", uniqueConstraints = {
    @UniqueConstraint(name = "uc_t_demo", columnNames = {"name"})
})
public class Demo extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_demo_seq")
    @SequenceGenerator(name = "t_demo_seq", allocationSize = 1)
    Long id;
   
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;
    
    @Column(columnDefinition = "VARCHAR(500)")
    private String description;
    
    @CreationTimestamp
    @Column(name="created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    @Column(name="updated_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Timestamp updatedAt;
}
