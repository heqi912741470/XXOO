package com.example.xss.entity;

public class Catalog {
    private Integer id;
    private String xsName;
    private String xsUrl;

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", xsName='" + xsName + '\'' +
                ", xsUrl='" + xsUrl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getXsName() {
        return xsName;
    }

    public void setXsName(String xsName) {
        this.xsName = xsName;
    }

    public String getXsUrl() {
        return xsUrl;
    }

    public void setXsUrl(String xsUrl) {
        this.xsUrl = xsUrl;
    }
}
