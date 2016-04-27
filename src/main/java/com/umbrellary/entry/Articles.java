package com.umbrellary.entry;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "articles")
public class Articles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = true, columnDefinition = "TEXT")
    private String title;
    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Articles(long id) {
        this.id = id;
    }

    public Articles() {
    }

}
