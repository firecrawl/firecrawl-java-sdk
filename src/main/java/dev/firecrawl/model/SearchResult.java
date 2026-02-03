package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * A single result item from a search response.
 */
public class SearchResult {
    private String title;
    private String description;
    private String url;
    private String markdown;
    private String html;
    private String rawHtml;
    private String[] links;
    private String screenshot;
    private Integer position;
    private String category;
    private Metadata metadata;

    /**
     * Returns the title of the search result.
     *
     * @return the title
     */
    public String getTitle() {
        if (title != null && !title.isEmpty()) return title;
        if (metadata != null && metadata.getTitle() != null && !metadata.getTitle().isEmpty()) {
            return metadata.getTitle();
        }
        // Derive from markdown first heading if available
        String derived = deriveTitleFromMarkdown();
        return (derived != null && !derived.isEmpty()) ? derived : null;
    }

    /**
     * Returns the description of the search result.
     *
     * @return the description
     */
    public String getDescription() {
        if (description != null && !description.isEmpty()) return description;
        if (metadata != null && metadata.getDescription() != null && !metadata.getDescription().isEmpty()) {
            return metadata.getDescription();
        }
        // Derive a simple description from markdown if possible
        String derived = deriveDescriptionFromMarkdown();
        return (derived != null && !derived.isEmpty()) ? derived : null;
    }

    /**
     * Returns the URL of the search result.
     *
     * @return the URL
     */
    public String getUrl() {
        if (url != null && !url.isEmpty()) return url;
        if (metadata != null && metadata.getSourceURL() != null && !metadata.getSourceURL().isEmpty()) {
            return metadata.getSourceURL();
        }
        // Fallback to first link in links[] if present
        if (links != null && links.length > 0) {
            for (String l : links) {
                if (l != null && !l.isEmpty() && looksLikeUrl(l)) {
                    return l;
                }
            }
        }
        return null;
    }

    /**
     * Returns the content of the search result in Markdown format.
     *
     * @return the Markdown content
     */
    public String getMarkdown() {
        return markdown;
    }

    /**
     * Returns the content of the search result in HTML format.
     *
     * @return the HTML content
     */
    public String getHtml() {
        return html;
    }

    /**
     * Returns the raw HTML content of the search result.
     *
     * @return the raw HTML content
     */
    public String getRawHtml() {
        return rawHtml;
    }

    /**
     * Returns the links found in the search result.
     *
     * @return the links
     */
    public String[] getLinks() {
        return links;
    }

    /**
     * Returns the screenshot of the search result (base64 encoded).
     *
     * @return the screenshot
     */
    public String getScreenshot() {
        return screenshot;
    }

    /**
     * Returns the position of the search result if provided.
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Returns the category of the search result if provided.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the metadata associated with the search result.
     *
     * @return the metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    private String deriveTitleFromMarkdown() {
        if (markdown == null || markdown.isEmpty()) return null;
        String[] lines = markdown.split("\n");
        for (String line : lines) {
            if (line == null) continue;
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;
            // Take first non-empty line, strip leading markdown header symbols
            if (trimmed.startsWith("#")) {
                // remove leading #'s and spaces
                trimmed = trimmed.replaceFirst("^#+\\s*", "").trim();
            }
            return trimmed.isEmpty() ? null : trimmed;
        }
        return null;
    }

    private String deriveDescriptionFromMarkdown() {
        if (markdown == null || markdown.isEmpty()) return null;
        String[] lines = markdown.split("\n");
        boolean skippedTitle = false;
        for (String line : lines) {
            if (line == null) continue;
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;
            if (!skippedTitle) { // skip first non-empty as title
                skippedTitle = true;
                continue;
            }
            // Return the next meaningful line as description
            return trimmed;
        }
        return null;
    }

    private boolean looksLikeUrl(String s) {
        String lower = s.toLowerCase();
        return lower.startsWith("http://") || lower.startsWith("https://");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResult that = (SearchResult) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(url, that.url) &&
                Objects.equals(markdown, that.markdown) &&
                Objects.equals(html, that.html) &&
                Objects.equals(rawHtml, that.rawHtml) &&
                Arrays.equals(links, that.links) &&
                Objects.equals(screenshot, that.screenshot) &&
                Objects.equals(position, that.position) &&
                Objects.equals(category, that.category) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, description, url, markdown, html, rawHtml, screenshot, position, category, metadata);
        result = 31 * result + Arrays.hashCode(links);
        return result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", markdown='" + (markdown != null ? markdown.substring(0, Math.min(markdown.length(), 50)) + "..." : null) + '\'' +
                ", html='" + (html != null ? html.substring(0, Math.min(html.length(), 50)) + "..." : null) + '\'' +
                ", rawHtml='" + (rawHtml != null ? rawHtml.substring(0, Math.min(rawHtml.length(), 50)) + "..." : null) + '\'' +
                ", links=" + Arrays.toString(links) +
                ", screenshot='" + (screenshot != null ? "[base64 data]" : null) + '\'' +
                ", position=" + position +
                ", category='" + category + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    /**
     * Metadata for a search result.
     */
    public static class Metadata {
        private String title;
        private String description;
        private String sourceURL;
        private int statusCode;
        private String error;

        /**
         * Returns the title from the metadata.
         *
         * @return the title
         */
        public String getTitle() {
            return title;
        }

        /**
         * Returns the description from the metadata.
         *
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns the source URL from the metadata.
         *
         * @return the source URL
         */
        public String getSourceURL() {
            return sourceURL;
        }

        /**
         * Returns the HTTP status code from the metadata.
         *
         * @return the HTTP status code
         */
        public int getStatusCode() {
            return statusCode;
        }

        /**
         * Returns the error message from the metadata.
         *
         * @return the error message
         */
        public String getError() {
            return error;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Metadata metadata = (Metadata) o;
            return statusCode == metadata.statusCode &&
                    Objects.equals(title, metadata.title) &&
                    Objects.equals(description, metadata.description) &&
                    Objects.equals(sourceURL, metadata.sourceURL) &&
                    Objects.equals(error, metadata.error);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description, sourceURL, statusCode, error);
        }

        @Override
        public String toString() {
            return "Metadata{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", sourceURL='" + sourceURL + '\'' +
                    ", statusCode=" + statusCode +
                    ", error='" + error + '\'' +
                    '}';
        }
    }
}
