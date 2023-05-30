package com.nhnacademy.board.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "writer")
    private String writerUserId;
    @Column(name = "write_time")
    private LocalDateTime writeTime;
    @Column(name = "view_count")
    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "writer", insertable=false, updatable = false)
    private User user;

    public Post(String title, String content, String writerUserId) {
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = LocalDateTime.now();
        viewCount = 0;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}