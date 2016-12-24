package ru.skogmark.go.blogger.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svip
 *         2016-12-17
 */
@XmlRootElement
public class ApplicationConfiguration {
    private PostSchedulerParams postSchedulerParams;
    private long awaitTerminationTimeoutSec;
    private String generatorResourceUrl;
    private String userAgent;
    private String defaultCharset;
    private boolean localMode;

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

    @XmlElement(name = "postScheduler")
    public void setPostSchedulerParams(PostSchedulerParams postSchedulerParams) {
        this.postSchedulerParams = postSchedulerParams;
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

    public boolean isLocalMode() {
        return localMode;
    }

    @XmlElement
    public void setLocalMode(boolean localMode) {
        this.localMode = localMode;
    }
}
