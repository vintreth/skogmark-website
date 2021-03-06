package ru.skogmark.go.gen.core.domain.old;

import ru.skogmark.go.gen.core.domain.ConjunctionType;

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
@Table(name = "dict_conjunction")
public class Conjunction implements RoleAwareEntity {
    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column(name = "creator_id", nullable = false)
    private long creatorId;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(length = 32, nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private ConjunctionType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
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

    public ConjunctionType getType() {
        return type;
    }

    public void setType(ConjunctionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Conjunction{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", dateCreated=" + dateCreated +
                ", content='" + content + '\'' +
                ", role=" + role +
                ", type=" + type +
                '}';
    }
}
