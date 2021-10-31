package com.blog.post.service;

import com.blog.post.dto.request.PostRequestDto;
import com.blog.post.dto.response.PostResponseDto;
import com.blog.post.repository.projection.LongId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
  PostResponseDto addPost(PostRequestDto dto) throws Exception;

  Page<LongId> getPosts(Pageable pg) throws Exception;

  PostResponseDto getById(Integer id) throws Exception;

  PostResponseDto updatePost(Integer id, PostRequestDto dto) throws Exception;

  void deletePost(Integer id) throws Exception;
}
