package aeroporto;

public class Fila {
	public Nodo inicio_fila;
	public int quantidadeAvioes;
	public Pista pista;

	public Fila(Pista pista) {
		this.quantidadeAvioes = 0;
		this.inicio_fila = null;
		this.pista = pista;
	}

	public boolean filaVazia() {
		if (this.inicio_fila == null) {
			return true;
		} else {
			return false;
		}
	}

	public void adicionarFinal(Aviao aviao) {
		Nodo novo = new Nodo(aviao);
		this.quantidadeAvioes++;
		if (filaVazia()) {
			this.inicio_fila = novo;
		} else {
			Nodo auxiliar = this.inicio_fila;
			while (auxiliar.proximo != null) {
				auxiliar = auxiliar.proximo;
			}
			auxiliar.proximo = novo;
		}
	}

	public Nodo removerInicio() {
		Nodo removido = null;
		if (filaVazia()) {
			System.out.println(" ");
			// fila vazia
		} else {
			removido = this.inicio_fila;
			this.inicio_fila = inicio_fila.proximo;
			this.quantidadeAvioes--;
		}
		return removido;
	}

	public void passarTempo() {
		Nodo auxiliar = this.inicio_fila;
		while (auxiliar != null) {
			auxiliar.aviao.combustivel = auxiliar.aviao.combustivel - 1;
			auxiliar.aviao.tempoEspera = auxiliar.aviao.tempoEspera + 1;
			auxiliar = auxiliar.proximo;
		}
	}

	public void printFila() {
		Nodo auxiliar = this.inicio_fila;
		System.out.println();
		System.out.println("Quantidade Aviões na fila: " + this.quantidadeAvioes);
		while (auxiliar != null) {
			System.out.println();
			System.out.println("Avião Id: " + auxiliar.aviao.id + " Combustivel: " + auxiliar.aviao.combustivel
					+ " Tempo de Espera: " + auxiliar.aviao.tempoEspera);
			auxiliar = auxiliar.proximo;
		}
	}

	public int verificarTemAviaoComPoucoCombustivel() {
		int quantidadeAvioesPoucoCombustivel = 0;
		Nodo auxiliar = this.inicio_fila;
		while (auxiliar != null) {
			if (auxiliar.aviao.poucoCombustivelAviao()) {
				quantidadeAvioesPoucoCombustivel++;
			}
			auxiliar = auxiliar.proximo;
		}
		return quantidadeAvioesPoucoCombustivel;
	}

	// usado pra colocar o avião na frente da fila
	public void adicionarInicio(Aviao aviao) {
		this.adicionarFinal(aviao);
		for (int i = 0; i < this.quantidadeAvioes - 1; i++) {
			Nodo apoio = this.removerInicio();
			this.adicionarFinal(apoio.aviao);
		}
	}

	public void colocarAviaoFrente() {
		if (this.verificarTemAviaoComPoucoCombustivel() > 0) {
			// o maximo de avioes com pouco combustivel que pode ter são 2
			// por isso podem ser adiantados até 2 aviões (caso tenham 2 com 1 de
			// combustível)
			Nodo AviaoPoucoCombustivel1 = this.inicio_fila;
			Nodo AviaoPoucoCombustivel2 = this.inicio_fila;
			int contadorAvioesPoucoCombustivel = 0;

			Nodo auxiliar = this.inicio_fila;
			for (int i = 0; i <= quantidadeAvioes; i++) {
				auxiliar = this.removerInicio();
				if (!auxiliar.aviao.poucoCombustivelAviao()) {
					this.adicionarFinal(auxiliar.aviao);
				} else {
					switch (contadorAvioesPoucoCombustivel) {
					case 0:
						AviaoPoucoCombustivel1 = auxiliar;
						contadorAvioesPoucoCombustivel++;
						break;
					case 1:
						AviaoPoucoCombustivel2 = auxiliar;
						contadorAvioesPoucoCombustivel++;
						break;
					}
				}
				auxiliar = auxiliar.proximo;
			}
			this.adicionarInicio(AviaoPoucoCombustivel1.aviao);
			if (contadorAvioesPoucoCombustivel > 1) {
				this.adicionarInicio(AviaoPoucoCombustivel2.aviao);
			}
		}
	}
}
