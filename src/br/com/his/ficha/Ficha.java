package br.com.his.ficha;

import org.apache.thrift.TException;

import br.gov.saude.esusab.dadotransp.DadoTransporteThrift;

public interface Ficha {
	
	public void imprimirDados() throws TException;
	public void setDadoTransporteThrift(DadoTransporteThrift dadoTransporteThrift);
	public String getFichaNome();

}
