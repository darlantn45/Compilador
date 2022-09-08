
public class ProximoCaracter {
	public LeitorArquivo ldat;
	public String linha;
	public int aux;
	public int N_linha;
	
	public ProximoCaracter(String arquivo) {
		ldat = new LeitorArquivo(arquivo);
		aux = 0;
		linha = ldat.lerLinha();
		N_linha = 1;
		
	}
	public char proximo() {
		if(linha != null) {
		if( aux == linha.length()) {
			
			do {

				linha = ldat.lerLinha();
					if(linha == null) {
						return'\0';
				}
				aux = 0;
				N_linha++;
				}while(linha.length() <= 0);
			return '\n';
			}
		
		char c = linha.charAt(aux);
		aux++;
		if(c == '#') {aux = linha.length(); return proximo();
}
		return c;
		}
		return '\0';
	}
	public void decrementa() {
		if(aux != 0)
		aux--;
	}
	public int numeroLinha() {
		return N_linha;
	}

}
