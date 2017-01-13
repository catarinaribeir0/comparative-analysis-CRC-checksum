/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.util.Random;
import java.util.Scanner;

/**
 * Programa simulador do Checksum de 8 bits.
 *
 * @author Catarina Pereira Ribeiro
 * @author Guilherme Cler da Silva
 * @author Leonardo de Melo Cavalcante
 * @author Victor Meireles Bandeira de Melo
 */
public class SimuladorChecksum {

    public static void main(String[] args) {
//        System.out.println(Integer.toHexString(Checksum8.gerarChecksum("G".getBytes(), 1)));
        int tamPacote, qtPacote, seed;
        float probabilidade;
        int colisoes = 0;
        int checksum = 0;
        int checksumCorrompido = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Informe o tamanho dos pacotes em bytes: ");
        tamPacote = in.nextInt();
        System.out.print("Informe o numero de pacotes a serem gerados: ");
        qtPacote = in.nextInt();
        System.out.print("Informe a probabilidade de alteração da mensagem (0 < p <= 1): ");
        probabilidade = in.nextFloat();
        System.out.print("Informe o valor a ser utilizado como semente dos números aleatórios: ");
        seed = in.nextInt();

        Random gerador = new Random(seed);

        long tempoInicial = System.currentTimeMillis();
        for (int i = 0; i < qtPacote; i++) {
            byte[] pacote = Mensagem.gerarMensagem(tamPacote, gerador);
            checksum = Checksum8.gerarChecksum(pacote, tamPacote);
            Mensagem.corromperMensagem(pacote, probabilidade, gerador);
            checksumCorrompido = Checksum8.gerarChecksum(pacote, tamPacote);

            if (checksum == checksumCorrompido) {
                colisoes++;
            }
        }
        long tempoFinal = System.currentTimeMillis();
        
        System.out.println("Tempo de execucao em milissegundos: " + (tempoFinal - tempoInicial));
        System.out.println("Colisoes: " + colisoes);
        System.out.printf("Porcentagem de colisoes: %.8f%% \n", ((double) colisoes / qtPacote) * 100);
    }
}