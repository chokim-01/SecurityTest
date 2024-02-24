package com.example.jg.web;

import com.example.jg.config.auth.LoginUser;
import com.example.jg.config.auth.dto.SessionUser;
import com.example.jg.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null)
            model.addAttribute("userName",user.getName());
        return "index";
    }
    @GetMapping("/api/v1/signPage")
    public String index(@LoginUser SessionUser user) {
        return "user-signIn";
    }

    @GetMapping("/posts/update/{id}")
    public String index(@PathVariable Long id,Model model) {
        model.addAttribute("post",postsService.findById(id));
        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

}
