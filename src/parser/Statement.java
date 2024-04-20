package parser;
import lowlevel.Function;

public abstract class Statement {
    public abstract String print(int indent);
    public abstract void genLLCode(Function currFunc) throws CodeGenerationException; 
}
