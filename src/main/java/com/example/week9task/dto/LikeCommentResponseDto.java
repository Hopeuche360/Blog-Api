package com.example.week9task.dto;

import com.example.week9task.model.Comments;
import lombok.Data;

@Data
public class LikeCommentResponseDto {
    private Comments comments;
    private Boolean like;
}
