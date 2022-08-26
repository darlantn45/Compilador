
public class GyhLex {
	public LeitorArquivo ldat;
	
	public GyhLex(String arquivo) {
		ldat = new LeitorArquivo(arquivo);
	}
	
	public Token proximoToken() {
		String linha;
		while((linha = ldat.lerLinha()) != null) {
			System.out.print(linha);
			String nome = "";
			String var = "";
		for(int i = 0; i < linha.length(); i++ ) {
			char c = linha.charAt(i);
			/*if(c ==' ' || c=='\n' || c=='\t') continue;
			if(c >= 'a' && c <= 'z') {
				nome += c;
				if(i+1 < linha.length()){
				c = linha.charAt(i+1);
				if(c < 'a' || c > 'z') {
					var += nome;
					nome = "";
					return new Token(TipoToken.Var,var);
					
				}
			}else {
				var += nome;
				nome = "";
				return new Token(TipoToken.Var,var);
			}
			}
			*/
			
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
		}
		return null;
	}
}
