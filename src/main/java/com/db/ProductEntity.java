package com.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "prod_id")
    private Integer prod_id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Integer count;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "fk_main_id")
    private MainEntity main;

    public ProductEntity() {

    }

    public ProductEntity(String name, Integer count, Double price, MainEntity main) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.main = main;
    }

    public Integer getProd_id() {
        return prod_id;
    }

    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MainEntity getMain() {
        return main;
    }

    public void setMain(MainEntity main) {
        this.main = main;
    }
}