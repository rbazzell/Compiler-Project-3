package parser;

import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;

public class BinaryExpression extends Expression {

    public Expression lhs = null;
    public Expression rhs = null;
    public Operator type;

    public BinaryExpression(Expression in_lhs, Expression in_rhs, Operator in_type) {
        lhs = in_lhs;
        rhs = in_rhs;
        type = in_type;
    }

    public String print(int indent) {
        String printString = "\t".repeat(indent);
        switch(type){
            case DIV:
                printString += "/\n";
                break;
            case EQ:
                printString += "==\n";
                break;
            case GT:
                printString += ">\n";
                break;
            case GTE:
                printString += ">=\n";
                break;
            case LT:
                printString += "<\n";
                break;
            case LTE:
                printString += "<=\n";
                break;
            case MINUS:
                printString += "-\n";
                break;
            case MULT:
                printString += "*\n";
                break;
            case NEQ:
                printString += "!=\n";
                break;
            case PLUS:
                printString += "+\n";
                break;
        }
        printString += lhs.print(indent + 1);
        printString += rhs.print(indent + 1);
        
        return printString;
    }

    public void genLLCode(Function currFunc) throws CodeGenerationException{
        rhs.genLLCode(currFunc);
        lhs.genLLCode(currFunc);
        OperationType oper = null;
        switch(type){
            case DIV:
                oper = OperationType.DIV_I;
                break;
            case EQ:
                oper = OperationType.EQUAL;
                break;
            case GT:
                oper = OperationType.GT;
                break;
            case GTE:
                oper = OperationType.GTE;
                break;
            case LT:
                oper = OperationType.LT;
                break;
            case LTE:
                oper = OperationType.LTE;
                break;
            case MINUS:
                oper = OperationType.SUB_I;
                break;
            case MULT:
                oper = OperationType.MUL_I;
                break;
            case NEQ:
                oper = OperationType.NOT_EQUAL;
                break;
            case PLUS:
                oper = OperationType.ADD_I;
                break;
        }
        regNum = currFunc.getNewRegNum();
        Operation binary = new Operation(oper, currFunc.getCurrBlock());      
        Operand src1 = new Operand(Operand.OperandType.REGISTER, lhs.regNum);
        Operand src2 = new Operand(Operand.OperandType.REGISTER, rhs.regNum);
        Operand dest = new Operand(Operand.OperandType.REGISTER, regNum);
        binary.setSrcOperand(0, src1);
        binary.setSrcOperand(1, src2);
        binary.setDestOperand(0, dest);
        currFunc.getCurrBlock().appendOper(binary);
        return;

    }
}
