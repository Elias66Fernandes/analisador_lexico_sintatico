public class Parser {

    private final Scanner scan;
    private Token currentToken;

    private final StringBuilder output = new StringBuilder();

    public String output() {
        return output.toString();
    }

    public Parser(byte[] input) {
        scan = new Scanner(input);
        currentToken = scan.nextToken();
    }

    private void nextToken() {
        currentToken = scan.nextToken();
    }

    private void match(TokenType t) {
        if (currentToken.type() == t) {
            nextToken();
        } else {
            throw new Error("syntax error: esperado " + t + " mas encontrado " + currentToken.type());
        }
    }

    // <program> ::= <statement>*
    public void parse() {
        statements();
    }

    void statements() {
        while (currentToken.type() != TokenType.EOF) {
            statement();
        }
    }

    // <statement> ::= <letStatement> | <printStatement>
    void statement() {
        if (currentToken.type() == TokenType.LET) {
            letStatement();
        } else if (currentToken.type() == TokenType.PRINT) {
            printStatement();
        } else {
            throw new Error("syntax error: comando inválido em " + currentToken.lexeme());
        }
    }

    // <letStatement> ::= 'let' IDENT '=' <expr> ';'
    void letStatement() {
        match(TokenType.LET);
        String varName = currentToken.lexeme();
        match(TokenType.IDENT);
        match(TokenType.EQ);
        expr();
        output.append("pop ").append(varName).append("\n");
        match(TokenType.SEMICOLON);
    }

    // <printStatement> ::= 'print' <expr> ';'
    void printStatement() {
        match(TokenType.PRINT);
        expr();
        output.append("print\n");
        match(TokenType.SEMICOLON);
    }

    void expr() {
        term();
        oper();
    }

    void number() {
        output.append("push ").append(currentToken.lexeme()).append("\n");
        match(TokenType.NUMBER);
    }

    void term() {
        if (currentToken.type() == TokenType.NUMBER)
            number();
        else if (currentToken.type() == TokenType.IDENT) {
            output.append("push ").append(currentToken.lexeme()).append("\n");
            match(TokenType.IDENT);
        } else
            throw new Error("syntax error: termo inválido");
    }

    void oper() {
        if (currentToken.type() == TokenType.PLUS) {
            match(TokenType.PLUS);
            term();
            output.append("add\n");
            oper();
        } else if (currentToken.type() == TokenType.MINUS) {
            match(TokenType.MINUS);
            term();
            output.append("sub\n");
            oper();
        }
    }
}

