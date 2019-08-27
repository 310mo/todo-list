package com.example.demo.controller;

import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import com.example.demo.entity.MyData;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class TodoController {

    @Autowired
    TodoService todoservice;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "this is sample content.");
        List<MyData> list = todoservice.searchbyisdone(mydata);
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata, BindingResult result, ModelAndView mav) {
        ModelAndView res = null;
        if (!result.hasErrors()) {
            todoservice.save(mydata);
            res = new ModelAndView("redirect:/");
        } else {
            mav.setViewName("index");
            mav.addObject("msg", "sorry, error is occured...");
            List<MyData> list = todoservice.searchbyisdone(mydata);
            mav.addObject("data", list);
            res = mav;
        }
        return res;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title", "edit mydata.");
        Optional<MyData> data = todoservice.searchbyid(mydata, id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView update(@ModelAttribute MyData mydata, @RequestParam(value="checkisdone", required=false)boolean checkisdone, ModelAndView mav) {
        todoservice.update(mydata, checkisdone);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ModelAndView find(@ModelAttribute MyData mydata, ModelAndView mav, HttpServletRequest request) {
        mav.setViewName("find");
        mav.addObject("title", "Find page");
        mav.addObject("msg", "MyDataのサンプルです。");
        mav.addObject("value", "");
        return mav;
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, @ModelAttribute MyData mydata, ModelAndView mav) {
        mav.setViewName("find");
        String content = request.getParameter("content");
        content = "%"+content+"%";
        String category = request.getParameter("category");
        if ("%%".equals(content)) {
            mav.addObject("title", "Find result");
            mav.addObject("msg", "検索結果");
            mav.addObject("content", "");
            mav.addObject("category", "");
            List<MyData> data = todoservice.searchbycategory(mydata, category);
            mav.addObject("formModel", data);
            
        }
        else {
            mav.addObject("title", "Find result");
            mav.addObject("msg", "検索結果");
            mav.addObject("content", "");
            mav.addObject("category", "");
            List<MyData> data = todoservice.searchbycontentorcategory(mydata, content, category);
            mav.addObject("formModel", data);
            
        }
        return mav;
    }
}
