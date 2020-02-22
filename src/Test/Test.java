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

                String webSiteDocPage =  Util2.getURLSource(mainUrl + newPageLink);



                if (i == 1) {
                    NodeList nodeListofThePage = (NodeList) xpath.evaluate("//li[contains(@id, 'result')]", Util2.wrapToDocument(webSiteDocPage),
                            XPathConstants.NODESET);
                    System.out.println("Page: " + i);
                    System.out.println("Number Of Products: " + nodeListofThePage.getLength() + "\n");
                    parsePage(nodeListofThePage);
                    System.out.println("\n");
                }
                else{
                    NodeList nodeListofThePage = (NodeList) xpath.evaluate("//span[contains(@data-component-type, 's-search-results')]//div//div[contains(@class, 's-result-item')]",Util2.wrapToDocument(webSiteDocPage) ,
                            XPathConstants.NODESET);
                    System.out.println("Page: " + i);
                    System.out.println("Number Of Products: " + nodeListofThePage.getLength() + "\n");
                    parseAnotherPages(nodeListofThePage);
                    System.out.println("\n");
                }



            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void parsePage(NodeList nodeListofThePage) {
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

                    Product tempProduct = new Product(productName, Util2.parsePrice(productPrice), productImg);

                    System.out.println(tempProduct.toString());
                }
            }
        }
        catch (Exception e){
            System.err.println("parsePage 2 "  + e.getMessage());
        }
    }

    private static void parseAnotherPages(NodeList nodeListofThePage) {
        try {
            for (int i = 0; i < nodeListofThePage.getLength(); i++) {
                Node node= nodeListofThePage.item(i);
                if(node instanceof Element){
                    Element element = (Element) node;
                    String productName =(String) xpath.evaluate(".//div//span//div//div//div[2]//h2//a//span",element ,
                            XPathConstants.STRING);

                    String productPrice =(String)   xpath.evaluate(".//div//span//div//div//div[3]//div//div//a//span//span[1]",element ,
                            XPathConstants.STRING);

                    String productImg = (String) xpath.evaluate(".//div//span//div//div//span//a//div//img//@src", element,
                            XPathConstants.STRING);

                    Product tempProduct = new Product(productName, Util2.parsePrice(productPrice), productImg);

                    System.out.println(tempProduct.toString());
                }
            }
        }
        catch (Exception e){
            System.err.println("parsePage 2 "  + e.getMessage());
        }
    }
}
