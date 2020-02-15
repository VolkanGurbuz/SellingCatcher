package Test;

import Util.Util2;

public class Test {


    public static void main(String[] args) {

    String exampleJsonUrl = "https://volkangurbuz.com/restapi/product/readAllStudent.php";

    String getJson = Util2.sendGetRequest(exampleJsonUrl);

     System.out.println("Get Json: " +  getJson);

    }

}
