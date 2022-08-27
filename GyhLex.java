
public class GyhLex {
	public String nome = "";
	public String var = "";
	public ProximoCaracter prox;
	
	public GyhLex(String arquivo) {
		 prox = new ProximoCaracter(arquivo);
	}
	
	public Token proximoToken() {
			
		char c = prox.proximo();
		while(c != '\0') {
			if(c >= 'a' && c <= 'z') {
				nome += c;
				c = prox.proximo();
				prox.decrementa();
				if(c < 'a' || c > 'z') {
					var += nome;
					nome = "";
					return new Token(TipoToken.Var,var);
					
			}
			}
				
				
			switch(c) {
		 	case '-': return new Token(TipoToken.OpAritSub, "-");
		 	case '+': return new Token(TipoToken.OpAritSoma, "+");
		 	case '*': return new Token(TipoToken.OpAritMult, "*");
		 	case '/': return new Token(TipoToken.OpAritDiv, "/");
		 	case '<': return new Token(TipoToken.OpRelMenor, "<");
		 	case '>': return new Token(TipoToken.OpRelMaior, ">");
		 	case ':': return new Token(TipoToken.Delim, ":");
		 	
		}
			

		 c = prox.proximo();	
}
	return null;
}
}
			
