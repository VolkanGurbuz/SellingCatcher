package Util;

import Model.Product;
import org.htmlcleaner.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public final class Util2 {

    //  also known as Helper class, is a class, which contains just static helper method

    private static HttpClient client = HttpClient.newHttpClient();
    public static final String firstPagePatter = "";
    public static XPathFactory xpf = XPathFactory.newInstance();
    public static XPath xpath = xpf.newXPath();


    public static String sendGetRequest(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            }
        } catch (Exception e) {
            System.err.println("Failure , " + e.toString());
        }
        return null;
    }

    public static String sendPostRequest(String URL, String postForm) {
        String response = null;
        try {
            BufferedReader br;
            StringBuilder sb = new StringBuilder();
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(postForm.getBytes("UTF-8"));
            con.getInputStream();

            if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            while ((response = br.readLine()) != null) {
                sb.append(response);
            }
            return sb.toString();
        } catch (Exception e) {
            System.err.println("Failure , " + e.toString());
        }
        return null;
    }

    //kanka simdi ben siteyi cekiyorum su sekilde

    public static String getURLSource(String url) throws IOException {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }

    private static String toString(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }

    public static Document wrapToDocument(String document) {
        try {
            HtmlCleaner cleaner = new HtmlCleaner();
            TagNode rootNode = cleaner.clean(document);
            DomSerializer domSerializer = new DomSerializer(new CleanerProperties());
            CleanerProperties properties = cleaner.getProperties();
            Serializer serializer = new SimpleHtmlSerializer(properties);
            StringWriter writer = new StringWriter();
            serializer.write(rootNode, writer, "UTF-8");

            //   dumpToFile(writer.toString(), i+"file.html");

            return domSerializer.createDOM(rootNode);
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void parseFirstPage(NodeList nodeListofThePage) {
        System.out.println(nodeListofThePage.getLength());
        try {
            for (int i = 0; i < nodeListofThePage.getLength() - 1; i++) {
                Node node = nodeListofThePage.item(i);
                if (node instanceof Element) {
                    Element element = (Element) node;
                    String productName = (String) xpath.evaluate(".//div[3]//div[1]//a//h2", element,
                            XPathConstants.STRING);

                    String productPrice = (String) xpath.evaluate(".//div[5]//div[1]//a//span[2]", element,
                            XPathConstants.STRING);

                    String productImg = (String) xpath.evaluate(".//div[2]//div[1]//div[1]//a//img//@src", element,
                            XPathConstants.STRING);

                    Product tempProduct = new Product(productName, Double.parseDouble(parsePrice(productPrice)), productImg);

                    System.out.println(tempProduct.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("parsePage 2 " + e.getMessage());
        }
    }
    public static void parseNonFirstPage(NodeList nodeListofThePage) {
        try {
            for (int i = 0; i < nodeListofThePage.getLength() - 1; i++) {
                Node node = nodeListofThePage.item(i);
                if (node instanceof Element) {
                    Element element = (Element) node;
                    String productName = (String) xpath.evaluate(".//div//span//div//div//div[2]//h2//a//span", element,
                            XPathConstants.STRING);

                    String productPrice = (String) xpath.evaluate(".//div//span//div//div//div[3]//div//div//a//span//span[1]", element,
                            XPathConstants.STRING);

                    String productPriceCheck = (String) xpath.evaluate(".//div//span//div//div//div[4]//div//div//a//span//span[1]", element,
                            XPathConstants.STRING);

                    String productPriceCheckOthers = (String) xpath.evaluate(".//div//span//div//div//div[3]//div//span[2]", element,
                            XPathConstants.STRING);

                    if (productPrice.isEmpty() && productPriceCheck.isEmpty()){
                        productPrice = productPriceCheckOthers;
                    }else if(productPrice.isEmpty()){
                        productPrice = productPriceCheck;
                    }

                    String productImg = (String) xpath.evaluate(".//div//span//div//div//span//a//div//@src", element,
                            XPathConstants.STRING);

                    Product tempProduct = new Product(productName, Double.parseDouble(parsePrice(productPrice)), productImg);
                    System.out.println(tempProduct.toString());
                }
            }
        } catch (Exception e) {
            System.err.println("parsePage 2 " + e.getMessage());
        }
    }

    public static void parsePage() {
        try {
            String mainUrl = "https://www.amazon.com.tr";
            String webSiteDoc = Util2.getURLSource("https://www.amazon.com.tr/gp/browse.html?node=13526049031&ref_=nav_em_T1_0_4_8_3_pca_shaving");

            Element pageElement = (Element) Util2.xpath.evaluate("//span[@class='pagnRA']//a", Util2.wrapToDocument(webSiteDoc), XPathConstants.NODE);

            int pageEnd = Integer.parseInt((String) Util2.xpath.evaluate("//span[@class='pagnDisabled']", Util2.wrapToDocument(webSiteDoc), XPathConstants.STRING));

            String pageLink = pageElement.getAttribute("href");
            pageLink = pageLink.substring(0, pageLink.length() - 1);

            for (int i = 1; i <= pageEnd; i++) {

                String newPageLink = mainUrl + pageLink + i;

                String webSiteDocPage = Util2.getURLSource(newPageLink);

                String exp = "//span[contains(@data-component-type, 's-search-results')]//div//div[contains(@class, 's-result-item')]";

                NodeList nodeListofNonFirstPage = (NodeList) Util2.xpath.evaluate(exp, Util2.wrapToDocument(webSiteDocPage),
                        XPathConstants.NODESET);

                NodeList nodeListofThePage = (NodeList) xpath.evaluate("//li[contains(@id, 'result')]", Util2.wrapToDocument(webSiteDocPage),
                        XPathConstants.NODESET);

                if (i == 1) {
                    Util2.parseFirstPage(nodeListofThePage);
                } else {
                    Util2.parseNonFirstPage(nodeListofNonFirstPage);
                }
            }
        } catch (Exception e) {
            System.err.println("parsePage" + e.getMessage());
        }
    }


    private static String parsePrice(String price) {
        try {
            List<Character> numberList = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',');
            String tempPrice = "";
            for (int i = 0; i < price.length(); i++) {
                if (numberList.contains(price.charAt(i))) {
                    if (price.charAt(i) == ',') {
                        tempPrice += ".";
                    } else {
                        tempPrice += (Character.toString(price.charAt(i)));
                    }
                }
            }

            return tempPrice;
        } catch (Exception e) {
            System.err.println("parsePrice " + e);
        }
        return null;
    }


    private static void dumpToFile(String content, String fileName) {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {

        }
    }

}
