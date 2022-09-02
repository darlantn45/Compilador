
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
		linha = ldat.lerLinha();
		aux = 0;
		}
		if(linha != null) {
			char c = linha.charAt(aux);
			aux++;
			if(c == '#') aux = linha.length();
			//System.out.print(c);
			return c;
		}else {
			return '\0';
		}
		}
		return '\0';
	}
	public void decrementa() {
		aux--;
	}
}
