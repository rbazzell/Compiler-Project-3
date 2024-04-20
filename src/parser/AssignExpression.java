package parser;

import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;
import compiler.CMinusCompiler;

public class AssignExpression extends Expression {

    public IDExpression lhs = null;
    public Expression rhs = null;

    public AssignExpression(IDExpression in_lhs, Expression in_rhs) {
        lhs = in_lhs;
        rhs = in_rhs;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent) + "=\n";
        printStr += lhs.print(indent + 1);
        printStr += rhs.print(indent + 1);
        return printStr;
    }

    public Operation genLLCode(Function currFunc) throws CodeGenerationException{
        if (CMinusCompiler.globalSymbolTable.contains(lhs.idStr.hashCode())) {
            regNum = rhs.regNum;
            //Generate store operation & append to currBlock
            Operation store = new Operation(OperationType.STORE_I, currFunc.getCurrBlock());
            Operand srcOp = new Operand(Operand.OperandType.REGISTER, regNum);
            Operand destOp = new Operand(Operand.OperandType.STRING, lhs.idStr);
            store.setSrcOperand(0, srcOp);
            store.setDestOperand(0, destOp);
            currFunc.getCurrBlock().appendOper(store);
        } else if (currFunc.getTable().containsKey(lhs.idStr.hashCode())) {
            regNum = lhs.regNum;
        } else {
            throw new CodeGenerationException("AssignExpression::genLLCode(): Variable not found in symbol table");
        }
        
        return null;

    }

}
