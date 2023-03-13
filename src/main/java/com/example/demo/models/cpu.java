package com.example.demo.models;

class mnemonic {
	protected String name;
	public String toString() {
		return this.name;
	}
}

class CPU {
	protected Register rax = new Register("a");
	protected Register rbx = new Register("b");
	protected Register rcx = new Register("c");
	protected Register rdx = new Register("d");
	protected Register rsp = new Register("s");
	protected Register rbp = new Register("b");
	protected Register rsi = new Register("s");
	protected Register rdi = new Register("d");
	protected Register r8 = new Register("8");
	protected Register r9 = new Register("9");
	protected Register r10 = new Register("10");
	protected Register r11 = new Register("11");
	protected Register r12 = new Register("12");
	protected Register r13 = new Register("13");
	protected Register r14 = new Register("14");
	protected Register r15 = new Register("15");
	protected long rip = 0;
	protected Memory memory = new Memory(0x100);
}

