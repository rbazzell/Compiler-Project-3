package parser;

import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;
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

    public void genLLCode(Function currFunc) throws CodeGenerationException{
        rhs.genLLCode(currFunc);
        if (CMinusCompiler.globalSymbolTable.contains(lhs.idStr.hashCode())) {
            regNum = rhs.regNum;
            //Generate store operation & append to currBlock
            Operation store = new Operation(OperationType.STORE_I, currFunc.getCurrBlock());
            Operand src1Op = new Operand(Operand.OperandType.REGISTER, regNum);
            Operand src2Op = new Operand(Operand.OperandType.STRING, lhs.idStr);
            store.setSrcOperand(0, src1Op);
            store.setSrcOperand(1, src2Op);
            currFunc.getCurrBlock().appendOper(store);
        } else if (currFunc.getTable().containsKey(lhs.idStr)) {
            lhs.genLLCode(currFunc);
            regNum = lhs.regNum;
            //Generate assign operation & append to currBlock
            Operation assign = new Operation(OperationType.ASSIGN, currFunc.getCurrBlock());
            Operand srcOp = new Operand(Operand.OperandType.REGISTER, rhs.regNum);
            Operand destOp = new Operand(Operand.OperandType.REGISTER, regNum);
            assign.setSrcOperand(0, srcOp);
            assign.setDestOperand(0, destOp);
            currFunc.getCurrBlock().appendOper(assign);
        } else {
            throw new CodeGenerationException("AssignExpression::genLLCode(): Variable not found in symbol table");
        }
        return;
    }
}
