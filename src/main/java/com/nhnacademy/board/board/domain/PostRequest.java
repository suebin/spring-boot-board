package com.nhnacademy.board.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostRequest {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String writerUserId;
    private LocalDateTime writeTime;
    private int viewCount;

    public PostRequest() {}

    public PostRequest(Long id, String title, String content, String writerUserId, LocalDateTime writeTime, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerUserId = writerUserId;
        this.writeTime = writeTime;
        this.viewCount = viewCount;
    }
}
