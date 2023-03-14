package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class jmp extends mnemonic {
	public jmp() {
		name = "jmp";
	}
	private CPU cpu;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("jmp.exec called.\n");
		this.cpu = cpu;
		cpu.getRip().setVal(Long.parseLong(args[0]));
		return true;
	}
}
