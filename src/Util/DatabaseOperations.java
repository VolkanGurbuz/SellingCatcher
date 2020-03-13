package Util;

import Model.Category;
import Model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseOperations {

    private MysqlConnect mysqlConnect;
    private HashMap<String, String> categoriesMap = new HashMap<>();

    public DatabaseOperations(MysqlConnect mysqlConnect) {
        this.mysqlConnect = mysqlConnect;

    }

    public MysqlConnect getMysqlConnect() {
        return mysqlConnect;
    }

    public void setMysqlConnect(MysqlConnect mysqlConnect) {
        this.mysqlConnect = mysqlConnect;
    }

    public void addCategoryToServer(ArrayList<Category> categories) {
        try {

            String query = " insert into category (category_name, category_sub_name, category_link)"
                    + " values (?, ?, ?)";
            Connection con = mysqlConnect.connect();
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            con.setAutoCommit(false);
            categories.forEach(category -> {
                try {

                    preparedStmt.setString(1, category.getCategoryName());
                    preparedStmt.setString(2, category.getSubCategoryName());
                    preparedStmt.setString(3, category.getCategoryLink());

                    // execute the preparedstatement
                    preparedStmt.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });

            int[] numUpdates = preparedStmt.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }

    }


    public void getAllCategories() {
        try {

            String query = "select * from category";
            try {
                Statement stmt = mysqlConnect.connect().createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String category_name = rs.getString("category_name");
                    String category_sub_name = rs.getString("category_sub_name");
                    String category_id = rs.getString("category_id");

                    categoriesMap.put(category_name + " = " + category_sub_name, category_id);

                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysqlConnect.disconnect();
            }

        } catch (Exception e) {

            System.err.println("getAllCategories " + e);
        }

    }


    public void addProductsToServer(ArrayList<Product> products) {
        try {

            String query = " insert into product (product_name, product_price, product_image, product_date, category_id)"
                    + " values (?, ?, ?,? ,?)";
            Connection con = mysqlConnect.connect();
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            con.setAutoCommit(false);

            products.forEach(product -> {
                try {
                    String categoryId = categoriesMap.get(product.getCategory().getCategoryName() + " = " + product.getCategory().getSubCategoryName());
                    preparedStmt.setString(1, product.getProductName());
                    preparedStmt.setDouble(2, product.getProductPrice());
                    preparedStmt.setString(3, product.getProductImg());
                    preparedStmt.setString(4, product.getProductDate());
                    preparedStmt.setString(5, categoryId);
                    // execute the preparedstatement
                    preparedStmt.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });

            int[] numUpdates = preparedStmt.executeBatch();
            for (int i = 0; i < numUpdates.length; i++) {
                if (numUpdates[i] == -2)
                    System.out.println("Execution " + i +
                            ": unknown number of rows updated");
                else
                    System.out.println("Execution " + i +
                            "successful: " + numUpdates[i] + " rows updated");
            }
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }

    }


}
