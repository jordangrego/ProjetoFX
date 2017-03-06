package br.com.montreal.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class UtilComum {
	public static String recuperarTextoArquivo(String pathArquivo) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(pathArquivo));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}

			return sb.toString();
		} finally {
			br.close();
		}
	}
	
	public static void gravarArquivo(byte[] arquivo, String pathArquivo) throws IOException {
		FileOutputStream fos = new FileOutputStream(pathArquivo);
		fos.write(arquivo);
		fos.close();
	}
}
