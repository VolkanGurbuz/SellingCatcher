package Test;

import Model.Page;
import Util.Util2;


public class Test {

    public static void main(String[] args) {

        Page pageAmazon = new Page("amazon" , "https://www.amazon.com.tr", "https://www.amazon.com.tr/gp/site-directory?ref_=nav_em_T1_0_2_2_12__fullstore");
        Util2 testUtil = new Util2();
       testUtil.parsePage(pageAmazon);

       // testUtil.getAllCategories(pageAmazon);

    }
}
