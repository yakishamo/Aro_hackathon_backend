package com.example.demo.models;

public class CPU {
	public CPU(){
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
	
	public long getRip() {
		return rip;
	}
	public Rflags getRflags() {
		return rflags;
	}
	public Memory getMemory() {
		return memory;
	}
	public Register select_register(String s) {
		if(s.matches("((r*|e*)ax)|ah|al)")) {
			return rax.setByName(s);
		} else if(s.matches("((r*|e*)bx)|ah|al)")) {
			return rbx.setByName(s);
		} else if(s.matches("((r*|e*)cx)|ch|cl)")) {
			return rcx.setByName(s);
		} else if(s.matches("((d*|d*)dx)|dh|dl)")) {
			return rdx.setByName(s);
		} else if(s.matches("((r*|e*)si)|sil")) {
			return rsi.setByName(s);
		} else if(s.matches("((r*|e*)di)|dil")) {
			return rdi.setByName(s);
		} else if(s.matches("((r*|e*)bp)|bpl")) {
			return rbp.setByName(s);
		} else if(s.matches("((r*|e*)sp)|spl")) {
			return rsp.setByName(s);
		} else if(s.matches("r8(d*|w*|b*)")) {
			return r8.setByName(s);
		} else if(s.matches("r9(d*|w*|b*)")) {
			return r9.setByName(s);
		} else if(s.matches("r10(d*|w*|b*)")) {
			return r10.setByName(s);
		} else if(s.matches("r11(d*|w*|b*)")) {
			return r11.setByName(s);
		} else if(s.matches("r12(d*|w*|b*)")) {
			return r12.setByName(s);
		} else if(s.matches("r13(d*|w*|b*)")) {
			return r13.setByName(s);
		} else if(s.matches("r14(d*|w*|b*)")) {
			return r14.setByName(s);
		} else if(s.matches("r15(d*|w*|b*)")) {
			return r15.setByName(s);
		} else {
			return null;
		}
	}
}
