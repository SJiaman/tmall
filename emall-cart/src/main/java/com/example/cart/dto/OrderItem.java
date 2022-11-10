package com.example.cart.dto;

import com.example.cart.entity.BaseEntity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 订单中的商品数据
 */
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private Long price;
    private Integer num;
    private Integer shopId;
    private String shopName;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(oid, orderItem.oid) && Objects.equals(pid, orderItem.pid) && Objects.equals(title, orderItem.title) && Objects.equals(price, orderItem.price) && Objects.equals(num, orderItem.num) && Objects.equals(shopId, orderItem.shopId) && Objects.equals(shopName, orderItem.shopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, oid, pid, title, price, num, shopId, shopName);
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

}
