package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todo")
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="content")
    private String content;
   
    @Column(name="category")
    private String category;

    @Column(name="done")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int done;

    public int getId() {
      return id;
    }
    public void setId(int id) {
      this.id = id;
    }
    public String getContent() {
      return content;
    }
    public void setContent(String content) {
      this.content = content;
      System.out.print(content);
    }
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    public int getDone() {
      return done;
    }
    public void setDone(int done) {
      this.done = done;
    }
  }