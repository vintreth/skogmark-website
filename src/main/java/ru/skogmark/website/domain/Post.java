package ru.skogmark.website.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author svip
 *         2016-03-10
 */
@Entity
@Table(name = "skogmark_post")
public class Post {

    @Column
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column(name = "created_at")
    private Date createdAt;

    /** Default constructor */
    public Post() {}

}
