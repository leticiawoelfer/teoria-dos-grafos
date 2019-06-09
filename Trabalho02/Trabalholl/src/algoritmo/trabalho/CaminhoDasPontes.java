package algoritmo.trabalho;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import algoritmo.dijkstra.Aresta;
import algoritmo.dijkstra.Grafo;

/**
 * 
 * @author gabriela.sena, leticia.woelfer
 * Item 1 do trabalho 2
 *
 */
public class CaminhoDasPontes {

	public static void main(String[] args) throws IOException {
		new CaminhoDasPontes().processar();
	}
	
	public final int QTD_MAX_ARESTA = 10000;
	public final int QTD_MAX_VERTICE= 1000;
	public final int ORIGEM = 0;
	public final String caminhoArquivo = "c:\\temp\\entrada.in";
	
	public void processar() throws IOException {
//		String caminhoArquivo = lerArquivo();
		List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
		boolean primeiraLinha = true;
		int qtdVertices;
		int qtdArestas;
		List<Aresta> arestas = new ArrayList<>();
		int destino = 0;
		for (String conteudoLinha : linhas) {
			if(primeiraLinha) {
				if(conteudoLinha.length() != 3) {
					System.err.println("Arquivo inválido. A primeira linha deve conter 3 caracters");
					return;
				}
				qtdVertices = Character.getNumericValue(conteudoLinha.charAt(0));
				qtdArestas = Character.getNumericValue(conteudoLinha.charAt(2));
				if(!validaVerticeAresta(qtdVertices, qtdArestas)) {
					return;
				}
				primeiraLinha = false;
				destino = qtdVertices + 1;
				continue;
			}			
			
			if(conteudoLinha.length() != 5) {
				System.err.println("Conteudo da linha inválido, deve conter 5 posições contendo 'Vertice Vertice Valor'");
				return;
			}
			
			int verticeOrigem = Character.getNumericValue(conteudoLinha.charAt(0));
			int verticeDestino = Character.getNumericValue(conteudoLinha.charAt(2));
			
			
			
			
			int valor = Character.getNumericValue(conteudoLinha.charAt(4));
			Aresta edge = new Aresta(verticeOrigem, verticeDestino, valor);
			arestas.add(edge);
						
		}
		
		
		
		Grafo grafo = new Grafo(arestas.toArray(new Aresta[arestas.size()]));
		grafo.calcularDistancia(ORIGEM);
		
		System.out.println("O menor caminho para o destino '" + destino + "' é: "+ grafo.getCaminhoMinimo(destino));
		
	}
	
	public boolean validaVerticeAresta(int qtdVertices, int qtdArestas) {
		if(qtdVertices > QTD_MAX_VERTICE) {
			System.err.println("Quantidade de vértices deve ser maior ou igual a 1.000");
			return false;
		}
		if(qtdArestas > QTD_MAX_ARESTA) {
			System.err.println("Quantidade de arestas deve ser maior ou igual a 10.000");
			return false;
		}
		return true;
	}
	
	public String lerArquivo() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Digite o nome do arquivo que está na pasta C:\\temp\\*.txt: ");
			String str;
				str = in.readLine();
			String caminhoArquivo = "C:\\temp\\" + str + ".txt";
			System.out.println(String.format("Validado se o arquivo : %s existe", caminhoArquivo));
			if (!new File(caminhoArquivo).exists()) {
				System.out.print("Arquivo não encontrado, favor informe novamente. ");
				return lerArquivo();
			} 
			return caminhoArquivo;
		} catch (IOException e) {
			System.out.print("Não foi possível identificar o nome do arquivo, favor informe novamente. ");
			return lerArquivo();
		}
	}

}