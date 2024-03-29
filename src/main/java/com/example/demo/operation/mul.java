package com.example.demo.operation;
import com.example.demo.models.*;
import java.util.*;
import java.math.*;

public class mul extends mnemonic {
	public mul() {
		name = "mul";
	}
	private CPU cpu;

	public boolean exec(String [] args, CPU cpu) {
		System.out.printf("mul.exec called.\n");
		this.cpu = cpu;
		Register reg1 = cpu.select_register(args[0]);
		int bit_size = reg1.getBitsize();
		Register reg2 = cpu.select_register("rax").setBitsize(bit_size);
		BigInteger res = BigInteger.ONE;
		res = res.multiply(new BigInteger(reg1.toString(), 10))
			.multiply(new BigInteger(reg2.toString(), 10));
		if(res.compareTo(BigInteger.ZERO) == 0) {
			cpu.getRflags().setZF(true);
		} else {
			cpu.getRflags().setZF(false);
		}
		long res_lower64 = res.longValue();
		long res_upper64 = res.shiftRight(64).longValue();
		if(bit_size == 8) {
			cpu.select_register("al").setVal(res_lower64);
			cpu.select_register("ah").setVal(res_lower64 >>> 8);
		} else if(bit_size == 16) {
			cpu.select_register("ax").setVal(res_lower64);
			cpu.select_register("dx").setVal(res_lower64 >>> 16);
		} else if(bit_size == 32) {
			cpu.select_register("eax").setVal(res_lower64);
			cpu.select_register("edx").setVal(res_lower64 >>> 32);
		} else if(bit_size == 64) {
			cpu.select_register("rax").setVal(res_lower64);
			cpu.select_register("rdx").setVal(res_upper64);
		}
		return true;
	}
}
