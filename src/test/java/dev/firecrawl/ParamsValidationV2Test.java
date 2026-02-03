package dev.firecrawl;

import dev.firecrawl.exception.ValidationException;
import dev.firecrawl.model.AgentParams;
import dev.firecrawl.model.BatchScrapeParams;
import dev.firecrawl.model.ExtractParams;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParamsValidationV2Test {

    @Test
    public void batchScrapeRequiresUrls() {
        BatchScrapeParams params = new BatchScrapeParams(new String[]{});
        ValidationException ex = assertThrows(ValidationException.class, params::validate);
        assertTrue(ex.getMessage().toLowerCase().contains("urls"));
    }

    @Test
    public void extractRequiresUrls() {
        ExtractParams params = new ExtractParams(new String[]{});
        ValidationException ex = assertThrows(ValidationException.class, params::validate);
        assertTrue(ex.getMessage().toLowerCase().contains("urls"));
    }

    @Test
    public void agentRequiresPrompt() {
        AgentParams params = new AgentParams(" ");
        ValidationException ex = assertThrows(ValidationException.class, params::validate);
        assertTrue(ex.getMessage().toLowerCase().contains("prompt"));
    }
}
