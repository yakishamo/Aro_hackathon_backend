package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.demo.models.CPU;

@RestController
public class asmController {
    @RequestMapping("/asm")
    public Result read(@RequestBody Assembly asm){

        if(asm.equals("") || asm == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        
        String[] terms = asm.getMnemonic().split("[, ]");
        asm.setTerms(Arrays.copyOfRange(terms, 1, terms.length));
				asm.setMnemonic(terms[0]);
        CPU cpu = new CPU();
				Result r = new Result();
        Calculator c = new Calculator(cpu, asm);
        r.setIsSuccess(c.run());
        r.setRegister(cpu);
        r.setMemory(cpu);
        return r;
    }


    @RequestMapping("/asm/all")
    public Object allResult(@RequestBody Assembly asm){
        if(asm == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        
        asm.setMnemonics(asm.getMnemonic().split("[\r\n]"));
               
        return null;
    }
}
