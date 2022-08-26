
public class Main{

	public static void main(String[] args) {
		GyhLex lex = new GyhLex(args[0]);
		Token t = lex.proximoToken();
		
		while(t!=null) {
			System.out.println(t.toString());
			t = lex.proximoToken();
		}
	}

}