package com.example.cart.dto;

import com.example.cart.entity.BaseEntity;

import java.io.Serializable;
import java.util.Objects;

public class Product extends BaseEntity implements Serializable {
    private Integer id;
    private Integer categoryId;
    private String itemType;
    private String title;
    private Long price;
    private Integer num;
    private Integer status;
    private Integer priority;
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(categoryId, product.categoryId) && Objects.equals(itemType, product.itemType) && Objects.equals(title, product.title) && Objects.equals(price, product.price) && Objects.equals(num, product.num) && Objects.equals(status, product.status) && Objects.equals(priority, product.priority) && Objects.equals(isDelete, product.isDelete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, categoryId, itemType, title, price, num, status, priority, isDelete);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", itemType='" + itemType + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", status=" + status +
                ", priority=" + priority +
                ", isDelete=" + isDelete +
                '}';
    }
}
