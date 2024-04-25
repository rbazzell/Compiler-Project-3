package parser;

import lowlevel.Function;
import lowlevel.Operation;

public class NUMExpression extends Expression {

    public int num;

    public NUMExpression(int in_num) {
        num = in_num;
    }

    public String print(int indent) {
        String printString = "\t".repeat(indent);
        printString += num + "\n";
        return printString;
    }

    public void genLLCode(Function currFunc) throws CodeGenerationException{

        return;

    }

}
