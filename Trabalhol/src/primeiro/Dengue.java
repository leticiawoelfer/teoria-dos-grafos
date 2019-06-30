package primeiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Gabriela Sena e Leticia Woelfer
 *
 */
public class Dengue {
	
	public static void main(String[] args) throws IOException {
		args = new String[] {"C:\\temp\\dengue.txt"};
		
		List<String> massaDeDados = Util.lerBytesArquivoAsString(args[0]);
		Map<String, String[]> dados = new HashMap<>();
		String chave = null;
		for (String string : massaDeDados) {
			if(string.length() == 1) {
//				List<String> cenarios = new ArrayList<>();
				String[] cenarios = new String[Integer.parseInt(string)];
				chave = "Teste " + dados.size()+1 + ". Quantidade de vilas: " + string;
				dados.put(chave, cenarios);
			} else {
				
			}
		}
		
	}

}
