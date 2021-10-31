package com.blog.post.dto.response;

import lombok.Data;

@Data
public class PostResponseDto {

  private Long id;

  private String title;

  private String content;

  private Long createdDate;

  private Integer postLikes;

  private Integer postComments;

}
