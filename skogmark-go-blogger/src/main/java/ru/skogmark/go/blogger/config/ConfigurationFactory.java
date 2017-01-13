package ru.skogmark.go.blogger.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * @author svip
 *         2016-12-17
 */
@Configuration
public class ConfigurationFactory {
    private static final String APPLICATION_CONFIG_FILE_NAME = "application-config.xml";
    private static final String EXTERNAL_CONFIG_DIRECTORY_PATH = "/../conf";
    private static final Logger logger = Logger.getLogger(ConfigurationFactory.class);

    private String executionPath = System.getProperty("user.dir");

    @Bean(name = "configuration")
    public ApplicationConfiguration getApplicationConfiguration() throws ConfigurationLoadingException {
        return loadConfiguration(ApplicationConfiguration.class, APPLICATION_CONFIG_FILE_NAME);
    }

    public void setExecutionPath(String executionPath) {
        this.executionPath = executionPath;
    }

    protected <T> T loadConfiguration(Class<T> configClass, String configFileName)
        throws ConfigurationLoadingException {
        logger.debug("Loading configuration for " + configClass);
        try {
            try (InputStream configFileInputStream = getConfigFileInputStream(configFileName)) {
                return unmarshalConfig(configClass, configFileInputStream);
            }
        } catch (IOException e) {
            throw new ConfigurationLoadingException(
                    "Failure to close input stream for config file " + configFileName, e);
        }
    }

    private InputStream getConfigFileInputStream(String configFileName) throws ConfigurationLoadingException {
        File externalConfigFile = new File(getConfigLocation() + "/" + configFileName);
        try {
            if (externalConfigFile.exists() && externalConfigFile.isFile()) {
                logger.debug("Loading config from external directory");
                return new FileInputStream(externalConfigFile);
            } else {
                logger.debug("Loading config from resources");
                return Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);
            }
        } catch (FileNotFoundException e) {
            throw new ConfigurationLoadingException("Unable to resolve file " + configFileName, e);
        }
    }

    private String getConfigLocation() {
        String configLocation;
        if (null != (configLocation = System.getProperty("app.configLocation"))) {
            return configLocation;
        }
        return executionPath + EXTERNAL_CONFIG_DIRECTORY_PATH;
    }

    @SuppressWarnings("unchecked")
    private <T> T unmarshalConfig(Class<T> configClass, InputStream configFileInputStream)
        throws ConfigurationLoadingException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(configClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (T) unmarshaller.unmarshal(configFileInputStream);
        } catch (JAXBException e) {
            throw new ConfigurationLoadingException("Failure to load configuration for " + configClass, e);
        }
    }
}
