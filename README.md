# comparative-analysis-CRC-checksum
Análise comparativa dos métodos CRC e checksum em termos de tempo de execução e capacidade de detecção de erros

**MENSAGENS ALEATÓRIAS**
A função de geração de mensagens aleatórias recebe como argumento um tamanho, em bytes, da mensagem a ser gerada. A implementação trabalha com geraço de números aleatórios para a obtenção dos valores dos bytes da mensagem resultante. A única restrição é que o tamanho especificado como argumento deverá ser respeitado.  

**INSERÇÃO DE ERROS ALEATÓRIOS**
Esta função recebe como argumentos uma mensagem (sequência de bytes), seu tamanho em bytes e uma probabilidade p de corrupção de bits (0 < p ≤ 1). A função, então, percorre cada bit da mensagem verificando se deve ou não alterar seu valor. Ao final do processamento, a nova mensagem (i.e., a mensagem original com bits potencialmente alterados) é retornada.  

**CHECKSUM**
O algoritmo de checksum consiste dos seguintes passos:
A mensagem deve ser tratada como uma sequência de números de 8 bits (1 byte).
Estes números devem ser somados em complemento a 1, i.e., a cada parcela da soma, se houve overflow, o resultado deve ser incrementado em uma unidade.
Ao final da soma de todos os bytes, o resultado deve ser invertido bit a bit. Este último valor é o checksum da mensagem.
A função de checksum a ser implementada deve receber como argumentos a mensagem e seu tamanho. Como resultado, a função deverá retornar o valor do checksum conforme descrito acima.  

**CRC**
A implementação do cálculo do CRC foi parametrizada pelo polinômio gerador, i.e., a implementação deverá funcionar com diferentes polinômios geradores especificados como argumento da função (além da mensagem e de seu tamanho). A implementação pode assumir que o polinômio gerador será sempre de grau 8.
Polinômios geradores escolhidos: AB, F4, E4.  
Assim como a função de cálculo de checksum, a função de cálculo de CRC deverá retornar os bits de CRC resultantes da computação. Como o polinômio gerador é sempre de grau 8, o CRC resultante sempre terá 8 bits (1 byte).
