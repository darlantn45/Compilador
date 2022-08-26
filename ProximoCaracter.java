
public class ProximoCaracter {
	public LeitorArquivo ldat;
	public String linha;
	public int aux;
	
	public ProximoCaracter(String arquivo) {
		ldat = new LeitorArquivo(arquivo);
		linha = ldat.lerLinha();
		
	}
	public char proximo() {
		aux = 0;
		if( aux == linha.length()) {
		linha = ldat.lerLinha();
		aux = 0;
		}
			char c = linha.charAt(aux);
			aux++;
			return c;
	}
}
