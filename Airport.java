package aeroporto;

import java.util.ArrayList;
import java.util.Random;

public class Airport {
	Pista pista1;
	Pista pista2;
	Fila filaAterrissagem1;
	Fila filaAterrissagem2;
	Fila filaDecolagem1;
	Fila filaDecolagem2;
	int idAviaoImpares = 1;
	int idAviaoPares = 0;
	ArrayList<Nodo> avioesAterrissagem;
	ArrayList<Nodo> avioesDecolagem;

	public Airport() {
		this.pista1 = new Pista();
		this.pista2 = new Pista();
		this.filaAterrissagem1 = new Fila(pista1);
		this.filaAterrissagem2 = new Fila(pista2);
		this.filaDecolagem1 = new Fila(pista1);
		this.filaDecolagem2 = new Fila(pista2);
		this.avioesAterrissagem = new ArrayList<Nodo>();
		this.avioesDecolagem = new ArrayList<Nodo>();
	}

	public void verFilas() {
		System.out.println("");
		System.out.println("---------------------------------------------");
		System.out.println("CONTEÚDO DE CADA FILA");
		System.out.println("");
		System.out.print("Fila Aterrissagem 1 (Pista 1): ");
		filaAterrissagem1.printFila();
		System.out.println();
		System.out.print("Fila Aterrissagem 2 (Pista 2): ");
		filaAterrissagem2.printFila();
		System.out.println();
		System.out.print("Fila Decolagem 1 (Pista 1): ");
		filaDecolagem1.printFila();
		System.out.print("Fila Decolagem 2 (Pista 2): ");
		filaDecolagem2.printFila();
		System.out.println("---------------------------------------------");
		System.out.println();
	}

	public void verPista() {
		System.out.println("**");
		System.out.println("Pista 1 tá livre para aterrisagem: " + this.pista1.getLivre());
		System.out.println("Pista 2 tá livre para aterrisagem:: " + this.pista2.getLivre());
		System.out.println("Pista 1 tá livre para decolagem: " + this.pista1.getLivre());
		System.out.println("Pista 2 tá livre para decolagem: " + this.pista2.getLivre());
		System.out.println("**");
	}

	public void reduzirCombustivelAumentarTempoEspera() {
		filaAterrissagem1.passarTempo();
		filaAterrissagem2.passarTempo();
		filaDecolagem1.passarTempo();
		filaDecolagem2.passarTempo();
	}

	public void adicionarAvioesFila() {
		// adicionar na fila aterrissagem
		
		Random random = new Random();
		int quantidadeAvioesAterrissar = random.nextInt(2) + 1;
		System.out.println("Foram adicionados " + quantidadeAvioesAterrissar + " aviões nas filas de aterrissagem");
		for (int i = 0; i < quantidadeAvioesAterrissar; i++) {
			int combustivel = random.nextInt(19) + 1;

			Aviao aviao = new Aviao(idAviaoPares, combustivel);
			this.idAviaoPares = this.idAviaoPares + 2;
			
			// conferir a quantidade de avião nas pistas
			int quantidadeAvioesPista1 = this.filaAterrissagem1.quantidadeAvioes;
			int quantidadeAvioesPista2 = this.filaAterrissagem2.quantidadeAvioes;

			if (quantidadeAvioesPista2 >= quantidadeAvioesPista1) {
			    this.filaAterrissagem1.adicionarFinal(aviao);
			} else {
			    this.filaAterrissagem2.adicionarFinal(aviao);
			}

		}
		// adicionar fila decolagem
		int quantidadeAvioesDecolar = random.nextInt(2) + 1;
		System.out.println("Foram adicionados " + quantidadeAvioesDecolar + " aviões na fila de decolagem");
		for (int i = 0; i < quantidadeAvioesDecolar; i++) {
		    Aviao aviao = new Aviao(idAviaoImpares, 20);
		    this.idAviaoImpares = this.idAviaoImpares + 2;
		    if (i % 2 == 0) {
		        this.filaDecolagem1.adicionarFinal(aviao);
		    } else {
		        this.filaDecolagem2.adicionarFinal(aviao);
		    }
		}
	}

	public int verificarEmergencia() {
		int quantidadeEmergencia = 0;
		quantidadeEmergencia += this.filaAterrissagem1.verificarTemAviaoComPoucoCombustivel();
		quantidadeEmergencia += this.filaAterrissagem2.verificarTemAviaoComPoucoCombustivel();
		return quantidadeEmergencia;
	}

	public void pousoEmergencia() throws Erro {
		int quantidadeEmergencia = this.verificarEmergencia();
		int avioesPoucoCombustivelFila1 = this.filaAterrissagem1.verificarTemAviaoComPoucoCombustivel();
		int avioesPoucoCombustivelFila2 = this.filaAterrissagem2.verificarTemAviaoComPoucoCombustivel();

		if (avioesPoucoCombustivelFila1 > 0) {
			this.filaAterrissagem1.colocarAviaoFrente();
			for (int i = 0; i < avioesPoucoCombustivelFila1; i++) {
				this.avioesAterrissagem.add(this.filaAterrissagem1.removerInicio());
			}
		}
		if (avioesPoucoCombustivelFila2 > 0) {
			this.filaAterrissagem2.colocarAviaoFrente();
			for (int i = 0; i < avioesPoucoCombustivelFila2; i++) {
				this.avioesAterrissagem.add(this.filaAterrissagem2.removerInicio());

			}
		}

		if (quantidadeEmergencia > 2) {
		    throw new Erro();
		} else if (quantidadeEmergencia == 1) {
		    this.pista2.pouso();
		} else if (quantidadeEmergencia == 2) {
		    this.pista2.pouso();
		    this.pista1.pouso();
		}
	}

	public void pouso() {
	    // Verificar se a pista 1 está livre e se há aviões nas filas de aterrissagem
	    if (this.pista1.getLivre() && (this.filaAterrissagem1.quantidadeAvioes > 0 || this.filaAterrissagem2.quantidadeAvioes > 0)) {
	        // Selecionar a fila de aterrissagem com mais aviões para aterrissar primeiro
	        if (this.filaAterrissagem1.quantidadeAvioes >= this.filaAterrissagem2.quantidadeAvioes) {
	            this.avioesAterrissagem.add(this.filaAterrissagem1.removerInicio());
	        } else {
	            this.avioesAterrissagem.add(this.filaAterrissagem2.removerInicio());
	        }
	        // Ocupar a pista 1
	        this.pista1.pouso();
	    }

	    // Verificar se a pista 2 está livre e se há aviões nas filas de aterrissagem
	    if (this.pista2.getLivre() && (this.filaAterrissagem1.quantidadeAvioes > 0 || this.filaAterrissagem2.quantidadeAvioes > 0)) {
	        // Selecionar a fila de aterrissagem com mais aviões para aterrissar primeiro
	        if (this.filaAterrissagem1.quantidadeAvioes >= this.filaAterrissagem2.quantidadeAvioes) {
	            this.avioesAterrissagem.add(this.filaAterrissagem1.removerInicio());
	        } else {
	            this.avioesAterrissagem.add(this.filaAterrissagem2.removerInicio());
	        }
	        // Ocupar a pista 2
	        this.pista2.pouso();
	    }
	}


	public void decolagem() {
	    // verificar se a pista está livre
	    if (this.pista1.getLivre()) {
	        if (this.filaDecolagem1.quantidadeAvioes > 0) {
	            this.avioesDecolagem.add(this.filaDecolagem1.removerInicio());
	            this.pista1.decolagem();
	        }
	    }

	    if (this.pista2.getLivre()) {
	        if (this.filaDecolagem2.quantidadeAvioes > 0) {
	            this.avioesDecolagem.add(this.filaDecolagem2.removerInicio());
	            this.pista2.decolagem();
	        }
	    }
	}


	public void printArraylistDecolagem() {
		System.out.println("Tamanho fila decolagem: " + this.avioesDecolagem.size());
		for (int i = 0; i < this.avioesDecolagem.size(); i++) {
			System.out.printf("%d %s", this.avioesDecolagem.get(i).aviao.id, " ");
		}
		System.out.println(" ");
	}

 	public void tempoEsperaDecolagem() {
		if (this.avioesDecolagem.size() > 0) {
			System.out.print("Tempo de espera médio da decolagem: ");
			System.out.println(this.avioesDecolagem.get(0).aviao.tempoEspera);
		} else {
			System.out.println("Tempo de espera médio da decolagem: sem decolagens ");
		}
		this.avioesDecolagem.clear();
	}

	public void tempoEsperaAterrissagem() {
		if (this.avioesAterrissagem.size() > 0) {
			System.out.print("Tempo de espera médio da aterrisagem: ");
			if (this.avioesAterrissagem.size() == 1) {
				System.out.println(this.avioesAterrissagem.get(0).aviao.tempoEspera);
			} else if (this.avioesAterrissagem.size() == 2) {
				System.out.println((this.avioesAterrissagem.get(0).aviao.tempoEspera
						+ this.avioesAterrissagem.get(1).aviao.tempoEspera) / 2);
			} else {
				System.out.println((this.avioesAterrissagem.get(0).aviao.tempoEspera
						+ this.avioesAterrissagem.get(1).aviao.tempoEspera
						+ this.avioesAterrissagem.get(2).aviao.tempoEspera) / 3);
			}
			System.out.println();
		} else {
			System.out.println("Tempo de espera médio da aterrisagem: sem aterrissagens ");
		}
		this.avioesAterrissagem.clear();
	}

	public void passarTempo() throws Erro {
		System.out.println();
		System.out.println("===================== INICIO DO CICLO =======================================");
		System.out.println();
		System.out.println("**** Início Unidade de Tempo ****");
		System.out.println();
		this.reduzirCombustivelAumentarTempoEspera();
		this.adicionarAvioesFila();
		System.out.println("Número de aviões que aterrissam sem reserva de combustível: " + this.verificarEmergencia());
		this.pousoEmergencia();
		this.pouso();
		this.decolagem();
		this.pista1.liberarPista();
		this.pista2.liberarPista();
		System.out.println("Número de aviões que aterrissaram: " + this.avioesAterrissagem.size());
		System.out.println("Número de aviões que decolaram: " + this.avioesDecolagem.size());
		this.verFilas();
		this.tempoEsperaDecolagem();
		this.tempoEsperaAterrissagem();
		System.out.println("***** Fim Unidade de Tempo *****");
		System.out.println();
	}

}
