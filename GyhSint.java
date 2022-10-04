
public class GyhSint {
	public Token t;
	public GyhLex lex;
	
	public GyhSint(String arquivo) {
		 lex = new GyhLex(arquivo);
		 lex.prox = new ProximoCaracter(arquivo);
		 t = lex.proximoToken();
	}
	
	public Token proximo() {
		t = lex.proximoToken();
		if(t == null) {
			System.exit(1);
		}
		return t;
	}
	 
	public void match(TipoToken A, TipoToken B) {
		if (A == B) {
			System.out.print("match de " + A +"\n");
		}else {
			System.out.print("Erro sintatico, entrou "+A+" e o esperado era "+ B +"\n");
			System.exit(1);
		}
	}
	public void programa() {
		if(t != null) {
		match(t.sigla,TipoToken.Delim);
		t = proximo();
		match(t.sigla,TipoToken.PCDec);
		t = proximo();
		listaDeclaracoes(t);
		//t = proximo();
		match(t.sigla,TipoToken.Delim);
		t = proximo();
		match(t.sigla,TipoToken.PCProg);
		t = proximo();
		listaComandos(t);
		}else {
		System.exit(1);
		}
	}
	public void listaDeclaracoes(Token t) {
		declaracao(t);
		t = proximo();
		X(t);
	}
	public void X(Token t) {
		if(t.sigla == TipoToken.Var) {
			listaDeclaracoes(t);
		}
	}
	public void declaracao(Token t) {
		match(t.sigla,TipoToken.Var);
		t = proximo();
		match(t.sigla,TipoToken.Delim);
		t = proximo();
		tipoVar(t);
	}
	public void tipoVar(Token t) {
		if(t.sigla == TipoToken.PCInt) {
			match(t.sigla,TipoToken.PCInt);
		}else {
			match(t.sigla,TipoToken.PCReal);
		}
	}
	public void listaComandos(Token t){
		//System.out.print(t.sigla);
		comando(t);
		t = proximo();
		//System.out.print(t.sigla);
		Y(t);
	}
	public void Y(Token t) {
		//System.out.println(t.sigla);
		if(t.sigla == TipoToken.Var || t.sigla == TipoToken.PCLer 
		|| t.sigla == TipoToken.PCImprimir|| t.sigla == TipoToken.PCSe ||
		t.sigla == TipoToken.PCEnqto || t.sigla == TipoToken.PCIni) {
			
			listaComandos(t);
		}
	}
	public void comando(Token t) {
		if(t.sigla == TipoToken.PCLer) {
			comandoEntrada(t);
		}else if(t.sigla == TipoToken.Var) {
			comandoAtribuicao(t);
		}else if(t.sigla == TipoToken.PCImprimir) {
			//comandoSaida(t);	
		}else if(t.sigla == TipoToken.PCSe) {
			comandoCondicao(t);
		}else if(t.sigla == TipoToken.PCEnqto) {
			comandoRepeticao(t);
		}else if (t.sigla == TipoToken.PCIni) {
			subAlgoritmo(t);
		}
	}
	public void comandoEntrada(Token t) {
		match(t.sigla,TipoToken.PCLer);
		t = proximo();
		match(t.sigla,TipoToken.Var);
		
	}
	public void comandoAtribuicao(Token t) {
		match(t.sigla,TipoToken.Var);
		t = proximo();
		match(t.sigla,TipoToken.Atrib);
		t = proximo();
		expressaoAritmetica(t);
	}
	public void expressaoAritmetica(Token t) {
		termoAritmetico(t);
		expressaoAritmetica_(t);
	}
	public void expressaoAritmetica_(Token t) {
		if(t.sigla == TipoToken.OpAritSoma) {
			match(t.sigla,TipoToken.OpAritSoma);
			t = proximo();
			termoAritmetico(t);
			t = proximo();
			expressaoAritmetica_(t);
		}else if(t.sigla == TipoToken.OpAritSub) {
			match(t.sigla,TipoToken.OpAritSub);
			t = proximo();
			termoAritmetico(t);
			t = proximo();
			expressaoAritmetica_(t);
		}
	}
	public void termoAritmetico(Token t) {
		fatorAritmetico(t);
		termoAritimetico_(t);

	}
	public void termoAritimetico_(Token t) {
		//t = proximo();
		System.out.println(t.sigla);
		if(t.sigla == TipoToken.OpAritMult) {
			match(t.sigla,TipoToken.OpAritMult);
			t = proximo();
			fatorAritmetico(t);
			t = proximo();
			termoAritimetico_(t);
		}else if(t.sigla == TipoToken.OpAritDiv) {
			match(t.sigla,TipoToken.OpAritDiv);
			//t = proximo();
			fatorAritmetico(t);
			t = proximo();
			termoAritimetico_(t);
		}
	}
	public void fatorAritmetico(Token t) {
		if(t.sigla == TipoToken.NumInt) {
			match(t.sigla,TipoToken.NumInt);
		}else if(t.sigla == TipoToken.NumReal) {
			match(t.sigla,TipoToken.NumReal);
		}else if(t.sigla == TipoToken.Var) {
			match(t.sigla,TipoToken.Var);
		}else if(t.sigla == TipoToken.AbrePar) {
			match(t.sigla,TipoToken.AbrePar);
			t = proximo();
			expressaoAritmetica(t);
			t = proximo();
			match(t.sigla,TipoToken.FechaPar);
		}
		//t = proximo();
		//System.out.println(t.sigla);
	}
	public void comandoCondicao(Token t) {
		match(t.sigla,TipoToken.PCSe);
		t = proximo();
		expressaoRelacional(t);
		t = proximo();
		match(t.sigla,TipoToken.PCEntao);
	}
	public void expressaoRelacional(Token t) {
		termoRelacional(t);
		expressaoRelacional_(t);
	}
	public void expressaoRelacional_(Token t) {
		if(t.sigla == TipoToken.OpBoolE || t.sigla == TipoToken.OpBoolOu) {
			operadorBooleano(t);
			expressaoRelacional_(t);
		}else if(t.sigla == TipoToken.AbrePar || t.sigla == TipoToken.OpRelMenor ||
				t.sigla == TipoToken.OpRelMenorIgual ||t.sigla == TipoToken.OpRelMaior ||
				t.sigla == TipoToken.OpRelMaiorIgual || t.sigla == TipoToken.OpRelIgual ||
				t.sigla == TipoToken.OpRelDif) {
			termoRelacional(t);
			expressaoRelacional_(t);
		}
		//t = proximo();
		//System.out.println(t.sigla);
	}
		public void operadorBooleano(Token t) {
			if(t.sigla == TipoToken.OpBoolE) {
				match(t.sigla,TipoToken.OpBoolE);
			}else {
				match(t.sigla,TipoToken.OpBoolOu);
			}
			t = proximo();
			/*}else {
			System.out.print("Erro sintatico, nao eh nem E nem Ou \n");
			System.exit(1);
		}*/
			
		}
		public void termoRelacional(Token t) {
			if(t.sigla == TipoToken.AbrePar) {
				match(t.sigla,TipoToken.AbrePar);
				t = proximo();
				expressaoRelacional(t);
				t = proximo();
				match(t.sigla,TipoToken.FechaPar);
			}else {
			expressaoAritmetica(t);
			t = proximo();
			if(t.sigla == TipoToken.OpRelMenor) {
				match(t.sigla,TipoToken.OpRelMenor);
			}else if (t.sigla == TipoToken.OpRelMenorIgual) {
				match(t.sigla,TipoToken.OpRelMenorIgual);
			}else if(t.sigla == TipoToken.OpRelMaior) {
				match(t.sigla,TipoToken.OpRelMaior);
			}else if (t.sigla == TipoToken.OpRelMaiorIgual) {
				match(t.sigla,TipoToken.OpRelMaiorIgual);
			}else if(t.sigla == TipoToken.OpRelIgual) {
				match(t.sigla,TipoToken.OpRelIgual);
			}else {
				match(t.sigla,TipoToken.OpRelDif);
			}
			t = proximo();
			expressaoAritmetica(t);
			/*}else {
			System.out.print("Erro sintatico, era esperado um operador relacional \n");
			System.exit(1);
		}*/
			}
		}
		public void comandoRepeticao(Token t) {
			match(t.sigla,TipoToken.PCEnqto);
			t = proximo();
			expressaoRelacional(t);
			t = proximo();
			comando(t);
		}
		public void subAlgoritmo(Token t) {
			match(t.sigla,TipoToken.PCIni);
			t = proximo();
			listaComandos(t);
			match(t.sigla,TipoToken.PCFim);
			
		}
		
}
	