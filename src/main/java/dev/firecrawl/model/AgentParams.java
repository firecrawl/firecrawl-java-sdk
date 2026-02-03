package dev.firecrawl.model;

import dev.firecrawl.exception.ValidationException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Parameters for agent requests.
 */
public class AgentParams extends BaseParams<AgentParams> {
    private String prompt;
    private String[] urls;
    private Map<String, Object> schema;
    private Integer maxCredits;
    private Boolean strictConstrainToUrls;
    private String model;

    public AgentParams(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public void validate() throws ValidationException {
        super.validate();
        if (prompt == null || prompt.trim().isEmpty()) {
            throw new ValidationException("Prompt must not be empty", "prompt");
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public AgentParams setPrompt(String prompt) {
        this.prompt = prompt;
        return self();
    }

    public String[] getUrls() {
        return urls;
    }

    public AgentParams setUrls(String[] urls) {
        this.urls = urls;
        return self();
    }

    public Map<String, Object> getSchema() {
        return schema;
    }

    public AgentParams setSchema(Map<String, Object> schema) {
        this.schema = schema;
        return self();
    }

    public Integer getMaxCredits() {
        return maxCredits;
    }

    public AgentParams setMaxCredits(Integer maxCredits) {
        this.maxCredits = maxCredits;
        return self();
    }

    public Boolean getStrictConstrainToUrls() {
        return strictConstrainToUrls;
    }

    public AgentParams setStrictConstrainToUrls(Boolean strictConstrainToUrls) {
        this.strictConstrainToUrls = strictConstrainToUrls;
        return self();
    }

    public String getModel() {
        return model;
    }

    public AgentParams setModel(String model) {
        this.model = model;
        return self();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AgentParams that = (AgentParams) o;
        return Objects.equals(prompt, that.prompt) &&
                Arrays.equals(urls, that.urls) &&
                Objects.equals(schema, that.schema) &&
                Objects.equals(maxCredits, that.maxCredits) &&
                Objects.equals(strictConstrainToUrls, that.strictConstrainToUrls) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), prompt, schema, maxCredits, strictConstrainToUrls, model);
        result = 31 * result + Arrays.hashCode(urls);
        return result;
    }

    @Override
    public String toString() {
        return "AgentParams{" +
                "prompt='" + prompt + '\'' +
                ", urls=" + Arrays.toString(urls) +
                ", schema=" + schema +
                ", maxCredits=" + maxCredits +
                ", strictConstrainToUrls=" + strictConstrainToUrls +
                ", model='" + model + '\'' +
                '}';
    }
}
