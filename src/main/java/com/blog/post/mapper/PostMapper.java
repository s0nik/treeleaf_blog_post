package com.blog.post.mapper;

import com.blog.post.database.Posts;
import com.blog.post.dto.request.PostRequestDto;
import com.blog.post.dto.response.PostResponseDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostMapper {

  public abstract Posts toPosts(PostRequestDto dto);

  @Mappings({
      @Mapping(target = "postLikes", ignore = true),
      @Mapping(target = "postComments", ignore = true)
  })
  public abstract PostResponseDto toPostResponseDto(Posts post);

  @AfterMapping
  public void toPostResponseDto(Posts post, @MappingTarget PostResponseDto responseDto) {
    if (post.getPostLikes() != null) {
      responseDto.setPostLikes(post.getPostLikes().size());
    }
    if (post.getPostComments() != null) {
      responseDto.setPostComments(post.getPostComments().size());
    }
  }

  public void toPosts(PostRequestDto dto, @MappingTarget Posts post) {
  }
}
