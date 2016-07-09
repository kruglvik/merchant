package org.coursework.merchant.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String code;

    @NotNull
    private String storageCode;

    @NotNull
    @Size(max = 40)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private double price;

    private double discountRatio = 0;

    @NotNull
    private Date expirationDate;

    @CreatedDate
    private Date createdDate;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id +
                ", code='" + code + '\'' +
                ", storageCode='" + storageCode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discountRatio=" + discountRatio +
                ", expirationDate=" + expirationDate +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                Double.compare(product.discountRatio, discountRatio) == 0 &&
                Objects.equals(code, product.code) &&
                Objects.equals(storageCode, product.storageCode) &&
                Objects.equals(name, product.name) &&
                Objects.equals(expirationDate, product.expirationDate) &&
                Objects.equals(createdDate, product.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, storageCode, name, price, discountRatio, expirationDate, createdDate);
    }

}
