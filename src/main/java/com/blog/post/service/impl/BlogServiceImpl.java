package com.blog.post.service.impl;

import com.blog.post.config.response.MyException;
import com.blog.post.database.Posts;
import com.blog.post.dto.request.PostRequestDto;
import com.blog.post.dto.response.PostResponseDto;
import com.blog.post.enums.ErrorCode;
import com.blog.post.mapper.PostMapper;
import com.blog.post.repository.PostRepository;
import com.blog.post.repository.projection.LongId;
import com.blog.post.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

  @Autowired
  PostRepository postRepository;

  @Autowired
  PostMapper postMapper;

  @Override
  public PostResponseDto addPost(PostRequestDto dto) throws Exception {
    Posts post = postMapper.toPosts(dto);
    post = postRepository.save(post);
    return postMapper.toPostResponseDto(post);
  }

  @Override
  public Page<LongId> getPosts(Pageable pg) throws Exception {
    return postRepository.findByStatusIsTrue(pg);
  }

  @Override
  public PostResponseDto getById(Integer id) throws Exception {
    Posts post = postRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new MyException(ErrorCode.I001, "Post not found"));
    return postMapper.toPostResponseDto(post);
  }

  @Override
  public PostResponseDto updatePost(Integer id, PostRequestDto dto) throws Exception {
    Posts post = postRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new MyException(ErrorCode.I001, "Post not found"));
    postMapper.toPosts(dto, post);
    postRepository.save(post);
    return postMapper.toPostResponseDto(post);
  }

  @Override
  public void deletePost(Integer id) throws Exception {
    Posts post = postRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new MyException(ErrorCode.I001, "Post not found"));
    post.setStatus(Boolean.FALSE);
    postRepository.save(post);
  }
}
