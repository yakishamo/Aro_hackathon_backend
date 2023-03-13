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

    public String getMnemonic(){
        return this.mnemonic;
    }

    public void setMnemonic(String mnemonic){
        this.mnemonic = mnemonic;
    }
}
