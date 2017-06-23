package br.com.his.ficha;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;

import br.com.his.FileManager;
import br.gov.saude.esusab.ras.atendprocedimentos.FichaProcedimentoChildThrift;
import br.gov.saude.esusab.ras.atendprocedimentos.FichaProcedimentoMasterThrift;
import br.gov.saude.esusab.ras.atendprocedimentos.FichaProcedimentoMasterThrift._Fields;
import br.gov.saude.esusab.ras.common.UnicaLotacaoHeaderThrift;

public class Procedimento extends FichaConcrete {
	@Override
	public String getFichaNome() {
		return PROCEDIMENTO;
	}

	@Override
	public void imprimirDados() throws TException {
		super.imprimirDados();

		FichaProcedimentoMasterThrift thrift = new FichaProcedimentoMasterThrift();
		FileManager fileManager = new FileManager();
		fileManager.unserializeBinaryProtocol(dadoTransporteThrift.getDadoSerializado(), thrift);

		// Baseado no dicionário de dados
		// HIS-CSPEC-BR-Dicionários de dados-Thrift-v2.11
		System.out.println("========= PROCEDIMENTO MASTER =========");
		Iterator<Entry<_Fields, FieldMetaData>> procedimento = thrift.metaDataMap.entrySet().iterator();
		while (procedimento.hasNext()) {
			Entry<_Fields, FieldMetaData> next = procedimento.next();
			System.out.println(next.getKey() + " - " + thrift.getFieldValue(next.getKey()));
		}
		
		System.out.println("========= PROCEDIMENTO CHILD =========");
		for(FichaProcedimentoChildThrift procedimentoChild :  thrift.getAtendProcedimentos()){
			System.out.println("		Procedimento realizado no local " + procedimentoChild.getLocalAtendimento());
			Iterator<Entry<br.gov.saude.esusab.ras.atendprocedimentos.FichaProcedimentoChildThrift._Fields, FieldMetaData>> itProcedimentoChild = 
					procedimentoChild.metaDataMap.entrySet().iterator();
			while(itProcedimentoChild.hasNext()) {
				Entry<br.gov.saude.esusab.ras.atendprocedimentos.FichaProcedimentoChildThrift._Fields, FieldMetaData> next = itProcedimentoChild.next();
				System.out.println(next.getKey() + " - " + procedimentoChild.getFieldValue(next.getKey()));
			}
		}
		
		System.out.println("========= HEADER TRANSPORT =========");
		Iterator<Entry<br.gov.saude.esusab.ras.common.UnicaLotacaoHeaderThrift._Fields, FieldMetaData>> header = 
				thrift.getHeaderTransport().metaDataMap.entrySet().iterator();
		while (header.hasNext()) {
			Entry<br.gov.saude.esusab.ras.common.UnicaLotacaoHeaderThrift._Fields, FieldMetaData> next = header.next();
			System.out.println(next.getKey() + " - " + thrift.getHeaderTransport().getFieldValue(next.getKey()));
		}

	}
}
