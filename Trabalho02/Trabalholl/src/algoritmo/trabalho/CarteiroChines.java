package algoritmo.trabalho;

import java.awt.font.GraphicAttribute;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmo.dijkstra.Aresta;
import algoritmo.dijkstra.Dijkstra;
import algoritmo.dijkstra.Grafo;
import algoritmo.dijkstra.Vertice;

/**
 * 
 * @author gabriela.sena, leticia.woelfer
 * Item 2 do trabalho 2
 *
 */
public class CarteiroChines {

	public static final int ORIGEM = 0;
	private Dijkstra dijkstra;
	private Map<Vertice, List<Vertice>> matrizCusto = new HashMap<>();

	public CarteiroChines() {
		super();
		dijkstra = new Dijkstra();
	}
	// A - 0, B - 1, C - 2, D - 3, E - 4, F - 5, G - 6, H - 7, I - 8, J - 9, K - 10,
	// L - 11, M - 12, N - 13, O - 14, P - 15

	public static void main(String[] args) {
		Aresta[] arestas = {
				// Origem, Destino, Custo
				new Aresta(0, 1, 8), // A - B
				new Aresta(0, 3, 4), // A - D
				new Aresta(1, 10, 1), // B - K
				new Aresta(1, 2, 2), // B - C
				new Aresta(2, 3, 3), // C - D
				new Aresta(2, 9, 2), // C - J
				new Aresta(2, 5, 1), // C - F
				new Aresta(3, 4, 3), // D - E
				new Aresta(4, 6, 7), // E - G
				new Aresta(4, 5, 2), // E - F
				new Aresta(5, 7, 6), // F - H
				new Aresta(6, 7, 9), // G - H
				new Aresta(7, 8, 1), // H - I
				new Aresta(8, 14, 1), // I - O
				new Aresta(8, 9, 8), // I - J
				new Aresta(9, 15, 1), // J - P
				new Aresta(9, 10, 3), // J - K
				new Aresta(10, 11, 2), // K - L
				new Aresta(11, 12, 4), // L - M
				new Aresta(11, 15, 3), // L - P
				new Aresta(12, 13, 12), // M - N
				new Aresta(13, 14, 3), // N - O
				new Aresta(14, 15, 7) // O - P
		};

		Grafo grafo = new Grafo(arestas);
		CarteiroChines carteiroChines = new CarteiroChines();
		carteiroChines.matrizCusto(grafo);
		//grafo.calcularDistancia(ORIGEM);
		//System.out.println("O menor caminho para o destino '" + 11 + "' é: " + grafo.getCaminhoMinimo(11));
	}
	

	public void processar() throws IOException {
	
	}

	protected static List<Vertice> grauImpar(Grafo grafo) {
		List<Vertice> impares = new ArrayList<>();
		for (Vertice vertice : grafo.getVertices())
			if (vertice.getArestas().size() % 2 > 0)
				impares.add(vertice);
		return impares;
	}

	public static void matrizCusto(Grafo grafo) {
		Vertice[][] matrizCusto = new Vertice[grafo.getVertices().length][grafo.getVertices().length];
		List<Vertice> vertices = Arrays.asList(grafo.getVertices());
		List<Vertice> impares = grauImpar(grafo);
		Dijkstra dj = new Dijkstra();
		for (Vertice origin : impares) {
			for (Vertice destination : vertices) {
				if (origin != destination) {
					dj.menorCaminho(grafo, origin, destination);
				}
			}//incompleto - dijkstra ainda com erros, não conseguimos seguir daqui
		}
	}

}
