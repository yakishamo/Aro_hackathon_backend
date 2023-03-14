package com.example.demo;

import com.example.demo.models.CPU;
import com.example.demo.operation.mnemonic;
import com.example.demo.operation.*;
import java.util.*;

public class Calculator {
	private CPU cpu;
	private Assembly asm;
	private ArrayList<mnemonic> mnemonic_list;

	public Calculator(CPU cpu, Assembly asm){
		this.cpu = cpu;
		this.asm = asm;
		mnemonic_list = new ArrayList<mnemonic>();
		regist_mnemonic(new nop());
		regist_mnemonic(new add());
		regist_mnemonic(new sub());
		regist_mnemonic(new mov());
		regist_mnemonic(new xchg());
		regist_mnemonic(new mul());
		regist_mnemonic(new div());
		regist_mnemonic(new cmp());
		regist_mnemonic(new jmp());
		regist_mnemonic(new je());
		regist_mnemonic(new jz());
		regist_mnemonic(new jne());
		regist_mnemonic(new jnz());
		regist_mnemonic(new js());
		regist_mnemonic(new jns());
	}

	public void regist_mnemonic(mnemonic m) {
		mnemonic_list.add(m);
	}

	public boolean run(){
		int list_size = mnemonic_list.size();
		System.out.printf("list_size : %d\n", list_size);
		for(int i = 0; i < list_size; i++) {
			mnemonic m = mnemonic_list.get(i);
			if(asm.getMnemonic().equals(m.toString())) {
				try {
					return m.exec(asm.getTerms(), cpu);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}
}
