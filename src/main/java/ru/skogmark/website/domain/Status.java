package ru.skogmark.website.domain;

import javax.persistence.*;

/**
 * @author kbogdanov 15.06.16
 */
@Entity
@Table(name = "s_status")
public class Status {

    @Id
    @GeneratedValue
    @Column(nullable = false, unique = true)
    private Integer id;

    @Column(nullable = false, length = 16)
    private String value;

    @Column(length = 6)
    private String color;

    public Status() {}

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
}
