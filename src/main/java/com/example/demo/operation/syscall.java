package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class syscall extends mnemonic {
	public syscall() {
		name = "syscall";
	}
	private CPU cpu;
	private Memory mem;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("syscall.exec called.\n");
		this.cpu = cpu;
		this.mem = cpu.getMemory();
		long rax = cpu.select_register("rax").toInt();
		long rdi = cpu.select_register("rdi").toInt();
		int rsi = (int)cpu.select_register("rsi").toInt();
		int rdx = (int)cpu.select_register("rdx").toInt();
	
		byte [] ascii = new byte[rdx];
		if(!(rax == 1 && rdi == 1)) return false;
		for(int i = 0; i < rdx; i++) {
			ascii[i] = (byte)mem.read(rsi+i, 1);
		}
		System.out.println(ascii[0]);
		try {
			System.out.println(new String(ascii, "US-ASCII"));
			cpu.setDisplay(new String(ascii, "US-ASCII"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
