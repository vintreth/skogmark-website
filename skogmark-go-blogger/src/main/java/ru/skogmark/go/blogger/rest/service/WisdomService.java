package ru.skogmark.go.blogger.rest.service;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skogmark.go.api.Wisdom;
import ru.skogmark.go.blogger.config.ApplicationConfiguration;
import ru.skogmark.go.blogger.rest.EntityRetrievingException;
import ru.skogmark.common.http.HttpException;
import ru.skogmark.common.http.HttpRequest;

import java.io.IOException;

/**
 * @author svip
 *         2016-12-17
 */
@Service
public class WisdomService {
    private static final Logger logger = Logger.getLogger(WisdomService.class);

    private HttpRequest httpRequest;
    private ApplicationConfiguration configuration;

    @Autowired
    public WisdomService(HttpRequest httpRequest, ApplicationConfiguration configuration) {
        this.httpRequest = httpRequest;
        this.configuration = configuration;
    }

    public Wisdom getWisdom() throws EntityRetrievingException {
        try {
            logger.debug("Retrieving a wisdom from rest api");
            String content = httpRequest.doGet(configuration.getGeneratorResourceUrl());
            if (null == content || content.isEmpty()) {
                throw new EntityRetrievingException("Empty content");
            }
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.reader(Wisdom.class).readValue(content);
        } catch (HttpException e) {
            throw new EntityRetrievingException("Failure to retrieve a post message", e);
        } catch (IOException e) {
            throw new EntityRetrievingException("Json content is invalid", e);
        }
    }
}
