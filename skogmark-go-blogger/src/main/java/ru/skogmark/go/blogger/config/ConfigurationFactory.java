package ru.skogmark.go.blogger.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * @author svip
 *         2016-12-17
 */
@org.springframework.context.annotation.Configuration
public class ConfigurationFactory {
    private static final String APPLICATION_CONFIG_PATH = "application-config.xml";
    private static final String EXTERNAL_CONFIG_DIRECTORY_PATH = "conf/";
    private static final Logger logger = Logger.getLogger(ConfigurationFactory.class);

    @Bean(name = "configuration")
    public ApplicationConfiguration getConfiguration() throws ConfigurationLoadingException {
        return loadConfiguration(ApplicationConfiguration.class, APPLICATION_CONFIG_PATH);
    }

    protected <T> T loadConfiguration(Class<T> configClass, String configPath)
        throws ConfigurationLoadingException {
        logger.debug("Loading configuration for " + configClass);
        try {
            try (InputStream configFileInputStream = getConfigFileInputStream(configPath)) {
                return unmarshalConfig(configClass, configFileInputStream);
            }
        } catch (IOException e) {
            throw new ConfigurationLoadingException("Failure to close input stream for config file " + configPath, e);
        }
    }

    private InputStream getConfigFileInputStream(String configPath) throws ConfigurationLoadingException {
        //todo provide config directory by annotation
        File externalConfigFile = new File(
            System.getProperty("user.dir") + "/" + EXTERNAL_CONFIG_DIRECTORY_PATH + configPath);
        try {
            if (externalConfigFile.exists() && externalConfigFile.isFile()) {
                logger.debug("Loading config from external directory");
                return new FileInputStream(externalConfigFile);
            } else {
                return Thread.currentThread().getContextClassLoader().getResourceAsStream(configPath);
            }
        } catch (FileNotFoundException e) {
            throw new ConfigurationLoadingException("Unable to resolve file " + configPath, e);
        }
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
