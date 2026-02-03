package dev.firecrawl.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Response from a map request.
 */
public class MapResponse extends BaseResponse {
    private static final Gson GSON = new Gson();

    // Accept either an array of strings or an array of objects for `links`
    private JsonElement links;

    /**
     * Returns the links found in the map, normalized to an array of URLs (strings).
     * If link items are objects, tries to extract the `url` field; otherwise uses toString().
     *
     * @return the links array (never null)
     */
    public String[] getLinks() {
        if (links == null || links.isJsonNull()) {
            return new String[0];
        }
        if (links.isJsonArray()) {
            JsonArray arr = links.getAsJsonArray();
            List<String> out = new ArrayList<>(arr.size());
            for (JsonElement el : arr) {
                if (el == null || el.isJsonNull()) continue;
                if (el.isJsonPrimitive() && el.getAsJsonPrimitive().isString()) {
                    out.add(el.getAsString());
                } else if (el.isJsonObject()) {
                    JsonObject obj = el.getAsJsonObject();
                    if (obj.has("url") && obj.get("url").isJsonPrimitive()) {
                        out.add(obj.get("url").getAsString());
                    } else if (obj.has("href") && obj.get("href").isJsonPrimitive()) {
                        out.add(obj.get("href").getAsString());
                    } else {
                        out.add(obj.toString());
                    }
                } else {
                    out.add(el.toString());
                }
            }
            return out.toArray(new String[0]);
        }
        // Fallback: if it's a single string/object
        if (links.isJsonPrimitive() && links.getAsJsonPrimitive().isString()) {
            return new String[] { links.getAsString() };
        }
        if (links.isJsonObject()) {
            JsonObject obj = links.getAsJsonObject();
            if (obj.has("url") && obj.get("url").isJsonPrimitive()) {
                return new String[] { obj.get("url").getAsString() };
            }
            if (obj.has("href") && obj.get("href").isJsonPrimitive()) {
                return new String[] { obj.get("href").getAsString() };
            }
            return new String[] { obj.toString() };
        }
        return new String[0];
    }

    /**
     * Returns the links found in the map as structured items if available.
     *
     * @return the link items array (never null)
     */
    public MapLink[] getLinkItems() {
        if (links == null || links.isJsonNull()) {
            return new MapLink[0];
        }
        if (links.isJsonArray()) {
            JsonArray arr = links.getAsJsonArray();
            List<MapLink> out = new ArrayList<>(arr.size());
            for (JsonElement el : arr) {
                if (el == null || el.isJsonNull()) continue;
                if (el.isJsonObject()) {
                    try {
                        MapLink link = GSON.fromJson(el, MapLink.class);
                        if (link != null) out.add(link);
                    } catch (Exception ignored) { }
                } else if (el.isJsonPrimitive()) {
                    MapLink link = new MapLink();
                    link.url = el.getAsString();
                    out.add(link);
                }
            }
            return out.toArray(new MapLink[0]);
        }
        if (links.isJsonObject()) {
            try {
                MapLink link = GSON.fromJson(links, MapLink.class);
                return link != null ? new MapLink[]{ link } : new MapLink[0];
            } catch (Exception ignored) {
                return new MapLink[0];
            }
        }
        if (links.isJsonPrimitive()) {
            MapLink link = new MapLink();
            link.url = links.getAsString();
            return new MapLink[]{ link };
        }
        return new MapLink[0];
    }

    /**
     * Link item returned by the map endpoint.
     */
    public static class MapLink {
        private String url;
        private String title;
        private String description;

        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MapResponse that = (MapResponse) o;
        return Arrays.equals(this.getLinks(), that.getLinks());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(getLinks());
        return result;
    }

    @Override
    public String toString() {
        return "MapResponse{" +
                "success=" + isSuccess() +
                ", warning='" + getWarning() + '\'' +
                ", links=" + Arrays.toString(getLinks()) +
                '}';
    }
}
