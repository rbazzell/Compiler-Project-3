package parser;
import lowlevel.Data;
import compiler.CMinusCompiler;
import lowlevel.Function;


public class VariableDeclaration extends Declaration {

    public String id;
    public int num;

    public VariableDeclaration(String in_id, int in_num) {
        id = in_id;
        num = in_num;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent) + "INT " + id;
        if(num != 0){
            printStr += "[" + num + "]";
        }
        return printStr + "\n";
    }

    public Data genLLCode(Function currFunc) throws CodeGenerationException{
        if(currFunc != null){
            //add to local symbol table
            currFunc.getTable().put(id, currFunc.getNewRegNum());
            //TODO: Maybe mess with VarSize???????
            //currFunc.setVarSize(currFunc.getVarSize() + 1);
            return null;
        }else{
            //add to global symbol table and return a Data object
            CMinusCompiler.globalSymbolTable.add(id.hashCode());
            return new Data(Data.TYPE_INT, id, (num != 0), num);
        }

    }
}
