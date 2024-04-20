package parser;

import java.util.ArrayList;
import lowlevel.CodeItem;

public class Program {

    public ArrayList<Declaration> decls;

    public Program(ArrayList<Declaration> in_decls) {
        decls = in_decls;
    }

    public String print(int indent) {
        String printStr = "\t".repeat(indent) + "PROGRAM\n";
        for (Declaration decl : decls) {
            printStr += decl.print(indent + 1);
        }
        return printStr;
    }

    public CodeItem genLLCode() throws CodeGenerationException {
        CodeItem head = decls.get(0).genLLCode(null);
        CodeItem curr = head;
        for (int i = 1; i < decls.size(); i++) {
            curr.setNextItem(decls.get(i).genLLCode(null));
            curr = curr.getNextItem();
        }
        return head;
    }
}
