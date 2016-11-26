package ru.skogmark.www.domain;

import javax.persistence.*;

/**
 * @author svip
 *         2016-07-11
 */
@Entity
@Table(name = "s_role")
public class Role {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false, length = 16)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
