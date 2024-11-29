package com.jspider.product_management.jdbc.curd.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Product implements Serializable {
    private int product_id;
    private String product_name;
    private String product_color;
    private double product_price;
    private LocalDate product_mfd;
    private LocalDate product_exp;

    public Product(){
        super();
    }

    public Product(int product_id, String product_name, String product_color, double product_price, LocalDate product_mfd, LocalDate product_exp) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_color = product_color;
        this.product_price = product_price;
        this.product_mfd = product_mfd;
        this.product_exp = product_exp;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public LocalDate getProduct_mfd() {
        return product_mfd;
    }

    public void setProduct_mfd(LocalDate product_mfd) {
        this.product_mfd = product_mfd;
    }

    public LocalDate getProduct_exp() {
        return product_exp;
    }

    public void setProduct_exp(LocalDate product_exp) {
        this.product_exp = product_exp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_id == product.product_id && Double.compare(product_price, product.product_price) == 0 && Objects.equals(product_name, product.product_name) && Objects.equals(product_color, product.product_color) && Objects.equals(product_mfd, product.product_mfd) && Objects.equals(product_exp, product.product_exp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, product_name, product_color, product_price, product_mfd, product_exp);
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_color='" + product_color + '\'' +
                ", product_price=" + product_price +
                ", product_mfd=" + product_mfd +
                ", product_exp=" + product_exp +
                '}';
    }
}
