package com.blog.post.controller;

import com.blog.post.config.response.MyException;
import com.blog.post.config.response.ServiceResponse;
import com.blog.post.dto.request.PostRequestDto;
import com.blog.post.dto.response.PostResponseDto;
import com.blog.post.enums.ErrorCode;
import com.blog.post.repository.projection.LongId;
import com.blog.post.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("post")
public class PostController {

  @Autowired
  BlogService blogService;

  @PostMapping
  public ServiceResponse<PostResponseDto> addPost(@RequestBody @Valid PostRequestDto dto, BindingResult result) throws Exception {
    if (result.hasErrors()) {
      Map<String, String> errors = result.getFieldErrors().stream()
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
      throw new MyException(ErrorCode.V001, errors);
    }
    ServiceResponse<PostResponseDto> response = new ServiceResponse<>();
    response.setData(blogService.addPost(dto));
    return response;
  }

  @GetMapping
  public ServiceResponse<Page<LongId>> getPosts(Pageable pg) throws Exception {
    ServiceResponse<Page<LongId>> response = new ServiceResponse<>();
    response.setData(blogService.getPosts(pg));
    return response;
  }

  @GetMapping("{id}")
  public ServiceResponse<PostResponseDto> getPostById(@PathVariable Integer id) throws Exception {
    ServiceResponse<PostResponseDto> response = new ServiceResponse<>();
    response.setData(blogService.getById(id));
    return response;
  }

  @PutMapping("{id}")
  public ServiceResponse<PostResponseDto> updatePost(@PathVariable Integer id, @RequestBody @Valid PostRequestDto dto, BindingResult result) throws Exception {
    if (result.hasErrors()) {
      Map<String, String> errors = result.getFieldErrors().stream()
          .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
    ServiceResponse<PostResponseDto> response = new ServiceResponse<>();
    response.setData(blogService.updatePost(id, dto));
    return response;
  }

  @DeleteMapping("{id}")
  public ServiceResponse deletePost(@PathVariable Integer id) throws Exception {
    ServiceResponse response = new ServiceResponse();
    blogService.deletePost(id);
    response.setMessage("Post deleted");
    return response;
  }

}
