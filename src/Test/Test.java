package Test;

import Model.Product;
import Util.Util2;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.*;
import java.util.Arrays;
import java.util.List;


public class Test {
   static XPathFactory xpf = XPathFactory.newInstance();
   static XPath xpath = xpf.newXPath();

    public static void main(String[] args) {
        try
        {

            String mainUrl = "https://www.amazon.com.tr";


            String webSiteDoc =  Util2.getURLSource("https://www.amazon.com.tr/gp/browse.html?node=13526049031&ref_=nav_em_T1_0_4_8_3_pca_shaving");

            NodeList nodeList = (NodeList) xpath.evaluate("//li[contains(@id, 'result')]",Util2.wrapToDocument(webSiteDoc) ,
                    XPathConstants.NODESET);
            Element pageElement = (Element) xpath.evaluate("//span[@class='pagnRA']//a"  , Util2.wrapToDocument(webSiteDoc), XPathConstants.NODE);

            int pageEnd  = Integer.parseInt((String) xpath.evaluate("//span[@class='pagnDisabled']"  , Util2.wrapToDocument(webSiteDoc), XPathConstants.STRING));

            String pageLink  =  pageElement.getAttribute("href");
            pageLink = pageLink.substring(0 , pageLink.length() -1);

            for (int i = 1; i <= pageEnd ; i++) {

              String  newPageLink = pageLink + i;

              //  System.out.println("page: "  + newPageLink );

                String webSiteDocPage =  Util2.getURLSource(mainUrl + pageLink);

                NodeList nodeListofThePage = (NodeList) xpath.evaluate("//li[contains(@id, 'result')]",Util2.wrapToDocument(webSiteDocPage) ,
                        XPathConstants.NODESET);
                System.out.println("Page: " + i + "\n");
                parsePage(nodeListofThePage);
                System.out.println("\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void parsePage(NodeList nodeListofThePage) {
        System.out.println(nodeListofThePage.getLength());
        try {
            for (int i = 0; i < nodeListofThePage.getLength()-1; i++) {
                Node node= nodeListofThePage.item(i);
                if(node instanceof Element){
                    Element element = (Element) node;
                    String productName =(String) xpath.evaluate(".//div[3]//div[1]//a//h2",element ,
                            XPathConstants.STRING);

                    String productPrice =(String)   xpath.evaluate(".//div[5]//div[1]//a//span[2]",element ,
                            XPathConstants.STRING);

                    String productImg = (String) xpath.evaluate(".//div[2]//div[1]//div[1]//a//img//@src", element,
                            XPathConstants.STRING);

                    Product tempProduct = new Product(productName, parsePrice(productPrice), productImg);

                    System.out.println(tempProduct.toString());
                }
            }
        }
        catch (Exception e){
            System.err.println("parsePage 2 "  + e.getMessage());
        }
    }

    private static double parsePrice(String price){
        List<Character> numberList = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',');
        String tempPrice = "";
        for (int i=0; i <    price.length(); i++)
        {
            if (numberList.contains(price.charAt(i))){
                if (price.charAt(i) == ','){
                    tempPrice += ".";
                }
                else {
                    tempPrice += (Character.toString(price.charAt(i)));
                }
            }
        }

        return Double.parseDouble(tempPrice);

    }
}
