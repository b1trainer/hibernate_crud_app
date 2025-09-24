package org.example.dao;

import jakarta.persistence.*;

@Entity(name = "PostStatusEntity")
@Table(name = "post_status")
public class PostStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(
            name = "global_seq",
            sequenceName = "global_id_sequence"
    )
    private Long id;
    private String status;

    @OneToOne
    @JoinColumn(name = "postId")
    private PostEntity post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
