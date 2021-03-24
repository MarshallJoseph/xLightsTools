package ca.marshall.xlightstools;

public class Website {
    private String title;
    private String url;
    private String baseUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Website(String title, String url, String baseUrl) {
        this.title = title;
        this.url = url;
        this.baseUrl = baseUrl;
    }
}
