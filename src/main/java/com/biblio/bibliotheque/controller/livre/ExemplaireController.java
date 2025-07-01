package com.biblio.bibliotheque.controller.livre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/exemplaires")
public class ExemplaireController {

    @GetMapping("/")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
}
