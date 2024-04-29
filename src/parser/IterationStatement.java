package parser;

import lowlevel.BasicBlock;
import lowlevel.Function;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;

public class IterationStatement extends Statement {

    public Expression expr;
    public Statement stmt;

    public IterationStatement(Expression in_expr, Statement in_stmt) {
        expr = in_expr;
        stmt = in_stmt;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent) + "while\n";
        printStr += expr.print(indent + 1);
        printStr += stmt.print(indent + 1);
        return printStr;
    }

    public void genLLCode(Function currFunc) throws CodeGenerationException{
        //Step 1: Create Basic Blocks for iter and post
        BasicBlock iterBlock = new BasicBlock(currFunc);
        BasicBlock postBlock = new BasicBlock(currFunc);
        //Step 2: Call genLLCode on the expression
        expr.genLLCode(currFunc);
        
        //Step 3: Add Branch to currBlock
        Operation branch = new Operation(OperationType.BEQ, currFunc.getCurrBlock());
        Operand srcReg = new Operand(OperandType.REGISTER, expr.regNum);
        Operand zero = new Operand(OperandType.INTEGER, 0);
        Operand branchDest = new Operand(OperandType.BLOCK, postBlock.getBlockNum());
        branch.setSrcOperand(0, srcReg);
        branch.setSrcOperand(1, zero);
        branch.setSrcOperand(2, branchDest);
        currFunc.getCurrBlock().appendOper(branch);
        
        //Step 4: Append iterBlock to the BB chain
        //Step 5: currBlock = iterBlock
        //(Step 4 & 5 happen simulatinously)
        currFunc.appendBlock(iterBlock);
        currFunc.setCurrBlock(iterBlock);
        
        //Step 6: codeGen iterBlock
        stmt.genLLCode(currFunc);
        expr.genLLCode(currFunc);
        Operation inverseBranch = new Operation(OperationType.BNE, currFunc.getCurrBlock());
        Operand src = new Operand(OperandType.REGISTER, expr.regNum);
        Operand inverseBranchDest = new Operand(OperandType.BLOCK, iterBlock.getBlockNum());
        inverseBranch.setSrcOperand(0, src);
        inverseBranch.setSrcOperand(1, zero);
        inverseBranch.setSrcOperand(2, inverseBranchDest);
        currFunc.getCurrBlock().appendOper(inverseBranch);

        //Step 7: Append postBlock to the BB chain
        currFunc.appendBlock(postBlock);
        currFunc.setCurrBlock(postBlock);
        return;

    }
}
