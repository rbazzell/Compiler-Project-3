package parser;

import java.util.ArrayList;
import lowlevel.Function;
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
        if(params.size() != 0) {
            headParam = params.get(0).genLLCode();
            FuncParam currParam = headParam;
            for (int i = 1; i < params.size(); i++) {
                currParam.setNextParam(params.get(i).genLLCode());
                currParam = currParam.getNextParam();
            }
        }

        //Generate function
        Function func = new Function(typeSpec.ordinal(), id, headParam);
        
        func.createBlock0();
        func.setCurrBlock(func.getFirstBlock());
        stmt.genLLCode(currFunc);
        //TODO: Might need to do some setup or something weird to make genReturnBlock work. If problems ask Dr. G
        func.genReturnBlock();
        return func;

    }
}


