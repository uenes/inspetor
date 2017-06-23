package br.com.his.ficha;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;

import br.com.his.FileManager;
import br.gov.saude.esusab.dadotransp.DadoTransporteThrift;
import br.gov.saude.esusab.ras.atendindividual.FichaAtendimentoIndividualChildThrift;
import br.gov.saude.esusab.ras.atendindividual.FichaAtendimentoIndividualMasterThrift;
import br.gov.saude.esusab.ras.atendindividual.FichaAtendimentoIndividualMasterThrift._Fields;
import br.gov.saude.esusab.ras.atendindividual.OutrosSiaThrift;

public class AtendimentoIndividual extends FichaConcrete {
	@Override
	public String getFichaNome() {
		return ATENDIMENTO_INDIVIDUAL;
	}

	@Override
	public void imprimirDados() throws TException {
		super.imprimirDados();

		final FichaAtendimentoIndividualMasterThrift thrift = new FichaAtendimentoIndividualMasterThrift();
		FileManager fileManager = new FileManager();
		fileManager.unserializeBinaryProtocol(dadoTransporteThrift.getDadoSerializado(), thrift);
		
		// Baseado no dicionário de dados HIS-CSPEC-BR-Dicionários de dados-Thrift-v2.11
		System.out.println("========= ATENDIMENTO INDIVIDUAL =========");
		Iterator<Entry<_Fields, FieldMetaData>> itAtendimento = thrift.metaDataMap.entrySet().iterator();
		while (itAtendimento.hasNext()) {
			Entry<_Fields, FieldMetaData> next = itAtendimento.next();
			System.out.println(next.getKey() + " - " + thrift.getFieldValue(next.getKey()));
		}

		System.out.println("========= ATENDIMENTO INDIVIDUAL CHILD =========");
		for (FichaAtendimentoIndividualChildThrift atendimentoChild : thrift.getAtendimentosIndividuais()) {
			System.out.println(">>>> Prontuário " + atendimentoChild.getNumeroProntuario() + " <<<<");
			Iterator<Entry<br.gov.saude.esusab.ras.atendindividual.FichaAtendimentoIndividualChildThrift._Fields, FieldMetaData>> iterator = atendimentoChild.metaDataMap
					.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<br.gov.saude.esusab.ras.atendindividual.FichaAtendimentoIndividualChildThrift._Fields, FieldMetaData> next = iterator
						.next();
				System.out.println(next.getKey() + " - " + atendimentoChild.getFieldValue(next.getKey()));
			}

			System.out.println("		========= OUTRO SIA =========");
			if (atendimentoChild != null && atendimentoChild.getOutrosSia() != null) {
				for (OutrosSiaThrift outro : atendimentoChild.getOutrosSia()) {
					Iterator<Entry<br.gov.saude.esusab.ras.atendindividual.OutrosSiaThrift._Fields, FieldMetaData>> itOutro = outro.metaDataMap
							.entrySet().iterator();
					while (itOutro.hasNext()) {
						Entry<br.gov.saude.esusab.ras.atendindividual.OutrosSiaThrift._Fields, FieldMetaData> next = itOutro
								.next();
						System.out.println(next.getKey() + " - " + outro.getFieldValue(next.getKey()));
					}
				}
			} else {
				System.out.println("Prontuário " + atendimentoChild.getNumeroProntuario() + " não contém 'Outro SIA'");
			}

		}

	}
}
