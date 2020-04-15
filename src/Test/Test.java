package Test;

import Model.Category;
import Model.Page;
import Util.DatabaseOperations;
import Util.MysqlConnect;
import Util.Util2;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


public class Test {

    public static void main(String[] args) throws IOException, SQLException {
        String url = "";
        Page samplePage = new Page("pageName" , url, "url/fullstore");
        Util2 testUtil = new Util2();
        MysqlConnect mysqlConnect = new MysqlConnect();
        DatabaseOperations dboperations = new DatabaseOperations(mysqlConnect);

        testUtil.getAllCategories(pageAmazon);

      //  dboperations.addCategoryToServer(categories);

        dboperations.getAllCategories();

        testUtil.parsePage(pageAmazon);

    dboperations.addProductsToServer(testUtil.getProductList());



    }
}
