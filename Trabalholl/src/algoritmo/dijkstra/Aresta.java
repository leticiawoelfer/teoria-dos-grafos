package algoritmo.dijkstra;

/**
 * 
 * @author gabriela.sena, leticia.woelfer
 *
 */
public class Aresta {
	
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