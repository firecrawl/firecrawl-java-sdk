package dev.firecrawl.model;

import java.util.Objects;

/**
 * A single image result item from a search response.
 */
public class SearchImageResult {
    private String title;
    private String url;
    private String imageUrl;
    private Integer imageWidth;
    private Integer imageHeight;
    private String source;
    private String sourceUrl;
    private Integer position;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public Integer getImageHeight() {
        return imageHeight;
    }

    public String getSource() {
        return source;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchImageResult that = (SearchImageResult) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(url, that.url) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(imageWidth, that.imageWidth) &&
                Objects.equals(imageHeight, that.imageHeight) &&
                Objects.equals(source, that.source) &&
                Objects.equals(sourceUrl, that.sourceUrl) &&
                Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, imageUrl, imageWidth, imageHeight, source, sourceUrl, position);
    }

    @Override
    public String toString() {
        return "SearchImageResult{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", source='" + source + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", position=" + position +
                '}';
    }
}
