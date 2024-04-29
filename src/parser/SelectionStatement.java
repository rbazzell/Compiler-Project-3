package parser;
import lowlevel.Function;
import lowlevel.BasicBlock;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;

public class SelectionStatement extends Statement {

    public Expression expr;
    public Statement stmt;
    public Statement elseStmt = null;

    public SelectionStatement(Expression in_expr, Statement in_stmt, Statement in_elseStmt) {
        expr = in_expr;
        stmt = in_stmt;
        elseStmt = in_elseStmt;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent) + "if\n";
        printStr += expr.print(indent + 1);
        printStr += stmt.print(indent + 1);
        if (elseStmt != null) {
            printStr += "\t".repeat(indent) + "else\n";
            printStr += elseStmt.print(indent + 1);
        }
        return printStr;
    }

    public void genLLCode(Function currFunc) throws CodeGenerationException{
        
        //Step 1: Create Basic Blocks for Then, Post, and Else
        BasicBlock thenBlock = new BasicBlock(currFunc);
        BasicBlock postBlock = new BasicBlock(currFunc);
        BasicBlock elseBlock = null;
        if(elseStmt != null){
            elseBlock = new BasicBlock(currFunc);
        }
        //Step 2: Call genLLCode on the expression
        expr.genLLCode(currFunc);
        
        //Step 3: Add Branch to currBlock
        Operation branch = new Operation(OperationType.BEQ, currFunc.getCurrBlock());
        Operand srcReg = new Operand(OperandType.REGISTER, expr.regNum);
        Operand zero = new Operand(OperandType.INTEGER, 0);
        Operand branchDest;
        if(elseBlock != null){
            branchDest = new Operand(OperandType.BLOCK, elseBlock.getBlockNum());
        } else {
            branchDest = new Operand(OperandType.BLOCK, postBlock.getBlockNum());
        }
        branch.setSrcOperand(0, srcReg);
        branch.setSrcOperand(1, zero);
        branch.setSrcOperand(2, branchDest);
        currFunc.getCurrBlock().appendOper(branch);
        
        //Step 4: Append thenBlock to the BB chain
        currFunc.appendToCurrentBlock(thenBlock);
        
        //Step 5: currBlock = thenBlock
        currFunc.setCurrBlock(thenBlock);
        
        //Step 6: codeGen thenBlock
        stmt.genLLCode(currFunc);
        
        //Step 7: Append postBlock to the BB chain
        currFunc.appendToCurrentBlock(postBlock);
        
        if (elseBlock != null) {
            //Step 8: currBlock = elseBlock
            currFunc.setCurrBlock(elseBlock);
        
            //Step 9: codeGen elseBlock
            elseStmt.genLLCode(currFunc);
            
            //Step 10: Append JMP-to-postBlock stmt to elseBlock
            Operation jump = new Operation(OperationType.JMP, currFunc.getCurrBlock());
            Operand src = new Operand(OperandType.BLOCK, postBlock.getBlockNum());
            jump.setSrcOperand(0, src);
            currFunc.getCurrBlock().appendOper(jump);
            //Step 11: Add elseBlock to UC
            currFunc.appendUnconnectedBlock(elseBlock);
        }
        //Step 12: currBlock = postBlock
        currFunc.setCurrBlock(postBlock);
        return;

    }
}
