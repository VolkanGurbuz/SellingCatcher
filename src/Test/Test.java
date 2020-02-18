package Test;

import Util.Util2;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.*;



public class Test {


    public static void main(String[] args) {
        try
        {
            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();
            String webSiteDoc =  Util2.getURLSource("https://www.amazon.com.tr/gp/browse.html?node=13526049031&ref_=nav_em_T1_0_4_8_3_pca_shaving");


            Element userElement = (Element) xpath.evaluate("//li[contains(@id, 'result')]",Util2.wrapToDocument(webSiteDoc) ,
                    XPathConstants.NODE);

            System.out.println(userElement.getAttribute("id"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
