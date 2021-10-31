package com.blog.post.database;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PostLikes {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Basic(optional = false)
  @Column(nullable = false, insertable = false, updatable = false)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "post", referencedColumnName = "id")
  private Posts post;

  @Column(nullable = false, updatable = false)
  private Integer user;

}
