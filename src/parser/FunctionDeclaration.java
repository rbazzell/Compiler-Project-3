package parser;

import java.util.ArrayList;
import lowlevel.Function;
import lowlevel.Operation;
import lowlevel.Operation.OperationType;
import lowlevel.Operand;
import lowlevel.Operand.OperandType;
import lowlevel.BasicBlock;
import lowlevel.FuncParam;

public class FunctionDeclaration extends Declaration {

    public typeSpecifier typeSpec;
    public String id;
    public ArrayList<Param> params = null;
    public CompoundStatement stmt;

    public FunctionDeclaration(typeSpecifier in_type, String in_id, ArrayList<Param> in_params, CompoundStatement in_stmt) {
        typeSpec = in_type;
        id = in_id;
        params = in_params;
        stmt = in_stmt;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent);
        printStr += typeSpec.name() + " " + id + "()\n";
        for (Param param : params) {
            printStr += param.print(indent + 1);
        }
        printStr += stmt.print(indent + 1);
        return printStr;
    }

    //only null will ever be passed here
    public Function genLLCode(Function currFunc) throws CodeGenerationException {
        if(currFunc != null){
            throw new CodeGenerationException("FunctionDeclaration::genLLCode(): currFunc not null");
        }

        //Generate parameter linked list
        FuncParam headParam = null;
        currFunc = new Function(typeSpec.ordinal(), id);
        
        if(params.size() != 0) {
            headParam = params.get(0).genLLCode(currFunc);
            FuncParam currParam = headParam;
            for (int i = 1; i < params.size(); i++) {
                currParam.setNextParam(params.get(i).genLLCode(currFunc));
                currParam = currParam.getNextParam();
            }
            
        }
        currFunc.setFirstParam(headParam);
        //Generate function
        currFunc.createBlock0();
        //create new block, set as currblock
        BasicBlock newBB = new BasicBlock(currFunc);
        currFunc.appendBlock(newBB);      
        currFunc.setCurrBlock(newBB);
        stmt.genLLCode(currFunc);
        //TODO: Might need to do some setup or something weird to make getReturnBlock work. If problems ask Dr. G
        currFunc.appendBlock(currFunc.getReturnBlock());
        currFunc.setCurrBlock(currFunc.getReturnBlock());

        //append unconnected chain
        //TODO: MIGHT NOT BE NEEDED???? DOES HE TAKE CARE OF THIS FOR US?
        if (currFunc.getFirstUnconnectedBlock() != null) {
            currFunc.appendBlock(currFunc.getFirstUnconnectedBlock());
            currFunc.setCurrBlock(currFunc.getLastBlock());
        }
        return currFunc;

    }
}


