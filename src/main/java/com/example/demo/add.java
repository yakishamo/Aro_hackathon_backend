package com.example.demo;
import com.example.demo.models.Register;
import com.example.demo.models.CPU;
import com.example.demo.models.Rflags;
import java.util.*;

class add extends mnemonic {
	add() {
		name = "add";
	}
	private CPU cpu;

	private boolean add_r_im(String r, long im) {
		Register reg = cpu.select_register(r);
		if(reg == null) return false;
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
	private boolean add_m_r(String [] m, String r) {
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
		int mem_val = (int)cpu.getMemory().read((int)reg1.toInt(), byte_size); //addr, size
		int reg_val = (int)reg2.toInt();
		cpu.getMemory().write((int)reg1.toInt(), byte_size, mem_val + reg_val);
		return true;
	}
	private boolean add_r_m(String r, String [] m) {
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
		int mem_val = (int)cpu.getMemory().read((int)reg1.toInt(), byte_size); //addr, size
		int reg_val = (int)reg2.toInt();
		reg2.setVal(mem_val + reg_val);
		return true;
	}
	private boolean add_m_im(String [] m, long im) {
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
		if(reg1 == null) return false;
		int mem_val = (int)cpu.getMemory().read((int)reg1.toInt(), byte_size); //addr, size
		cpu.getMemory().write((int)reg1.toInt(), byte_size, mem_val + im);
		return true;
	}
	
	public boolean exec(String [] args, CPU cpu) {
		this.cpu = cpu;
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
				return add_m_im(Arrays.copyOfRange(args, 0, 3), Integer.parseInt(args[3]));
			} catch (Exception e) {
				return add_m_r(Arrays.copyOfRange(args, 0, 3), args[3]);
			}
		}
	}
}
