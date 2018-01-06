package ru.skogmark.go.blogger.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author svip
 *         2016-12-17
 */
@XmlRootElement
public class BloggerSettings {
    private PostSchedulerParams postSchedulerParams;
    private long awaitTerminationTimeoutSec;
    private String generatorResourceUrl;
    private boolean postWhileStartingEnabled;

    public static class PostSchedulerParams {
        private int maxTaskDelayMinutes;
        private long taskIntervalSec;
        private TimeTable timeTable;

        public static class TimeTable {
            private Integer[] times;

            public Integer[] getTimes() {
                return times;
            }

            @XmlElement(name = "time")
            public void setTimes(Integer[] times) {
                this.times = times;
            }
        }

        public int getMaxTaskDelayMinutes() {
            return maxTaskDelayMinutes;
        }

        public long getTaskIntervalSec() {
            return taskIntervalSec;
        }

        /**
         * Time table for posting message to a blog
         */
        public TimeTable getTimeTable() {
            return timeTable;
        }

        @XmlElement
        public void setMaxTaskDelayMinutes(int maxTaskDelayMinutes) {
            this.maxTaskDelayMinutes = maxTaskDelayMinutes;
        }

        @XmlElement
        public void setTaskIntervalSec(long taskIntervalSec) {
            this.taskIntervalSec = taskIntervalSec;
        }

        @XmlElement
        public void setTimeTable(TimeTable timeTable) {
            this.timeTable = timeTable;
        }
    }

    public PostSchedulerParams getPostSchedulerParams() {
        return postSchedulerParams;
    }

    public long getAwaitTerminationTimeoutSec() {
        return awaitTerminationTimeoutSec;
    }

    public String getGeneratorResourceUrl() {
        return generatorResourceUrl;
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

    public boolean isPostWhileStartingEnabled() {
        return postWhileStartingEnabled;
    }

    @XmlElement
    public void setPostWhileStartingEnabled(boolean postWhileStartingEnabled) {
        this.postWhileStartingEnabled = postWhileStartingEnabled;
    }
}
