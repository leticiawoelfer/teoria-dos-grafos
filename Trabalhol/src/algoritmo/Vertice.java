package algoritmo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Gabriela Sena e Leticia Woelfer
 *
 */
public class Vertice {
	
		private String valor;
		private boolean encontrado;
		private int grau;
		private List<Vertice> vizinhos = new ArrayList<Vertice>();
		
		public Vertice() { }
		
		public Vertice(String valor) {
			this.valor = valor;
		}
		
		public String getValor() {
			return valor;
		}
		
		public void setValor(String valor) {
			this.valor = valor;
		}
		
		public boolean isEncontrado() {
			return encontrado;
		}
		
		public void setEncontrado(boolean encontrado) {
			this.encontrado = encontrado;
		}
		
		public int getGrau() {
			return grau;
		}

		public void setGrau(int grau) {
			this.grau = grau;
		}

		public List<Vertice> getVizinhos() {
			return vizinhos;
		}
		
		public void setVizinhos(List<Vertice> vizinhos) {
			this.vizinhos = vizinhos;
			this.grau = vizinhos.size();
		}
		
		public void adicionarVizinho(Vertice vertice) {
			adicionaGrau(vertice);
			this.vizinhos.add(vertice);
			this.grau++;
		}
		
		public void adicionaGrau(Vertice vertice) {
			vertice.setGrau(vertice.getGrau()+1);
		}
}
