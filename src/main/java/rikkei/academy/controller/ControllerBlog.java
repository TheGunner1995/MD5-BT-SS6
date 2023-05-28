package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.model.Blog;
import rikkei.academy.service.blog.IBlogService;

@Controller
public class ControllerBlog {
    @Autowired
    private IBlogService iBlogService;

    @GetMapping(value = {"/", "/blog"})
    public ModelAndView listBlog() {
        ModelAndView modelAndView = new ModelAndView("/blog");
        modelAndView.addObject("listBlog", iBlogService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView fromCreate() {
        ModelAndView modelAndView = new ModelAndView("/createBlog");
        modelAndView.addObject("newBlog", new Blog());
        return modelAndView;
    }

    @PostMapping("createBlog")
    public String createBlog(@ModelAttribute("newBlog") Blog blog){
        iBlogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog");
        modelAndView.addObject("blog", new Blog());
        return "redirect: /blog";
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable("id") Long id){
        iBlogService.remove(id);
        return "redirect: /blog";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editBlog(@PathVariable("id") Long id){
        Blog blog = iBlogService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("editBlog", blog);
        return modelAndView;
    }

    @PostMapping("edit")
    public String UpdateBlog(@ModelAttribute("editBlog") Blog blog){
        iBlogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("/blog");
        modelAndView.addObject("upBlog", blog);
        return "redirect: /blog";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView Detail(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("listBlog", iBlogService.findById(id));
        return modelAndView;
    }

}
