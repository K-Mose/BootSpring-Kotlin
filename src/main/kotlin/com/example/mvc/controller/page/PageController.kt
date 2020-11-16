package com.example.mvc.controller.page

import com.example.mvc.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@Controller // static 하위의 html 페이지를 찾음.
class PageController {

    // http://localhost:8080/main
    @GetMapping("/main")
    fun main():String{
        println("init main")
        return "main.html" // static안에 html파일 찾음
    }

    @ResponseBody
    @GetMapping("/test")
    fun response():UserRequest{
        return UserRequest().apply{
            this.name = "mose"
        }
//        return "main.html"
    }
}