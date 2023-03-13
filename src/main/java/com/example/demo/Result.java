package com.example.demo;

import com.example.demo.models.CPU;
import com.example.demo.models.Register;
import com.example.demo.models.Memory;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class Result {

    private ArrayList<String> register;
    private ArrayList<String> memory;

    public void setRegister(CPU cpu){
			try {
			register.add(cpu.select_register("rax").toString());
			register.add(cpu.select_register("rbx").toString());
			register.add(cpu.select_register("rcx").toString());
			register.add(cpu.select_register("rdx").toString());
			register.add(cpu.select_register("rsp").toString());
			register.add(cpu.select_register("rbp").toString());
			register.add(cpu.select_register("rsi").toString());
			register.add(cpu.select_register("rdi").toString());
			register.add(cpu.select_register("r8").toString());
			register.add(cpu.select_register("r9").toString());
			register.add(cpu.select_register("r10").toString());
			register.add(cpu.select_register("r11").toString());
			register.add(cpu.select_register("r12").toString());
			register.add(cpu.select_register("r13").toString());
			register.add(cpu.select_register("r14").toString());
			register.add(cpu.select_register("r15").toString());
			register.add(String.valueOf(cpu.getRflags().toInt()));
			register.add(String.valueOf(cpu.getRip()));	
			} catch (Exception e) {
				System.out.print(cpu.select_register("rax"));
			}
    }

    public void setMemory(CPU cpu){
			Memory mem = cpu.getMemory();
			System.out.print(mem);
			for(int i = 0; i < mem.getMemorysize(); i++) {
				System.out.printf(":%s", String.valueOf(mem.read(i,1)));
				memory.add(String.valueOf(mem.read(i,1)));
			}
		}
}
