package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class jne extends mnemonic {
	public jne() {
		name = "jne";
	}
	private CPU cpu;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("jne.exec called.\n");
		this.cpu = cpu;
		if(!cpu.getRflags().ZF())
			cpu.getRip().setVal(Long.parseLong(args[0]));
		return true;
	}
}
