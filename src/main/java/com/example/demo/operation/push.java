package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class push extends mnemonic {
	public push() {
		name = "push";
	}
	private CPU cpu;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("push.exec called.\n");
		this.cpu = cpu;
		Register reg = cpu.select_register(args[0]);
		int byte_size = reg.getBitsize()/8;
		if(byte_size != 2 && byte_size != 8) {
			return false;
		}
		Register rsp = cpu.select_register("rsp");
		rsp.setVal(rsp.toInt() + byte_size);
		cpu.getMemory().write((int)rsp.toInt(), byte_size, reg.toInt());
		return true;
	}
}
