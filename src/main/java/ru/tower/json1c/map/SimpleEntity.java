package ru.tower.json1c.map;

import javax.persistence.*;

@Entity
@Table(name = "TMP_TEST_TAB1")
public class SimpleEntity {

    @Id
    @Column(name = "idcol")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
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
