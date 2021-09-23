package ru.tower.json1c.parse;

import java.util.Date;

public class PlanPositionFile {

    private String name;
    private String id;
    private Date upload_date;
    private Boolean eds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }

    public Boolean getEds() {
        return eds;
    }

    public void setEds(Boolean eds) {
        this.eds = eds;
    }
}
