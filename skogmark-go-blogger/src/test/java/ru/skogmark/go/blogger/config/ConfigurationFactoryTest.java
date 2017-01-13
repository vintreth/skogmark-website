package ru.skogmark.go.blogger.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Kirill Bogdanov (ksbogdan@mts.ru)
 *         13.01.2017 18:14
 */
public class ConfigurationFactoryTest {
    private static final String TEST_EXECUTION_PATH = System.getProperty("user.dir")
        + "/src/test/resources/ru/skogmark/go/blogger/config/xml/bin";

    @Test
    public void testDefaultExternalCfg() throws Exception {
        ConfigurationFactory configurationFactory = new ConfigurationFactory();
        configurationFactory.setExecutionPath(TEST_EXECUTION_PATH);
        ApplicationConfiguration applicationConfiguration = configurationFactory.getApplicationConfiguration();

        assertNotNull(applicationConfiguration);
        assertEquals(100, applicationConfiguration.getPostSchedulerParams().getMaxTaskDelayMinutes());
        assertEquals(100, applicationConfiguration.getPostSchedulerParams().getTaskIntervalSec());
    }

    @Test
    public void testCustomExternalCfg() throws Exception {
        System.setProperty("app.configLocation", TEST_EXECUTION_PATH + "/../custom");
        ConfigurationFactory configurationFactory = new ConfigurationFactory();
        ApplicationConfiguration applicationConfiguration = configurationFactory.getApplicationConfiguration();

        assertNotNull(applicationConfiguration);
        assertEquals(200, applicationConfiguration.getPostSchedulerParams().getMaxTaskDelayMinutes());
        assertEquals(200, applicationConfiguration.getPostSchedulerParams().getTaskIntervalSec());
    }

    @Test
    public void testResourcesCfg() throws Exception {
        System.setProperty("app.configLocation", TEST_EXECUTION_PATH + "/../unknown");
        ConfigurationFactory configurationFactory = new ConfigurationFactory();
        ApplicationConfiguration applicationConfiguration = configurationFactory.getApplicationConfiguration();

        assertNotNull(applicationConfiguration);
        assertEquals(180, applicationConfiguration.getPostSchedulerParams().getMaxTaskDelayMinutes());
        assertEquals(60, applicationConfiguration.getPostSchedulerParams().getTaskIntervalSec());
    }
}