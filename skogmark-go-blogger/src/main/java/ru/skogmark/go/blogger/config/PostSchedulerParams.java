package ru.skogmark.go.blogger.config;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author svip
 *         2016-12-17
 */
public class PostSchedulerParams {
    private int maxTaskDelayHours;
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

    public int getMaxTaskDelayHours() {
        return maxTaskDelayHours;
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
    public void setMaxTaskDelayHours(int maxTaskDelayHours) {
        this.maxTaskDelayHours = maxTaskDelayHours;
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
