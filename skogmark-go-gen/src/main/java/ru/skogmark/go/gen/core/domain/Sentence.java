package ru.skogmark.go.gen.core.domain;

import java.time.LocalDateTime;

public class Sentence {
    private long id;
    private long creatorId;
    private LocalDateTime dateCreated;
    private String content;
    private SentenceRole role;
    private Gender gender;
    private Tense tense;

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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SentenceRole getRole() {
        return role;
    }

    public void setRole(SentenceRole role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Tense getTense() {
        return tense;
    }

    public void setTense(Tense tense) {
        this.tense = tense;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", creatorId=" + creatorId +
                ", dateCreated=" + dateCreated +
                ", content='" + content + '\'' +
                ", role=" + role +
                ", gender=" + gender +
                ", tense=" + tense +
                '}';
    }
}
