package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class div extends mnemonic {
	public div() {
		name = "div";
	}
	private CPU cpu;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("div.exec called.\n");
		this.cpu = cpu;
		Register reg1 = cpu.select_register(args[0]);
		int bit_size = reg1.getBitsize();
		Register reg2 = cpu.select_register("rax").setBitsize(bit_size);
		long reg1_val = reg1.toInt();
		long reg2_val = reg2.toInt();
		long quo = reg1_val / reg2_val;
		long rem = reg1_val % reg2_val;
		cpu.select_register("rax").setVal(quo);
		cpu.select_register("rdx").setVal(rem);
		return true;
	}
}
