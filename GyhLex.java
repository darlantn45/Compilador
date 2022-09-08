
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
				do {
				nome += c;
				c = prox.proximo();
				}while(c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' );
					prox.decrementa();
					var += nome;
					nome = "";
					return new Token(TipoToken.Var,var);
					
					
			}else if(c >= 'A' && c <= 'Z' ) {
				nome += c;
				c = prox.proximo();
				prox.decrementa();
				if(c < 'A' || c > 'Z') {
					var += nome;
					nome = "";
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
			case "E": return new Token(TipoToken.OpBoolE, "E");
			case "OU": return new Token(TipoToken.OpBoolOu, "OU");
			default:
				var +=c;
				System.out.print("Erro lexico: <"+ var + "> Nao eh palavra reservada\n na linha: "+ prox.numeroLinha()+"\n");
				return null;
			}
			}
			}else if (c >= '0' && c <= '9') {
				int aux = 0;
				do {
					nome += c;
					c = prox.proximo();
					if(c == '.') aux = 1;
					}while(c >= '0' && c <= '9' || c == '.' );
						prox.decrementa();
						var += nome;
						nome = "";
						if(aux == 0 )return new Token(TipoToken.NumInt,var);
						else return new Token(TipoToken.NumReal,var);
			}
		
				
				
			else{switch(c) {
		 	case '-':return new Token(TipoToken.OpAritSub, "-");
		 	case '+': return new Token(TipoToken.OpAritSoma, "+");
		 	case '*': return new Token(TipoToken.OpAritMult, "*");
		 	case '/': return new Token(TipoToken.OpAritDiv, "/");
		 	case '(': return new Token(TipoToken.AbrePar, "(");
		 	case ')': return new Token(TipoToken.FechaPar, ")");
		 	case '"': 
		 		do {
					nome += c;
					c = prox.proximo();
					}while(c != '"');
		 				nome += c;
						var += nome;
						nome = "";
		 		return new Token(TipoToken.Cadeia,var);
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
		 			System.out.print("Erro lexico: <=> caractere desconhecido\n na linha: "+ prox.numeroLinha()+"\n");
		 			return null;
		 		}
		 	case '!': 
		 		c = prox.proximo();
		 		if( c == '=') {
		 			return new Token(TipoToken.OpRelDif, "==");
		 		}else {
		 			prox.decrementa();
		 			System.out.print("Erro lexico: <!> caractere desconhecido\n na linha: "+ prox.numeroLinha()+"\n");
		 			return null;
		 		}
		 		default:
		 			System.out.print("Erro lexico: <"+ c + "> caractere desconhecido\n na linha: "+ prox.numeroLinha()+"\n");
		 			return null;
		 			

		 		
		 	
		}
			}
		 c = prox.proximo();
		}
	return null;
}
}
			
