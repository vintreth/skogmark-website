package ru.skogmark.website.domain;

import java.sql.Date;

/**
 * @author kbogdanov 15.06.16
 */
public class Gig {

    private Integer id;

    private User createdBy;

    private Date createdAt;

    private Date startDate;

    private String stage;

    private String location;

    private String link;

    private Status status;
}
