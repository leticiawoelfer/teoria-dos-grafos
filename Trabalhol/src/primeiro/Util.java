package primeiro;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import algoritmo.Vertice;

/**
 * 
 * @author Gabriela Sena e Leticia Woelfer
 *
 */
public class Util {

	public static List<String> lerBytesArquivoAsString(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		byte[] bytes = Files.readAllBytes(path);
		List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
		return allLines;
	}
	
	public static String escreveArquivo(String fileName, String conteudo) throws IOException {
		fileName = fileName.substring(0, fileName.length()-4)+"(resultado).txt";
		Path path = Paths.get(fileName);
		BufferedWriter bw = Files.newBufferedWriter(path);
		bw.write(conteudo);
		bw.flush();
		bw.close();
		return fileName;
	}
	
	public static String getSeqGraus(Map<String, Vertice> grafo) {
		List<Integer> graus = new ArrayList<>();
		int i = 0;
		Iterator<String> interator = grafo.keySet().iterator();
		while (interator.hasNext()) {
		    String key = interator.next();
		    graus.add(grafo.get(key).getGrau());
		    i++;
		}
		Collections.sort(graus);
		String seqGraus = "";
		for (Integer grau : graus) {
			seqGraus+=","+grau;
		}
		seqGraus = seqGraus.substring(1, seqGraus.length());
		return "("+seqGraus+")";
	}
	
	public static String getQtdArestas(Map<String, Vertice> grafo) {
		int qtdTotalGraus = 0;
		Iterator<String> interator = grafo.keySet().iterator();
		while (interator.hasNext()) {
		    String key = interator.next();
		    qtdTotalGraus += grafo.get(key).getGrau();
		}
		return ""+(qtdTotalGraus / 2);
	}

	public static int montaGrafo(String caminhoArquivo, Map<String, Vertice> grafo) throws IOException {
		List<String> massaDeDados = Util.lerBytesArquivoAsString(caminhoArquivo);
		// montar o grafo com base no arquivo lido
		int qtdVertices = 0;
		if (massaDeDados != null) {
			qtdVertices = Integer.parseInt(massaDeDados.get(0));
			for (int i = 1; i <= qtdVertices; i++) {
				Vertice novoVertice = new Vertice("" + i);
				grafo.put("" + i, novoVertice);
			}
			for (int j = 1; j < massaDeDados.size(); j++) {
				String linha[] = massaDeDados.get(j).split(" ");
				grafo.get(linha[0]).adicionarVizinho(grafo.get(linha[1]));
			}
		}
		return qtdVertices;
	}

}
