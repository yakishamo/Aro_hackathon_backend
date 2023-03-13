package com.example.demo.models;
import java.util.*;

abstract class mnemonic {
	protected String name;
	public String toString() {
		return this.name;
	}
	public abstract boolean exec(String arg1, String arg2); //success...true fail...false
}

class nop extends mnemonic {
	nop() {
		name = "nop";
	}
	public boolean exec(String arg1, String arg2) {
		return true;
	}
}

class CPU {
	public Register rax = new Register("a");
	public Register rbx = new Register("b");
	public Register rcx = new Register("c");
	public Register rdx = new Register("d");
	public Register rsp = new Register("s");
	public Register rbp = new Register("b");
	public Register rsi = new Register("s");
	public Register rdi = new Register("d");
	public Register r8 = new Register("8");
	public Register r9 = new Register("9");
	public Register r10 = new Register("10");
	public Register r11 = new Register("11");
	public Register r12 = new Register("12");
	public Register r13 = new Register("13");
	public Register r14 = new Register("14");
	public Register r15 = new Register("15");
	public long rip = 0;
	public Memory memory = new Memory(0x100);
	private ArrayList<mnemonic> mnemonic_list = new ArrayList<mnemonic>();
	
	public void regist_mnemonic(mnemonic m) {
		mnemonic_list.add(m);
	}
}

