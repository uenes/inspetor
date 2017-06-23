package br.com.his.ficha;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;

import br.com.his.FileManager;
import br.gov.saude.esusab.ras.atendodonto.FichaAtendimentoOdontologicoChildThrift;
import br.gov.saude.esusab.ras.atendodonto.FichaAtendimentoOdontologicoMasterThrift;
import br.gov.saude.esusab.ras.atendodonto.FichaAtendimentoOdontologicoMasterThrift._Fields;
import br.gov.saude.esusab.ras.atendodonto.ProcedimentoQuantidadeThrift;

public class AtendimentoOdontologico extends FichaConcrete {
	@Override
	public String getFichaNome() {
		return ATENDIMENTO_ODONTOLOGICO;
	}
	
	@Override
	public void imprimirDados() throws TException{
		super.imprimirDados();
		
		final FichaAtendimentoOdontologicoMasterThrift thrift = new FichaAtendimentoOdontologicoMasterThrift();
		FileManager fileManager = new FileManager();
        fileManager.unserializeBinaryProtocol(dadoTransporteThrift.getDadoSerializado(), thrift);
		
        // Baseado no dicionário de dados HIS-CSPEC-BR-Dicionários de dados-Thrift-v2.11
		System.out.println("========= ATENDIMENTO ODONTOLÓGICO =========");
		Iterator<Entry<_Fields, FieldMetaData>> itOdonto = thrift.metaDataMap.entrySet().iterator();
		while (itOdonto.hasNext()) {
			Entry<_Fields, FieldMetaData> next = itOdonto.next();
			System.out.println(next.getKey() + " - " + thrift.getFieldValue(next.getKey()));
		}
		
		System.out.println("========= ATENDIMENTO ODONTOLÓGICO CHILD =========");
		for (FichaAtendimentoOdontologicoChildThrift odontoChild : thrift.getAtendimentosOdontologicos()) {
			Iterator<Entry<br.gov.saude.esusab.ras.atendodonto.FichaAtendimentoOdontologicoChildThrift._Fields, FieldMetaData>> iterator = 
					odontoChild.metaDataMap.entrySet().iterator();
			while (iterator.hasNext()){
				Entry<br.gov.saude.esusab.ras.atendodonto.FichaAtendimentoOdontologicoChildThrift._Fields, FieldMetaData> next = iterator.next();
				System.out.println(next.getKey() + " - " + odontoChild.getFieldValue(next.getKey()));
			}
			
			System.out.println("		>>>>> PROCEDIMENTO QUANTIDADE DE LOCAL ATENDIMENTO " + odontoChild.getLocalAtendimento() + " <<<<<");
			for(ProcedimentoQuantidadeThrift procedimentoQuantidade : odontoChild.getProcedimentosRealizados()) {
				Iterator<Entry<br.gov.saude.esusab.ras.atendodonto.ProcedimentoQuantidadeThrift._Fields, FieldMetaData>> itProc = procedimentoQuantidade.metaDataMap.entrySet().iterator();
				while(itProc.hasNext()) {
					Entry<br.gov.saude.esusab.ras.atendodonto.ProcedimentoQuantidadeThrift._Fields, FieldMetaData> next = itProc.next();
					System.out.println(next.getKey() + " - " + procedimentoQuantidade.getFieldValue(next.getKey()));
				}
			}
		}

	}
}
