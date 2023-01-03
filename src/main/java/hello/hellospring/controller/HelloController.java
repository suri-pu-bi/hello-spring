package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // API 불러올 때, 이것을 사용하면 viewResolver를 사용하지 않음
    // HTTP의 Body 부분에 이 데이터를 직접 넣어주겠다는 의미 (HTML body tag를 말하는 것이 아님)
    // 실행시켜보면 html 태그 이런거 없고, 데이터가 그냥 그대로 전달
    public String helloString(@RequestParam("name") String name) { // MVC 모델로 view를 사용하는 것이 아니기 때문에 model 필요X
        return "hello " + name;
    }

    // 데이터를 내놓을 때는?
    @GetMapping("hello-api")
    @ResponseBody

    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 넘김
    }
    // json data: key, value로 이루어진 data

//    HelloController.Hello 라고 쓸 수 있음
    static class Hello {
        private String name;
        // private 이기 때문에 외부에 접근 X -> method를 통해 접근할 수 있도록 함 (property 접근 방식)


        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
