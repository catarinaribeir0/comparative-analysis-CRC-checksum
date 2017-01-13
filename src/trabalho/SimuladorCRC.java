/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.util.Random;
import java.util.Scanner;

/**
 * Programa simulador do CRC8.
 *
 * @author Catarina Pereira Ribeiro
 * @author Guilherme Cler da Silva
 * @author Leonardo de Melo Cavalcante
 * @author Victor Meireles Bandeira de Melo
 */
public class SimuladorCRC {
    
    public static void main(String[] args) {
        int tamPacote, qtPacote, seed, polinomio;
        float probabilidade;
        int colisoes = 0;
        int crc = 0;
        int crcCorrompido = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Informe o tamanho dos pacotes em bytes: ");
        tamPacote = in.nextInt();
        System.out.print("Informe o numero de pacotes a serem gerados: ");
        qtPacote = in.nextInt();
        System.out.print("Informe a probabilidade de alteração da mensagem (0 < p <= 1): ");
        probabilidade = in.nextFloat();
        System.out.print("Informe o valor a ser utilizado como semente dos números aleatórios: ");
        seed = in.nextInt();
        System.out.print("Informe o valor do polinomio gerador (hexadecimal, grau 8): ");
        polinomio = in.nextInt();
 
        Random gerador = new Random(seed);
 
        long tempoInicial = System.currentTimeMillis();
        for (int i = 0; i < qtPacote; i++) {
            byte[] pacote = Mensagem.gerarMensagem(tamPacote, gerador);
            crc = CRC8.gerarCRC(pacote, tamPacote, polinomio);
            Mensagem.corromperMensagem(pacote, probabilidade, gerador);
            crcCorrompido = CRC8.gerarCRC(pacote, tamPacote, polinomio);
 
            if (crc == crcCorrompido) {
                colisoes++;
            }
        }
        long tempoFinal = System.currentTimeMillis();
        
        System.out.println("Tempo de execucao em milissegundos: " + (tempoFinal - tempoInicial));
        System.out.println("Colisoes: " + colisoes);
        System.out.printf("Porcentagem de colisoes: %.8f%% \n", ((double) colisoes / qtPacote) * 100);
    }
}