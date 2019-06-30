package grafos.algoritmo;

import java.util.ArrayList;

/**
 * 
 * @author gabriela.sena, leticia.woelfer
 *
 */
public class Kruskal {

}

class Aresta {
	
	private int origem;
	private int destino;
	private int peso;

	public Aresta(int origem, int destino, int peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}

	public int getOrigem() {
		return origem;
	}

	public int getDestino() {
		return destino;
	}

	public int getPeso() {
		return peso;
	}

	public int getVizinho(int atual) {
		if (this.destino == atual) {
			return this.origem;
		} else {
			return this.destino;
		}
	}
}

class Grafo {

	private Vertice[] vertices;
	private int qtdVertice;
	private int qtdAresta;

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

class Vertice implements Comparable<Vertice>{
	
	private int distancia = Integer.MAX_VALUE;
	private boolean visitado = false;
	private int codigo;
	private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	private Vertice pai;
	
	public Vertice(int codigo) {
		this.codigo = codigo;
	}	

	public int getCodigo() {
		return codigo;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public void setArestas(ArrayList<Aresta> arestas) {
		this.arestas = arestas;
	}

	public Vertice getPai() {
		return pai;
	}

	public void setPai(Vertice pai) {
		this.pai = pai;
	}

	@Override
	public int compareTo(Vertice vertice) {
		if (this.getDistancia() < vertice.getDistancia())
			return -1;
		else if (this.getDistancia() == vertice.getDistancia())
			return 0;
		return 1;
	}
	
}


