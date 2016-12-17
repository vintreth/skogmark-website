package ru.skogmark.go.blogger.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author svip
 *         2016-12-17
 */
public class PostSchedulerParams {
    @XmlElement
    private int maxJobDelayHours;

    @XmlElement
    private long jobIntervalSec;

    @XmlElement
    private TimeTable timeTable;

    public static class TimeTable {
        @XmlElement(name = "time")
        private Integer[] times;

        public Integer[] getTimes() {
            return times;
        }
    }

    public int getMaxJobDelayHours() {
        return maxJobDelayHours;
    }

    public long getJobIntervalSec() {
        return jobIntervalSec;
    }

    /**
     * Time table for posting message to a blog
     */
    public TimeTable getTimeTable() {
        return timeTable;
    }
}
