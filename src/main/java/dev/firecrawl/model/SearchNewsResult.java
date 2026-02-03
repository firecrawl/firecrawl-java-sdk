package dev.firecrawl.model;

import java.util.Objects;

/**
 * A single news result item from a search response.
 */
public class SearchNewsResult {
    private String title;
    private String url;
    private String snippet;
    private String source;
    private String sourceUrl;
    private String date;
    private String imageUrl;
    private Integer position;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getSource() {
        return source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchNewsResult that = (SearchNewsResult) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(url, that.url) &&
                Objects.equals(snippet, that.snippet) &&
                Objects.equals(source, that.source) &&
                Objects.equals(sourceUrl, that.sourceUrl) &&
                Objects.equals(date, that.date) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, snippet, source, sourceUrl, date, imageUrl, position);
    }

    @Override
    public String toString() {
        return "SearchNewsResult{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", snippet='" + snippet + '\'' +
                ", source='" + source + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", date='" + date + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", position=" + position +
                '}';
    }
}
