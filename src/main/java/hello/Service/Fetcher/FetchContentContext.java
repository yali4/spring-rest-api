package hello.Service.Fetcher;

public class FetchContentContext {
    protected FetchContentStrategy fetchContentStrategy;

    public FetchContentContext(FetchContentStrategy fetchContentStrategy) {
        this.fetchContentStrategy = fetchContentStrategy;
    }

    public String fetchContent(String fetchContentUrl) {
        return this.fetchContentStrategy.fetchContent(fetchContentUrl);
    }
}
