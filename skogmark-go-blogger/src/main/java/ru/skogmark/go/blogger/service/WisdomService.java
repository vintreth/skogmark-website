package ru.skogmark.go.blogger.service;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skogmark.go.blogger.config.Configuration;
import ru.skogmark.go.blogger.domain.Wisdom;
import ru.skogmark.go.blogger.exception.FailDomainObjectRetrievingException;
import ru.skogmark.go.blogger.rest.HttpException;
import ru.skogmark.go.blogger.rest.HttpRequest;

import java.io.IOException;

/**
 * @author svip
 *         2016-12-17
 */
@Service
public class WisdomService {
    private static final Logger logger = Logger.getLogger(WisdomService.class);

    private HttpRequest httpRequest;
    private Configuration configuration;

    @Autowired
    public WisdomService(HttpRequest httpRequest, Configuration configuration) {
        this.httpRequest = httpRequest;
        this.configuration = configuration;
    }

    public Wisdom getWisdom() throws FailDomainObjectRetrievingException {
        try {
            logger.debug("Retrieving a wisdom from rest api");
            String content = httpRequest.doGet(configuration.getGeneratorResourceUrl());
            if (content.isEmpty()) {
                throw new FailDomainObjectRetrievingException("Empty content");
            }
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.reader(Wisdom.class).readValue(content);
        } catch (HttpException e) {
            throw new FailDomainObjectRetrievingException("Failure to retrieve a post message", e);
        } catch (IOException e) {
            throw new FailDomainObjectRetrievingException("Json content is invalid", e);
        }
    }
}
