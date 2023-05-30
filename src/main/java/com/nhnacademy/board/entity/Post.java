package com.nhnacademy.board.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Post {
    @EmbeddedId
    private Pk pk;

    private String title;
    private String content;
    @Column(name = "write_time")
    private LocalDateTime writeTime;
    @Column(name = "view_count")
    private int viewCount;

    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    @Getter
    @Setter
    public static class Pk implements Serializable {
        @Column(name = "post_id")
        private Long id;

        @Column(name = "writer")
        private String writerUserId;
    }

    @MapsId("writerUserId")
    @ManyToOne
    @JoinColumn(name = "writer")
    private User user;

    public Post(String title, String content, String writerUserId) {
        this.title = title;
        this.content = content;
        this.pk.writerUserId = writerUserId;
        this.writeTime = LocalDateTime.now();
        viewCount = 0;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}