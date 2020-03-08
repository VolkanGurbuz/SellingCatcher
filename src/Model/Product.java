package Model;

import com.google.gson.Gson;

import java.util.Date;

public class Product {

    private String productId;
    private String productName;
    private double productPrice;
    private String productImg;
    private String productDate;
    private String categoryId;
    private Category category;


    public Product(String productName, double productPrice, String productImg, Category c) {
            this.productName = productName;
            this.productPrice = productPrice;
            this.productImg = productImg;
            this.category = c;
    }


    public Product(String productName, double productPrice, String productImg, Category c, String d) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.category = c;
        this.productDate = d;
    }


//    public Product(String productId, String productName, double productPrice, String productImg) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productImg = productImg;
//    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    //    @Override
//    public String toString() {
//        return "Product{" +
//                "productId='" + productId + '\'' +
//                ", productName='" + productName + '\'' +
//                ", productPrice=" + productPrice +
//                ", productImg='" + productImg + '\'' +
//                ", productDate=" + productDate +
//                ", categoryId='" + categoryId + '\'' +
//                '}';
//    }
//    @Override
//    public String toString() {
//        return "Product{" +
//                "productName='" + productName + '\'' +
//                ", productPrice=" + productPrice +
//                ", productImg='" + productImg + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this, Product.class);
    }
}
