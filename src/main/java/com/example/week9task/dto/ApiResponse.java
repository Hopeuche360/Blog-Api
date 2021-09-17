package com.example.week9task.dto;

import com.example.week9task.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResponse {
    private String message;
    private Object data;
}
