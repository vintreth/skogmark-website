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
        Map<Integer, Integer> results = doPick(templateSelectionHandler);

        // then
        assertTrue(results.get(8) > results.get(10));
        assertTrue(results.get(10) > results.get(3));
        assertTrue(results.get(15) > results.get(3));
        assertTrue(results.get(16) > results.get(3));
        assertTrue(results.get(3) > results.get(11));
        assertTrue(results.get(3) > results.get(12));
        assertTrue(results.get(3) > results.get(13));
        assertTrue(results.get(3) > results.get(14));
        assertTrue(results.get(3) > results.get(1));
    }

    private static TemplateSelectionHandler createTemplateSelectionHandler() {
        TemplateBuilder templateBuilder = new TemplateBuilder(mock(SentenceDao.class), mock(ConjunctionDao.class));
        return new TemplateSelectionHandler(templateBuilder);
    }

    private static Map<Integer, Integer> doPick(TemplateSelectionHandler templateSelectionHandler) {
        WisdomPayload payload = new WisdomPayload();
        Map<Integer, Integer> results = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            templateSelectionHandler.handle(payload);
            Integer key = payload.getTemplate().getId();
            if (!results.containsKey(key)) {
                results.put(key, 1);
            } else {
                results.put(key, results.get(key) + 1);
            }
        }
        return results;
    }
}