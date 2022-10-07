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
			t = proximo();
		}else {
			System.out.print("#Erro sintatico:\nInesperado Token: "+A+" \nEsperado Token "+ B +"\n");
			System.exit(1);
		}
	}
	
	//================================================
	// Método Programa
	//Programa → ':' 'DEC' ListaDeclaracoes ':' 'PROG' ListaComandos;
	//================================================
	public void programa() {
		match(t.sigla,TipoToken.Delim); // :
		match(t.sigla,TipoToken.PCDec); // 'DEC'
		listaDeclaracoes(); // Método ListaDeclaracoes
		match(t.sigla,TipoToken.Delim); // :
		match(t.sigla,TipoToken.PCProg); // 'PROG'
		listaComandos(); // Método ListaComandos
	}// programa
	public void listaDeclaracoes() {
		
		declaracao();
		X();
	}
	public void X() {
		if(t.sigla == TipoToken.Var) {
			listaDeclaracoes();
		}
	}
	public void declaracao() {
		match(t.sigla,TipoToken.Var);
		match(t.sigla,TipoToken.Delim);
		tipoVar();
	}
	public void tipoVar() {
		if(t.sigla == TipoToken.PCInt) {
			match(t.sigla,TipoToken.PCInt);
		}else {
			match(t.sigla,TipoToken.PCReal);
		}
	}
	public void listaComandos(){
		comando();
		Y();
	}
	public void Y() {
		if(t.sigla == TipoToken.Var || t.sigla == TipoToken.PCLer 
		|| t.sigla == TipoToken.PCImprimir|| t.sigla == TipoToken.PCSe ||
		t.sigla == TipoToken.PCEnqto || t.sigla == TipoToken.PCIni) {
			
			listaComandos();
		}
	}
	public void comando() {
		if(t.sigla == TipoToken.PCLer) {
			comandoEntrada();
		}else if(t.sigla == TipoToken.Var) {
			comandoAtribuicao();
		}else if(t.sigla == TipoToken.PCImprimir) {
			comandoSaida();	
		}else if(t.sigla == TipoToken.PCSe) {
			comandoCondicao();
		}else if(t.sigla == TipoToken.PCEnqto) {
			comandoRepeticao();
		}else if (t.sigla == TipoToken.PCIni) {
			subAlgoritmo();
		}
	}
	public void comandoEntrada() {
		match(t.sigla,TipoToken.PCLer);
		match(t.sigla,TipoToken.Var);
		
	}
	public void comandoAtribuicao() {
		match(t.sigla,TipoToken.Var);
		match(t.sigla,TipoToken.Atrib);
		expressaoAritmetica();
	}
	public void expressaoAritmetica() {
		termoAritmetico();
		expressaoAritmetica_();
	}
	public void expressaoAritmetica_() {
		if(t.sigla == TipoToken.OpAritSoma) {
			match(t.sigla,TipoToken.OpAritSoma);
			termoAritmetico();
			expressaoAritmetica_();
		}else if(t.sigla == TipoToken.OpAritSub) {
			match(t.sigla,TipoToken.OpAritSub);
			termoAritmetico();
			expressaoAritmetica_();
		}
	}
	public void termoAritmetico() {
		fatorAritmetico();
		termoAritimetico_();

	}
	public void termoAritimetico_() {
		if(t.sigla == TipoToken.OpAritMult) {
			match(t.sigla,TipoToken.OpAritMult);
			fatorAritmetico();
			termoAritimetico_();
		}else if(t.sigla == TipoToken.OpAritDiv) {
			match(t.sigla,TipoToken.OpAritDiv);
			fatorAritmetico();
			termoAritimetico_();
		}
	}
	public void fatorAritmetico() {
		if(t.sigla == TipoToken.NumInt) {
			match(t.sigla,TipoToken.NumInt);
		}else if(t.sigla == TipoToken.NumReal) {
			match(t.sigla,TipoToken.NumReal);
		}else if(t.sigla == TipoToken.Var) {
			match(t.sigla,TipoToken.Var);
		}else if(t.sigla == TipoToken.AbrePar) {
			match(t.sigla,TipoToken.AbrePar);
			expressaoAritmetica();
			match(t.sigla,TipoToken.FechaPar);
		}
	}
	public void comandoCondicao() {
		match(t.sigla,TipoToken.PCSe);
		expressaoRelacional();
		match(t.sigla,TipoToken.PCEntao);
		comando();
		if(t.sigla == TipoToken.PCSenao)
			F();		
	}
	public void F() {
		match(t.sigla,TipoToken.PCSenao);
		comando();
	}
	public void expressaoRelacional() {
		termoRelacional();
		expressaoRelacional_();
	}
	public void expressaoRelacional_() {
		if(t.sigla == TipoToken.OpBoolE || t.sigla == TipoToken.OpBoolOu) {
			operadorBooleano();
			expressaoRelacional();
		}else if(t.sigla == TipoToken.AbrePar || t.sigla == TipoToken.OpRelMenor ||
				t.sigla == TipoToken.OpRelMenorIgual ||t.sigla == TipoToken.OpRelMaior ||
				t.sigla == TipoToken.OpRelMaiorIgual || t.sigla == TipoToken.OpRelIgual ||
				t.sigla == TipoToken.OpRelDif) {
			termoRelacional();
			expressaoRelacional();
		}
	}
		public void operadorBooleano() {
			if(t.sigla == TipoToken.OpBoolE) {
				match(t.sigla,TipoToken.OpBoolE);
			}else if (t.sigla == TipoToken.OpBoolOu){
				match(t.sigla,TipoToken.OpBoolOu);
			}else {
				System.out.print("#Erro sintatico:\nEsperado Token operador booleano\n");
			System.exit(1);
		}	
		}
		public void termoRelacional() {
			if(t.sigla == TipoToken.AbrePar) {
				match(t.sigla,TipoToken.AbrePar);
				expressaoRelacional();
				match(t.sigla,TipoToken.FechaPar);
			}else {
			expressaoAritmetica();
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
			}else if (t.sigla == TipoToken.OpRelDif) {
				match(t.sigla,TipoToken.OpRelDif);
			}else {
				System.out.print("#Erro sintatico:\nEsperado Token operador relacional \n");
				System.exit(1);
		}
			expressaoAritmetica();
			}
		}
		public void comandoRepeticao() {
			match(t.sigla,TipoToken.PCEnqto);
			expressaoRelacional();
			comando();
		}
		public void subAlgoritmo() {
			match(t.sigla,TipoToken.PCIni);
			listaComandos();
			match(t.sigla,TipoToken.PCFim);
			
		}
		public void comandoSaida() {
			match(t.sigla,TipoToken.PCImprimir);
			if(t.sigla == TipoToken.Cadeia) {
				match(t.sigla,TipoToken.Cadeia);
			}else if (t.sigla == TipoToken.Var){ 
				match(t.sigla,TipoToken.Var);
			}else {
				System.out.print("#Erro sintatico:\nEsperado Token Cadeia ou Token Var\n");
				System.exit(1);
			}
		}
		
}