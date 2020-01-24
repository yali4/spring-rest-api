package hello.Service.Fetcher.Strategy;

import hello.Service.Fetcher.FetchContentStrategy;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class OkHttpStrategy implements FetchContentStrategy {
    @Override
    public String fetchContent(String fetchContentUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(fetchContentUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
