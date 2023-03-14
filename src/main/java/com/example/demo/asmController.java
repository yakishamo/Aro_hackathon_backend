package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.CPU;

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
				Result r = new Result();
        Calculator c = new Calculator(cpu, asm);
        r.setIsSuccess(c.run());
        r.setRegister(cpu);
        r.setMemory(cpu);
        return r;
    }

}
