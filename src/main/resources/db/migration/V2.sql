CREATE INDEX idx_posts_writerId ON posts (writerId);
CREATE INDEX idx_writers_name ON writers (firstName, lastName);
CREATE INDEX idx_labels_name ON labels (name);

CREATE SEQUENCE global_id_sequence
    MINVALUE 1
    START WITH 20
    INCREMENT BY 50;
