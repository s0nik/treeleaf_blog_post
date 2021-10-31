package com.blog.post.database;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PostComments {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Basic(optional = false)
  @Column(nullable = false, insertable = false, updatable = false)
  private Long id;

  @Column(name = "comment", nullable = false)
  private String comment;

  @Column(name = "comment_date")
  private Long commentDate = System.currentTimeMillis();

  @Column(name = "status")
  private Boolean status = Boolean.TRUE;

  @ManyToOne(optional = false)
  @JoinColumn(name = "post", referencedColumnName = "id")
  private Posts post;

  @Column(nullable = false, updatable = false)
  private Integer user;

}
