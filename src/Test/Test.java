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

        Page pageAmazon = new Page("amazon" , "https://www.amazon.com.tr", "https://www.amazon.com.tr/gp/site-directory?ref_=nav_em_T1_0_2_2_12__fullstore");
        Util2 testUtil = new Util2();
        MysqlConnect mysqlConnect = new MysqlConnect();
        DatabaseOperations dboperations = new DatabaseOperations(mysqlConnect);

        testUtil.getAllCategories(pageAmazon);

      //  dboperations.addCategoryToServer(categories);

        dboperations.getAllCategories();

        testUtil.parsePage(pageAmazon);

    dboperations.addProductsToServer(testUtil.getProductList());


        //String webSiteDoc = Util2.getURLSource("https://www.zirvemotomotiv.com.tr");

       // Util2.wrapToDocument(webSiteDoc);

       // pageAmazon.showAllCategories();




    }
}
