package com.example.demo.models;

public class Rflags {
	Rflags() {
		cf = pf = af = zf = sf = tf = intf = df = of = iopl_1 = iopl_2 = 
		nt = rf = vm = ac = vif = vip = id = 0;
	}
	Rflags(int reg) {
		cf = (reg >>> 0)&1;
		pf = (reg >>> 2)&1;
		af = (reg >>> 4)&1;
		zf = (reg >>> 6)&1;
		sf = (reg >>> 7)&1;
		tf = (reg >>> 8)&1;
		intf = (reg >>> 9)&1;
		df = (reg >>> 10)&1;
		of = (reg >>> 11)&1;
		iopl_1 = (reg >>> 12)&1;
		iopl_2 = (reg >>> 13)&1;
		nt = (reg >>> 14)&1;
		rf = (reg >>> 16)&1;
		vm = (reg >>> 17)&1;
		ac = (reg >>> 18)&1;
		vif = (reg >>> 19)&1;
		vip= (reg >>> 20)&1;
		id = (reg >>> 21)&1;
	}
	protected int
		cf,   //(0)�������
		pf,   //(2)�������
		af,   //(4)�����
		zf,   //(6)�����
		sf,   //(7)�����
		tf,   //(8)�������
		intf, //(9)���������
		df,   //(10)�����
		of,   //(11)����������
		iopl_1, //(12)I/O�����������2����
		iopl_2, //(13)
		nt,   //(14)Nested Task
		rf,   //(16)�����
		vm,   //(17)��8086���
		ac,   //(18)����������
		vif,  //(19)���������
		vip,  //(20)�����������
		id;   //(21)�����
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
	public long IOPL() {
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

	public Rflags setCF(boolean b) {
		if(b) {
			cf = 1;
		} else {
			cf = 0;
		}
		return this;
	}
	public Rflags setPF(boolean b) {
		if(b) {
			pf = 1;
		} else {
			pf = 0;
		}
		return this;
	}
	public Rflags setAF(boolean b) {
		if(b) {
			af = 1;
		} else {
			af = 0;
		}
		return this;
	}
	public Rflags setZF(boolean b) {
		if(b) {
			zf = 1;
		} else {
			zf = 0;
		}
		return this;
	}
	public Rflags setSF(boolean b) {
		if(b) {
			sf = 1;
		} else {
			sf = 0;
		}
		return this;
	}
	public Rflags setTF(boolean b) {
		if(b) {
			tf = 1;
		} else {
			tf = 0;
		}
		return this;
	}
	public Rflags setIF(boolean b) {
		if(b) {
			intf = 1;
		} else {
			intf = 0;
		}
		return this;
	}
	public Rflags setDF(boolean b) {
		if(b) {
			df = 1;
		} else {
			df = 0;
		}
		return this;
	}
	public Rflags setOF(boolean b) {
		if(b) {
			of = 1;
		} else {
			of = 0;
		}
		return this;
	}
	public Rflags setIOPL(int i) {
		if(i >= 0 && i <= 3) {
			iopl_1 = i&1;
			iopl_2 = (i>>1)&1;
		}
		return this;
	}
	public Rflags setNT(boolean b) {
		if(b) {
			nt = 1;
		} else {
			nt = 0;
		}
		return this;
	}
	public Rflags setRF(boolean b) {
		if(b) {
			rf = 1;
		} else {
			rf = 0;
		}
		return this;
	}
	public Rflags setVM(boolean b) {
		if(b) {
			vm = 1;
		} else {
			vm = 0;
		}
		return this;
	}
	public Rflags setAC(boolean b) {
		if(b) {
			ac = 1;
		} else {
			ac = 0;
		}
		return this;
	}
	public Rflags setVIF(boolean b) {
		if(b) {
			vif = 1;
		} else {
			vif = 0;
		}
		return this;
	}
	public Rflags setVIP(boolean b) {
		if(b) {
			vip = 1;
		} else {
			vip = 0;
		}
		return this;
	}
	public Rflags setID(boolean b) {
		if(b) {
			id = 1;
		} else {
			id = 0;
		}
		return this;
	}
}
