package hello.Service.Fetcher.Strategy;

import hello.Service.Fetcher.FetchContentStrategy;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLConnectionStrategy implements FetchContentStrategy {
    @Override
    public String fetchContent(String fetchContentUrl) {
        try {
            URL url = new URL(fetchContentUrl);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String readLine;
            while ((readLine = reader.readLine()) != null) {
                response.append(readLine);
            }

            reader.close();
            return response.toString();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
