package grafos.algoritmo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prim {
	
	private int distancia;
	private Vertice pai;
	private boolean executado;
	
	public Prim() { }
	
	public Prim(int distancia, Vertice pai, boolean executado) {
		super();
		this.distancia = distancia;
		this.pai = pai;
		this.executado = executado;
	}

	public static void main(String[] args) throws IOException {
		new Prim().processar();
	}

	public void processar() throws IOException {
		List<String> linhas = Files.readAllLines(Paths.get("c:\\temp\\entrada.in"));
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
		int somaArvoreGeradoraMinima = prim(grafo);

		System.out.println("Entrada\n" + conteudoArquivo);
		System.out.println("Saída\n" + somaArvoreGeradoraMinima);
	}

	private int prim(Grafo grafo) {
		List<Vertice> vertices = Arrays.asList(grafo.getVertices());
		Map<Integer, Prim> tabelaPrim = new HashMap<>();
		for (Vertice v : vertices) {
			tabelaPrim.put(v.getCodigo(), new Prim(0, null, false));
		}		
		int proxMenor = 0;
		for (Vertice vOrigem : vertices) {
			Prim item = tabelaPrim.get(proxMenor);
			item.executado = true;
			tabelaPrim.put(vOrigem.getCodigo(), item);
			System.out.println("Executando vértice: "+vOrigem.getCodigo());
			for (Aresta a : vOrigem.getArestas()) {
				Prim adjacente = tabelaPrim.get(a.getDestino());
				if (!adjacente.executado) {
					tabelaPrim.put(a.getDestino(), new Prim(a.getPeso(), vOrigem, false));
					String s1 = "",s2 = "",s3 = "";
					for (Map.Entry<Integer, Prim> itemm : tabelaPrim.entrySet()) {
						s1 = s1+itemm.getValue().distancia+"   ";
						s2 = itemm.getValue().pai == null ? s2+"n   " : s2+itemm.getValue().pai.getCodigo()+"   ";
						s3 = s3+itemm.getValue().executado+"   ";
					}				
					System.out.println(s1);
					System.out.println(s2);
					System.out.println(s3+"\n");
				}							
			}
			proxMenor = getProximoMenor(tabelaPrim);
		}
		int soma = 0;
		for (Map.Entry<Integer, Prim> item : tabelaPrim.entrySet()) {
			soma = soma + item.getValue().distancia;
		}
		
		return soma;
	}

	private int getProximoMenor(Map<Integer, Prim> tabelaPrim) {
		int menorDist = Integer.MAX_VALUE;
		int proxMenor = 0;
		for (Map.Entry<Integer, Prim> item : tabelaPrim.entrySet()) {
			if (!item.getValue().executado) {
				if (item.getValue().distancia < menorDist) {
					menorDist = item.getValue().distancia;
					proxMenor = item.getKey();
				}
			}
		}		
		return proxMenor;
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
