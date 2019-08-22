package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="todo")
public class MyData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="content")
    @NotEmpty(message="空白で送信しないでください")
    @Length(max=50, message="入力は50文字までです")
    private String content;
   
    @Column(name="category")
    @NotEmpty(message="空白で送信しないでください")
    @Length(max=10, message="入力は10文字までです")
    private String category;

    @Column(name="isdone")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private boolean isdone;

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
    }
    public String getCategory() {
      return category;
    }
    public void setCategory(String category) {
      this.category = category;
    }
    public boolean getIsdone() {
      return isdone;
    }
    public void setIsdone(boolean isdone) {
      this.isdone = isdone;
    }
  }