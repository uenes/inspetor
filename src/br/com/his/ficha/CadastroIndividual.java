package br.com.his.ficha;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;

import br.com.his.FileManager;
import br.gov.saude.esusab.ras.cadastroindividual.CadastroIndividualThrift;
import br.gov.saude.esusab.ras.cadastroindividual.CadastroIndividualThrift._Fields;
import br.gov.saude.esusab.ras.cadastroindividual.EmSituacaoDeRuaThrift;

public class CadastroIndividual extends FichaConcrete {

	@Override
	public String getFichaNome() {
		return CADASTRO_INDIVIDUAL;
	}

	@Override
	public void imprimirDados() throws TException {
		super.imprimirDados();

		final CadastroIndividualThrift thrift = new CadastroIndividualThrift();
		FileManager fileManager = new FileManager();
		fileManager.unserializeBinaryProtocol(dadoTransporteThrift.getDadoSerializado(), thrift);

		// Baseado no dicion�rio de dados
		// HIS-CSPEC-BR-Dicion�rios de dados-Thrift-v2.11
		System.out.println("========= CADASTRO INDIVIDUAL MASTER =========");
		Iterator<Entry<_Fields, FieldMetaData>> cadastroIndividual = thrift.metaDataMap.entrySet().iterator();
		while (cadastroIndividual.hasNext()) {
			Entry<_Fields, FieldMetaData> next = cadastroIndividual.next();
			System.out.println(next.getKey() + " - " + thrift.getFieldValue(next.getKey()));
		}
		
		System.out.println("========= SITUA��O DE RUA =========");
		EmSituacaoDeRuaThrift emSituacaoDeRua = thrift.getEmSituacaoDeRua();
		System.out.println("STATUS SITUA��O DE RUA - " + emSituacaoDeRua.isStatusSituacaoRua());
		
		System.out.println("========= IDENTIFICA��O USU�RIO CIDAD�O =========");
		Iterator<Entry<br.gov.saude.esusab.ras.cadastroindividual.IdentificacaoUsuarioCidadaoThrift._Fields, FieldMetaData>> id = 
				thrift.getIdentificacaoUsuarioCidadao().metaDataMap.entrySet().iterator();
		while (id.hasNext()) {
			Entry<br.gov.saude.esusab.ras.cadastroindividual.IdentificacaoUsuarioCidadaoThrift._Fields, FieldMetaData> next = id.next();
			System.out.println(next.getKey() + " - " + thrift.getIdentificacaoUsuarioCidadao().getFieldValue(next.getKey()));
		}
		
		System.out.println("========= INFORMA��ES S�CIO DEMOGR�FICAS =========");
		Iterator<Entry<br.gov.saude.esusab.ras.cadastroindividual.InformacoesSocioDemograficasThrift._Fields, FieldMetaData>> demo = 
				thrift.getInformacoesSocioDemograficas().metaDataMap.entrySet().iterator();
		while (demo.hasNext()) {
			Entry<br.gov.saude.esusab.ras.cadastroindividual.InformacoesSocioDemograficasThrift._Fields, FieldMetaData> next = demo.next();
			System.out.println(next.getKey() + " - " + thrift.getInformacoesSocioDemograficas().getFieldValue(next.getKey()));
		}
		
		System.out.println("========= CONDI��ES DE SA�DE =========");
		Iterator<Entry<br.gov.saude.esusab.ras.cadastroindividual.CondicoesDeSaudeThrift._Fields, FieldMetaData>> itCondicoes = 
				thrift.getCondicoesDeSaude().metaDataMap.entrySet().iterator();
		while (itCondicoes.hasNext()) {
			Entry<br.gov.saude.esusab.ras.cadastroindividual.CondicoesDeSaudeThrift._Fields, FieldMetaData> next = itCondicoes.next();
			System.out.println(next.getKey() + " - " + thrift.getCondicoesDeSaude().getFieldValue(next.getKey()));
		}
		
	}

}
