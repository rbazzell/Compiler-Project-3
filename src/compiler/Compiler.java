package compiler;

import java.io.*;

import parser.CodeGenerationException;
import parser.ParseException;
import scanner.DFAException;

public interface Compiler {
  public void compile(String filePrefix) throws IOException, CodeGenerationException, ParseException, DFAException;
}
