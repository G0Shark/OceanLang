import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public List<Token> TokenList = new ArrayList<Token>();
    public boolean constructorMatcher(String regex, String code){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(code);
        return m.matches();
    }
    public void initilaze(String code){
        Parser p = new Parser();
        p.Pars(Lex(CodeSetup(code)), CodeSetup(code));
    }
    public ArrayList<String> CodeSetup(String code){
        StringBuilder command = new StringBuilder();
        ArrayList<String> CodeSetup = new ArrayList<String>();
        for(int i = 0; i < code.length(); i++){
            if (code.charAt(i)==' ' || code.charAt(i)=='\n' || code.charAt(i)=='\t' || code.charAt(i)=='\r' || code.charAt(i)==';'){
                CodeSetup.add(command.toString());
                command.delete(0, command.length());
            }else {
                command.append(code.charAt(i));
            }
        }
        return CodeSetup;
    }
    public List<Token> Lex(ArrayList<String> SetupCode){
        System.out.println(SetupCode);
        for(int i = 0; i < SetupCode.size(); i++){
            if (constructorMatcher("^[0-9]*", SetupCode.get(i))){
                Token token = new Token(Token.TokenType.TOKEN_TYPE_NUMBER, SetupCode.get(i));
                TokenList.add(token);
            }else if(constructorMatcher("^[а-я]*", SetupCode.get(i))){
                if (constructorMatcher("^[лог]*", SetupCode.get(i))){
                    Token token = new Token(Token.TokenType.TOKEN_TYPE_LOG, SetupCode.get(i));
                    TokenList.add(token);
                }else if(constructorMatcher("^[юзер]*", SetupCode.get(i))){
                    if (constructorMatcher("^написал*", SetupCode.get(i+=1))){
                        Token token = new Token(Token.TokenType.TOKEN_TYPE_USER_OUT, SetupCode.get(i));
                        TokenList.add(token);
                    }else {
                        continue;
                    }
                } else {
                    Token token = new Token(Token.TokenType.TOKEN_TYPE_VARIABLE, SetupCode.get(i));
                    TokenList.add(token);
                }
            }else if(constructorMatcher("^+", SetupCode.get(i))){
                Token token = new Token(Token.TokenType.TOKEN_TYPE_PLUS, SetupCode.get(i));
                TokenList.add(token);
            }else if(constructorMatcher("^[-]*", SetupCode.get(i))){
                Token token = new Token(Token.TokenType.TOKEN_TYPE_MINUS, SetupCode.get(i));
                TokenList.add(token);
            }else if(constructorMatcher("^[=]*", SetupCode.get(i))){
                Token token = new Token(Token.TokenType.TOKEN_TYPE_EQUALS, SetupCode.get(i));
                TokenList.add(token);
            }else if (constructorMatcher("^[$]*", SetupCode.get(i))){
                Token token = new Token(Token.TokenType.TOKEN_TYPE_END, SetupCode.get(i));
                TokenList.add(token);
            }else{
                System.out.println("Unnamed_token");
            }
        }
        return TokenList;
    }
}