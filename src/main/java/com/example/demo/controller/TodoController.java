package com.example.demo.controller;

import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.entity.MyData;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

@Controller
public class TodoController {

    @Autowired
    TodoService service;

    @RequestMapping(value = "/", method=RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("formModel") MyData mydata, ModelAndView mav) {
        return service.index(mydata, mav);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView form(@ModelAttribute("formModel") @Validated MyData mydata, BindingResult result, ModelAndView mav) {
        return service.form(mydata, result, mav);
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView edit(@ModelAttribute MyData mydata, @PathVariable int id, ModelAndView mav) {
        return service.edit(mydata, id, mav);
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    @Transactional(readOnly=false)
    public ModelAndView update(@ModelAttribute MyData mydata, @RequestParam(value="checkisdone", required=false)boolean checkisdone, ModelAndView mav) {
        return service.update(mydata, checkisdone, mav);
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ModelAndView find(@ModelAttribute MyData mydata, ModelAndView mav, HttpServletRequest request) {
        return service.find(mydata, mav, request);
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request, ModelAndView mav) {
        return service.search(request, mav);
    }
}
