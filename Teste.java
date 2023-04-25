package aeroporto;

import java.util.Scanner;

public class Teste {
    public static void main(String[] args) throws Erro {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a quantidade de loops de ciclos que deseja: ");
        int quantidadeLoops = scanner.nextInt();
        System.out.println("Iniciando " + quantidadeLoops + " loops de ciclos.");

        Airport aeroporto = new Airport();
        for (int i = 0; i < quantidadeLoops; i++) {
            aeroporto.passarTempo();
        }
    }
}