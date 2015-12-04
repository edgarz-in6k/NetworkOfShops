package com.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Product")
public class ProductEntity {

    @NotNull
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "prod_id")
    private Integer prod_id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Min(1)
    @Column(name = "count")
    private Integer count;

    @NotNull
    @Min(0)
    @Column(name = "price")
    private Double price;

    @NotNull
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