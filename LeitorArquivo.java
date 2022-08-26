import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivo {
	public String line;
	public String file;
	public BufferedReader br;
	
	public LeitorArquivo(String nome) {
		this.file = nome;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String lerLinha() {
		try{
            line = br.readLine(); 
      
		}catch (IOException e) {
            e.printStackTrace();
        }
	return line;
}

}