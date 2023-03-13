package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.CPU;
import com.example.demo.models.Memory;

@RestController
public class asmController {
    @RequestMapping("/asm/{mnemonic}")
    public Result read(@PathVariable String mnemonic){
        Assembly asm = new Assembly(); 
        if(mnemonic.equals("") || mnemonic == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"); 
        }
        asm.setMnemonic(mnemonic);
        String[] terms = asm.getMnemonic().split("_");
        asm.setTerms(terms);
        CPU cpu = new CPU();
        Calculater c = new  Calculater(cpu, asm);
        c.run();
        Result r = new Result();
        r.setRegister();
        r.setRegister();
        return r;
    }

}