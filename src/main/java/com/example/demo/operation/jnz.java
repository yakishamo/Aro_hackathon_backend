package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class jnz extends mnemonic {
	public jnz() {
		name = "jnz";
	}
	private CPU cpu;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("jnz.exec called.\n");
		this.cpu = cpu;
		if(!cpu.getRflags().ZF())
			cpu.getRip().setVal(Long.parseLong(args[0]));
		return true;
	}
}
