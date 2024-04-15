package src.scanner;

import java.io.IOException;

public interface Scanner {
    public Token getNextToken() throws IOException, DFAException;

    public Token viewNextToken();
}