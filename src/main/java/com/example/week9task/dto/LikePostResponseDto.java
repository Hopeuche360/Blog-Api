package com.example.week9task.dto;

import com.example.week9task.model.Posts;
import lombok.Data;

@Data
public class LikePostResponseDto {
    private Posts posts;
    private Boolean like;
}
