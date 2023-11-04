package dev.walid.graphqldemo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostInput {
    private String title;
    private String content;
    private String image;
    private Long userId;
}
