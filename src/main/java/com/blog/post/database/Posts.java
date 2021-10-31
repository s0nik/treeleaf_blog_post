package com.blog.post.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Posts {

  @Id
  @Basic(optional = false)
  @Column(nullable = false, insertable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Lob
  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "created_date")
  private Long createdDate = System.currentTimeMillis();

  @OneToMany(mappedBy = "post")
  @JsonIgnore
  private List<PostLikes> postLikes;

  @OneToMany(mappedBy = "post")
  @JsonIgnore
  private List<PostComments> postComments;

  @Column(name = "status")
  private Boolean status = Boolean.TRUE;

}
