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
			//System.out.println(t.sigla);
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
	//================================================
	
	//================================================
	// Método ListaDeclaracoes
	//ListaDeclaracoes → Declaracao X;
	//================================================
	public void listaDeclaracoes() {	
		declaracao();// Método ListaDeclaracoes
		X();// Método X
	}//listaDeclaracoes
	//================================================
	
	//================================================
	// Método X
	//X → ListaDeclaracoes | palavra vazia;
	//================================================
	public void X() {
		if(t.sigla == TipoToken.Var) {//se for igual a variavel, volta para listadeclaracoes
			listaDeclaracoes();// Método ListaDeclaracoes
		}//palavra vazia não precisa representar, seria um else aqui
	}//X
	//================================================
	
	//================================================
	// Método Declaracao
	//Declaracao → VARIAVEL ':' TipoVar;
	//================================================
	public void declaracao() {
		match(t.sigla,TipoToken.Var);// VARIAVEL
		match(t.sigla,TipoToken.Delim);// :
		tipoVar();// Mpetodo TipoVar
	}//declaracao
	//================================================
	
	//================================================
	// Método TipoVar
	//TipoVar → 'INT' | 'REAL';
	//================================================
	public void tipoVar() {
		if(t.sigla == TipoToken.PCInt) {
			match(t.sigla,TipoToken.PCInt);// 'INT'
		}else if (t.sigla == TipoToken.PCReal){
			match(t.sigla,TipoToken.PCReal);// 'REAL'
		}else {
			System.out.print("#Erro sintatico:\nEsperado Token do  TipoVar \n");
			System.exit(1);
		}
	}//tipoVar
	//================================================
	
	//================================================
	// Método ListaComandos
	//ListaComandos → Comando Y;
	//================================================
	public void listaComandos(){
		comando();// Método Comando
		Y();// Método Y
	}//listaComandos
	//================================================
	
	//================================================
	// Método Y
	//Y → ListaComandos | palavra vazia;
	//================================================
	public void Y() {
		if(t.sigla == TipoToken.Var || t.sigla == TipoToken.PCLer 
		|| t.sigla == TipoToken.PCImprimir|| t.sigla == TipoToken.PCSe ||
		t.sigla == TipoToken.PCEnqto || t.sigla == TipoToken.PCIni) {
			
			listaComandos();//Método ListaComandos
		}
	}//Y
	//================================================
	
	//================================================
	// Método Comando
	//Comando → ComandoAtribuicao | ComandoEntrada |ComandoSaida
	//| ComandoCondicao | ComandoRepeticao | SubAlgoritmo;
	//================================================
	public void comando() {
		if(t.sigla == TipoToken.PCLer) {
			comandoEntrada();// Método ComandoEntrada
		}else if(t.sigla == TipoToken.Var) {
			comandoAtribuicao();// Método ComandoAtribuicao
		}else if(t.sigla == TipoToken.PCImprimir) {
			comandoSaida();	// Método ComandoSaida
		}else if(t.sigla == TipoToken.PCSe) {
			comandoCondicao();//Método ComandoSaida
		}else if(t.sigla == TipoToken.PCEnqto) {
			comandoRepeticao();// Método ComandoRepeticao
		}else if (t.sigla == TipoToken.PCIni) {
			subAlgoritmo();// Método SubAlgoritmo
		}
	}// comando
	//================================================
	
	//================================================
	// Método ComandoEntrada
	//ComandoEntrada → 'LER' VARIAVEL;
	//================================================
	public void comandoEntrada() {
		match(t.sigla,TipoToken.PCLer);// 'LER'
		match(t.sigla,TipoToken.Var);// VARIAVEL
	}//comandoEntrada
	//================================================
	
	//================================================
	// Método ComandoAtribuicao
	//ComandoAtribuicao → VARIAVEL ':=' ExpressaoAritmetica;
	//================================================
	public void comandoAtribuicao() {
		match(t.sigla,TipoToken.Var);// VARIAVEL
		match(t.sigla,TipoToken.Atrib);// :=
		expressaoAritmetica();// Método ExpressaoAritmetica
	}//comandoAtribuicao
	//================================================
	
	//================================================
	// Método ExpressaoAritmetica
	//ExpressaoAritmetica → TermoAritmetico ExpressaoAritmetica_;
	//================================================
	public void expressaoAritmetica() {
		termoAritmetico();// Método TermoAritmetico
		expressaoAritmetica_();// Método ExpressaoAritmetica_
	}//expressaoAritmetica
	//================================================
	
	//================================================
	// Método ExpressaoAritmetica
	//ExpressaoAritmetica_ → '-' TermoAritmetico ExpressaoAritmetica_ | palavra_vazia
	//ExpressaoAritmetica_ → ‘+’ TermoAtirmetico ExpressaoArimetica_ | palavra_vazia
	//================================================
	public void expressaoAritmetica_() {
		if(t.sigla == TipoToken.OpAritSoma) {
			match(t.sigla,TipoToken.OpAritSoma);
			termoAritmetico();//Método TermoAritmetico
			expressaoAritmetica_();// Método ExpressaoAritmetica_
		}else if(t.sigla == TipoToken.OpAritSub) {
			match(t.sigla,TipoToken.OpAritSub);
			termoAritmetico();//Método TermoAritmetico
			expressaoAritmetica_();// Método ExpressaoAritmetica_
		}
	}//expressaoAritmetica_
	//================================================
	
	//================================================
	// Método TermoAritmetico
	//TermoAritmetico → FatorAritmetico TermoAritmetico_;
	//================================================
	public void termoAritmetico() {
		fatorAritmetico();// Método FatorAritmetico
		termoAritimetico_();// Método TermoAritmetico_
	}//termoAritmetico
	//================================================
	
	//================================================
	// Método TermoAritmetico_
	//TermoAritmetico_ → '*' FatorAritmetico TermoAritmetico_| palavra_vazia
	//TermoAritmetico_ → '/' FatorAritmetico TermoAritmetico_| palavra_vazia
	//================================================
	public void termoAritimetico_() {
		if(t.sigla == TipoToken.OpAritMult) {
			match(t.sigla,TipoToken.OpAritMult);
			fatorAritmetico();// Método FatorAritmetico
			termoAritimetico_();// Método TermoAritmetico_
		}else if(t.sigla == TipoToken.OpAritDiv) {
			match(t.sigla,TipoToken.OpAritDiv);
			fatorAritmetico();// Método FatorAritmetico
			termoAritimetico_();// Método TermoAritmetico_
		}
	}//termoAritimetico_
	//================================================
	
	//================================================
	// Método FatorAritmetico
	//FatorAritmetico → NUMINT| NUMREAL | VARIAVEL | '(' ExpressaoAritmetica ')'
	//================================================
	public void fatorAritmetico() {
		if(t.sigla == TipoToken.NumInt) {
			match(t.sigla,TipoToken.NumInt); // NUMINT
		}else if(t.sigla == TipoToken.NumReal) {
			match(t.sigla,TipoToken.NumReal); // NUMREAL
		}else if(t.sigla == TipoToken.Var) {
			match(t.sigla,TipoToken.Var);// VARIAVEL
		}else if(t.sigla == TipoToken.AbrePar) {
			match(t.sigla,TipoToken.AbrePar);// (
			expressaoAritmetica();// Método ExpressaoAritmetica
			match(t.sigla,TipoToken.FechaPar);// )
		}
	}//fatorAritmetico
	//================================================
	
	//================================================
	// Método ComandoCondicao
	//ComandoCondicao → 'SE' ExpressaoRelacional 'ENTAO' Comando F
	//================================================
	public void comandoCondicao() {
		match(t.sigla,TipoToken.PCSe);// 'SE'
		expressaoRelacional();// Método ExpressaoRelacional
		match(t.sigla,TipoToken.PCEntao);// 'ENTAO'
		comando();// Método Comando
		if(t.sigla == TipoToken.PCSenao)
			F();// Método F		
	}//comandoCondicao
	//================================================
	
	//================================================
	// Método F
	//F -> 'SENAO' Comando | palavra_vazia 
	//================================================
	public void F() {
		match(t.sigla,TipoToken.PCSenao);//'SENAO'
		comando();//Comando
	}// F
	//================================================
	
	//================================================
	// Método ExpressaoRelacional
	//ExpressaoRelacional → TermoRelacional ExpressaoRelacional_;
	//================================================
	public void expressaoRelacional() {
		termoRelacional();// Método TermoRelacional
		expressaoRelacional_();// Método ExpressaoRelacional_
	}//expressaoRelacional
	//================================================
	
	//================================================
	// Método ExpressaoRelacional_
	//ExpressaoRelacional_ → TermoRelacional OperadorBooleano ExpressaoRelacional_ | palavra_vazia
	//ExpressaoRelacional_ → TermoRelacional ExpressaoRelacional_ | palavra_vazia
	//================================================
	public void expressaoRelacional_() {
		if(t.sigla == TipoToken.OpBoolE || t.sigla == TipoToken.OpBoolOu || t.sigla == TipoToken.AbrePar 
				|| t.sigla == TipoToken.OpRelMenor ||t.sigla == TipoToken.OpRelMenorIgual 
				||t.sigla == TipoToken.OpRelMaior ||t.sigla == TipoToken.OpRelMaiorIgual 
				|| t.sigla == TipoToken.OpRelIgual ||t.sigla == TipoToken.OpRelDif) {
			operadorBooleano();// Método TermoRelacional
			termoRelacional();// Método OperadorBooleano
			expressaoRelacional_();// Método ExpressaoRelacional_
		}
	}//expressaoRelacional_
	//================================================

	//================================================
	// Método OperadorBooleano
	//OperadorBooleano → 'E' | 'OU';
	//================================================
		public void operadorBooleano() {
			if(t.sigla == TipoToken.OpBoolE) {
				match(t.sigla,TipoToken.OpBoolE);//'E'
			}else if (t.sigla == TipoToken.OpBoolOu){
				match(t.sigla,TipoToken.OpBoolOu);//'OU'
			}else {
				System.out.print("#Erro sintatico:\nEsperado Token operador booleano \n");
				System.exit(1);
			}
	}//operadorBooleano
	//================================================

	//================================================
	// Método termoRelacional
	//TermoRelacional → ExpressaoAritmetica OP_REL ExpressaoAritmetica | '(' ExpressaoRelacional ')';
	//================================================
	public void termoRelacional() {
		if(t.sigla == TipoToken.AbrePar) {
			match(t.sigla,TipoToken.AbrePar);// (
			expressaoRelacional();// Método ExpressaoRelacional 
			match(t.sigla,TipoToken.FechaPar);// )
		}else {
		expressaoAritmetica();// Método ExpressaoAritmetica
		// OP_REL
		//------------------------------------------------
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
		//------------------------------------------------
		expressaoAritmetica();// Método ExpressaoAritmetica 
		}
	}//termoRelacional
	//================================================

	//================================================
	// Método ComandoRepeticao
	//ComandoRepeticao → 'ENQTO' ExpressaoRelacional Comando;
	//================================================
		public void comandoRepeticao() {
			match(t.sigla,TipoToken.PCEnqto);// 'ENQTO'
			expressaoRelacional();// Método ExpressaoRelacional
			comando();// Método Comando
	}//comandoRepeticao
	//================================================

	//================================================
	// Método SubAlgoritmo
	//SubAlgoritmo → 'INI' ListaComandos 'FIM';
	//================================================
	public void subAlgoritmo() {
		match(t.sigla,TipoToken.PCIni);// 'INI'
		listaComandos();// Método ListaComandos
		match(t.sigla,TipoToken.PCFim);// 'FIM'		
	}//subAlgoritmo
	//================================================

	//================================================
	// Método ComandoSaida
	//ComandoSaida → 'IMPRIMIR'  VARIAVEL | 'IMPRIMIR' CADEIA;
	//================================================
		public void comandoSaida() {
			match(t.sigla,TipoToken.PCImprimir);// 'IMPRIMIR'
			if(t.sigla == TipoToken.Cadeia) {
				match(t.sigla,TipoToken.Cadeia);// CADEIA
			}else if (t.sigla == TipoToken.Var){ 
				match(t.sigla,TipoToken.Var);// 'VARIAVEL'
			}else {
				System.out.print("#Erro sintatico:\nEsperado Token Cadeia ou Token Var\n");
				System.exit(1);
			}
		}//comandoSaida
		
}//GyhSint