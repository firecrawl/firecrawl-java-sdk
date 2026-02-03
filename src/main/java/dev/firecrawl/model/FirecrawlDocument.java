package dev.firecrawl.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a document retrieved from the Firecrawl API.
 */
public class FirecrawlDocument {
    private String markdown;
    private String summary;
    private String html;
    private String rawHtml;
    private String screenshot;
    private String[] links;
    private Actions actions;
    private ChangeTracking changeTracking;
    private Map<String, Object> branding;
    private String warning;
    private Map<String, Object> metadata;

    /**
     * Returns the document content in Markdown format.
     *
     * @return the Markdown content
     */
    public String getMarkdown() {
        return markdown;
    }

    /**
     * Returns the summary of the page if requested.
     *
     * @return the summary content
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Returns the document content in HTML format.
     *
     * @return the HTML content
     */
    public String getHtml() {
        return html;
    }

    /**
     * Returns the raw HTML content of the document.
     *
     * @return the raw HTML content
     */
    public String getRawHtml() {
        return rawHtml;
    }

    /**
     * Returns the screenshot of the document (base64 encoded).
     *
     * @return the screenshot
     */
    public String getScreenshot() {
        return screenshot;
    }

    /**
     * Returns the links found in the document.
     *
     * @return the links
     */
    public String[] getLinks() {
        return links;
    }

    /**
     * Returns the actions results if actions were requested.
     *
     * @return actions results
     */
    public Actions getActions() {
        return actions;
    }

    /**
     * Returns the change tracking info if requested.
     *
     * @return change tracking info
     */
    public ChangeTracking getChangeTracking() {
        return changeTracking;
    }

    /**
     * Returns branding info if provided.
     */
    public Map<String, Object> getBranding() {
        return branding;
    }

    /**
     * Returns warning if provided inside the data object.
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Returns the metadata associated with the document.
     *
     * @return the metadata
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * Returns the document content as text (alias for getMarkdown()).
     *
     * @return the text content
     */
    public String getText() {
        return markdown;
    }

    // Nested types for v2 response
    public static class Actions {
        private String[] screenshots;
        private ScrapeItem[] scrapes;
        private JavascriptReturn[] javascriptReturns;
        private String[] pdfs;

        public String[] getScreenshots() { return screenshots; }
        public ScrapeItem[] getScrapes() { return scrapes; }
        public JavascriptReturn[] getJavascriptReturns() { return javascriptReturns; }
        public String[] getPdfs() { return pdfs; }

        public static class ScrapeItem {
            private String url;
            private String html;
            public String getUrl() { return url; }
            public String getHtml() { return html; }
        }

        public static class JavascriptReturn {
            private String type;
            private Object value;
            public String getType() { return type; }
            public Object getValue() { return value; }
        }
    }

    public static class ChangeTracking {
        private String previousScrapeAt;
        private String changeStatus;
        private String visibility;
        private String diff;
        private Map<String, Object> json;

        public String getPreviousScrapeAt() { return previousScrapeAt; }
        public String getChangeStatus() { return changeStatus; }
        public String getVisibility() { return visibility; }
        public String getDiff() { return diff; }
        public Map<String, Object> getJson() { return json; }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirecrawlDocument that = (FirecrawlDocument) o;
        return Objects.equals(markdown, that.markdown) &&
                Objects.equals(summary, that.summary) &&
                Objects.equals(html, that.html) &&
                Objects.equals(rawHtml, that.rawHtml) &&
                Objects.equals(screenshot, that.screenshot) &&
                Arrays.equals(links, that.links) &&
                Objects.equals(actions, that.actions) &&
                Objects.equals(changeTracking, that.changeTracking) &&
                Objects.equals(branding, that.branding) &&
                Objects.equals(warning, that.warning) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(markdown, summary, html, rawHtml, screenshot, actions, changeTracking, branding, warning, metadata);
        result = 31 * result + Arrays.hashCode(links);
        return result;
    }

    @Override
    public String toString() {
        return "FirecrawlDocument{" +
                "markdown='" + (markdown != null ? markdown.substring(0, Math.min(markdown.length(), 50)) + "..." : null) + '\'' +
                ", summary='" + (summary != null ? summary.substring(0, Math.min(summary.length(), 50)) + "..." : null) + '\'' +
                ", html='" + (html != null ? html.substring(0, Math.min(html.length(), 50)) + "..." : null) + '\'' +
                ", rawHtml='" + (rawHtml != null ? rawHtml.substring(0, Math.min(rawHtml.length(), 50)) + "..." : null) + '\'' +
                ", screenshot='" + (screenshot != null ? "[base64 data]" : null) + '\'' +
                ", links=" + Arrays.toString(links) +
                ", actions=" + (actions != null ? "present" : "null") +
                ", changeTracking=" + (changeTracking != null ? "present" : "null") +
                ", branding=" + (branding != null ? "present" : "null") +
                ", warning='" + warning + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
