package Test;

import Model.Page;
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


    public static void main(String[] args) {

        Page pageAmazon = new Page("amazon" , "https://www.amazon.com.tr");

        Util2 testUtil = new Util2();
        testUtil.parsePage(pageAmazon);

    }


}
