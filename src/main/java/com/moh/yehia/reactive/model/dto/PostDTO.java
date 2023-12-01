package com.moh.yehia.reactive.model.dto;

import java.time.LocalDateTime;

public record PostDTO(String id, String title, String description, String body, String slug, LocalDateTime createdAt) {
}
