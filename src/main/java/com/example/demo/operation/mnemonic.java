package com.example.demo.operation;
import java.util.*;
import com.example.demo.models.CPU;

public abstract class mnemonic {
	protected String name;
	public String toString() {
		return this.name;
	}
	public abstract boolean exec(String [] args, CPU cpu); //success...true fail...false
}
