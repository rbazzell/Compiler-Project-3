package parser;

import java.util.ArrayList;

import lowlevel.Attribute;
import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;

public class CallExpression extends Expression {

    public String callID = null;
    public ArrayList<Expression> args = null;

    public CallExpression(String in_ID, ArrayList<Expression> in_args) {
        callID = in_ID;
        args = in_args;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent);
        printStr += callID + "()\n";
        for (Expression arg : args) {
            printStr += arg.print(indent + 1);
        }
        return printStr;
    }

    public void genLLCode(Function currFunc) throws CodeGenerationException {
        //genCode all args
        int i = 0;
        for(Expression arg : args){
            arg.genLLCode(currFunc);

            //create pass oper
            Operation pass = new Operation(OperationType.PASS, currFunc.getCurrBlock());
            Operand argReg = new Operand(OperandType.REGISTER, arg.regNum);
            pass.setSrcOperand(0, argReg);
            pass.addAttribute(new Attribute("PARAM_NUM", Integer.toString(i)));
            currFunc.getCurrBlock().appendOper(pass);
            i++;
        }

        //generate the call
        Operation call = new Operation(OperationType.CALL, currFunc.getCurrBlock());
        call.addAttribute(new Attribute("numParams", Integer.toString(args.size())));
        call.setSrcOperand(0, new Operand(OperandType.STRING, callID));
        currFunc.getCurrBlock().appendOper(call);

        //generate the move of RetReg into other reg
        regNum = currFunc.getNewRegNum();
        Operation move = new Operation(OperationType.ASSIGN, currFunc.getCurrBlock());
        Operand srcReg = new Operand(OperandType.MACRO, "RetReg");
        Operand destReg = new Operand(OperandType.REGISTER, regNum);
        move.setSrcOperand(0, srcReg);
        move.setDestOperand(0, destReg);
        currFunc.getCurrBlock().appendOper(move);
        return;

    }

}
