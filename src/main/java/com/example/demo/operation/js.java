package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class js extends mnemonic {
	public js() {
		name = "js";
	}
	private CPU cpu;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("js.exec called.\n");
		this.cpu = cpu;
		if(cpu.getRflags().SF())
			cpu.getRip().setVal(Long.parseLong(args[0]));
		return true;
	}
}
