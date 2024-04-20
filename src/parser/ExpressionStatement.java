package parser;

import lowlevel.Function;

public class ExpressionStatement extends Statement {

    public Expression expr = null;

    public ExpressionStatement(Expression in_expr) {
        expr = in_expr;
    }

    public String print(int indent) {
        String printStr = "";
        if(expr != null){
            printStr += expr.print(indent);
        }
        return printStr;
    }

    public void genLLCode(Function currFunc) throws CodeGenerationException{
        if (expr != null) {
            currFunc.getCurrBlock().appendOper(expr.genLLCode(currFunc));
        }
        return;

    }
}
