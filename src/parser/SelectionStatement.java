package parser;
import lowlevel.Function;
import lowlevel.BasicBlock;

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
        
        //Step 1: Creat Basic Blocks for Then, Post, and Else
        BasicBlock thenBlock = new BasicBlock(currFunc);
        BasicBlock postBlock = new BasicBlock(currFunc);
        BasicBlock elseBlock = null;
        if(elseStmt != null){
            elseBlock = new BasicBlock(currFunc);
        }
        //Step 2: Call genLLCode on the expression
        thenBlock.appendOper(expr.genLLCode(currFunc));

        //Step 3: Add Branch to currBlock
        //Step 4: Appen thenBlock to the BB chain
        //Step 5: currBlock = thenBlock
        //Step 6: codeGen thenBlock
        //Step 7: Append postBlock to the BB chain
        //Step 8: currBlock = elseBlock
        //Step 9: codeGen elseBlock
        //Step 10: Append JMP-to-postBlock stmt to elseBlock
        //Step 11: Add elseBlock to UC
        //Step 12: currBlock = postBlock
        return;

    }
}
