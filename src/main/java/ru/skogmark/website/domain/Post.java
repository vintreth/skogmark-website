package ru.skogmark.website.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @author svip
 *         2016-03-10
 */
@Entity
@Table(name = "sk_post")
public class Post {

    @Column
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column
    private String content;

    @Column(name = "preview_text")
    private String previewText;

    @OneToOne
    private Image previewImage;

    /** Default constructor */
    public Post() {}

}
