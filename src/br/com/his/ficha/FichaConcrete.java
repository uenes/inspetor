package br.com.his.ficha;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TMemoryBuffer;

import br.com.his.FileManager;
import br.gov.saude.esusab.dadotransp.DadoTransporteThrift;
import br.gov.saude.esusab.ras.cadastrodomiciliar.CadastroDomiciliarThrift;
import br.gov.saude.esusab.ras.cadastroindividual.CadastroIndividualThrift;

public class FichaConcrete implements Ficha {
	public static final String ATENDIMENTO_INDIVIDUAL = "Atendimento Individual";
	public static final String ATENDIMENTO_ODONTOLOGICO = "Atendimento Odontológico";
	public static final String ATIVIDADE_COLETIVA = "Atividade Coletiva";
	public static final String CADASTRO_DOMICILIAR = "Cadastro Domiciliar";
	public static final String CADASTRO_INDIVIDUAL = "Cadastro Individual";
	public static final String PROCEDIMENTO = "Procedimento";
	public static final String VISITA_DOMICILIAR = "Visita Domiciliar";

	protected DadoTransporteThrift dadoTransporteThrift;

	@Override
	public void imprimirDados() throws TException {
		System.out.println("CNES: " + dadoTransporteThrift.getCnesDadoSerializado());
		System.out.println("IBGE: " + dadoTransporteThrift.getCodIbge());
		System.out.println("INE: " + dadoTransporteThrift.getIneDadoSerializado());
		System.out.println("Num Lote: " + dadoTransporteThrift.getNumLote());
		System.out.println("Tipo: " + dadoTransporteThrift.getTipoDadoSerializado());
		System.out.println("UUID: " + dadoTransporteThrift.getUuidDadoSerializado());
		System.out.println("Dados: " + dadoTransporteThrift.getDadoSerializado());
		System.out.println("ORIG.: " + dadoTransporteThrift.getOriginadora());
		System.out.println("REM.: " + dadoTransporteThrift.getRemetente());
		System.out.println("VER: " + dadoTransporteThrift.getVersao());
	}

	@Override
	public void setDadoTransporteThrift(DadoTransporteThrift dadoTransporteThrift) {
		this.dadoTransporteThrift = dadoTransporteThrift;
	}

	@Override
	public String getFichaNome() {
		return "Ficha";
	}
}
