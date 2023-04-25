package aeroporto;

public class Nodo {
	public Nodo proximo;
	public Aviao aviao;

	public Nodo(Aviao aviao) {
		this.proximo = null;
		this.aviao = aviao;
	}
}
