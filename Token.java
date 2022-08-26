
public class Token {
   public TipoToken sigla;
   public String lexema;
   
   public Token(TipoToken sigla, String lexema) {
	   this.sigla = sigla;
	   this.lexema =lexema;
   }
   
   @Override
   public String toString() {
	   return "< "+sigla+", "+lexema+" >";
   }
}
