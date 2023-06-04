package today.whatisthaodoing.ShortenURL.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonSerialize
@JsonDeserialize
@Document(collation = "shortURL")
public class ShortenURL {


    @JsonProperty
    private String originalURL;
    @Id
    @JsonProperty
    private String shortedURL;

    public ShortenURL() {
    }

    public ShortenURL(String originalURL, String shortedURL) {
        this.originalURL = originalURL;
        this.shortedURL = shortedURL;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortedURL() {
        return shortedURL;
    }

    public void setShortedURL(String shortedURL) {
        this.shortedURL = shortedURL;
    }
}
