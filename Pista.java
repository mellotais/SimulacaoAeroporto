package aeroporto;

public class Pista {
	private Boolean livre;
	private int decolados;
	private int pousados;

	public Pista() {
		this.livre = true;
		this.pousados = 0;
		this.decolados = 0;
	}

	public void pouso() {
		if (this.livre) {
			this.livre = false;
			this.pousados++;
		} else {
			System.out.println("Pista Ocupada!");
		}
	}

	public void decolagem() {
		if (this.livre) {
			this.livre = false;
			this.decolados++;
		} else {
			System.out.println("Pista Ocupada!");
		}
	}

	public void liberarPista() {
		if (!this.livre) {
			this.livre = true;
		} else {
			System.out.println("Pista já Liberada!");
		}
	}

	public boolean getLivre() {
		return this.livre;
	}

	public int getPousados() {
		return this.pousados;
	}

	public int getDecolados() {
		return this.decolados;
	}
}
