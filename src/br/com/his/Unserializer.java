package br.com.his;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import br.com.his.ficha.AtendimentoOdontologico;
import br.com.his.ficha.AtividadeColetiva;
import br.com.his.ficha.CadastroDomiciliar;
import br.com.his.ficha.Ficha;
import br.com.his.ficha.FichaBuilder;
import br.com.his.ficha.FichaConcrete;
import br.com.his.ficha.Procedimento;
import br.gov.saude.esusab.dadotransp.DadoTransporteThrift;

public class Unserializer {
	private String filePath;

	public Unserializer(String filePath) {
		this.filePath = filePath;
	}

	public void imprimirFicha() throws FileNotFoundException, IOException, TException {

		final List<DadoTransporteThrift> dados = FileManager.readFromZip(filePath);
		System.out.println("Quantidade de Fichas = " + dados.size());

		for (final DadoTransporteThrift dadoTransporteThrift : dados) {
			Ficha ficha = FichaBuilder.createFicha(dadoTransporteThrift);
			imprimirHeader(ficha.getFichaNome());
			ficha.imprimirDados();
			System.out.println("\n");
		}
	}

	private void imprimirHeader(String header) {
		System.out.println("---------------------------------------------------------");
		System.out.println("             " + header + "              ");
		System.out.println("---------------------------------------------------------");
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
