package br.com.his.ficha;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;

import br.com.his.FileManager;
import br.gov.saude.esusab.ras.visitadomiciliar.FichaVisitaDomiciliarChildThrift;
import br.gov.saude.esusab.ras.visitadomiciliar.FichaVisitaDomiciliarMasterThrift;
import br.gov.saude.esusab.ras.visitadomiciliar.FichaVisitaDomiciliarMasterThrift._Fields;

public class VisitaDomiciliar extends FichaConcrete {
	@Override
	public String getFichaNome() {
		return VISITA_DOMICILIAR;
	}

	@Override
	public void imprimirDados() throws TException{
		super.imprimirDados();
		
		final FichaVisitaDomiciliarMasterThrift thrift = new FichaVisitaDomiciliarMasterThrift();
		FileManager fileManager = new FileManager();
        fileManager.unserializeBinaryProtocol(dadoTransporteThrift.getDadoSerializado(), thrift);

		// Baseado no dicionário de dados
		// HIS-CSPEC-BR-Dicionários de dados-Thrift-v2.11
		System.out.println("========= VISITA DOMICILIAR =========");
		Iterator<Entry<_Fields, FieldMetaData>> itVisitaDomiciliar = thrift.metaDataMap.entrySet().iterator();
		while (itVisitaDomiciliar.hasNext()) {
			Entry<_Fields, FieldMetaData> next = itVisitaDomiciliar.next();
			System.out.println(next.getKey() + " - " + thrift.getFieldValue(next.getKey()));
		}
		
		System.out.println("========= VISITA DOMICILIAR CHILD =========");
        for (FichaVisitaDomiciliarChildThrift visitaChild : thrift.getVisitasDomiciliares()){
        	Iterator<Entry<br.gov.saude.esusab.ras.visitadomiciliar.FichaVisitaDomiciliarChildThrift._Fields, FieldMetaData>> itVisitaChild = 
        			visitaChild.metaDataMap.entrySet().iterator();
        	while (itVisitaChild.hasNext()) {
        		Entry<br.gov.saude.esusab.ras.visitadomiciliar.FichaVisitaDomiciliarChildThrift._Fields, FieldMetaData> next = 
        				itVisitaChild.next();
        		System.out.println(next.getKey() + " - " + visitaChild.getFieldValue(next.getKey()));        		
        	}
        }
	}
}
