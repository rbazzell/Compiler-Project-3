package parser;

import compiler.CMinusCompiler;
import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;

public class IDExpression extends Expression {

    public String idStr;
    public Expression expr = null;

    public IDExpression(String in_id, Expression in_expr) {
        idStr = in_id;
        expr = in_expr;
    }

    public String print(int indent) {
        // Implement Print
        String printString = "\t".repeat(indent);
        printString += idStr;
        if(expr != null) {
            printString += "[]\n" + expr.print(indent + 1);
        } else {
            printString += "\n";
        }
        return printString;
    }

    //idexpr.genCode is only called
    public void genLLCode(Function currFunc) throws CodeGenerationException{
        Operation loadOp = null;
        
        if (CMinusCompiler.globalSymbolTable.contains(idStr.hashCode())) {
            //In the global symbol table
            regNum = currFunc.getNewRegNum();
            
            //Generate a load operation & append to current block
            Operand operand = new Operand(OperandType.STRING, idStr);
            Operand dest = new Operand(OperandType.REGISTER, regNum);
            loadOp = new Operation(OperationType.LOAD_I, currFunc.getCurrBlock());
            loadOp.setSrcOperand(0, operand);
            loadOp.setDestOperand(0, dest);

            currFunc.getCurrBlock().appendOper(loadOp);

        } else if (currFunc.getTable().containsKey(idStr)) {
            //In the local symbol table
            regNum = currFunc.getTable().get(idStr);

        } else {
            //Not in global or local symbol table
            throw new CodeGenerationException("IDExpression.genLLCode(): ID not found in global or local symbol table!");
        }
        //Don't have to return anything because IDExpr creates its own load or makes no op
        return;
    }
}
