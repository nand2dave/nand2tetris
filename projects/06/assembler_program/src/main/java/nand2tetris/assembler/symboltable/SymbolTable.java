package nand2tetris.assembler.symboltable;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
  Map<String, Integer> hashmap = new HashMap<String, Integer>();
  
  /**
   *  Set default values
   */
  public SymbolTable() {
    hashmap.put("SP", 0);
    hashmap.put("LCL", 1);
    hashmap.put("ARG", 2);
    hashmap.put("THIS", 3);
    hashmap.put("THAT", 4);
    
    hashmap.put("R0", 0);
    hashmap.put("R1", 1);
    hashmap.put("R2", 2);
    hashmap.put("R3", 3);
    hashmap.put("R4", 4);
    hashmap.put("R5", 5);
    hashmap.put("R6", 6);
    hashmap.put("R7", 7);
    hashmap.put("R8", 8);
    hashmap.put("R9", 9);
    hashmap.put("R10", 10);
    hashmap.put("R11", 11);
    hashmap.put("R12", 12);
    hashmap.put("R13", 13);
    hashmap.put("R14", 14);
    hashmap.put("R15", 15);
    
    hashmap.put("SCREEN", 16384);
    hashmap.put("KBD", 24576);
  }
  
  public void addEntry (String symbol, int address) {
    hashmap.put(symbol, address);
  }
  
   boolean contains (String symbol) {
     return hashmap.containsKey(symbol);
   }
   
   public int getAddress (String symbol) {
     if (hashmap.get(symbol) == null) {
       return -1;
     }
     return hashmap.get(symbol);
   }
}
