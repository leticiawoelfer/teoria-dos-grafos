package algoritmo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import primeiro.Util;

/**
 * 
 * @author Gabriela Sena e Leticia Woelfer
 *
 */
public class BuscaProfundidade {

	public void buscaEmProfundidade(Vertice vertice) {
		System.out.println("Iniciando busca em profundidade pelo vértice "+vertice.getValor());
		System.out.println(vertice.getValor());
		Stack<Vertice> pilha = new Stack<Vertice>();
		pilha.add(vertice);
		vertice.setEncontrado(true);
		int tempo = 0;

		while (!pilha.isEmpty()) {
			Vertice proximo = pilha.pop();
			List<Vertice> vizinhos = proximo.getVizinhos();
			for (Vertice encontrado : vizinhos) {
				tempo++;
				if (!encontrado.isEncontrado()) {
					pilha.push(encontrado);
					encontrado.setEncontrado(true);
					System.out.println(encontrado.getValor());
				}
			}
		}

		System.out.println(tempo);
		for (Vertice vertice2 : pilha) {
			System.out.println(vertice2.getValor());
		}
	}
	
	public static void main(String args[]) throws IOException {
		// ler grafos de um arquivo texto
		System.out.println("Informe o caminho do arquivo com as informações do grafos: (sem validações)");
		Scanner sc = new Scanner(System.in);
		String caminhoArquivo = sc.nextLine();
		Map<String, Vertice> grafo = new HashMap<>();
		int qtdVertices = 0;
		if (!caminhoArquivo.isEmpty()) {
			qtdVertices = Util.montaGrafo(caminhoArquivo, grafo);
			// esse método é chamado apenas para conferência, serve apenas para teste
			imprimeGrafoNoConsole(grafo);
		} else {
			System.out.println("O caminho do arquivo ou o arquivo informado é inválido.");
		}
		// gerar arquivo texto com: número de vértices, número de arestas e sequência de grau do grafo
		String qtdArestas = Util.getQtdArestas(grafo);
		String seqGrau = Util.getSeqGraus(grafo);

		String result = "";
		result += "Nº de vértices: " + qtdVertices + " | \n";
		result += "Nº de arestas: " + qtdArestas + " | \n";
		result += "Sequência de grau: " + seqGrau;
		System.out.println(result);
		String nomeArquivoResult = Util.escreveArquivo(caminhoArquivo, result);
		System.out.println("Arquivo salvo com sucesso! Se encontra no caminho: "+nomeArquivoResult);
		new BuscaProfundidade().buscaEmProfundidade(grafo.get("4"));
	}


	/***
	 * Método apenas para imprimir no console as informações do grafo
	 * 
	 * @param grafo
	 */
	private static void imprimeGrafoNoConsole(Map<String, Vertice> grafo) {
		grafo.forEach((k, v) -> {
			String listaAdjacentes = "";
			if (v.getVizinhos().size() > 0) {
				for (Vertice vizinho : v.getVizinhos()) {
					listaAdjacentes += " " + vizinho.getValor();
				}
			}
			System.out.println("vertice: " + k + " grau "+v.getGrau()+ " - adjacentes: " + listaAdjacentes);
		});
	}
}
