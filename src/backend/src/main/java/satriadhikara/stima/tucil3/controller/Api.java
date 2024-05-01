package satriadhikara.stima.tucil3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {

    @GetMapping("/api")
    public String api(
            @RequestParam(value = "StartWord", required = true) String startWord,
            @RequestParam(value = "EndWord", required = true) String endWord,
            @RequestParam(value = "Method", required = true) String method) {
        return "StartWord: " + startWord + ", EndWord: " + endWord + ", Method: " + method;
    }
}
