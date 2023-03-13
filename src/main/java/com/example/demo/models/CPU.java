package com.example.demo.models;
import java.util.*;

abstract class mnemonic {
	protected String name;
	private CPU cpu;
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
		System.out.print("nop.");
		return true;
	}
}

class CPU {
	CPU(){
		rax = new Register("a");
		rbx = new Register("b");
		rcx = new Register("c");
		rdx = new Register("d");
		rsp = new Register("s");
		rbp = new Register("b");
		rsi = new Register("s");
		rdi = new Register("d");
		r8 = new Register("8");
		r9 = new Register("9");
		r10 = new Register("10");
		r11 = new Register("11");
		r12 = new Register("12");
		r13 = new Register("13");
		r14 = new Register("14");
		r15 = new Register("15");
		rflags = new Rflags();
		rip = 0;
		memory = new Memory(0x100);
	}
	private Register rax;
	private Register rbx;
	private Register rcx;
	private Register rdx;
	private Register rsp;
	private Register rbp;
	private Register rsi;
	private Register rdi;
	private Register r8;
	private Register r9;
	private Register r10;
	private Register r11;
	private Register r12;
	private Register r13;
	private Register r14;
	private Register r15;
	private Rflags rflags;
	private long rip;
	private Memory memory;
	private ArrayList<mnemonic> mnemonic_list = new ArrayList<mnemonic>();

	public void regist_mnemonic(mnemonic m) {
		mnemonic_list.add(m);
	}
}



