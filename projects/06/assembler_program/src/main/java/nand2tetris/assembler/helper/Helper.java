package nand2tetris.assembler.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import nand2tetris.assembler.model.CommandTypes;
import nand2tetris.assembler.parser.Parser;
import nand2tetris.assembler.symboltable.SymbolTable;

/**
 * Contains various needed methods i.e. writing to a file.
 * @author David
 *
 */
public class Helper {
  
  public void writeToFile(BufferedWriter bufferedWriter, String machineCodeLine, int lineNumber) {
    try {
      if (lineNumber > 0) {
        bufferedWriter.newLine();
      }
      bufferedWriter.append(machineCodeLine);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public String to16BitNumber(String number) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(number);
    int numbersToFill = 16 - number.length();
    for (int i = 0; i < numbersToFill; i++) {
      if (stringBuilder.length() <= 16) {
        stringBuilder.insert(0, "0");
      }
    }
    return stringBuilder.toString();
  }
  
  public String removeCommentsFromCommand(String command) {
    String commandWithoutComments = command;  
    if (command.startsWith("//")) {
      commandWithoutComments = "";
    } else if (command.contains("//")) {
      int slashIndex = command.indexOf('/');
      commandWithoutComments = command.substring(0, slashIndex);
    }
    return commandWithoutComments;
  }
  
  public void fillSymbolTableWithLabelAddresses(SymbolTable symbolTable, Parser parser) throws IOException {
    int line = 0;
    while (parser.hasMoreCommands()) {
      if (parser.commandType().equals(CommandTypes.L_COMMAND)){
         symbolTable.addEntry(parser.symbol(), line); 
         parser.advance();
         continue;
      } 
      parser.setCurrentCommand(this.removeCommentsFromCommand(parser.getCurrentCommand()));
      if (parser.getCurrentCommand().isEmpty()) {
        parser.advance();
        continue;
      }
      line++;
      parser.advance();
    }
  }

}
