package com.pluralsight;

public class Product {

    private String productName,sku,department;
    private String price;

    public Product(String sku,String productName, String price, String department) {
        this.productName = productName;
        this.department = department;
        this.price = price;
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    public String getDepartment() {
        return department;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}