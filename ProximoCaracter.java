
public class ProximoCaracter {
	public LeitorArquivo ldat;
	public String linha;
	public int aux;
	
	public ProximoCaracter(String arquivo) {
		ldat = new LeitorArquivo(arquivo);
		aux = 0;
		linha = ldat.lerLinha();
		
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
				}while(linha.length() <= 0);
			}
		
		char c = linha.charAt(aux);
		aux++;
		if(c == '#') {aux = linha.length(); return proximo();
}
		return c;
		
		}else {
			return '\0';
		}
	}
	public void decrementa() {
		aux--;
	}

}
