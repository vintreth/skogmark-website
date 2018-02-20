package ru.skogmark.go.gen.core.pipeline;

import org.junit.Test;
import ru.skogmark.go.gen.core.dao.ConjunctionDao;
import ru.skogmark.go.gen.core.dao.SentenceDao;
import ru.skogmark.go.gen.core.template.TemplateBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TemplateSelectionHandlerTest {
    @Test
    public void shouldPickTheMostWeightedTemplatesFirst() throws Exception {
        // given
        TemplateSelectionHandler templateSelectionHandler = createTemplateSelectionHandler();

        // when
        Map<String, Integer> results = doPick(templateSelectionHandler);

        // then
        assertTrue(results.get("слева  MAIN COMMA справа  MAIN") > results.get("SENTENCE#290 EMPTY MAIN"));
        assertTrue(results.get("SENTENCE#290 EMPTY MAIN") > results.get("MAIN EMPTY MAIN"));
        assertTrue(results.get("SENTENCE#20 EMPTY SECONDARY") > results.get("MAIN EMPTY MAIN"));
        assertTrue(results.get("SECONDARY CONJUNCTION#1 SENTENCE#10") > results.get("MAIN EMPTY MAIN"));
        assertTrue(results.get("MAIN EMPTY MAIN") > results.get("SENTENCE#729 EMPTY MAIN EMPTY SENTENCE#592"));
        assertTrue(results.get("MAIN EMPTY MAIN") > results.get("SENTENCE#131 EMPTY MAIN EMPTY SENTENCE#592"));
        assertTrue(results.get("MAIN EMPTY MAIN") > results.get("SENTENCE#728 EMPTY MAIN EMPTY SENTENCE#592"));
        assertTrue(results.get("MAIN EMPTY MAIN") > results.get("SENTENCE#130 EMPTY MAIN EMPTY SENTENCE#592"));
    }

    private static TemplateSelectionHandler createTemplateSelectionHandler() {
        TemplateBuilder templateBuilder = new TemplateBuilder(mock(SentenceDao.class), mock(ConjunctionDao.class));
        return new TemplateSelectionHandler(templateBuilder);
    }

    private static Map<String, Integer> doPick(TemplateSelectionHandler templateSelectionHandler) {
        WisdomPayload payload = new WisdomPayload();
        Map<String, Integer> results = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            templateSelectionHandler.handle(payload);
            String key = payload.getTemplate().asString();
            if (!results.containsKey(key)) {
                results.put(key, 1);
            } else {
                results.put(key, results.get(key) + 1);
            }
        }
        return results;
    }
}