package Test;

import Model.Page;
import Util.MysqlConnect;
import Util.Util2;

import java.io.IOException;
import java.sql.*;


public class Test {

    public static void main(String[] args) throws IOException, SQLException {

        Page pageAmazon = new Page("amazon" , "https://www.amazon.com.tr", "https://www.amazon.com.tr/gp/site-directory?ref_=nav_em_T1_0_2_2_12__fullstore");
        Util2 testUtil = new Util2();

       // testUtil.getAllCategories(pageAmazon);
       // testUtil.parsePage(pageAmazon);


        //String webSiteDoc = Util2.getURLSource("https://www.zirvemotomotiv.com.tr");

       // Util2.wrapToDocument(webSiteDoc);

       // pageAmazon.showAllCategories();

        MysqlConnect mysqlConnect = new MysqlConnect();


        String sql = "SELECT * FROM `product` ";
        try {
            Statement stmt = mysqlConnect.connect().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String s = rs.getString("product_name");
                System.out.println(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }



    }
}
