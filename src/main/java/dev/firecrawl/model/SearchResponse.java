package dev.firecrawl.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Response from a search request.
 */
public class SearchResponse extends BaseResponse {
    private static final Gson GSON = new Gson();

    // Accept either an array or an object for the `data` field from the API
    private JsonElement data;
    private String id;
    private Integer creditsUsed;

    /**
     * Returns the search results as an array, normalizing various API shapes.
     *
     * @return the search results array (never null)
     */
    public SearchResult[] getData() {
        SearchResult[] web = parseResultsFromSource("web");
        return web != null ? web : parseResults();
    }

    /**
     * Returns the search results as a list.
     *
     * @return the search results list (never null)
     */
    public List<SearchResult> getResults() {
        SearchResult[] arr = getData();
        if (arr.length == 0) return Collections.emptyList();
        return Arrays.asList(arr);
    }

    /**
     * Returns web search results if available.
     */
    public SearchResult[] getWebResults() {
        SearchResult[] web = parseResultsFromSource("web");
        return web != null ? web : new SearchResult[0];
    }

    /**
     * Returns image search results if available.
     */
    public SearchImageResult[] getImageResults() {
        JsonArray arr = getSourceArray("images");
        if (arr == null) return new SearchImageResult[0];
        java.util.ArrayList<SearchImageResult> list = new java.util.ArrayList<>();
        for (JsonElement el : arr) {
            if (el != null && el.isJsonObject()) {
                try {
                    SearchImageResult r = GSON.fromJson(el, SearchImageResult.class);
                    if (r != null) list.add(r);
                } catch (Exception ignored) { }
            }
        }
        return list.toArray(new SearchImageResult[0]);
    }

    /**
     * Returns news search results if available.
     */
    public SearchNewsResult[] getNewsResults() {
        JsonArray arr = getSourceArray("news");
        if (arr == null) return new SearchNewsResult[0];
        java.util.ArrayList<SearchNewsResult> list = new java.util.ArrayList<>();
        for (JsonElement el : arr) {
            if (el != null && el.isJsonObject()) {
                try {
                    SearchNewsResult r = GSON.fromJson(el, SearchNewsResult.class);
                    if (r != null) list.add(r);
                } catch (Exception ignored) { }
            }
        }
        return list.toArray(new SearchNewsResult[0]);
    }

    /**
     * Returns the request id if provided by the API.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the credits used if provided by the API.
     */
    public Integer getCreditsUsed() {
        return creditsUsed;
    }

    private SearchResult[] parseResults() {
        if (data == null || data.isJsonNull()) {
            return new SearchResult[0];
        }
        if (data.isJsonArray()) {
            return normalizeArray(data.getAsJsonArray());
        }
        if (data.isJsonObject()) {
            JsonObject obj = data.getAsJsonObject();
            // Common shapes: { data: [...] } or { results: [...] }
            if (obj.has("results") && obj.get("results").isJsonArray()) {
                return normalizeArray(obj.getAsJsonArray("results"));
            }
            if (obj.has("data") && obj.get("data").isJsonArray()) {
                return normalizeArray(obj.getAsJsonArray("data"));
            }
            // If data is an object, check inside it for arrays
            if (obj.has("data") && obj.get("data").isJsonObject()) {
                JsonObject dataObj = obj.getAsJsonObject("data");
                if (dataObj.has("results") && dataObj.get("results").isJsonArray()) {
                    return normalizeArray(dataObj.getAsJsonArray("results"));
                }
                if (dataObj.has("items") && dataObj.get("items").isJsonArray()) {
                    return normalizeArray(dataObj.getAsJsonArray("items"));
                }
                if (dataObj.has("organic_results") && dataObj.get("organic_results").isJsonArray()) {
                    return normalizeArray(dataObj.getAsJsonArray("organic_results"));
                }
                if (dataObj.has("webPages") && dataObj.get("webPages").isJsonObject()) {
                    JsonObject webPages = dataObj.getAsJsonObject("webPages");
                    if (webPages.has("value") && webPages.get("value").isJsonArray()) {
                        return normalizeArray(webPages.getAsJsonArray("value"));
                    }
                }
                if (dataObj.has("value") && dataObj.get("value").isJsonArray()) {
                    return normalizeArray(dataObj.getAsJsonArray("value"));
                }
                // Also, within data object, check v2 sources
                for (String key : new String[]{"web", "news", "images"}) {
                    if (dataObj.has(key)) {
                        if (dataObj.get(key).isJsonArray()) {
                            return normalizeArray(dataObj.getAsJsonArray(key));
                        } else if (dataObj.get(key).isJsonObject()) {
                            JsonObject src2 = dataObj.getAsJsonObject(key);
                            if (src2.has("results") && src2.get("results").isJsonArray()) {
                                return normalizeArray(src2.getAsJsonArray("results"));
                            }
                            if (src2.has("organic_results") && src2.get("organic_results").isJsonArray()) {
                                return normalizeArray(src2.getAsJsonArray("organic_results"));
                            }
                            if (src2.has("items") && src2.get("items").isJsonArray()) {
                                return normalizeArray(src2.getAsJsonArray("items"));
                            }
                            if (src2.has("value") && src2.get("value").isJsonArray()) {
                                return normalizeArray(src2.getAsJsonArray("value"));
                            }
                        }
                    }
                }
            }
            // Additional common containers
            if (obj.has("organic_results") && obj.get("organic_results").isJsonArray()) {
                return normalizeArray(obj.getAsJsonArray("organic_results"));
            }
            if (obj.has("items") && obj.get("items").isJsonArray()) {
                return normalizeArray(obj.getAsJsonArray("items"));
            }
            if (obj.has("webPages") && obj.get("webPages").isJsonObject()) {
                JsonObject webPages = obj.getAsJsonObject("webPages");
                if (webPages.has("value") && webPages.get("value").isJsonArray()) {
                    return normalizeArray(webPages.getAsJsonArray("value"));
                }
            }
            if (obj.has("value") && obj.get("value").isJsonArray()) {
                return normalizeArray(obj.getAsJsonArray("value"));
            }
            // v2 sources shape: { web: [...], news: [...], images: [...] } or objects containing arrays
            for (String key : new String[]{"web", "news", "images"}) {
                if (obj.has(key)) {
                    if (obj.get(key).isJsonArray()) {
                        return normalizeArray(obj.getAsJsonArray(key));
                    } else if (obj.get(key).isJsonObject()) {
                        JsonObject src = obj.getAsJsonObject(key);
                        if (src.has("results") && src.get("results").isJsonArray()) {
                            return normalizeArray(src.getAsJsonArray("results"));
                        }
                        if (src.has("organic_results") && src.get("organic_results").isJsonArray()) {
                            return normalizeArray(src.getAsJsonArray("organic_results"));
                        }
                        if (src.has("items") && src.get("items").isJsonArray()) {
                            return normalizeArray(src.getAsJsonArray("items"));
                        }
                        if (src.has("value") && src.get("value").isJsonArray()) {
                            return normalizeArray(src.getAsJsonArray("value"));
                        }
                    }
                }
            }
            // Fallback: try to treat the object itself as a single SearchResult (normalized)
            try {
                JsonObject normalized = normalizeObject(obj);
                SearchResult single = GSON.fromJson(normalized, SearchResult.class);
                return single != null ? new SearchResult[]{ single } : new SearchResult[0];
            } catch (Exception ignored) {
                return new SearchResult[0];
            }
        }
        return new SearchResult[0];
    }

    private SearchResult[] parseResultsFromSource(String key) {
        JsonArray arr = getSourceArray(key);
        if (arr == null) return null;
        return normalizeArray(arr);
    }

    private JsonArray getSourceArray(String key) {
        if (data == null || data.isJsonNull()) return null;
        if (data.isJsonArray()) {
            // treat array as direct web results
            return key != null && "web".equals(key) ? data.getAsJsonArray() : null;
        }
        if (!data.isJsonObject()) return null;
        JsonObject obj = data.getAsJsonObject();

        // data may nest sources under "data"
        if (obj.has("data") && obj.get("data").isJsonObject()) {
            JsonObject dataObj = obj.getAsJsonObject("data");
            JsonArray nested = extractSourceArray(dataObj, key);
            if (nested != null) return nested;
        }

        return extractSourceArray(obj, key);
    }

    private JsonArray extractSourceArray(JsonObject obj, String key) {
        if (obj.has(key)) {
            JsonElement src = obj.get(key);
            if (src.isJsonArray()) return src.getAsJsonArray();
            if (src.isJsonObject()) {
                JsonObject srcObj = src.getAsJsonObject();
                if (srcObj.has("results") && srcObj.get("results").isJsonArray()) {
                    return srcObj.getAsJsonArray("results");
                }
                if (srcObj.has("data") && srcObj.get("data").isJsonArray()) {
                    return srcObj.getAsJsonArray("data");
                }
                if (srcObj.has("items") && srcObj.get("items").isJsonArray()) {
                    return srcObj.getAsJsonArray("items");
                }
                if (srcObj.has("organic_results") && srcObj.get("organic_results").isJsonArray()) {
                    return srcObj.getAsJsonArray("organic_results");
                }
                if (srcObj.has("value") && srcObj.get("value").isJsonArray()) {
                    return srcObj.getAsJsonArray("value");
                }
            }
        }
        return null;
    }

    private SearchResult[] normalizeArray(com.google.gson.JsonArray arr) {
        java.util.ArrayList<SearchResult> list = new java.util.ArrayList<>();
        for (JsonElement el : arr) {
            if (el != null && el.isJsonObject()) {
                JsonObject normalized = normalizeObject(el.getAsJsonObject());
                try {
                    SearchResult sr = GSON.fromJson(normalized, SearchResult.class);
                    if (sr != null) list.add(sr);
                } catch (Exception ignored) { }
            }
        }
        return list.toArray(new SearchResult[0]);
    }

    private JsonObject normalizeObject(JsonObject src) {
        JsonObject dst = new JsonObject();

        // Initialize fields to backfill progressively
        String title = null;
        String description = null;
        String url = null;

        // Normalize metadata if present and backfill
        if (src.has("metadata") && src.get("metadata").isJsonObject()) {
            JsonObject rawMeta = src.getAsJsonObject("metadata");
            JsonObject meta = new JsonObject();
            String mTitle = firstString(rawMeta, "title", "pageTitle", "ogTitle", "twitterTitle", "name", "headline");
            String mDesc = firstString(rawMeta, "description", "metaDescription", "ogDescription", "twitterDescription", "snippet", "summary");
            String mUrl = firstString(rawMeta, "sourceURL", "sourceUrl", "url", "link", "href", "permalink", "resolvedUrl", "canonical");
            Integer mStatus = firstInt(rawMeta, "statusCode", "status_code", "status");
            String mErr = firstString(rawMeta, "error", "err", "message", "warning", "detail");
            if (mTitle != null) meta.addProperty("title", mTitle);
            if (mDesc != null) meta.addProperty("description", mDesc);
            if (mUrl != null) meta.addProperty("sourceURL", mUrl);
            if (mStatus != null) meta.addProperty("statusCode", mStatus);
            if (mErr != null) meta.addProperty("error", mErr);
            dst.add("metadata", meta);
            if (mTitle != null) title = mTitle;
            if (mDesc != null) description = mDesc;
            if (mUrl != null) url = mUrl;
        }

        // Prefer explicit top-level values if present
        String t2 = firstString(src, "title", "name", "headline");
        String d2 = firstString(src, "description", "snippet", "summary");
        String u2 = firstString(src, "url", "link", "href", "sourceURL", "permalink");
        if (t2 != null) title = t2;
        if (d2 != null) description = d2;
        if (u2 != null) url = u2;

        // Look into nested containers for content and metadata
        JsonObject content = firstObject(src, "content", "page", "document", "doc");
        if (content == null && src.has("data") && src.get("data").isJsonObject()) {
            // Some shapes put an item inside a nested "data" object
            JsonObject dataObj = src.getAsJsonObject("data");
            // Try content again within this
            content = firstObject(dataObj, "content", "page", "document", "doc");
            if (title == null) title = firstString(dataObj, "title", "name", "headline");
            if (description == null) description = firstString(dataObj, "description", "snippet", "summary");
            if (url == null) url = firstString(dataObj, "url", "link", "href", "sourceURL", "permalink");
            if (dst.get("metadata") == null && dataObj.has("metadata") && dataObj.get("metadata").isJsonObject()) {
                dst.add("metadata", dataObj.getAsJsonObject("metadata"));
            }
        }

        if (content != null) {
            // Pull out content fields
            String markdown = firstString(content, "markdown", "md", "text", "content");
            String html = firstString(content, "html");
            String rawHtml = firstString(content, "rawHtml", "raw_html");
            if (markdown != null) dst.addProperty("markdown", markdown);
            if (html != null) dst.addProperty("html", html);
            if (rawHtml != null) dst.addProperty("rawHtml", rawHtml);
            if (content.has("links") && content.get("links").isJsonArray()) {
                dst.add("links", content.getAsJsonArray("links"));
            }
            if (content.has("screenshot")) {
                if (content.get("screenshot").isJsonPrimitive()) {
                    dst.add("screenshot", content.get("screenshot"));
                } else if (content.get("screenshot").isJsonObject()) {
                    // sometimes screenshots could be nested; ignore complex types safely
                }
            }
        } else {
            // If no content object, try to read content fields from src directly
            String markdown = firstString(src, "markdown", "md", "text");
            String html = firstString(src, "html");
            String rawHtml = firstString(src, "rawHtml", "raw_html");
            if (markdown != null) dst.addProperty("markdown", markdown);
            if (html != null) dst.addProperty("html", html);
            if (rawHtml != null) dst.addProperty("rawHtml", rawHtml);
            if (src.has("links") && src.get("links").isJsonArray()) {
                dst.add("links", src.getAsJsonArray("links"));
            }
            if (src.has("screenshot") && src.get("screenshot").isJsonPrimitive()) {
                dst.add("screenshot", src.get("screenshot"));
            }
        }

        if (title != null) dst.addProperty("title", title);
        if (description != null) dst.addProperty("description", description);
        if (url != null) dst.addProperty("url", url);

        Integer position = firstInt(src, "position", "rank");
        if (position != null) dst.addProperty("position", position);
        String category = firstString(src, "category");
        if (category != null) dst.addProperty("category", category);

        // If there was no metadata, synthesize minimal metadata from what we found
        if (!dst.has("metadata")) {
            JsonObject meta = new JsonObject();
            if (title != null) meta.addProperty("title", title);
            if (description != null) meta.addProperty("description", description);
            if (url != null) meta.addProperty("sourceURL", url);
            dst.add("metadata", meta);
        }

        return dst;
    }

    private String firstString(JsonObject obj, String... keys) {
        for (String k : keys) {
            if (obj.has(k) && obj.get(k).isJsonPrimitive()) {
                try {
                    String v = obj.get(k).getAsString();
                    if (v != null && !v.isEmpty()) return v;
                } catch (Exception ignored) { }
            }
        }
        return null;
    }

    private JsonObject firstObject(JsonObject obj, String... keys) {
        for (String k : keys) {
            if (obj.has(k) && obj.get(k).isJsonObject()) {
                return obj.getAsJsonObject(k);
            }
        }
        return null;
    }

    private Integer firstInt(JsonObject obj, String... keys) {
        for (String k : keys) {
            if (obj.has(k) && obj.get(k).isJsonPrimitive()) {
                try {
                    return obj.get(k).getAsInt();
                } catch (Exception ignored) {
                    try {
                        String s = obj.get(k).getAsString();
                        if (s != null && !s.isEmpty()) {
                            return Integer.parseInt(s.replaceAll("[^0-9-]", "").trim());
                        }
                    } catch (Exception ignored2) { }
                }
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SearchResponse that = (SearchResponse) o;
        return Arrays.equals(this.getData(), that.getData());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(getData());
        result = 31 * result + Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(creditsUsed);
        return result;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", id='" + id + '\'' +
                ", creditsUsed=" + creditsUsed +
                ", results=" + Arrays.toString(getData()) +
                '}';
    }
}
