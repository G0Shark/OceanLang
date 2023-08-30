public class Token {
    public enum TokenType {
        TOKEN_TYPE_VARIABLE,
        TOKEN_TYPE_NUMBER,
        TOKEN_TYPE_LOG,
        TOKEN_TYPE_PLUS,
        TOKEN_TYPE_MINUS,
        TOKEN_TYPE_EQUALS,
        TOKEN_TYPE_END,
        TOKEN_TYPE_USER_OUT,
        TOKEN_TYPE_UNKNOWN
    }
    public String token;
    public TokenType type;
    public Token(TokenType type, String token){
        this.type = type;
        this.token = token;
    }
}
