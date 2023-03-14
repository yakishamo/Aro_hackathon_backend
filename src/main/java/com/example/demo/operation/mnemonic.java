package com.example.demo;
import java.util.*;
import com.example.demo.models.CPU;

abstract class mnemonic {
	protected String name;
	public String toString() {
		return this.name;
	}
	public abstract boolean exec(String [] args, CPU cpu); //success...true fail...false
}

class nop extends mnemonic {
	nop() {
		name = "nop";
	}
	public boolean exec(String [] args, CPU cpu) {
		System.out.print("nop.");
		return true;
	}
}


