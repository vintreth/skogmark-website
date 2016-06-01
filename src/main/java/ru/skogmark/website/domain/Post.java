package ru.skogmark.website.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

/**
 * @author svip
 *         2016-03-10
 */
@Entity
@Table(name = "s_post")
public class Post {

    @Column
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name="created_by")
    private Integer createdBy;

    @Column(nullable = false)
    private String content;

    @Column(name = "preview_text")
    private String previewText;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column
    private Integer shows;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "s_tag_post",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags;

    /** Default constructor */
    public Post() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public Integer getShows() {
        return shows;
    }

    public void setShows(Integer shows) {
        this.shows = shows;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
