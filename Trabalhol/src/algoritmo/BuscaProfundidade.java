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
		System.out.println("Iniciando busca em profundidade pelo v�rtice "+vertice.getValor());
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
		System.out.println("Informe o caminho do arquivo com as informa��es do grafos: (sem valida��es)");
		Scanner sc = new Scanner(System.in);
		String caminhoArquivo = sc.nextLine();
		Map<String, Vertice> grafo = new HashMap<>();
		int qtdVertices = 0;
		if (!caminhoArquivo.isEmpty()) {
			qtdVertices = Util.montaGrafo(caminhoArquivo, grafo);
			// esse m�todo � chamado apenas para confer�ncia, serve apenas para teste
			imprimeGrafoNoConsole(grafo);
		} else {
			System.out.println("O caminho do arquivo ou o arquivo informado � inv�lido.");
		}
		// gerar arquivo texto com: n�mero de v�rtices, n�mero de arestas e sequ�ncia de grau do grafo
		String qtdArestas = Util.getQtdArestas(grafo);
		String seqGrau = Util.getSeqGraus(grafo);

		String result = "";
		result += "N� de v�rtices: " + qtdVertices + " | \n";
		result += "N� de arestas: " + qtdArestas + " | \n";
		result += "Sequ�ncia de grau: " + seqGrau;
		System.out.println(result);
		String nomeArquivoResult = Util.escreveArquivo(caminhoArquivo, result);
		System.out.println("Arquivo salvo com sucesso! Se encontra no caminho: "+nomeArquivoResult);
		new BuscaProfundidade().buscaEmProfundidade(grafo.get("4"));
	}


	/***
	 * M�todo apenas para imprimir no console as informa��es do grafo
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
