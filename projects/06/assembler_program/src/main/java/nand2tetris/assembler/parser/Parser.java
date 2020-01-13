package nand2tetris.assembler.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.dgc.Lease;

import nand2tetris.assembler.model.CommandTypes;

public class Parser {
  
  String currentCommand;
  BufferedReader bufferedReader;
  
  public String getCurrentCommand() {
    return currentCommand;
  }

  public void setCurrentCommand(String currentCommand) {
    this.currentCommand = currentCommand;
  }
  
  public Parser(String filePath) throws IOException {
    File file = new File("./" + filePath);
    FileReader fileReader = new FileReader(file);
    bufferedReader = new BufferedReader(fileReader);
    currentCommand =  bufferedReader.readLine();
  }
  
  /**
   * Reads the next command from the file and makes it the current command.
   * @throws IOException 
   */
  public void advance() throws IOException {
    currentCommand =  bufferedReader.readLine();
    if (currentCommand != null) {
      currentCommand = currentCommand.replaceAll("\\s+","");
    }
  }
  
  public boolean hasMoreCommands() {
    boolean hasMoreCommands = false;
    if (currentCommand != null) {
      hasMoreCommands = true;
    } 
    return hasMoreCommands;
  }
  
  /**
   * A_COMMAND, C_COMMAND or L_COMMAND
   * @return
   */
  public CommandTypes commandType() {
    if (this.currentCommand.startsWith("@")) {
      return CommandTypes.A_COMMAND;
    } else if (this.currentCommand.startsWith("(")) {
      return CommandTypes.L_COMMAND;
    } else {
      return CommandTypes.C_COMMAND;
    }
  }
  
  /**
   * Returns the Xxx part of @Xxx or (Xxx)
   * @return
   */
  public String symbol() {
    if(commandType().equals(CommandTypes.A_COMMAND )){
      return currentCommand.replace("@", "");
    } else if (commandType().equals(CommandTypes.L_COMMAND)) {
      return currentCommand.replace("(", "").replace("", "").replace(")", "");
    } else {
      return null;
    }
    
  }
  
  /** 
   * @return
   *    Returns the dest mnemonic of the current C-command  (8 possibilities).
   */
  public String dest() {
    int equationIndex = currentCommand.indexOf("=");
    if (equationIndex == -1) {
      return null;
    }
    return currentCommand.substring(0, equationIndex).trim();
  }
  
  /**
   * @return
   *    Returns the comp mnemonic of the current C-command (28 possibilities) .
   */
  public String comp() {
    int startIndex = 0;
    int endIndex = 0;currentCommand.indexOf(";");
    if (currentCommand.contains("=") && currentCommand.contains(";")) { // i.e. "D=M+1"
      startIndex = currentCommand.indexOf("=") + 1;
      endIndex = currentCommand.indexOf(";");
    } else if (currentCommand.contains("=")) { // i.e. "D=M"
      startIndex = currentCommand.indexOf("=") + 1;
      endIndex = currentCommand.length();
    } else { // i.e. "0;JMP"
      startIndex = 0;
      endIndex = currentCommand.indexOf(";");
    }
    
    return currentCommand.substring(startIndex, endIndex).trim();
  }
  
  /**
   * @return
   *    Returns the jump mnemonic of the current C-command (8 possibilities).
   */
  public String jmp() {
    if (!currentCommand.contains(";")) {
      return null;
    }
    int startIndex = currentCommand.indexOf(';') + 1;
    return currentCommand.substring(startIndex, currentCommand.length()).trim();
  }

}
