package algoritmo.dijkstra;

import java.util.ArrayList;

/**
 * 
 * @author gabriela.sena, leticia.woelfer
 *
 */
public class Grafo {

	private Vertice[] vertices;
	private int qtdVertice;
	private int qtdAresta;
	private final int ORIGEM = 0;

	public Grafo(Aresta[] aresta) {
		this.qtdVertice = calculaQtdVertice(aresta);
		this.vertices = new Vertice[this.qtdVertice];
		for (int n = 0; n < this.qtdVertice; n++) {
			this.vertices[n] = new Vertice(n);
		}
		this.qtdAresta = aresta.length;
		for (int verticeParaEqualizar = 0; verticeParaEqualizar < this.qtdAresta; verticeParaEqualizar++) {
			this.vertices[aresta[verticeParaEqualizar].getOrigem()].getArestas().add(aresta[verticeParaEqualizar]);
			this.vertices[aresta[verticeParaEqualizar].getDestino()].getArestas().add(aresta[verticeParaEqualizar]);
		}
	}

	public void calcularDistancia(int origem) {
		this.vertices[origem].setDistancia(0);
		int proximoVertice = origem;

		for (int i = 0; i < this.vertices.length; i++) {
			ArrayList<Aresta> arestasAtuais = this.vertices[proximoVertice].getArestas();
			for (int vertice = 0; vertice < arestasAtuais.size(); vertice++) {
				int visinho = arestasAtuais.get(vertice).getVizinho(proximoVertice);
	
				if (!this.vertices[visinho].isVisitado()) { /**/
					int tentative = this.vertices[proximoVertice].getDistancia() + arestasAtuais.get(vertice).getPeso();
					if (tentative < vertices[visinho].getDistancia()) {
						vertices[visinho].setDistancia(tentative);
					}
				}
			}
			
			vertices[proximoVertice].setVisitado(true);
			proximoVertice = getMenorCaminho();
		}
	}

	private int getMenorCaminho() {
		int indice = 0;
		int distancia = Integer.MAX_VALUE;
		for (int i = 0; i < this.vertices.length; i++) {
			int distanciaAtual = this.vertices[i].getDistancia();
			if (!this.vertices[i].isVisitado() && distanciaAtual < distancia) {
				distancia = distanciaAtual;
				indice = i;
			}
		}
		return indice;
	}
	
	public int getCaminhoMinimo(int destino) {
		return this.vertices[destino].getDistancia();
	}
	
	public Vertice getVerticeComCaminhoMinimo(int destino) {
		return this.vertices[destino];
	}
	
	private int calculaQtdVertice(Aresta[] arestas) {
		int vertice = 0;
		for (Aresta aresta : arestas) {
			if (aresta.getDestino() > vertice)
				vertice = aresta.getDestino();
			if (aresta.getOrigem() > vertice)
				vertice = aresta.getOrigem();
		}
		vertice++;
		return vertice;
	}


	public String getResultado() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Quantidade de vertices: %s. Quantidade de arestas: %s", this.qtdVertice, this.qtdAresta)).append("\n");
		for (int i = 0; i < this.vertices.length; i++) {
			sb.append(String.format("Distancia entre origem 0 e destino '%s' : '%s'", i, vertices[i].getDistancia())).append("\n");
		}
		return sb.toString();
	}

	public Vertice[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertice[] vertices) {
		this.vertices = vertices;
	}
	
	
}