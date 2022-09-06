
public class GyhLex {
	public String nome = "";
	public String var = "";
	public ProximoCaracter prox;
	
	public GyhLex(String arquivo) {
		 prox = new ProximoCaracter(arquivo);
	}
	
	public Token proximoToken() {
			
		char c = prox.proximo();
		while(c ==' ' || c =='\n' || c =='\t') c = prox.proximo();
		
		while(c != '\0') {			
			var = "";
			if(c >= 'a' && c <= 'z') {
				nome += c;
				c = prox.proximo();
				prox.decrementa();
				if(c < 'a' || c > 'z') {
					var += nome;
					nome = "";
					return new Token(TipoToken.Var,var);
					
					
			}}else if(c >= 'A' && c <= 'Z' ) {
				nome += c;
				c = prox.proximo();
				prox.decrementa();
				if(c < 'A' || c > 'Z') {
					var += nome;
					nome = "";
					//System.out.print(var);
			switch(var) {
			case "DEC": return new Token(TipoToken.PCDec, "DEC");
			case "PROG" : return new Token(TipoToken.PCProg, "PROG");
			case "INT" : return new Token(TipoToken.PCInt, "INT");
			case "REAL" : return new Token(TipoToken.PCReal, "REAL");
			case "LER" : return new Token(TipoToken.PCLer, "LER");
			case "IMPRIMIR" : return new Token(TipoToken.PCImprimir, "IMPRIMIR");
			case "SE" : return new Token(TipoToken.PCSe, "SE");
			case "SENAO" : return new Token(TipoToken.PCSenao, "SENAO");
			case "ENTAO" : return new Token(TipoToken.PCEntao, "ENTAO");
			case "ENQTO" : return new Token(TipoToken.PCEnqto, "ENQTO");
			case "INI" : return new Token(TipoToken.PCIni, "INI");
			case "FIM" : return new Token(TipoToken.PCFim, "FIM");
			}
			}
			}else if (c >= '0' && c <= '9') {
				nome += c;
				c = prox.proximo();
				prox.decrementa();
				if(c < '0' || c > '9') {
					var += nome;
					nome = "";
					return new Token(TipoToken.NumInt,var);
				
			}
			}
		
				
				
			switch(c) {
		 	case '-': return new Token(TipoToken.OpAritSub, "-");
		 	case '+': return new Token(TipoToken.OpAritSoma, "+");
		 	case '*': return new Token(TipoToken.OpAritMult, "*");
		 	case '/': return new Token(TipoToken.OpAritDiv, "/");
		 	case '(': return new Token(TipoToken.AbrePar, "(");
		 	case ')': return new Token(TipoToken.FechaPar, ")");
		 	case '"': 
		 		return new Token(TipoToken.Cadeia, "\"");
		 	case '<':
		 		c = prox.proximo();
		 		if(c == '=') {
		 			return new Token(TipoToken.OpRelMenorIgual, "<=");
		 		}else {
		 			prox.decrementa();
		 			return new Token(TipoToken.OpRelMenor, "<");
		 		}
		 	case '>':
		 		c = prox.proximo();
		 		if(c == '=') {
		 			return new Token(TipoToken.OpRelMaiorIgual, ">=");
		 		}else {
		 			prox.decrementa();
		 			return new Token(TipoToken.OpRelMaior, ">");
		 		}
		 	case ':': 
		 		c = prox.proximo();
		 		if( c == '=') {
		 			return new Token(TipoToken.Atrib, ":=");
		 		}else {
		 			prox.decrementa();
		 			return new Token(TipoToken.Delim, ":");
		 		}
		 	case '=': 
		 		c = prox.proximo();
		 		if( c == '=') {
		 			return new Token(TipoToken.OpRelIgual, "==");
		 		}else {
		 			prox.decrementa();
		 			System.out.print("Erro lexico");
		 		}
		 	case '!': 
		 		c = prox.proximo();
		 		if( c == '=') {
		 			return new Token(TipoToken.OpRelDif, "==");
		 		}else {
		 			prox.decrementa();
		 			System.out.print("Erro lexico");
		 		}
		 	
		}

		 c = prox.proximo();	
		}
	return null;
}
}
			
