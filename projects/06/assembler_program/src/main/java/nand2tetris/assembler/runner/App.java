package nand2tetris.assembler.runner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import nand2tetris.assembler.code.Code;
import nand2tetris.assembler.helper.Helper;
import nand2tetris.assembler.model.CommandTypes;
import nand2tetris.assembler.parser.Parser;
import nand2tetris.assembler.symboltable.SymbolTable;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Starting Assembler..." );
        
        Parser parser = new Parser(args[0]);
        Code codeModule = new Code();
        SymbolTable symbolTable = new SymbolTable();
        FileWriter fileWriter = new FileWriter(new File(args[0].replace(".asm", ".hack")));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Helper helper = new Helper();
        helper.fillSymbolTableWithLabelAddresses(symbolTable, parser);
        String machineCodeLine = new String();
        int variableAddress = 16; 
        int line = 0;
        parser = new Parser(args[0]);
        while (parser.hasMoreCommands()) {
          if (parser.commandType().equals(CommandTypes.L_COMMAND)){
             symbolTable.addEntry(parser.symbol(), line); 
             parser.advance();
             continue;
          } else if (parser.commandType().equals(CommandTypes.A_COMMAND)) {
            if (parser.symbol().matches("[0-9]+[0-9]*")) { // -> address is number, not a variable
              machineCodeLine = helper.to16BitNumber(Integer.toBinaryString(Integer.parseInt(parser.symbol())));
            } else if (symbolTable.getAddress(parser.symbol()) != -1) { // variable
              machineCodeLine = helper.to16BitNumber(Integer.toBinaryString(symbolTable.getAddress(parser.symbol())));
            } else { // new variable
              symbolTable.addEntry(parser.symbol(), variableAddress);
              machineCodeLine = helper.to16BitNumber(Integer.toBinaryString(variableAddress));
              variableAddress++;
            }
          } else { // C_COMMAND
            parser.setCurrentCommand(helper.removeCommentsFromCommand(parser.getCurrentCommand()));
            if (parser.getCurrentCommand().isEmpty()) {
              parser.advance();
              continue;
            }
            machineCodeLine = "111" 
                  + codeModule.comp(parser.comp())
                  + codeModule.dest(parser.dest())
                  + codeModule.jump(parser.jmp());
          } 
          helper.writeToFile(bufferedWriter, machineCodeLine, line) ;
          line++;
          parser.advance();
        }
        bufferedWriter.close();
        System.out.println( "Finished Assembler!" );
    }
    
}
