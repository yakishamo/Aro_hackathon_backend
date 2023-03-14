package com.example.demo.models;

public class Register {
	protected long reg;
	protected String name;
	protected int bit_size;
	protected boolean upper;
	
	Register(String name) {
		this.name = name;
		bit_size = 64;
		upper = false;
	}

	Register(String name, long reg) {
		this.name = name;
		bit_size = 64;
		upper = false;
		this.reg = reg;
	}
	
	public long toInt() {
		if(upper && bit_size == 8) {
			return reg & (0xff00);
		}
		return reg & (0xffffffff >>> (64-bit_size));
	}

	public int getBytesize() {
		return bit_size;
	}

	public String toString() {
		return String.valueOf(reg);
	}

	public Register setBitsize(int bit) {
		if(bit > 64 || bit < 0) bit = 64;
		bit_size = bit;
		return this;
	}

	public Register setUpper(boolean b) {
		upper = b;
		return this;
	}

	public Register setVal(long val) {
		long mask = 0xffffffff;
		mask = mask >>> (64-bit_size);
		val = val & mask;
		if(upper & bit_size == 8) {
			val = val << 8;
		}
		reg = val;
		return this;
	}
	
	public Register setByName(String name_) {
		int name_val;
		try {
			name_val = Integer.parseInt(this.name);
		} catch(NumberFormatException e) {
			name_val = 0;
		}
		String right = name_.substring(name_.length() - 1);
		if(name_val >= 8 && name_val <= 15) {//from r8 to r15
			this.setUpper(false);
			if(name.substring(name.length()-1).equals(right)) {
				return this.setBitsize(64);
			} else if(right.equals("d")) {
				return this.setBitsize(32);
			} else if(right.equals("w")) {
				return this.setBitsize(16);
			} else if(right.equals("b")) {
				return this.setBitsize(8);
			}
		} else {
			if(right.equals("l")) {
				return this.setBitsize(8).setUpper(false);
			} else if(right.equals("h")) {
				return this.setBitsize(8).setUpper(true);
			} else if(right.equals("x") || right.equals("p") || right.equals("i")) {
				String left = name_.substring(0,1);
				if(left.equals(this.name)) {
					return this.setBitsize(16);
				} else if(left.equals("e")) {
					return this.setBitsize(32);
				} else if(left.equals("r")) {
					return this.setBitsize(64);
				}
			}
		}
		return null;
	}
};
