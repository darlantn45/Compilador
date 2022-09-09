
public class ProximoCaracter {
	public LeitorArquivo ldat;
	public String linha;
	public int aux;
	public int N_linha;
	
	public ProximoCaracter(String arquivo) {//aqui leremos a primera linha do arquivo
		ldat = new LeitorArquivo(arquivo);
		aux = 0;
		linha = ldat.lerLinha();
		N_linha = 1;
		
	}
	public char proximo() {
		if(linha != null) {//se for diferente de nulo, quer dizer que não chegou no final do arquivo
		if( aux == linha.length()) {// se aux for do tamanho da string, teremos que ler a próxima linha
			
			do {//vai ler a proxima linha

				linha = ldat.lerLinha();
					if(linha == null) {
						return'\0';//caso for a ultima linha retorna \0 para finalizar while do GyhLex
				}
				aux = 0;//aux recebe zero
				N_linha++;// aumenta mais um em N_linha, para saber em que linhas estamos
				}while(linha.length() <= 0);
			return '\n';
			}
		
		char c = linha.charAt(aux);
		aux++;
		if(c == '#') {aux = linha.length(); return proximo();//caso c = #, que dizer que é uma linha do comenatário, então pula ela e faz uma recursão de proximo
}
		return c;
		}
		return '\0';
	}
	public void decrementa() {
		if(aux != 0)
		aux--;//para decrementar
	}
	public int numeroLinha() {
		return N_linha;//para saber em que linha está no arquivo
	}

}
