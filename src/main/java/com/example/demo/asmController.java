package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class asmController {
    @RequestMapping("/asm/{mnemonic}")
    public Assembly read(@PathVariable String mnemonic){
        Assembly asm = new Assembly();
        if(mnemonic.equals("") || mnemonic == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"); 
        }
        asm.setMnemonic(mnemonic);
        String[] terms = asm.getMnemonic().split(", ");
        asm.setTerms(terms);
        return asm;
    }

}