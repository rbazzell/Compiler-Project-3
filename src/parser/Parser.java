package parser;

import scanner.DFAException;
import java.io.IOException;

public interface Parser {
    public Program parse() throws IOException, DFAException, ParseException;
    public void printAST(Program head) throws IOException;
}
