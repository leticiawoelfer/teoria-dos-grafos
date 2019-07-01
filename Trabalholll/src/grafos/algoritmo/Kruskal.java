package grafos.algoritmo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author gabriela.sena, leticia.woelfer
 *
 */
/*public class Kruskal {

	public static void main(String[] args) throws IOException {
		new Kruskal().processar();
	}

	public final String caminhoArquivo = "c:\\temp\\entrada.in";

	public void processar() throws IOException {
		List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
		boolean primeiraLinha = true;
		List<Aresta> arestas = new ArrayList<>();
		String conteudoArquivo = "";
		for (String conteudoLinha : linhas) {
			String linha[] = conteudoLinha.trim().split(" ");
			if (primeiraLinha) {
				if (linha.length >= 3) {
					System.err.println("Arquivo inválido. A primeira linha deve conter 3 caracters");
					return;
				}
				primeiraLinha = false;
				conteudoArquivo += conteudoLinha + "\n";
				continue;
			}

			if (linha.length >= 4) {
				System.err.println("Conteudo da linha inválido, deve conter 3 "
						+ "posições contendo 'Vertice Vertice Valor'");
				return;
			}

			int verticeOrigem = Integer.parseInt(linha[0]);
			int verticeDestino = Integer.parseInt(linha[1]);

			int valor = Integer.parseInt(linha[2]);
			Aresta edge = new Aresta(verticeOrigem, verticeDestino, valor);
			arestas.add(edge);
			conteudoArquivo += conteudoLinha + "\n";
		}
		Grafo grafo = new Grafo(arestas.toArray(new Aresta[arestas.size()]));
		int somaArvoreGeradoraMinima = kruskal(grafo, arestas);

		System.out.println("Entrada\n" + conteudoArquivo);
		System.out.println("Saída\n" + somaArvoreGeradoraMinima);
	}

	private int kruskal(Grafo grafo, List<Aresta> listaArestas) {
		List<Vertice> listaVertices = Arrays.asList(grafo.getVertices());
		ConjuntoDisjunto conjuntoDisjunto = new ConjuntoDisjunto(listaVertices);
	    int somaMenorCaminho = 0;
	    
	    //Ordena crescentemente as arestas de acordo com o valor
	    Collections.sort(listaArestas);
	    System.out.println("listaArestas: "+listaArestas.toString());
		for (Aresta aresta : listaArestas) {
			Vertice origem = listaVertices.get(aresta.getOrigem());
			Vertice destino = listaVertices.get(aresta.getDestino());
			//se origem e destino nao sao do mesmo conjunto, então unir e somar o valor
			if (conjuntoDisjunto.procurar(origem) != conjuntoDisjunto.procurar(destino)) {
				conjuntoDisjunto.unir(origem, destino);
				somaMenorCaminho = somaMenorCaminho + aresta.getPeso();
			}
		}
		return somaMenorCaminho;
	}
}

class Aresta implements Comparable<Aresta> {

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

	@Override
	public int compareTo(Aresta aresta) {
		// 0:date1 == date2 , 1:date1 > date2, -1:date1 < date2
		if (this.getPeso() < aresta.getPeso())
			return -1;
		else if (this.getPeso() == aresta.getPeso())
			return 0;
		return 1;
	}
	
	@Override
	public String toString() {		
		return getOrigem()+","+getDestino()+"("+getPeso()+")";
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

	public Vertice[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertice[] vertices) {
		this.vertices = vertices;
	}

}

class Vertice {

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

	public int getGrau() {
		return arestas.size();
	}

}

class No {
	int grau;
	int indice;
	No pai;

	public No(int grau, int id, No pai) {
		this.grau = grau;
		this.indice = id;
		this.pai = pai;
	}
}

class ConjuntoDisjunto {

	List<Vertice> listaVerts;

	public ConjuntoDisjunto(List<Vertice> vertices) {
		this.listaVerts = vertices;
	}

	public int procurar(Vertice vertice) {
		while (vertice.getPai() != null)
			vertice = vertice.getPai();

		return vertice.getCodigo();
	}

	public void unir(Vertice origem, Vertice destino) {
		int codigoOrigem = procurar(origem);
		int codigoDestino = procurar(destino);

		// Vertices são iguais, logo do mesmo conjunto
		if (codigoOrigem == codigoDestino)
			return;

		// Busca os vértices pais na lista de vértices
		Vertice vertA = getVertice(codigoOrigem);
		Vertice vertB = getVertice(codigoDestino);

		// Concatena o menor grafo no maior grafo
		try {
			if (vertA.getGrau() < vertB.getGrau()) {
				vertA.setPai(vertB);
			} else if (vertA.getGrau() > vertB.getGrau()) {
				vertB.setPai(vertA);
			} else {
				vertB.setPai(vertA);
				// vertA.getGrau()++; //nao entendi isso aqui
			}
		} catch (NullPointerException e) {
			System.err.println("Ocorreu um erro! O algoritmo não encontrou o vértice desejado na lista de vértices.");
		}
		
	}

	private Vertice getVertice(int codigo) {
		for (Vertice vertice : listaVerts) {
			if (vertice.getCodigo() == codigo) {
				return vertice;
			}
		}
		return null;
	}
}
*/