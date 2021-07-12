package com.example.springdataintro02.entities;

import javax.persistence.*;

@Table(name = "picture")
@Entity
public class Picture extends BaseEntity {
    private String title;
    private String caption;
    private String path;
    private PersonalAlbum personalAlbum;


    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "caption")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToOne

    public PersonalAlbum getPersonalAlbum() {
        return personalAlbum;
    }

    public void setPersonalAlbum(PersonalAlbum personalAlbum) {
        this.personalAlbum = personalAlbum;
    }
}