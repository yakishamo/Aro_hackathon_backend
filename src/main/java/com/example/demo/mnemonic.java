package com.example.demo;
import java.util.*;
import com.example.demo.models.CPU;

abstract class mnemonic {
	protected String name;
	public String toString() {
		return this.name;
	}
	public abstract boolean exec(String [] args, CPU cpu); //success...true fail...false
}

class nop extends mnemonic {
	nop() {
		name = "nop";
	}
	public boolean exec(String [] args, CPU cpu) {
		System.out.print("nop.");
		return true;
	}
}

/*
class add extends mnemonic {
	add() {
		name = "add";
	}

	private boolean add_r_im(String r, long im) {
		Register reg = cpu.select_register(r);
		if(rag == null) return false;
		long reg_val = reg.toInt();
		reg_val += im;
		reg.setVal(reg_val);
		return true;
	}
	private boolean add_r_r(String r1, String r2) {
		Register reg1 = cpu.select_register(r1);
		Register reg2 = cpu.select_register(r2);
		if(reg1 == null || reg2 == null) return false;
		reg1.setVal(reg1.toInt() + reg2.toInt());
		return true;
	}
	private boolean add_m_r(String [] m, String r);
	int byte_size = 0;
	if(!(m[1].equals("PTR"))) return false;
	if(m[0].equals("QWORD")) {
		byte_size = 8;
	} else if(m[0].equals("DWORD")) {
		byte_size = 4;
	} else if(m[0].equals("WORD")) {
		byte_size = 2;
	} else if(m[0].equals("BYTE")) {
		byte_size = 1;
	} else {
		return false;
	}
	String reg_name = m[2].substring(1,m[2].length-1);
	int mem_val = cpu.memory.read()//
	private boolean add_r_m(String r, String [] m);
	private boolean add_m_im(String [] m, long im);
	public boolean exec(String [] args) {
		if(!(args[0].matches("((Q*|D*)WORD)|(BYTE))"))) {
			if((args[1].matches("((Q*|D*)WORD)|(BYTE))"))) {
				try {
					return add_r_m(args[0], Arrays.copyOfRange(args, 1, 4));
				} catch (Exception e) {
					return false;
				}
			} else {
				try {
					return add_r_im(args[0], Integer.parseInt(args[1]));
				} catch (Exception e) {
					return add_r_r(args[0], args[1]);
				}
			}
		} else {
			try {
				return add_m_im(Arrays.copyOfRange(args[0], 0, 3), Integer.parseInt(args[3]));
			} catch (Exception e) {
				return add_m_r(Arrays.copyOfRange(args[0], 0, 3), args[3]);
			}
		}
	}
}*/
