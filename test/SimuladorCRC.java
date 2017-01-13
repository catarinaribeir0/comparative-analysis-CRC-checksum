/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import trabalho.*;
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
public class SimuladorCRC {

    public static void main(String[] args) throws FileNotFoundException, IOException {
//        System.out.println(Integer.toHexString(Checksum8.gerarChecksum("G".getBytes(), 1)));
        int colisoes = 0;
        int crc = 0;
        int crcCorrompido = 0;
//        Scanner in = new Scanner(System.in);
//        System.out.print("Informe o tamanho dos pacotes em bytes: ");
//        tamPacote = in.nextInt();
//        System.out.print("Informe o numero de pacotes a serem gerados: ");
//        qtPacote = in.nextInt();
//        System.out.print("Informe a probabilidade de alteração da mensagem (0 < p <= 1): ");
//        probabilidade = in.nextFloat();
//        System.out.print("Informe o valor a ser utilizado como semente dos números aleatórios: ");
//        seed = in.nextInt();

        int tamPacote = 0, qtPacote = 20000, seed = 0, polinomio = 0xC4; //Dec = 196
        float probabilidade = 0.1f;

        OutputStream output = new FileOutputStream("D:\\Guilherme\\Desktop\\CRC.txt");
        OutputStreamWriter writer = new OutputStreamWriter(output);
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write("qtPacote;tamPacote;probabilidade;seed;polinomio;tempoTotal;colisoes\r\n");

        Random r = new Random(1023);
        int cont = 1;
        for (int i = 0; i < 5; i++) {
            seed = r.nextInt(10000) - 200;
            tamPacote = 0;
            Random gerador = new Random(seed);
            for (int j = 0; j < 3; j++) {
                probabilidade = 0.1f;
                tamPacote += 500;
                for (int k = 0; k < 3; k++) {
                    if (k > 0) {
                        probabilidade += 0.4f;
                    }
                    for (int l = 0; l < 3; l++) {
                        switch (l) {
                            case 0:
                                polinomio = 0x23;
                                break;
                            case 1:
                                polinomio = 0xAB;
                                break;
                            case 2:
                                polinomio = 0xF4;
                                break;
                            default:
                                break;
                        }
                        colisoes = 0;
                        System.out.println("Execução " + cont);
                        long tempoInicial = System.currentTimeMillis();
                        for (int m = 0; m < qtPacote; m++) {
                            crc = 0;
                            crcCorrompido = 0;
                            byte[] pacote = Mensagem.gerarMensagem(tamPacote, gerador);
                            if (tamPacote == 1500) {
                                cont++;
                                cont--;
                            }
                            byte[] corrompido = pacote.clone();
                            crc = CRC8.gerarCRC(pacote, tamPacote, polinomio);
                            Mensagem.corromperMensagem(corrompido, probabilidade, gerador);
                            crcCorrompido = CRC8.gerarCRC(corrompido, tamPacote, polinomio);
                            
                            if (crc == crcCorrompido) {
                                //System.out.println("colisao");
                                colisoes++;
                            }
                        }
                        long tempoTotal = System.currentTimeMillis() - tempoInicial;
                        bw.write(qtPacote + ";" + tamPacote + ";" + probabilidade
                                + ";" + seed + ";" + Integer.toHexString(polinomio).toUpperCase() + ";" + tempoTotal + ";" + colisoes + "\r\n");
                        cont++;
                    }
                }

            }

        }
        bw.close();
//        System.out.println("Tempo de execucao em milissegundos: " + (tempoFinal - tempoInicial));
//        System.out.println("Colisoes: " + colisoes);
//        System.out.printf("Porcentagem de colisoes: %.8f%% \n", ((double) colisoes / qtPacote) * 100);
    }
}
