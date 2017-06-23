package br.com.his.ficha;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;

import br.com.his.FileManager;
import br.gov.saude.esusab.ras.cadastrodomiciliar.CadastroDomiciliarThrift;
import br.gov.saude.esusab.ras.cadastrodomiciliar.FamiliaRowThrift;
import br.gov.saude.esusab.ras.common.EnderecoLocalPermanenciaThrift;
import br.gov.saude.esusab.ras.common.EnderecoLocalPermanenciaThrift._Fields;

public class CadastroDomiciliar extends FichaConcrete {
	@Override
	public String getFichaNome() {
		return CADASTRO_DOMICILIAR;
	}

	@Override
	public void imprimirDados() throws TException {
		super.imprimirDados();

		final CadastroDomiciliarThrift thrift = new CadastroDomiciliarThrift();
		FileManager fileManager = new FileManager();
		fileManager.unserializeBinaryProtocol(dadoTransporteThrift.getDadoSerializado(), thrift);

		// Baseado no dicionário de dados
		// HIS-CSPEC-BR-Dicionários de dados-Thrift-v2.11
		System.out.println("========= CADASTRO DOMICILIAR MASTER =========");
		Iterator<Entry<br.gov.saude.esusab.ras.cadastrodomiciliar.CadastroDomiciliarThrift._Fields, FieldMetaData>> itDomiciliar = thrift.metaDataMap
				.entrySet().iterator();
		while (itDomiciliar.hasNext()) {
			Entry<br.gov.saude.esusab.ras.cadastrodomiciliar.CadastroDomiciliarThrift._Fields, FieldMetaData> next = itDomiciliar
					.next();
			System.out.println(next.getKey() + " - " + thrift.getFieldValue(next.getKey()));
		}

		System.out.println("========= ENDEREÇO LOCAL PERMANENCIA =========");
		Iterator<Entry<_Fields, FieldMetaData>> itLocalPermanencia = thrift.getEnderecoLocalPermanencia().metaDataMap
				.entrySet().iterator();
		while (itLocalPermanencia.hasNext()) {
			Entry<_Fields, FieldMetaData> next = itLocalPermanencia.next();
			System.out
					.println(next.getKey() + " - " + thrift.getEnderecoLocalPermanencia().getFieldValue(next.getKey()));
		}

		System.out.println("========= FAMILIA ROW =========");
		for (FamiliaRowThrift familia : thrift.getFamilias()) {
			Iterator<Entry<br.gov.saude.esusab.ras.cadastrodomiciliar.FamiliaRowThrift._Fields, FieldMetaData>> itFamilia = familia.metaDataMap
					.entrySet().iterator();
			while (itFamilia.hasNext()) {
				Entry<br.gov.saude.esusab.ras.cadastrodomiciliar.FamiliaRowThrift._Fields, FieldMetaData> next = itFamilia
						.next();
				System.out.println(next.getKey() + " - " + familia.getFieldValue(next.getKey()));
			}
		}

		System.out.println("========= CONDIÇÃO MORADIA =========");
		Iterator<Entry<br.gov.saude.esusab.ras.cadastrodomiciliar.CondicaoMoradiaThrift._Fields, FieldMetaData>> itCondicaoMoradia = thrift
				.getCondicaoMoradia().metaDataMap.entrySet().iterator();
		while (itCondicaoMoradia.hasNext()) {
			Entry<br.gov.saude.esusab.ras.cadastrodomiciliar.CondicaoMoradiaThrift._Fields, FieldMetaData> next = itCondicaoMoradia
					.next();
			System.out.println(next.getKey() + " - " + thrift.getCondicaoMoradia().getFieldValue(next.getKey()));
		}
		
		System.out.println("========= ENDEREÇO LOCAL PERMANÊNCIA =========");
		Iterator<Entry<_Fields, FieldMetaData>> endereco = thrift.getEnderecoLocalPermanencia().metaDataMap.entrySet().iterator();
		while (endereco.hasNext()) {
			Entry<_Fields, FieldMetaData> next = endereco.next();
			System.out.println(next.getKey() + " - " + thrift.getEnderecoLocalPermanencia().getFieldValue(next.getKey()));
		}
		
	}
}
