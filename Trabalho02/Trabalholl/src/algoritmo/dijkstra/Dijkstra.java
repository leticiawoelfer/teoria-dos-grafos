package algoritmo.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
	
	private List<Vertice> menorCaminho = new ArrayList<>();
	private List<Vertice> naoVisitados = new ArrayList<>();
	private Vertice verticeCaminho;
	private Vertice atual;
	private Vertice vizinho;

	public List<Vertice> menorCaminho(Grafo grafo, Vertice origin, Vertice destination) {
		menorCaminho.clear();
		naoVisitados.clear();
		menorCaminho.add(origin); // Adiciona a origem na lista do menor caminho
		for (Vertice vertice : grafo.getVertices()) {
			if (vertice.equals(origin))
				vertice.setDistancia(0);
			else
				vertice.setDistancia(9999);
			naoVisitados.add(vertice);
		}
		Collections.sort(naoVisitados);
		while (!naoVisitados.isEmpty()) {
			atual = naoVisitados.get(0);
			for (int x = 0; x < atual.getArestas().size(); x++) {
				int destino = atual.getArestas().get(x).getDestino();
				vizinho = grafo.getVertices()[destino];
				if (!vizinho.isVisitado()) {
					if (vizinho.getDistancia() > (atual.getDistancia() + atual.getArestas().get(x).getPeso())) {
						vizinho.setDistancia(atual.getDistancia() + atual.getArestas().get(x).getPeso());
						vizinho.setPai(atual);
						if (vizinho.getDistancia() > (atual.getDistancia() + atual.getArestas().get(x).getPeso())) {
							vizinho.setDistancia(atual.getDistancia() + atual.getArestas().get(x).getPeso());
							vizinho.setPai(atual);

							if (vizinho.equals(destination)) {
								menorCaminho.clear();
								verticeCaminho = vizinho;
								menorCaminho.add(vizinho);
								while (verticeCaminho.getPai() != null) {
									menorCaminho.add(verticeCaminho.getPai());
									verticeCaminho = verticeCaminho.getPai();
								}
								// Ordena a lista do menor caminho, para que ele
								// seja exibido da origem ao destino.
								Collections.sort(menorCaminho);
							}
						}
					}
				}
			}
			atual.setVisitado(true);
			naoVisitados.remove(atual);
			Collections.sort(naoVisitados);
		}
		return menorCaminho;
	}

}
