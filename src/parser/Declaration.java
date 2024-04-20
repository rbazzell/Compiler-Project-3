package parser;

import lowlevel.CodeItem;

public abstract class Declaration {

     public static enum typeSpecifier {
          VOID,
          INT
     }

     public abstract String print(int indent);

     public abstract CodeItem genLLCode();
}
