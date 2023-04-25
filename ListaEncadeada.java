package aeroporto;


public class ListaEncadeada {
private Nodo inicio;


public ListaEncadeada() {
	inicio = null;
}

public void adicionar(Aviao aviao) {
	Nodo novoNodo = new Nodo(aviao);
	if (inicio == null) {
		inicio = novoNodo;
	} else {
		Nodo atual = inicio;
		while (atual.proximo != null) {
			atual = atual.proximo;
		}
		atual.proximo = novoNodo;
	}
}

public Aviao remover() {
	if (inicio == null) {
		return null;
	}
	Aviao aviao = inicio.aviao;
	inicio = inicio.proximo;
	return aviao;
}

public boolean estaVazia() {
	return inicio == null;
}
}