package com.example.demo;

import com.example.demo.models.CPU;
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
	}

	public void regist_mnemonic(mnemonic m) {
		mnemonic_list.add(m);
	}

	public boolean run(){
		int list_size = mnemonic_list.size();
		for(int i = 0; i < list_size; i++) {
			mnemonic m = mnemonic_list.get(i);
			if(asm.getMnemonic().equals(m)) {
				try {
					return m.exec(asm.getTerms(), cpu);
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}
}
