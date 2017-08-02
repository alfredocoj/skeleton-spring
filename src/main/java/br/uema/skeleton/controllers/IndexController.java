package br.uema.skeleton.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController extends ControllerBase {

    @RequestMapping
    public ModelAndView index() {
        return new ModelAndView("index/index");
    }
}
