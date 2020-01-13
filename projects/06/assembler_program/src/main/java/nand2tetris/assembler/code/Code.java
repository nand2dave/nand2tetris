package nand2tetris.assembler.code;

public class Code {
  
  /**
   * Returns the binary code of the dest mnemonic (3 bits).
   * @param mnemonic
   *            The mnemonic (i.e. D=M+1).
   * @return
   */
  public String dest (String mnemonic) {
    String bits = null;
    if (mnemonic == null) {
      bits = "000";
    } else if (mnemonic.equals("M")) {
      bits = "001";
    } else if (mnemonic.equals("D")) {
      bits = "010";
    } else if (mnemonic.equals("MD")) {
      bits = "011";
    } else if (mnemonic.equals("A")) {
      bits = "100";
    } else if (mnemonic.equals("AM")) {
      bits = "101";
    } else if (mnemonic.equals("AD")) {
      bits = "110";
    } else if (mnemonic.equals("ADM")) {
      bits = "111";
    }
    return bits;
  }
  
  /**
   * Returns the binary code of the comp mnemonic (7 bits).
   * @param mnemonic
   *            The mnemonic (i.e. D=M+1).
   * @return
   */
  public String comp (String mnemonic) {
    String bits = null;
    if  (mnemonic.equals("0")) {
      bits = "0101010";
    } else if (mnemonic.equals("1")) {
      bits = "0111111";
    } else if (mnemonic.equals("-1")) {
      bits = "0111010";
    } else if (mnemonic.equals("D")) {
      bits = "0001100";
    } else if (mnemonic.equals("A")) {
      bits = "0110000";
    } else if (mnemonic.equals("!D")) {
      bits = "0001101";
    } else if (mnemonic.equals("!A")) {
      bits = "0110001";
    } else if (mnemonic.equals("-D")) {
      bits = "0001111";
    } else if (mnemonic.equals("-A")) {
      bits = "0110011";
    } else if (mnemonic.equals("D+1")) {
      bits = "0011111";
    } else if (mnemonic.equals("A+1")) {
      bits = "0110111";
    } else if (mnemonic.equals("D-1")) {
      bits = "0001110";
    } else if (mnemonic.equals("A-1")) {
      bits = "0110010";
    } else if (mnemonic.equals("D+A")) {
      bits = "0000010";
    } else if (mnemonic.equals("D-A")) {
      bits = "0010011";
    } else if (mnemonic.equals("A-D")) {
      bits = "0000111";
    } else if (mnemonic.equals("D&A")) {
      bits = "0000000";
    } else if (mnemonic.equals("D|A")) {
      bits = "0010101";
    } else if (mnemonic.equals("M")) {
      bits = "1110000";
    } else if (mnemonic.equals("!M")) {
      bits = "1110001";
    } else if (mnemonic.equals("-M")) {
      bits = "1110011";
    } else if (mnemonic.equals("M+1")) {
      bits = "1110111";
    } else if (mnemonic.equals("M-1")) {
      bits = "1110010";
    } else if (mnemonic.equals("D+M")) {
      bits = "1000010";
    } else if (mnemonic.equals("D-M")) {
      bits = "1010011";
    } else if (mnemonic.equals("M-D")) {
      bits = "1000111";
    } else if (mnemonic.equals("D&M")) {
      bits = "1000000";
    } else if (mnemonic.equals("D|M")) {
      bits = "1010101";
    } 
    return bits;
  }
  
  /**
   * Returns the binary code of the jump mnemonic (3 bits).
   * @param mnemonic
   *            The mnemonic (i.e. D=M+1).
   * @return
   */
  public String jump (String mnemonic) {
    String bits = null;
    if (mnemonic == null) {
      bits = "000";
    } else if (mnemonic.equals("JGT")) {
      bits = "001";
    } else if (mnemonic.equals("JEQ")) {
      bits = "010";
    } else if (mnemonic.equals("JGE")) {
      bits = "011";
    } else if (mnemonic.equals("JLT")) {
      bits = "100";
    } else if (mnemonic.equals("JNE")) {
      bits = "101";
    } else if (mnemonic.equals("JLE")) {
      bits = "110";
    } else if (mnemonic.equals("JMP")) {
      bits = "111";
    }
    return bits;
  }

}
