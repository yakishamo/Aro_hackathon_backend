package com.example.demo.operation;

import com.example.demo.models.CPU;

public class nop extends mnemonic {
	public nop() {
		name = "nop";
	}
	public boolean exec(String [] args, CPU cpu) {
		System.out.print("nop.");
		return true;
	}
}

