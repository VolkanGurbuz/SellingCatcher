package Model;

import java.util.Date;

public class Product {

    private String productId;
    private String productName;
    private double productPrice;
    private Date productDate;


    public Product(String productId, String productName, double productPrice, Date productDate) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDate = productDate;
    }


    public Product(String productId, String productName, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDate=" + productDate +
                '}';
    }
}
