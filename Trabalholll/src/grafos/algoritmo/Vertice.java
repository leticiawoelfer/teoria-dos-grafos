package grafos.algoritmo;

import java.util.ArrayList;

/**
 * 
 * @author gabriela.sena, leticia.woelfer
 *
 */
public class Vertice implements Comparable<Vertice>{
	
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
