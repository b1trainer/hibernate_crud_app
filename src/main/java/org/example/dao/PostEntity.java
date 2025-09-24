package org.example.dao;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "PostEntity")
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(
            name = "global_seq",
            sequenceName = "global_id_sequence"
    )
    private Long id;
    private String content;
    private Instant created;
    private Instant updated;

    @ManyToMany
    @JoinTable(
            name = "posts_labels",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private List<LabelEntity> labels = new ArrayList<>();

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private PostStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "writerId")
    private WriterEntity writer;

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public void setLabels(List<LabelEntity> labels) {
        this.labels = labels;
    }

    public void setStatus(PostStatusEntity status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public List<LabelEntity> getLabels() {
        return labels;
    }

    public PostStatusEntity getStatus() {
        return status;
    }

    public WriterEntity getWriter() {
        return writer;
    }

    public void setWriter(WriterEntity writer) {
        this.writer = writer;
    }

}
