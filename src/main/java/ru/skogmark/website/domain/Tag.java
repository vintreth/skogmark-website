package ru.skogmark.website.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author svip
 *         2016-05-29
 */
@Entity
@Table(name = "s_tag")
public class Tag {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String value;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    public Tag() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
}
