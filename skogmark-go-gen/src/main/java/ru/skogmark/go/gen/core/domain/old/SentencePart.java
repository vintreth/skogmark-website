package ru.skogmark.go.gen.core.domain.old;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by SwEEp on 06.01.2017.
 */
@Entity
@Table(name = "sentence_part")
public class SentencePart implements RoleAwareEntity {
    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column(name = "creator_id", nullable = false)
    private int creatorId;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(length = 255, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
