/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

/**
 * Classe para cáclulo do Checksum de 8 bits de uma mensagem.
 *
 * @author Catarina Pereira Ribeiro
 * @author Guilherme Cler da Silva
 * @author Leonardo de Melo Cavalcante
 * @author Victor Meireles Bandeira de Melo
 */
public class Checksum8 {
    
    /**
    * Não deixa que um objeto do tipo Checksum8 seja instanciado.
    */
    private Checksum8() {
        
    }
    
    /**
    * Gera o Checksum de 8 bits de uma mensagem informada.
    *
    * @param mensagem a mensagem que terá seu Checksum calculado.
    * @param tamanho o tamanho da mensagem.
    *
    * @return o Checksum da mensagem.
    */
    public static int gerarChecksum(byte[] mensagem, int tamanho) {
        int checksum = 0;
        //Enquanto houver bytes a serem somados.
        for (int i = 0; i < tamanho; i++) {
            checksum += mensagem[i]; //Soma os bytes ao checksum.
            
            //Como o checksum tem 8 bits (1 byte), o maior número representável é 256 (2^8).
            //Se o valor da soma é maior ou igual a 256 (2^8), significa que houve overflow.
            //Neste caso, é necessário descartar o bit de overflow e somar 1 bit ao resultado da soma.
            if (checksum >= 256) {
                checksum -= 255;
            }
        }
        checksum = 255 - checksum; //Equivalente a inverter os bits.
        return checksum;
    }
}