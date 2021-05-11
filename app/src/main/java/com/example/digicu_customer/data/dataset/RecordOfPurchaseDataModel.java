package com.example.digicu_customer.data.dataset;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RecordOfPurchaseDataModel implements Serializable {
    private int id;
    private Date date;
    private int price;
    private List<ProductDataModel> productDataModels;
    private ShopDataModel shopDataModel;

    public RecordOfPurchaseDataModel(int id, Date date, int price, List<ProductDataModel> productDataModels, ShopDataModel shopDataModel) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.productDataModels = productDataModels;
        this.shopDataModel = shopDataModel;
    }

    @Override
    public String toString() {
        return "RecordOfPurchaseDataModel{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", productDataModels=" + productDataModels +
                ", shopDataModel=" + shopDataModel +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<ProductDataModel> getProductDataModels() {
        return productDataModels;
    }

    public void setProductDataModels(List<ProductDataModel> productDataModels) {
        this.productDataModels = productDataModels;
    }

    public ShopDataModel getShopDataModel() {
        return shopDataModel;
    }

    public void setShopDataModel(ShopDataModel shopDataModel) {
        this.shopDataModel = shopDataModel;
    }
}
