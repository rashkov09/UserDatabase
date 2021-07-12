package com.example.springdataintro02.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PersonalAlbum extends BaseEntity{
    private String name;
    private String backgroundColor;
    private PublicOrNot publicOrNot;
    private Set<Picture> pictures;
    private User user;


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "background_color")
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Column(name = "public_or_not")
    public PublicOrNot getPublicOrNot() {
        return publicOrNot;
    }

    public void setPublicOrNot(PublicOrNot publicOrNot) {
        this.publicOrNot = publicOrNot;
    }

   @OneToMany(fetch = FetchType.EAGER, mappedBy = "personalAlbum")

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @ManyToOne

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
