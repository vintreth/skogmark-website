package ru.skogmark.go.gen.core.template;

import org.junit.Test;
import ru.skogmark.go.gen.core.dao.ConjunctionDao;
import ru.skogmark.go.gen.core.dao.SentenceDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TemplateBuilderTest {
    private final TemplateBuilder templateBuilder = templateBuilder();

    @Test
    public void custom() {
    }

    @Test
    public void sentence() {
    }

    @Test
    public void none() {
    }

    @Test
    public void subject() {
    }

    @Test
    public void subject1() {
    }

    @Test
    public void predicate() {
    }

    @Test
    public void adverbial() {
    }

    @Test
    public void single() {
    }

    @Test
    public void list() {
    }

    @Test
    public void signature() {
    }

    @Test
    public void conjunction() {
    }

    @Test
    public void complex() {
    }

    @Test
    public void compound() {
    }

    @Test
    public void shouldBuildTemplateWithCorrectCommaRepresentation() {
        String custom0 = "custom0";
        String custom1 = "custom1";

        Template template = templateBuilder.custom(custom0).comma().custom(custom1).build();

        assertNotNull(template);
        assertFalse(template.getTemplateParts().isEmpty());
        assertEquals(3, template.getTemplateParts().size());
        assertTrue(template.getTemplateParts().get(1).getContent().isPresent());
        assertEquals(", ", template.getTemplateParts().get(1).getContent().get());
        assertEquals(",", template.getTemplateParts().get(1).getCode());
    }

    @Test
    public void shouldBuildTemplateWithCorrectSpaceRepresentation() {
        String custom0 = "custom0";
        String custom1 = "custom1";

        Template template = templateBuilder.custom(custom0).space().custom(custom1).build();

        assertNotNull(template);
        assertFalse(template.getTemplateParts().isEmpty());
        assertEquals(3, template.getTemplateParts().size());
        assertTrue(template.getTemplateParts().get(1).getContent().isPresent());
        assertEquals(" ", template.getTemplateParts().get(1).getContent().get());
        assertEquals(" ", template.getTemplateParts().get(1).getCode());
    }

    @Test
    public void shouldBuildTemplateWithCustomWeightValue() {
        String custom0 = "custom0";
        String custom1 = "custom1";

        Template template = templateBuilder.custom(custom0).space().custom(custom1).weight(0.3f).build();

        assertNotNull(template);
        assertFalse(template.getTemplateParts().isEmpty());
        assertEquals(3, template.getTemplateParts().size());
        assertEquals(0.3f, template.getWeight(), 0);
    }

    @Test
    public void shouldBuildTemplateWithDefaultWeightValue() {
        String custom0 = "custom0";
        String custom1 = "custom1";

        Template template = templateBuilder.custom(custom0).space().custom(custom1).build();

        assertNotNull(template);
        assertFalse(template.getTemplateParts().isEmpty());
        assertEquals(3, template.getTemplateParts().size());
        assertEquals(0.5f, template.getWeight(), 0);
    }

    private TemplateBuilder templateBuilder() {
        SentenceDao sentenceDao = mock(SentenceDao.class);
        ConjunctionDao conjunctionDao = mock(ConjunctionDao.class);
        return new TemplateBuilder(sentenceDao, conjunctionDao);
    }
}