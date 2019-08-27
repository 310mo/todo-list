package com.example.demo.service;

import com.example.demo.entity.MyData;
import com.example.demo.repository.MyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Service
public class TodoService {
    @Autowired
    MyDataRepository repository;

    public List<MyData> searchbyisdone(MyData mydata) {
        List<MyData> list=repository.findByIsdone(false);
        return list;
    }

    public void save(MyData mydata) {
        repository.saveAndFlush(mydata);
    }

    public Optional<MyData> searchbyid(MyData mydata, int id) {
        return repository.findById(id);
    }

    public void update(MyData mydata, boolean checkisdone) {
        if (checkisdone) {
            mydata.setIsdone(true);
        }
        repository.saveAndFlush(mydata);
    }

    public List<MyData> searchbycategory(MyData mydata, String category) {
        List<MyData> data = repository.findByCategory(category);
            
        return data;
    }

    public List<MyData> searchbycontentorcategory(MyData mydata, String content, String category) {
        List<MyData> data = repository.findByContentLikeOrCategory(content, category);

        return data;
    }

}