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

    public ModelAndView index(MyData mydata, ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "this is sample content.");
        List<MyData> list=repository.findByIsdone(false);
        mav.addObject("data", list);
        return mav;
    }

    public ModelAndView form(MyData mydata, BindingResult result, ModelAndView mav) {
        ModelAndView res = null;
        if (!result.hasErrors()) {
            repository.saveAndFlush(mydata);
            res = new ModelAndView("redirect:/");
        } else {
            mav.setViewName("index");
            mav.addObject("msg", "sorry, error is occured...");
            List<MyData> list=repository.findByIsdone(false);
            mav.addObject("data", list);
            res = mav;
        }
        return res;
    }

    public ModelAndView edit(MyData mydata, int id, ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title", "edit mydata.");
        Optional<MyData> data = repository.findById(id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    public ModelAndView update(MyData mydata, boolean checkisdone, ModelAndView mav) {
        
        if (checkisdone) {
            mydata.setIsdone(true);
        }
        repository.saveAndFlush(mydata);
        return new ModelAndView("redirect:/");
    }

    public ModelAndView find(MyData mydata, ModelAndView mav, HttpServletRequest request) {
        mav.setViewName("find");
        mav.addObject("title", "Find page");
        mav.addObject("msg", "MyDataのサンプルです。");
        mav.addObject("value", "");
        return mav;
    }

    public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
        mav.setViewName("find");
        String content = request.getParameter("content");
        content = "%"+content+"%";
        String category = request.getParameter("category");
        if ("%%".equals(content)) {
            mav.addObject("title", "Find result");
            mav.addObject("msg", "検索結果");
            mav.addObject("content", "");
            mav.addObject("category", "");
            List<MyData> data = repository.findByCategory(category);
            mav.addObject("formModel", data);
            
        }
        else {
            mav.addObject("title", "Find result");
            mav.addObject("msg", "検索結果");
            mav.addObject("content", "");
            mav.addObject("category", "");
            List<MyData> data = repository.findByContentLikeOrCategory(content, category);
            mav.addObject("formModel", data);
            
        }
        return mav;
    }

}