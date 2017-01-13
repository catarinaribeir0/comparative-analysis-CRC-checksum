/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.util.Random;

/**
 * Classe que possui métodos para geração de mensagem aleatória
 * e inserção de erros em uma mensagem.
 *
 * @author Catarina Pereira Ribeiro
 * @author Guilherme Cler da Silva
 * @author Leonardo de Melo Cavalcante
 * @author Victor Meireles Bandeira de Melo
 */
public class Mensagem {
    
    /**
    * Não deixa que um objeto do tipo Mensagem seja instanciado.
    */
    private Mensagem() {

    }

    
    /**
    * Gera uma mensagem aleatória de tamanho especificado, dado um gerador de números aleatórios.
    *
    * @param tamanho o tamanho da mensagem a ser gerada.
    * @param gerador o gerador de números aleatórios usado para gerar a mensagem.
    *
    * @return o array de bytes contendo a mensagem gerada.
    */
    public static byte[] gerarMensagem(int tamanho, Random gerador) {
        byte[] mensagem = new byte[tamanho];
        gerador.nextBytes(mensagem);
        return mensagem;
    }
    
    /**
    * Insere ao menos um erro em uma mensagem especificada, dada uma probabilidade b de corrupção
    * da mensagem e um gerador de números aletórios.
    *
    * @param mensagem a mensagem a ser corrompida.
    * @param p a probabilidade da mensagem ser corrompida.
    * @param gerador o gerador de números aleatórios usado para gerar a mensagem.
    */
    public static void corromperMensagem(byte[] mensagem, float p, Random gerador) {
        int contador = 0;
        while (contador == 0) {
            for (int i = 0; i < mensagem.length; i++) {
                for (byte j = 0; j < 8; j++) {
                    if ((gerador.nextInt() / Integer.MAX_VALUE) <= p) {
                        mensagem[i] = (byte) (mensagem[i] ^ (0x01 << j));
                        contador++;
                    }
                }
            }
        }
    }
}
