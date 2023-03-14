package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.CPU;

@RestController
public class asmController {
    @RequestMapping("/asm/")
    public Result read(@RequestBody Assembly asm){
        
        if(asm.equals("") || asm == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"); 
        }
        String[] terms = asm.getMnemonic().split("[, ]");
        asm.setTerms(terms);
        CPU cpu = new CPU();
				Result r = new Result();
        Calculator c = new Calculator(cpu, asm);
        r.setIsSuccess(c.run());
        r.setRegister(cpu);
        r.setMemory(cpu);
        return r;
    }

}
