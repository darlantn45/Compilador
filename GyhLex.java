
public class GyhLex {
	public String nome = "";
	public String var = "";
	public ProximoCaracter prox;
	public int aux;
	
	public GyhLex(String arquivo) {
		 prox = new ProximoCaracter(arquivo);
	}
	
	public Token proximoToken() {
			
		char c = prox.proximo();
		while(c ==' ' || c =='\n' || c =='\t') c = prox.proximo();// enquanto for espaço, tabulação ou quebra de linha. Pega o próximo cacarcter
		
		while(c != '\0') {//continua rodando até receber um \0			
			var = "";
			if(c >= 'a' && c <= 'z') {//entra nesse if se for letra minusculas de a até z
				do {
				nome += c;
				c = prox.proximo();
				}while(c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' );//depois da primeira letra ser minúscula, pega todo o resto até um delemitador
					prox.decrementa();
					var += nome;
					nome = "";
					return new Token(TipoToken.Var,var);//retorna um token do tipo variavél
					
					
			}else if(c >= 'A' && c <= 'Z' ) {//se for letra maiscula é palavra reservada
				nome += c;
				c = prox.proximo();
				prox.decrementa();
				if(c < 'A' || c > 'Z') {// como é palavra reservada só é aceito letra maiúscula
					var += nome;
					nome = "";
			switch(var) {//nesse switch contém todoas as palavras reservadas, onde irá fazer a comparação, se bater com o case retorna o token da plavra reservada
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
			default://caso não exista essa palavra reservada retorna um erro lexico
				var +=c;
				System.out.print("Erro lexico: <"+ var + "> Nao eh palavra reservada\n na linha: "+ prox.numeroLinha()+"\n");
				return null;//e finaliza o programa
			}
			}
			}else if (c >= '0' && c <= '9') {//aqui ver se o numero e real ou inteiro no intervalo de 0 até 9
				int aux = 0;
				do {
					nome += c;
					c = prox.proximo();
					if(c == '.') aux = 1;
					}while(c >= '0' && c <= '9' || c == '.' );//caso aparece um ponto, que dizer que é um número, caso contrário será inteiro
						prox.decrementa();
						var += nome;
						nome = "";
						if(aux == 0 )return new Token(TipoToken.NumInt,var);//retorna o token do tipo inteiro
						else return new Token(TipoToken.NumReal,var);// retorn o token do tipo real
			}
		
				
				
			else{switch(c) {//nesse switch terá os operadores aritméticos, operadores relacionais, delimitador, atribuição, parêntesis e cadeia


		 	case '-':
		 		if(aux == 1) {//se entra nesse if quer dizer que temos uma atribução e um menos na frente, então é número negativo
					do {
						nome += c;
						c = prox.proximo();
						}while(c >= '0' && c <= '9' || c == '.' );//vai pegar tudo dentro do paramentros estabelecidos
							prox.decrementa();
							var += nome;
							nome = "";
							aux = 0;
							return new Token(TipoToken.NumReal,var);//retorna um token do tipo real
				}else {//caso entre no else, então temos uma operação de subtração
		 		return new Token(TipoToken.OpAritSub, "-");//retorna token de subtração
				}
		 	case '+': return new Token(TipoToken.OpAritSoma, "+");//retorna token de adição
		 	case '*': return new Token(TipoToken.OpAritMult, "*");//retorna token de multiplicação
		 	case '/': return new Token(TipoToken.OpAritDiv, "/");//retorna token de divisão
		 	case '(': return new Token(TipoToken.AbrePar, "(");//retorna token de abre parentese
		 	case ')': return new Token(TipoToken.FechaPar, ")");//retorna token de fecha parentese
		 	case '"': //que dizer que é uma cadeia
		 		do {
					nome += c;
					c = prox.proximo();
					}while(c != '"');//vai ser pego até encontra outra aspa dupla
		 				nome += c;
						var += nome;
						nome = "";
		 		return new Token(TipoToken.Cadeia,var);//retornando um token de cadeia
		 	case '<':
		 		c = prox.proximo();
		 		if(c == '=') {//caso entra aqui é um operador de menor igual
		 			return new Token(TipoToken.OpRelMenorIgual, "<=");
		 		}else {//caso entre aqui é operador de menor
		 			prox.decrementa();
		 			return new Token(TipoToken.OpRelMenor, "<");
		 		}
		 	case '>':
		 		c = prox.proximo();
		 		if(c == '=') {//caso entra aqui operado de maior ou igual
		 			return new Token(TipoToken.OpRelMaiorIgual, ">=");
		 		}else {//caso entra aqui operador de maior
		 			prox.decrementa();
		 			return new Token(TipoToken.OpRelMaior, ">");
		 		}
		 	case ':': 
		 		c = prox.proximo();
		 		if( c == '=') {// se entra aqui é uma atribuição
		 			c = prox.proximo();
		 			while(c ==' ' || c =='\n' || c =='\t') c = prox.proximo();//então vemos o proximo caracter
		 			if(c == '-') {//se for um menos, então é um número negativo
		 				aux = 1;
		 			}
		 			prox.decrementa();
		 			return new Token(TipoToken.Atrib, ":=");
		 		}else {//caso entra aqui é um delimitador
		 			prox.decrementa();
		 			return new Token(TipoToken.Delim, ":");
		 		}
		 	case '=': 
		 		c = prox.proximo();
		 		if( c == '=') {//caso entra aqui é um operador de igual igual, para fazer comparação
		 			return new Token(TipoToken.OpRelIgual, "==");
		 		}else {//caso entra, quer dizer que é um erro lexico
		 			System.out.print("Erro lexico: <=> caractere desconhecido\n na linha: "+ prox.numeroLinha()+"\n");
		 			return null;
		 		}
		 	case '!': 
		 		c = prox.proximo();
		 		if( c == '=') {//caso entra aqui é o operador de diferente
		 			return new Token(TipoToken.OpRelDif, "!=");
		 		}else {//caso entra, quer dizer que é um erro lexico
		 			prox.decrementa();
		 			System.out.print("Erro lexico: <!> caractere desconhecido\n na linha: "+ prox.numeroLinha()+"\n");
		 			return null;
		 		}
		 		default://se não esntrou em nenhum é o caracter desconhecido, então erro lexico
		 			System.out.print("Erro lexico: <"+ c + "> caractere desconhecido\n na linha: "+ prox.numeroLinha()+"\n");
		 			return null;
		 			

		 		
		 	
		}
			}
		 c = prox.proximo();
		}
	return null;//quando finaliza o programa retorna nullo
	}
}
			
