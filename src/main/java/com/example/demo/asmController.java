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

    static final int MAX = 1000;

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
        	cpu = new CPU(asm.getRegister(), asm.getMemory(), asm.getDisplay());
				} catch (Exception e) {
					r.setIsSuccess(false);
					r.setError_message("仮想CPUの初期化に失敗しました。");
					return r;
				}
        Calculator c = new Calculator(cpu, asm);
				boolean isSuccess = c.run();
        r.setIsSuccess(isSuccess);
				if(!isSuccess) r.setError_message("命令の実行に失敗しました。");
        r.setRegister(cpu);
        r.setMemory(cpu);
				r.setDisplay(cpu);
        return r;
    }

    @CrossOrigin
    @RequestMapping("/asm/all")
    public Object allResult(@RequestBody Assembly asm){
        if(asm == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }

        Result r = new Result();
        CPU cpu;
        try {
            cpu = new CPU(asm.getRegister(), asm.getMemory(), asm.getDisplay());
        } catch (Exception e) {
            r.setIsSuccess(false);
            return r;
        }
        int counter = 0;
        int row =(int) cpu.getRip().setVal(1).toInt();
        while(true){
            if(counter >= MAX){
                throw  new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT, "実行回数が制限を超えています。");
            }
            row = (int)cpu.getRip().toInt();
            asm.setMnemonic(asm.getMnemonics()[row-1]);
            cpu.getRip().setVal(row+1);

            String[] terms = asm.getMnemonic().split("[, ]+");
            asm.setTerms(Arrays.copyOfRange(terms, 1, terms.length));
            asm.setMnemonic(terms[0]);
            Calculator c = new Calculator(cpu, asm);
            r.setIsSuccess(c.run());
            if(r.getIsSuccess() == false){
							r.setError_message(String.format("%d: 命令の実行に失敗しました。", row));
							return r;
              //  throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "構文エラー");
            }
            if(cpu.getRip().toInt() > asm.getMnemonics().length){
                r.setRegister(cpu);
                r.setMemory(cpu);
								r.setDisplay(cpu);
                break;
            }
            counter++;

        }
        return r;
    }

}
