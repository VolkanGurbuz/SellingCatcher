package Util;

import java.net.URI;
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

                if(response.statusCode() == 200){
                    return response.body();
                }
        }
        catch (Exception e){
            System.err.println("Failure , " + e.toString());
        }
        return null;
    }

}
