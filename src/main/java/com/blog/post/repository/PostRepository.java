package com.blog.post.repository;

import com.blog.post.database.Posts;
import com.blog.post.dto.response.PostResponseDto;
import com.blog.post.repository.projection.LongId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Long> {

  Page<LongId> findByStatusIsTrue(Pageable pg);

  Optional<Posts> findByIdAndStatusTrue(Integer id);
}
