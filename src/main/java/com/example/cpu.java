class RFLAGS {
	RFLAGS() {
		cf = pf = af = zf = sf = tf = intf = df = of = iopl_1 = iopl_2 = 
		nt = rf = vm = ac = vif = vip = id = 0;
	}
	protected int
		cf,
		pf,
		af,
		zf,
		sf,
		tf,
		intf,
		df,
		of,
		iopl_1,
		iopl_2,
		nt,
		rf,
		vm,
		ac,
		vif,
		vip,
		id;
	public String toString() {
		String ret = "[ ";
		if(CF()) ret = ret + "CF ";
		if(AF()) ret = ret + "PF ";
		if(ZF()) ret = ret + "ZF ";
		if(SF()) ret = ret + "SF ";
		if(TF()) ret = ret + "TF ";
		if(IF()) ret = ret + "IF ";
		if(DF()) ret = ret + "DF ";
		if(OF()) ret = ret + "OF ";
		if(NT()) ret = ret + "NT ";
		if(RF()) ret = ret + "RF ";
		if(VM()) ret = ret + "VM ";
		if(AC()) ret = ret + "AC ";
		if(VIF()) ret = ret + "VIF ";
		if(VIP()) ret = ret + "VIP ";
		if(ID()) ret = ret + "ID ";
		ret = ret + "] IOPL:" + IOPL();
		return ret;
	}
	public int toInt() {
		return 
			cf << 0 |
			1  << 1 | 
			pf << 2 |
			0  << 3 |
			af << 4 |
			0  << 5 |
			zf << 6 |
			sf << 7 |
			tf << 8 |
			intf << 9 |
			df << 10 |
			of << 11 |
			iopl_1 << 12 |
			iopl_2 << 13 |
			nt << 14 |
			0  << 15 |
			rf << 16 |
			vm << 17 |
			ac << 18 |
			vif << 19 |
			vip << 20 |
			id << 21;
	}

	public boolean CF() {
		return cf == 1;
	}
	public boolean PF() {
		return pf == 1;
	}
	public boolean AF() {
		return af == 1;
	}	
	public boolean ZF() {
		return zf == 1;
	}	
	public boolean SF() {
		return sf == 1;
	}	
	public boolean TF() {
		return tf == 1;
	}	
	public boolean IF() {
		return intf == 1;
	}	
	public boolean DF() {
		return df == 1;
	}	
	public boolean OF() {
		return of == 1;
	}	
	public int IOPL() {
		return iopl_1 & iopl_2 << 1;
	}
	public boolean NT() {
		return nt == 1;
	}	
	public boolean RF() {
		return rf == 1;
	}	
	public boolean VM() {
		return vm == 1;
	}	
	public boolean AC() {
		return ac == 1;
	}	
	public boolean VIF() {
		return vif == 1;
	}	
	public boolean VIP() {
		return vip == 1;
	}	
	public boolean ID() {
		return id == 1;
	}

	public RFLAGS setCF(boolean b) {
		if(b) {
			cf = 1;
		} else {
			cf = 0;
		}
		return this;
	}
	public RFLAGS setPF(boolean b) {
		if(b) {
			pf = 1;
		} else {
			pf = 0;
		}
		return this;
	}
	public RFLAGS setAF(boolean b) {
		if(b) {
			af = 1;
		} else {
			af = 0;
		}
		return this;
	}
	public RFLAGS setZF(boolean b) {
		if(b) {
			zf = 1;
		} else {
			zf = 0;
		}
		return this;
	}
	public RFLAGS setSF(boolean b) {
		if(b) {
			sf = 1;
		} else {
			sf = 0;
		}
		return this;
	}
	public RFLAGS setTF(boolean b) {
		if(b) {
			tf = 1;
		} else {
			tf = 0;
		}
		return this;
	}
	public RFLAGS setIF(boolean b) {
		if(b) {
			intf = 1;
		} else {
			intf = 0;
		}
		return this;
	}
	public RFLAGS setDF(boolean b) {
		if(b) {
			df = 1;
		} else {
			df = 0;
		}
		return this;
	}
	public RFLAGS setOF(boolean b) {
		if(b) {
			of = 1;
		} else {
			of = 0;
		}
		return this;
	}
	public RFLAGS setIOPL(int i) {
		if(i >= 0 && i <= 3) {
			iopl_1 = i&1;
			iopl_2 = (i>>1)&1;
		}
		return this;
	}
	public RFLAGS setNT(boolean b) {
		if(b) {
			nt = 1;
		} else {
			nt = 0;
		}
		return this;
	}
	public RFLAGS setRF(boolean b) {
		if(b) {
			rf = 1;
		} else {
			rf = 0;
		}
		return this;
	}
	public RFLAGS setVM(boolean b) {
		if(b) {
			vm = 1;
		} else {
			vm = 0;
		}
		return this;
	}
	public RFLAGS setAC(boolean b) {
		if(b) {
			ac = 1;
		} else {
			ac = 0;
		}
		return this;
	}
	public RFLAGS setVIF(boolean b) {
		if(b) {
			vif = 1;
		} else {
			vif = 0;
		}
		return this;
	}
	public RFLAGS setVIP(boolean b) {
		if(b) {
			vip = 1;
		} else {
			vip = 0;
		}
		return this;
	}
	public RFLAGS setID(boolean b) {
		if(b) {
			id = 1;
		} else {
			id = 0;
		}
		return this;
	}
}

class REGISTER {
	protected long reg;
	protected String name;
	protected int bit_size;
	protected boolean upper;
	REGISTER(String name) {
		this.name = name;
		bit_size = 64;
		upper = false;
	}
	
	public long toInt() {
		if(upper && bit_size == 8) {
			return reg & (0xff00);
		}
		return reg & (0xffffffff >>> (64-bit_size));
	}

	public REGISTER setBitsize(int bit) {
		if(bit > 64 || bit < 0) bit = 64;
		bit_size = bit;
		return this;
	}

	public REGISTER setUpper(boolean b) {
		upper = b;
		return this;
	}

	public REGISTER setVal(long val) {
		long mask = 0xffffffff;
		mask = mask >>> (64-bit_size);
		val = val & mask;
		if(upper & bit_size == 8) {
			val = val << 8;
		}
		reg = val;
		return this;
	}
	
	public REGISTER setByName(String name_) {
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
}



class CPU {
	protected REGISTER rax = new REGISTER("a");
	protected REGISTER rbx = new REGISTER("b");
	protected REGISTER rcx = new REGISTER("c");
	protected REGISTER rdx = new REGISTER("d");
	protected REGISTER rsp = new REGISTER("s");
	protected REGISTER rbp = new REGISTER("b");
	protected REGISTER rsi = new REGISTER("s");
	protected REGISTER rdi = new REGISTER("d");
	protected REGISTER r8 = new REGISTER("8");
	protected REGISTER r9 = new REGISTER("9");
	protected REGISTER r10 = new REGISTER("10");
	protected REGISTER r11 = new REGISTER("11");
	protected REGISTER r12 = new REGISTER("12");
	protected REGISTER r13 = new REGISTER("13");
	protected REGISTER r14 = new REGISTER("14");
	protected REGISTER r15 = new REGISTER("15");
	protected long rip = 0;
}

class test {
	public static void main(String[] args){
		RFLAGS rflags = new RFLAGS();
		REGISTER rax  = new REGISTER("d");
		System.out.printf("%s\n", rflags);
		rflags.setCF(true).setAC(true).setDF(true).setIOPL(2);
		System.out.printf("%d\n", rflags.toInt());
		System.out.print(rflags);
		System.out.printf("\n");
		rax.setByName("rdi").setVal(0xffffffff);
		System.out.printf("rax : %d\n", rax.setByName("di").toInt());
	}
}
