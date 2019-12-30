package hello.Service;

import hello.Service.Model.ExchangeRate;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateService {

    public List<ExchangeRate> getRates() {

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

            JSONObject json = new JSONObject(response.toString());
            JSONObject rates = json.getJSONObject("rates");

            for (Object key : rates.keySet()) {

                String currency = (String) key;
                Double ratio = (Double) rates.get(currency);

                ExchangeRate rate = new ExchangeRate();
                rate.setCurrency(currency);
                rate.setRate(ratio);

                result.add(rate);
            }

        } catch(Exception e) {
            System.out.println(e);
        }

        return result;
    }

}
