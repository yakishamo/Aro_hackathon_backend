package com.example.demo.models; 

class Memory {
	private int memory_size;
	public byte [] memory;

	Memory(int memory_size) {
		this.memory_size = memory_size;
		memory = new byte[memory_size];
	}
	
	//size is specified in bytes
	public long read(int addr, int size) throws ArrayIndexOutOfBoundsException {
		long ret = 0;
		for(int i = 0; i < size; i++) {
			System.out.printf("    ret : %x\n", ret);
			ret += (memory[addr + i] << (8*i)) & (0xffffffffffffffffL >>> (56 - 8*i));
		}
		System.out.printf("    ret : %x\n", ret);
		return ret;
	}

	public void write(int addr, int size, long data) throws ArrayIndexOutOfBoundsException {
		byte write_data;
		int i = 0;
		while(data != 0 && i < size) {
			write_data = (byte)(data & 0xff);
			data = data >>> 8;
			memory[addr+i] = write_data;
			i++;
		}
	}
}