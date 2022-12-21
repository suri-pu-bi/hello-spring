package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello") // 웹 어플리케이션(브라우저)에서 '/hello' 가 들어오면 이 method 호출
    public String hello(Model model) { // MVC 모델
        model.addAttribute("data", "수미님");
        return "hello";
        // templates의 hello.html의 이름과 같음
        // 즉, templates 폴더 안의 hello.html한테 data를 넘겨서 화면을 실행시켜라 라는 뜻

    }

    @GetMapping("hello-mvc")
    // @RequestParam에 옵션 required = True (defalut) : 기본으로 값을 넘겨야함 / False이면 값을 안넘겨도 됨
    // HTTP GET 방식: http://localhost:8080/hello-mvc?name=spring!!!
    // ctrl + p : 옵션 정보
    public String helloMvc(@RequestParam(value = "name") String name, Model model){ // name이라는 Parameter 받음
        model.addAttribute("name", name);
        return "hello-template"; // 받은 파라미터 name을 hello-template에 넘긴다.
    }

}
