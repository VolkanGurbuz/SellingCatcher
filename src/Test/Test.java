package Test;

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



public class Test {


    public static void main(String[] args) {
        try
        {
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();
            String webSiteDoc =  Util2.getURLSource("https://www.amazon.com.tr/gp/browse.html?node=13526049031&ref_=nav_em_T1_0_4_8_3_pca_shaving");

            NodeList nodeList = (NodeList) xpath.evaluate("//li[contains(@id, 'result')]",Util2.wrapToDocument(webSiteDoc) ,
                    XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node= nodeList.item(i);
                if(node instanceof Element){
                    Element element = (Element) node;
                    String productName =(String) xpath.evaluate(".//div[3]//div[1]//a//h2",element ,
                            XPathConstants.STRING);

                    String productPrice =(String)   xpath.evaluate(".//div[5]//div[1]//a//span[2]",element ,
                            XPathConstants.STRING);

                    System.out.println("products: "+ productName + " productPrice: " + productPrice) ;

                }
            }

           // System.out.println(userElement.getAttribute("id"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
