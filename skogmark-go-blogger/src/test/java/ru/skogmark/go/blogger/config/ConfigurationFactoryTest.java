package ru.skogmark.go.blogger.config;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Kirill Bogdanov (ksbogdan@mts.ru)
 *         13.01.2017 18:14
 */
public class ConfigurationFactoryTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testNonConfiguredDir() throws Exception {
        ApplicationConfiguration applicationConfiguration = new ConfigurationFactory().getApplicationConfiguration();
    }
}