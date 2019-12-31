package hello.Service;

import hello.Service.Model.ExchangeRate;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateService {

    public List<ExchangeRate> getRatesViaOkHttp() {

        List<ExchangeRate> result = new ArrayList<ExchangeRate>();

        String url = "https://api.exchangeratesapi.io/latest";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            return jsonToModels(response.body().string());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<ExchangeRate> getRatesViaURLConnection() {

        List<ExchangeRate> result = new ArrayList<ExchangeRate>();

        try {
            StringBuilder response = new StringBuilder();
            URL url = new URL("https://api.exchangeratesapi.io/latest");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String readLine;
            while ((readLine = reader.readLine()) != null) {
                response.append(readLine);
            }

            reader.close();

            return jsonToModels(response.toString());

        } catch(Exception e) {
            System.out.println(e);
        }

        return result;
    }

    private List<ExchangeRate> jsonToModels(String response) {
        List<ExchangeRate> result = new ArrayList<ExchangeRate>();
        JSONObject json = new JSONObject(response);
        JSONObject rates = json.getJSONObject("rates");

        for (Object key : rates.keySet()) {

            String currency = (String) key;
            Double ratio = (Double) rates.get(currency);

            ExchangeRate rate = new ExchangeRate();
            rate.setCurrency(currency);
            rate.setRate(ratio);

            result.add(rate);
        }

        return result;
    }

}
