package com.example.FirstRest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class Greet {

    @GetMapping("/hello/{x}")
    public String message(@PathVariable String x){
        return "Hello World " + x;
    }

    @GetMapping("/gender/{x}/{y}")
    public String genderMessage(@RequestHeader HttpHeaders headers, @PathVariable String x, @PathVariable String y) {
        StringBuilder sb = new StringBuilder();
        Set<String> headerNames = headers.keySet();
        for (String headerName : headerNames) {
            sb.append(headerName).append(": ").append(headers.getFirst(headerName)).append("\n");
        }
        return "Path variables: x=" + x + ", y=" + y + "\nHeaders:\n" + sb.toString();
    }
    @GetMapping("/greeting")
    public ResponseEntity<Object> getGreeting(@RequestHeader("locale") String locale) {
        String greeting;

        switch (locale.toUpperCase()) {
            case "US":
                greeting = "Hi";
                break;
            case "UK":
                greeting = "Hello";
                break;
            case "IN":
                greeting = "Namaste";
                break;
            case "FR":
                greeting = "Ola";
                break;
            default:
                greeting = "Unknown locale: " + locale;
        }

        return ResponseEntity.status(200).body(greeting);
    }
}
