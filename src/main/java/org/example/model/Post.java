package org.example.model;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Post {
    private Long id;
    private Long writerId;
    private String content;
    private Instant created;
    private Instant updated;
    private List<Label> labels;
    private PostStatus status;

    public Post() {
    }

    public Post(Long id, Long writerId, String content, Instant created, Instant updated, List<Label> labels, PostStatus status) {
        this.id = id;
        this.writerId = writerId;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.status = status;
    }

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

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
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

    public List<Label> getLabels() {
        return labels;
    }

    public PostStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("""
                        ===============
                        Post id: %d
                        content: %s
                        created: %s
                        updated: %s
                        labels: %s
                        status: %s
                        writerId: %d
                        ===============
                        """,
                id,
                content,
                created.toString(),
                updated.toString(),
                labels.stream().map(Label::getName).collect(Collectors.joining(",")),
                status.getStatus(),
                writerId);
    }
}
