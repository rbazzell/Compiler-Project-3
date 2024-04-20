package parser;

import java.util.ArrayList;
import lowlevel.Function;

public class CompoundStatement extends Statement {

    public ArrayList<VariableDeclaration> localDecls = null;
    public ArrayList<Statement> stmtList = null;

    public CompoundStatement(ArrayList<VariableDeclaration> in_localDecls, ArrayList<Statement> in_stmtList) {
        localDecls = in_localDecls;
        stmtList = in_stmtList;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent) + "{ }\n";
        if(localDecls != null){
            for(int i = 0; i < localDecls.size(); i++){
                printStr += localDecls.get(i).print(indent + 1);
            }
        } 
        if(stmtList != null){
            for(int i = 0; i < stmtList.size(); i++){
                printStr += stmtList.get(i).print(indent + 1);
            }
        } 
        return printStr;
    }

    public void genLLCode(Function currFunc) throws CodeGenerationException{

        //Fills the local symbol table
        for(int i = 0; i < localDecls.size(); i++){
            localDecls.get(i).genLLCode(currFunc);
        }

        //The statements inside will generate and append to currFunction properly
        //So no magic needed here
        for(int i = 0; i < stmtList.size(); i++){
            stmtList.get(i).genLLCode(currFunc);
        }
        return;
    }

}
