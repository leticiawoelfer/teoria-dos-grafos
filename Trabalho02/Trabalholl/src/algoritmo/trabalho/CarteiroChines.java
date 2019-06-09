package algoritmo.trabalho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmo.dijkstra.Aresta;
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
	
	// A - 0, C - 1, D - 2, E - 3, F - 4, G - 5, H - 6, I - 7, J - 8, M - 9, N - 10, P - 11. 
	
	public static void main(String[] args) {
		Aresta[] arestas = {
				// Origem, Destino, Custo
				new Aresta(0, 1, 1), // A - C 
				new Aresta(0, 5, 1), // A - G 
				new Aresta(1, 5, 1), // C - G
				new Aresta(1, 2, 1), // C - D
				new Aresta(2, 3, 1), // D - E
				new Aresta(2, 6, 1), // D - H
				new Aresta(3, 8, 1), // E - J
				new Aresta(3, 4, 1), // E - F
				new Aresta(4, 10, 1), // F - N
				new Aresta(10, 9, 1), // N - M
				new Aresta(9, 11, 1), // M - P
				new Aresta(9, 8, 1), // M - J
				new Aresta(8, 6, 1), // J - H
				new Aresta(7, 11, 1), // I - P
				new Aresta(7, 6, 1), // I - H
				new Aresta(7, 5, 1), // I - G
		};
		
		Grafo grafo = new Grafo(arestas);
		grafo.calcularDistancia(ORIGEM);
		System.out.println("O menor caminho para o destino '" + 11 + "' é: "+ grafo.getCaminhoMinimo(11));
		matrizCusto(grafo);
	}

	
	public void processar() {
		
	}
	

	protected List<Vertice> grauImpar(Grafo grafo) {
		List<Vertice> impares = new ArrayList<>();
		for (Vertice vertice : grafo.getVertices())
			if (vertice.getArestas().size() % 2 > 0)
				impares.add(vertice);
		return impares;
	}

	public static void matrizCusto(Grafo grafo) {
		Map<Vertice, Vertice> matrizCusto = new HashMap<>();
		List<Vertice> impares = Arrays.asList(grafo.getVertices());
		List<Vertice> aux = impares;
		for (Vertice origin : impares) {
			for (Vertice destination : aux) {
				grafo.calcularDistancia(origin.getCodigo());
				matrizCusto.put(origin, grafo.getVerticeComCaminhoMinimo(destination.getCodigo()));
			}
		}
		for(Map.Entry<Vertice,Vertice> road : matrizCusto.entrySet()){
			Vertice vertice = road.getKey();
			System.err.println("Vertice:"+road.getKey().getCodigo());
				System.out.println("Distancia:"+ road.getValue().getDistancia()+" entre ["+vertice.getCodigo()+" e "+road.getValue().getCodigo()+"]");
		}
	}
	
}
