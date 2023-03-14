package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.demo.models.CPU;

@RestController
public class asmController {
    @CrossOrigin
    @PostMapping("/asm")
    public Result read(@RequestBody Assembly asm){
				System.out.printf("in Result\n");
        if(asm.equals("") || asm == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        
        String[] terms = asm.getMnemonic().split("[, ]+");
        asm.setTerms(Arrays.copyOfRange(terms, 1, terms.length));
				asm.setMnemonic(terms[0]);
				Result r = new Result();
				CPU cpu;
				try {
        	cpu = new CPU(asm.getRegister(), asm.getMemory());
				} catch (Exception e) {
					r.setIsSuccess(false);
					return r;
				}
        Calculator c = new Calculator(cpu, asm);
        r.setIsSuccess(c.run());
        r.setRegister(cpu);
        r.setMemory(cpu);
        return r;
    }

    @CrossOrigin
    @RequestMapping("/asm/all")
    public Object allResult(@RequestBody Assembly asms){
        if(asms == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        
        asms.setMnemonics(asms.getMnemonic().split("[\r\n]+"));
        Assembly asm = new Assembly();
        asm.setMemory(Arrays.copyOf(asms.getMemory(), asms.getMemory().length));
        asm.setRegister(Arrays.copyOf(asms.getRegister(), asms.getRegister().length));

        Result r = new Result();
        
        for(int i=0;i<asms.getMnemonics().length;i++){
            asm.setMnemonic(asms.getMnemonics()[i]);

            String[] terms = asm.getMnemonic().split("[, ]+");
            asm.setTerms(Arrays.copyOfRange(terms, 1, terms.length));
                    asm.setMnemonic(terms[0]);
                    CPU cpu;
                    try {
                cpu = new CPU(asm.getRegister(), asm.getMemory());
                    } catch (Exception e) {
                        r.setIsSuccess(false);
                        return r;
                    }
            Calculator c = new Calculator(cpu, asm);
            r.setIsSuccess(c.run());
            r.setRegister(cpu);
            r.setMemory(cpu);
            if(r.getIsSuccess() == false){
                break;
            }
        }
        return r;
    }

}
