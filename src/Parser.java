import java.util.*;

public class Parser {
    public Hashtable<String, VariableClass> Obj = new Hashtable<String, VariableClass>();
    public void Pars(List<Token> Tokens, List<String> SetupCode){
        System.out.println("Парсер/Иностранец: начало");
        for (int i = 0; i < Tokens.size(); i++){

            if (Tokens.get(i).type== Token.TokenType.TOKEN_TYPE_LOG){
                if (Obj.containsKey(Tokens.get(i+=1).token)) {
                    System.out.println("Лог " + Obj.get(Tokens.get(i).token).variable);
                } else {
                    System.out.println("Лог " + Tokens.get(i).token);
                }
            }else if (Tokens.get(i).type== Token.TokenType.TOKEN_TYPE_VARIABLE){
                if (!Obj.containsKey(Tokens.get(i).token)) {
                    if (Tokens.get(i += 1).type == Token.TokenType.TOKEN_TYPE_EQUALS) {
                        if (Tokens.get(i += 1).type == Token.TokenType.TOKEN_TYPE_VARIABLE) {
                        VariableClass var1 = new VariableClass(VariableClass.VariableType.VARIABLE_TYPE_STRING, Tokens.get(i).token);
                        Obj.put(Tokens.get(i -= 2).token, var1);
                        i += 2;
                        } else if (Tokens.get(i).type == Token.TokenType.TOKEN_TYPE_NUMBER) {
                            if (Tokens.get(i += 1).type == Token.TokenType.TOKEN_TYPE_MINUS) {
                                int a = Integer.parseInt(Tokens.get(i += 1).token);
                                int b = Integer.parseInt(Tokens.get(i -= 2).token);
                                int c = b-a;
                                VariableClass var1 = new VariableClass(VariableClass.VariableType.VARIABLE_TYPE_INT, String.valueOf(c));
                                Obj.put(Tokens.get(i -= 2).token, var1);
                                i += 4;
                            } else if (Tokens.get(i).type == Token.TokenType.TOKEN_TYPE_PLUS) {
                                int a = Integer.parseInt(Tokens.get(i += 1).token);
                                int b = Integer.parseInt(Tokens.get(i -= 2).token);
                                int c = b+a;
                                VariableClass var1 = new VariableClass(VariableClass.VariableType.VARIABLE_TYPE_INT, String.valueOf(c));
                                Obj.put(Tokens.get(i -= 2).token, var1);
                                i += 4;
                            } else {
                                VariableClass var = new VariableClass(VariableClass.VariableType.VARIABLE_TYPE_INT, Tokens.get(i -= 1).token);
                                Obj.put(Tokens.get(i -= 2).token, var);
                                i += 2;
                            }
                        }else if (Tokens.get(i).type== Token.TokenType.TOKEN_TYPE_USER_OUT){
                            Scanner s = new Scanner(System.in);
                            VariableClass v = new VariableClass(VariableClass.VariableType.VARIABLE_TYPE_STRING, s.nextLine());
                            Obj.put(Tokens.get(i-=2).token, v);
                            i+=2;
                        }
                    }
                }else{
                    continue;
                }
            }else if(Tokens.get(i).type== Token.TokenType.TOKEN_TYPE_EQUALS) {
                if (Obj.containsKey(Tokens.get(i -= 1).token) && Tokens.get(i += 3).type == Token.TokenType.TOKEN_TYPE_END) {
                    VariableClass var = Obj.get(Tokens.get(i -= 3).token);
                    if (Tokens.get(i += 2).type == Token.TokenType.TOKEN_TYPE_NUMBER && var.type == VariableClass.VariableType.VARIABLE_TYPE_INT) {
                        var.variable = Tokens.get(i).token;
                        i += 1;
                    } else if (Tokens.get(i).type == Token.TokenType.TOKEN_TYPE_VARIABLE && var.type == VariableClass.VariableType.VARIABLE_TYPE_STRING) {
                        var.variable = Tokens.get(i).token;
                        i += 1;
                    }else if(Tokens.get(i).type== Token.TokenType.TOKEN_TYPE_USER_OUT && var.type == VariableClass.VariableType.VARIABLE_TYPE_STRING){
                        Scanner s = new Scanner(System.in);
                        var.variable = s.nextLine();
                        i+=1;
                    }else {
                        i += 1;
                    }
                }else{
                    continue; 
                }
            }else if(Tokens.get(i).type== Token.TokenType.TOKEN_TYPE_END) {
                continue;
            } else {
                System.out.println("я хз");
            }
        }
    }
}
