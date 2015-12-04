package com.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Main")
public class MainEntity {

    @NotNull
    @Min(1)
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "main_id")
    private Integer id;

    @NotNull
    @Column(name = "nameCustomer")
    private String nameCustomer;

    @NotNull
    @Column(name = "nameShop")
    private String nameShop;

    @NotNull
    @OneToMany(mappedBy = "main")
    private Set<ProductEntity> productSet;

    public MainEntity() {

    }

    public MainEntity(String nameCustomer, String nameShop) {
        this.nameCustomer = nameCustomer;
        this.nameShop = nameShop;
        this.productSet = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameDistributor) {
        this.nameShop = nameDistributor;
    }

    public Set<ProductEntity> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<ProductEntity> productSet) {
        this.productSet = productSet;
    }
}
