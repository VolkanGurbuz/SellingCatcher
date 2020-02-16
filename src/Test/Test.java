package Test;

import Util.Util2;

public class Test {


    public static void main(String[] args) {

        String exampleJsonUrl = "https://volkangurbuz.com/restapi/product/readAllStudent.php";

        String getJson = Util2.sendGetRequest(exampleJsonUrl);

       // System.out.println("Get Json: " + getJson);

        String exampleJsonPostUrl = "https://www.amazon.com.tr/xa/dealcontent/v2/GetDeals";
        String formBody = "{\"requestMetadata\":{\"marketplaceID\":\"A33AVAJ2PDY3EV\",\"clientID\":\"goldbox_mobile_pc\",\"sessionID\":\"259-3784078-1319449\"},\"dealTargets\":[{\"dealID\":\"0e782399\"},{\"dealID\":\"10a84bf1\"},{\"dealID\":\"2cc95013\"},{\"dealID\":\"30b40347\"},{\"dealID\":\"4a06ebd5\"},{\"dealID\":\"55ee3d3f\"},{\"dealID\":\"8e495e40\"},{\"dealID\":\"bb3d3e4b\"},{\"dealID\":\"cffbb963\"},{\"dealID\":\"dd016133\"},{\"dealID\":\"ea7644de\"},{\"dealID\":\"f60ab753\"}],\"customerContext\":{\"languageOfPreference\":\"tr_TR\"}}";

        System.out.println("get post json: " + Util2.sendPostRequest(exampleJsonPostUrl, formBody));


    }


}
