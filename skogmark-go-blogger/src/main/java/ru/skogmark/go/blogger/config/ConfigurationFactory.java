package ru.skogmark.go.blogger.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

/**
 * @author svip
 *         2016-12-17
 */
@org.springframework.context.annotation.Configuration
public class ConfigurationFactory {
    private static final String APPLICATION_CONFIG_PATH = "skogmark-go-blogger-config.xml";
    private static final Logger logger = Logger.getLogger(ConfigurationFactory.class);

    @Bean(name = "configuration")
    public Configuration getConfiguration() throws FailConfigurationLoadingException {
        return loadConfiguration(Configuration.class, APPLICATION_CONFIG_PATH);
    }

    @SuppressWarnings("unchecked")
    protected <T> T loadConfiguration(Class<T> configClass, String configPath)
            throws FailConfigurationLoadingException {
        logger.debug("Loading configuration for " + configClass);
        try {
            //todo config folder location
            //todo refactor config params
            URL configUrl = Thread.currentThread().getContextClassLoader().getResource(configPath);
            if (null == configUrl) {
                throw new FailConfigurationLoadingException("Unable to resolve configuration path " + configPath);
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(configClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (T) unmarshaller.unmarshal(configUrl);
        } catch (JAXBException e) {
            throw new FailConfigurationLoadingException("Failure to load configuration for " + configClass, e);
        }
    }
}
