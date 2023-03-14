package com.example.demo.operation;
import com.example.demo.models.Register;
import com.example.demo.models.CPU;
import com.example.demo.models.Rflags;
import java.util.*;

public class xchg extends mnemonic {
	public xchg() {
		name = "xchg";
	}
	private CPU cpu;

	private boolean xchg_r_r(String r1, String r2) {
		Register reg1 = cpu.select_register(r1);
		Register reg2 = cpu.select_register(r2);
		if(reg1 == null || reg2 == null) return false;
		long temp = reg1.toInt();
		reg1.setVal(reg2.toInt());
		reg2.setVal(temp);
		return true;
	}
	private boolean xchg_m_r(String [] m, String r) {
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
		String reg_name = m[2].substring(1,m[2].length()-1);
		Register reg1 = cpu.select_register(reg_name);
		Register reg2 = cpu.select_register(r);
		if(reg1 == null || reg2 == null) return false;
		long mem_val = cpu.getMemory().read((int)reg1.toInt(), byte_size); //addr, size
		long reg_val = reg2.toInt();
		long temp = reg_val;
		reg2.setVal(mem_val);
		cpu.getMemory().write((int)reg1.toInt(), byte_size, temp);

		return true;
	}
	private boolean xchg_r_m(String r, String [] m) {
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
		String reg_name = m[2].substring(1,m[2].length()-1);
		Register reg1 = cpu.select_register(reg_name);
		Register reg2 = cpu.select_register(r);
		if(reg1 == null || reg2 == null) return false;
		long mem_val = cpu.getMemory().read((int)reg1.toInt(), byte_size); //addr, size
		long reg_val = reg2.toInt();
		long temp = reg_val;
		reg2.setVal(mem_val);
		cpu.getMemory().write((int)reg1.toInt(), byte_size, temp);
		return true;
	}
	
	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("add.exec called.\n");
		System.out.printf("%s %s\n", args[0], args[1]);
		this.cpu = cpu;
		if(!(args[0].matches("((Q*|D*)WORD)|(BYTE)"))) {
			if((args[1].matches("((Q*|D*)WORD)|(BYTE)"))) {
				try {
					return xchg_r_m(args[0], Arrays.copyOfRange(args, 1, 4));
				} catch (Exception e) {
					return false;
				}
			} else {
				return xchg_r_r(args[0], args[1]);	
			}
		} else {
			return xchg_m_r(Arrays.copyOfRange(args, 0, 3), args[3]);
		}
	}
}
