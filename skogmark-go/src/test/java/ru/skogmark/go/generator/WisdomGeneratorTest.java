package ru.skogmark.go.generator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skogmark.go.domain.Wisdom;

import static org.junit.Assert.assertFalse;

/**
 * @author svip
 *         2016-11-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ContextConfiguration(locations = {"classpath:test-context.xml", "classpath:session-mock-context.xml"})
public class WisdomGeneratorTest {
    private WisdomGenerator wisdomGenerator;

    @Autowired
    public void setWisdomGenerator(WisdomGenerator wisdomGenerator) {
        this.wisdomGenerator = wisdomGenerator;
    }

    @Test
    public void testGeneration() throws Exception {
        Wisdom wisdom = wisdomGenerator.generateOne();
        assertFalse(wisdom.getContent().isEmpty());
    }

    @Test
    public void testAdvancedGeneration() throws Exception {
        Wisdom wisdom = wisdomGenerator.generateOneAdvanced();
        assertFalse(wisdom.getContent().isEmpty());
    }
}