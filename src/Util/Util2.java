package Util;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class Util2 {

    //  also known as Helper class, is a class, which contains just static helper method

    private static HttpClient client = HttpClient.newHttpClient();

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

    private static String toString(InputStream inputStream) throws IOException
    {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")))
        {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null)
            {
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

            return domSerializer.createDOM(rootNode);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }


}
