package aeroporto;

public class Aviao {
	public int id;
	public int combustivel;
	public int tempoEspera;

	public Aviao(int id, int combustivel) {
		this.id = id;
		this.combustivel = combustivel;
		this.tempoEspera = 5;
	}

	public boolean poucoCombustivelAviao() {
		if (combustivel > 1) {
			return false;
		} else {
			return true;
		}
	}
}
