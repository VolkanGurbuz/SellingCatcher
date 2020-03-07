package Model;

import com.google.gson.Gson;

import java.util.Date;

public class Product {

    private String productId;
    private String productName;
    private double productPrice;
    private String productImg;
    private Date productDate;
    private String categoryId;
    private Category category;


    public Product(String productId, String productName, double productPrice, String productImg, Date productDate, String categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImg = productImg;
        this.productDate = productDate;
        this.categoryId = categoryId;
    }


    public Product(String productName, double productPrice, String productImg, Category c) {
            this.productName = productName;
            this.productPrice = productPrice;
            this.productImg = productImg;
            this.category = c;
    }

//    public Product(String productId, String productName, double productPrice, String productImg) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productImg = productImg;
//    }

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

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
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
