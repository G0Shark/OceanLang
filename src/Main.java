import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String code = s.nextLine();
        Lexer l = new Lexer();
        l.initilaze(code);
    }
}