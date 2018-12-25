package ru.skogmark.contentgenerator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.skogmark.common.http.HttpMethod;
import ru.skogmark.common.http.HttpRequestHeader;
import ru.skogmark.common.http.JacksonObjectMapperSerializerAdapter;
import ru.skogmark.common.http.SerializerAwareHttpRequest;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@Import({MainController.class, ContentGeneratorApplication.class})
public class MainControllerTest {
    @Test
    public void testPing() {
        HttpRequestHeader header = new HttpRequestHeader();
        header.setHttpMethod(HttpMethod.GET);
        header.setUrl("http://localhost:8185/ping");
        String response = new SerializerAwareHttpRequest(new JacksonObjectMapperSerializerAdapter())
                .makeRequest(header, null);
        assertEquals("ok", response);
    }
}