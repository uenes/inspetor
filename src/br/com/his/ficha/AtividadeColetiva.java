package br.com.his.ficha;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;

import br.com.his.FileManager;
import br.gov.saude.esusab.ras.atividadecoletiva.FichaAtividadeColetivaThrift;
import br.gov.saude.esusab.ras.atividadecoletiva.FichaAtividadeColetivaThrift._Fields;
import br.gov.saude.esusab.ras.atividadecoletiva.ParticipanteRowItemThrift;
import br.gov.saude.esusab.ras.atividadecoletiva.ProfissionalCboRowItemThrift;

public class AtividadeColetiva extends FichaConcrete {
	@Override
	public String getFichaNome() {
		return ATIVIDADE_COLETIVA;
	}

	@Override
	public void imprimirDados() throws TException {
		super.imprimirDados();

		final FichaAtividadeColetivaThrift thrift = new FichaAtividadeColetivaThrift();
		FileManager fileManager = new FileManager();
		fileManager.unserializeBinaryProtocol(dadoTransporteThrift.getDadoSerializado(), thrift);

		// Baseado no dicionário de dados
		// HIS-CSPEC-BR-Dicionários de dados-Thrift-v2.11
		System.out.println("========= ATIVIDADE COLETIVA =========");
		Iterator<Entry<_Fields, FieldMetaData>> itAtividadeColetiva = thrift.metaDataMap.entrySet().iterator();
		while (itAtividadeColetiva.hasNext()) {
			Entry<_Fields, FieldMetaData> next = itAtividadeColetiva.next();
			System.out.println(next.getKey() + " - " + thrift.getFieldValue(next.getKey()));
		}
		
		System.out.println("========= PARTICIPANTE ROW ITEM =========");
		for (ParticipanteRowItemThrift participante : thrift.getParticipantes()) {
			Iterator<Entry<br.gov.saude.esusab.ras.atividadecoletiva.ParticipanteRowItemThrift._Fields, FieldMetaData>> itParticipante = participante.metaDataMap.entrySet().iterator();
			while (itParticipante.hasNext()) {
				Entry<br.gov.saude.esusab.ras.atividadecoletiva.ParticipanteRowItemThrift._Fields, FieldMetaData> next = itParticipante.next();
				System.out.println(next.getKey() + " - " + participante.getFieldValue(next.getKey()));
			}
		}
		
		System.out.println("========= PROFISSIONAIS =========");
		for (ProfissionalCboRowItemThrift profissional : thrift.getProfissionais()) {
			Iterator<Entry<br.gov.saude.esusab.ras.atividadecoletiva.ProfissionalCboRowItemThrift._Fields, FieldMetaData>> itProfissinal = 
					profissional.metaDataMap.entrySet().iterator();
			while (itProfissinal.hasNext()) {
				Entry<br.gov.saude.esusab.ras.atividadecoletiva.ProfissionalCboRowItemThrift._Fields, FieldMetaData> next = itProfissinal.next();
				System.out.println(next.getKey() + " - " + profissional.getFieldValue(next.getKey()));
			}
			
		}

	}
}
