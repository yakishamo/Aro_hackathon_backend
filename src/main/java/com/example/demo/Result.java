package com.example.demo;

import com.example.demo.models.CPU;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Result {

    private ArrayList<String> register;
    private ArrayList<String> memory;

		Result() {
			regiser = new ArrayList<String>();
			memory = new ArrayListM<String>();
		}

    public void setRegister(CPU cpu){
			register.add(cpu.select_register("rax").toString);
			register.add(cpu.select_register("rbx").toString);
			register.add(cpu.select_register("rcx").toString);
			register.add(cpu.select_register("rdx").toString);
			register.add(cpu.select_register("rsp").toString);
			register.add(cpu.select_register("rbp").toString);
			register.add(cpu.select_register("rsi").toString);
			register.add(cpu.select_register("rdi").toString);
			register.add(cpu.select_register("r8").toString);
			register.add(cpu.select_register("r9").toString);
			register.add(cpu.select_register("r10").toString);
			register.add(cpu.select_register("r11").toString);
			register.add(cpu.select_register("r12").toString);
			register.add(cpu.select_register("r13").toString);
			register.add(cpu.select_register("r14").toString);
			register.add(cpu.select_register("r15").toString);
			register.add(cpu.getRflags().toInt().toString());
			register.add(cpu.getRip().toString());	
    }

    public void setMemory(CPU cpu){
			Memory mem = cpu.getMemory();
			for(int i = 0; i < mem.getMemorysize(); i++) {
				memory.add(mem.read(i,1).toString());
			}
		}
}
