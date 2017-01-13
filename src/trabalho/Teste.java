/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Guilherme
 */
public class Teste {

    public static void main(String[] args) throws IOException {
        OutputStream output = new FileOutputStream("C:\\Users\\Guilherme\\Desktop\\teste.txt");
        OutputStreamWriter writer = new OutputStreamWriter(output);
        BufferedWriter bw = new BufferedWriter(writer);

        int i = 0;
        int j = 2;
        int k = 5;

        bw.write(i + "|" + j + "|" + k + ";" + "\n");
        bw.close();
    }
}
