package hello.Service;

import hello.Service.Fetcher.FetchContentContext;
import hello.Service.Fetcher.FetchContentStrategy;
import hello.Service.Fetcher.Strategy.OkHttpStrategy;
import hello.Service.Model.ExchangeRate;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateService {

    public List<ExchangeRate> getRates() {
        FetchContentStrategy fetchContentStrategy = new OkHttpStrategy();
        //FetchContentStrategy fetchContentStrategy = new URLConnectionStrategy();
        FetchContentContext fetchContentContext = new FetchContentContext(fetchContentStrategy);
        String fetchedContent = fetchContentContext.fetchContent("https://api.exchangeratesapi.io/latest");

        return this.transform(fetchedContent);
    }

    private List<ExchangeRate> transform(String response) {
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
