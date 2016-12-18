package ru.skogmark.go.blogger.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svip
 *         2016-12-17
 */
@XmlRootElement
public class Configuration {
    private BloggerParams bloggerParams;
    private long awaitTerminationTimeoutSec;
    private String generatorResourceUrl;
    private String userAgent;
    private String defaultCharset;

    public BloggerParams getBloggerParams() {
        return bloggerParams;
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

    @XmlElement(name = "blogger")
    public void setBloggerParams(BloggerParams bloggerParams) {
        this.bloggerParams = bloggerParams;
    }

    @XmlElement
    public void setAwaitTerminationTimeoutSec(long awaitTerminationTimeoutSec) {
        this.awaitTerminationTimeoutSec = awaitTerminationTimeoutSec;
    }

    @XmlElement
    public void setGeneratorResourceUrl(String generatorResourceUrl) {
        this.generatorResourceUrl = generatorResourceUrl;
    }

    @XmlElement
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @XmlElement
    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }
}
