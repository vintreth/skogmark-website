package ru.skogmark.go.blogger.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svip
 *         2016-12-17
 */
@XmlRootElement
public class Configuration {
    @XmlElement(name = "postScheduler")
    private PostSchedulerParams postSchedulerParams;

    @XmlElement
    private long awaitTerminationTimeoutSec;

    @XmlElement
    private String generatorResourceUrl;

    @XmlElement
    private String userAgent;

    @XmlElement
    private String defaultCharset;

    public PostSchedulerParams getPostSchedulerParams() {
        return postSchedulerParams;
    }

    public long getAwaitTerminationTimeoutSec() {
        return awaitTerminationTimeoutSec;
    }

    public String getGeneratorResourceUrl() {
        return generatorResourceUrl;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getDefaultCharset() {
        return defaultCharset;
    }
}
