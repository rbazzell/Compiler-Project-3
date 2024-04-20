package parser;
import lowlevel.CodeItem;

public class ReturnStatement extends Statement {

    public Expression expr = null;

    public ReturnStatement(Expression in_expr) {
        expr = in_expr;
    }

    public String print(int indent) {
        String printString = "\t".repeat(indent);
        printString += "return\n";
        if(expr != null){
            printString += expr.print(indent + 1);
        }
        return printString;
    }

    public CodeItem genLLCode(){

        return null;

    }
}
