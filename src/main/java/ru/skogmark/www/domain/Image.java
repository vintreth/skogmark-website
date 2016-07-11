package ru.skogmark.www.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Any site image
 * @author kbogdanov 11.03.16
 */
@Entity
@Table(name = "s_image")
public class Image {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue
    private Integer id;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(nullable = false)
    private String path;

    /**
     * File name
     */
    @Column(nullable = false)
    private String name;

    /**
     * Title for the image
     */
    @Column(nullable = false)
    private String title;

    @Column(name = "mime_type", nullable = false, length = 32)
    private String mimeType;

    public Image() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
