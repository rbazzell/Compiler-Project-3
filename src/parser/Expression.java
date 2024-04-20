package parser;

import lowlevel.Function;
import lowlevel.Operation;

public abstract class Expression {

    public int regNum;
    public static enum Operator {
        LTE,
        LT,
        GT,
        GTE,
        EQ,
        NEQ,
        PLUS,
        MINUS,
        MULT,
        DIV
    };

    public abstract String print(int indent);

    public abstract Operation genLLCode(Function currFunc) throws CodeGenerationException;
}
