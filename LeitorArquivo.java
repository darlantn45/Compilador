import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LeitorArquivo {
	public InputStream is;
	
	public LeitorArquivo(String nome) {
		try {
			is = new FileInputStream(nome);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int lerProxCaractere() {
		int c=-1;
		try {
			c=is.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (c);
	}
}

/*public class LeitorArquivo {
	public InputStream is;
	
	public LeitorArquivo(String nome) {
		String file = nome;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            while ((line = br.readLine()) != null) {
            System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}*/