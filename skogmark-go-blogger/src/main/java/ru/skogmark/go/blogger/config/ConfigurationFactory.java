package ru.skogmark.go.blogger.config;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.URL;

/**
 * @author svip
 *         2016-12-17
 */
@Component
public class ConfigurationFactory {
    private static final String CONFIG_FILE_NAME = "skogmark-go-blogger-config.xml";
    private static final Logger logger = Logger.getLogger(ConfigurationFactory.class);

    public Configuration getConfiguration() throws FailConfigLoadingException {
        logger.debug("Loading application config");
        try {
            URL configUrl = Thread.currentThread().getContextClassLoader().getResource(CONFIG_FILE_NAME);
            if (null == configUrl) {
                throw new FailConfigLoadingException("Unable to resolve application config path");
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return (Configuration) unmarshaller.unmarshal(configUrl);
        } catch (JAXBException e) {
            throw new FailConfigLoadingException("Failure to load application config", e);
        }
    }
}
