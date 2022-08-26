
public class GyhLex {
	public LeitorArquivo ldat;
	
	public GyhLex(String arquivo) {
		ldat = new LeitorArquivo(arquivo);
	}
	
	public Token proximoToken() {
		int caractere=-1;
		
		while((caractere=ldat.lerProxCaractere()) != -1 ) {
			char c = (char) caractere;
			
			if(c==' ' || c=='\n' || c=='\t') continue;
			
			switch(c) {
			 	case '-': return new Token(TipoToken.OpAritSub, "-");
			 	case '+': return new Token(TipoToken.OpAritSoma, "+");
			 	case '*': return new Token(TipoToken.OpAritMult, "*");
			 	case '/': return new Token(TipoToken.OpAritDiv, "/");
			 	case '<': return new Token(TipoToken.OpRelMenor, "<");
			 	case '>': return new Token(TipoToken.OpRelMaior, ">");
			 	case ':': return new Token(TipoToken.Delim, ":");
			 	
			}
		}
		return null;
	}
}
