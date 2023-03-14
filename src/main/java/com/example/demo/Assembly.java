package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assembly {
    private String mnemonic;
    private String[] terms;
    private long[] register;
    private int[] memory;
    private String[] mnemonics;
}
