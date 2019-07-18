package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.repositories.MyDataRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HelloController {

    @Autowired
    MyDataRepository repository;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("msg", "this is sample content.");
        Iterable<MyData> list=repository.findAll();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView form(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
        repository.saveAndFlush(mydata);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title", "edit mydata.");
        Optional<MyData> data = repository.findById(id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView update(@ModelAttribute MyData mydata, ModelAndView mav) {
        System.out.println(mydata.getDone());
        repository.saveAndFlush(mydata);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value="/find", method=RequestMethod.GET)
    public ModelAndView find(@ModelAttribute MyData mydata, ModelAndView mav, HttpServletRequest request) {
        mav.setViewName("find");
        mav.addObject("title", "Find page");
        mav.addObject("msg", "MyDataのサンプルです。");
        mav.addObject("value", "");
        return mav;
    }

    @RequestMapping(value="/find", method=RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
        mav.setViewName("find");
        String content = request.getParameter("content");
        content = "%"+content+"%";
        String category = request.getParameter("category");
        System.out.println(content);
        System.out.println(category);
        if (content=="" && category=="") {
            mav = new ModelAndView("redirect:/find");
        }
        else {
            mav.addObject("title", "Find result");
            mav.addObject("msg", "検索結果");
            mav.addObject("content", "");
            mav.addObject("category", "");
            System.out.println("let's data!");
            List<MyData> data = repository.findByContentLikeOrCategory(content, category);
            mav.addObject("formModel", data);
            
        }
        return mav;
    }

}

class DataObject {
    private int id;
    private String name;
    private String value;

    public DataObject(int id, String name, String value) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}