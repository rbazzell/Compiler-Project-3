package parser;
import lowlevel.CodeItem;

public abstract class Statement {
    public abstract String print(int indent);
    public abstract CodeItem genLLCode(); 
}
