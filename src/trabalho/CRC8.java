/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

/**
 * Classe para cálculo do CRC8 de uma mensagem.
 *
 * @author Catarina Pereira Ribeiro
 * @author Guilherme Cler da Silva
 * @author Leonardo de Melo Cavalcante
 * @author Victor Meireles Bandeira de Melo
 */
public class CRC8 {

    /**
    * Não deixa que um objeto do tipo CRC8 seja instanciado.
    */
    private CRC8() {

    }

    /**
    * Gera o CRC de 8 bits de uma mensagem informada.
    *
    * @param mensagem a mensagem que terá seu CRC calculado.
    * @param tamanho o tamnho da mensagem.
    * @param polinomio o polinômio gerador de grau 8 do CRC. 
    *
    * @return o CRC da mensagem.
    */
    
    public static int gerarCRC(byte[] mensagem, int tamanho, int polinomio) {
        int crc = 0x00;
        for (int i = 0; i < tamanho; i++) {
            crc = crc ^ (mensagem[i] & 0xFF); //Faz um XOR com cada byte da mensagem.
            for (int j = 0; j < 8; j++) { //Para cada bit do byte;
                if ((crc & 0x01) != 0) { //Se o bit mais a direita do CRC for 1.
                    crc = crc >>> 1; //Desloca o valor do CRC em um bit para a direita.
                    crc = crc ^ polinomio; //Faz um XOR com o polinômio;
                } else { //Se o bit mais a direita do CRC for 0.
                    crc = (crc >>> 1); //Apenas desloca o valor do CRC em um bit para a direita, sem fazer XOR com o polinômio.
                }
            }
        }
        return (crc & 0xFF);
    }
}
