package br.com.his;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.thrift.TException;

public class DeserializeFichasEsusMain {
	private static final String FILE_PATH = "/Users/uferreira/workspace/DeserializeFichasEsus/files/Procedimento_2270072-4830D348-03DC-46E9-98C2-649AB6F3DED1.zip";

	public static void main(String[] args) {
		Unserializer unserializer = new Unserializer(FILE_PATH);
		
		try {
			unserializer.imprimirFicha();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO exception: " + e.getMessage());
		} catch (TException e) {
			System.out.println("Exception of Thrift: " + e.getMessage());
		}

//		System.out.println(new Date(1491523200000l));
	}

}
