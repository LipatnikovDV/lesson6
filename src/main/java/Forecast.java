import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class Forecast {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .build();

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment("25123")
                .addQueryParameter("apikey", "HjuKxobyPtcuSHiTiwaUYtEjXWjlI7rd")
                .addQueryParameter("language", "ru-ru")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();

        Integer codeResponse = response.code();
        System.out.println(codeResponse);
        String wetherResponse = (response.body()).string();
        System.out.println(wetherResponse);


        String wetherDate = objectMapper.readTree(wetherResponse).get("Headline").at("/EffectiveDate").asText();
        System.out.println("Дата:" + wetherDate);
        String wetherText = objectMapper.readTree(wetherResponse).get("Headline").at("/Text").asText();
        System.out.println(wetherText);
        String wetherTemperature = objectMapper.readTree(wetherResponse).get("Headline").at("/Temperature").asText();
        System.out.println(wetherTemperature);

        //У меня закончились попытки запросов.... :)



    }
}
