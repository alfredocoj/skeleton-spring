package br.uema.skeleton.controllers;

import br.uema.skeleton.entities.Permissao;
import br.uema.skeleton.models.PermissaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

abstract public class ControllerBase {

    @Autowired
    private PermissaoModel permissaoModel;

    public ModelAndView retornoDefaultModelAndView(String url, ModelAndView mv){
        mv.setViewName(url);
        mv.addObject("permissoes", permissaoModel.getAll(new Permissao()));
        return mv;
    }

    public ModelAndView index(String url, ModelAndView mv){
        return retornoDefaultModelAndView(url, mv);
    }

    public Object index(Object objeto){
        return objeto;
    }

    public ModelAndView create(String url, ModelAndView mv){
        return retornoDefaultModelAndView(url, mv);
    }

    public Object create(Object objeto){
        return objeto;
    }

    public ModelAndView update(String url, ModelAndView mv){
        return retornoDefaultModelAndView(url, mv);
    }

    public Object update(Object objeto){
        return objeto;
    }

    public ModelAndView delete(String url, ModelAndView mv){
        return retornoDefaultModelAndView(url, mv);
    }

    public Object delete(Object objeto){
        return objeto;
    }
}
