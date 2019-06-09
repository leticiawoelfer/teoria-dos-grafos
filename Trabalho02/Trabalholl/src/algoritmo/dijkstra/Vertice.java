package algoritmo.dijkstra;

import java.util.ArrayList;


/**
 * 
 * @author gabriela.sena, leticia.woelfer
 *
 */
public class Vertice {
	
	private int distancia = Integer.MAX_VALUE;
	private boolean visitado = false;
	private int codigo;
	private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
	
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
}
