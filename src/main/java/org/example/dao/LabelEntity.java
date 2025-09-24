package org.example.dao;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "LabelEntity")
@Table(name = "labels")
public class LabelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(
            name = "global_seq",
            sequenceName = "global_id_sequence"
    )
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "labels")
    private Set<PostEntity> posts = new HashSet<>();

    public LabelEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }
}
