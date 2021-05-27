package com.WebDatabaseApplication.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_of_product")
    private String name;

    @Column(name = "model_of_product")
    private String modelProduct;

    @Column(name = "type_of_product")
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User author;

    @Column(name = "status")
    private String status;


    public Product() {
    }

    public Product(Long id, String name, String modelProduct, String type, User user,String status) {
        this.author=user;
        this.name = name;
        this.modelProduct = modelProduct;
        this.type = type;
        this.id = id;
        this.status=status;
    }

    public Product( String name, String modelProduct, String type, String status, User user) {
        this.author=user;
        this.name = name;
        this.modelProduct = modelProduct;
        this.type = type;
         this.status = status;

    }

    public Product(String name, String modelProduct, String type, User user) {
        this.author=user;
        this.name = name;
        this.modelProduct = modelProduct;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String title) {
        this.type = title;
    }

    public User getAuthor() {
        return author;
    }

     public String getAuthorName() {
        return author != null  ? author.getUsername() : "unknown author";
    }

    public void setAuthor(User autor) {
        this.author = autor;
    }
    public Long getAuthorId(){
        return author.getId();
    }

    public String getModelProduct() {
        return modelProduct;
    }

    public void setModelProduct(String model) {
        this.modelProduct = model;
    }
    public String getStatus() {
        return status;
    }

    public String getStatusName() {

        return status != null  ? getStatus() : "unknown status";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}