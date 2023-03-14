package com.example.demo.operation;
import com.example.demo.models.Register;
import com.example.demo.models.CPU;
import com.example.demo.models.Rflags;
import java.util.*;

public class sub extends mnemonic {
	public sub() {
		name = "sub";
	}
	private CPU cpu;

	private boolean sub_r_im(String r, long im) {
		Register reg = cpu.select_register(r);
		if(reg == null) return false;
		long reg_val = reg.toInt();
		long res = reg_val - im;
		reg.setVal(res);
		Rflags f = cpu.getRflags();
		if(res == 0) f.setZF(true);
		else f.setZF(false);
		if((res >>> (reg.getBitsize()-1)) == 1) f.setSF(true);
		else f.setSF(false);
		return true;
	}
	private boolean sub_r_r(String r1, String r2) {
		Register reg1 = cpu.select_register(r1);
		Register reg2 = cpu.select_register(r2);
		if(reg1 == null || reg2 == null) return false;
		long res = reg1.toInt() - reg2.toInt();
		reg1.setVal(res);
		Rflags f = cpu.getRflags();
		if(res == 0) f.setZF(true);
		else f.setZF(false);
		if((res >>> (reg1.getBitsize()-1)) == 1) f.setSF(true);
		else f.setSF(false);
		return true;
	}
	private boolean sub_m_r(String [] m, String r) {
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
		long res = mem_val - reg_val;
		cpu.getMemory().write((int)reg1.toInt(), byte_size, res);
		//フラグレジスタの設定
		Rflags f = cpu.getRflags();
		if(res == 0) f.setZF(true);
		else f.setZF(false);
		if((res >>> (byte_size*8-1)) == 1) f.setSF(true);
		else f.setSF(false);
		
		return true;
	}
	private boolean sub_r_m(String r, String [] m) {
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
		long res = reg_val - mem_val;
		reg2.setVal(res);
		Rflags f = cpu.getRflags();
		if(res == 0) f.setZF(true);
		else f.setZF(false);
		if((res >>> (byte_size*8-1)) == 1) f.setSF(true);
		else f.setSF(false);
		return true;
	}
	private boolean sub_m_im(String [] m, long im) {
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
		long mem_val = cpu.getMemory().read((int)reg1.toInt(), byte_size); //addr, size
		long res = mem_val - im;
		cpu.getMemory().write((int)reg1.toInt(), byte_size, res);
		Rflags f = cpu.getRflags();
		if(res == 0) f.setZF(true);
		else f.setZF(false);
		if((res >>> (byte_size*8-1)) == 1) f.setSF(true);
		else f.setSF(false);
		return true;
	}
	
	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("sub.exec called.\n");
		System.out.printf("%s %s\n", args[0], args[1]);
		this.cpu = cpu;
		if(!(args[0].matches("((Q*|D*)WORD)|(BYTE)"))) {
			if((args[1].matches("((Q*|D*)WORD)|(BYTE)"))) {
				try {
					return sub_r_m(args[0], Arrays.copyOfRange(args, 1, 4));
				} catch (Exception e) {
					return false;
				}
			} else {
				try {
					return sub_r_im(args[0], Integer.parseInt(args[1]));
				} catch (Exception e) {
					return sub_r_r(args[0], args[1]);
				}
			}
		} else {
			try {
				return sub_m_im(Arrays.copyOfRange(args, 0, 3), Integer.parseInt(args[3]));
			} catch (Exception e) {
				return sub_m_r(Arrays.copyOfRange(args, 0, 3), args[3]);
			}
		}
	}
}
