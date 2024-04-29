package parser;

import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;

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
        //assign a register number
        regNum = currFunc.getNewRegNum();
        Operation immediate = new Operation(OperationType.ASSIGN, currFunc.getCurrBlock());
        Operand destReg = new Operand(OperandType.REGISTER, regNum);
        Operand number = new Operand(OperandType.INTEGER, num);
        immediate.setSrcOperand(0, number);
        immediate.setDestOperand(0, destReg);
        currFunc.getCurrBlock().appendOper(immediate);
        return;

    }

}
