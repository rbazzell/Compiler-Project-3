package parser;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;

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

    public void genLLCode(Function currFunc) throws CodeGenerationException {
        //genCode the expression
        if (expr != null) {
            expr.genLLCode(currFunc);

            //generates the assign expression
            Operation assign = new Operation(OperationType.ASSIGN, currFunc.getCurrBlock());
            Operand srcOperand = new Operand(OperandType.REGISTER, expr.regNum);
            Operand destOperand = new Operand(Operand.OperandType.MACRO, "RetReg");
            assign.setSrcOperand(0, srcOperand);
            assign.setDestOperand(0, destOperand);
            currFunc.getCurrBlock().appendOper(assign);
        }
        
        //gen a jump
        Operation jump = new Operation(OperationType.JMP, currFunc.getCurrBlock());
        Operand jumpTarget = new Operand(OperandType.BLOCK, currFunc.getReturnBlock().getBlockNum());
        //Jump target treated as a src!!!
        jump.setSrcOperand(0, jumpTarget);
        currFunc.getCurrBlock().appendOper(jump);
        return;

    }
}
