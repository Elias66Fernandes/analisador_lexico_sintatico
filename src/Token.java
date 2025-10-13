public record Token(TokenType type, String lexeme) {

    public String toString() {
        return "<" + type + ">" + lexeme + "</" + type + ">";
    }

}