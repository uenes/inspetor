package br.com.his.ficha;

import br.gov.saude.esusab.dadotransp.DadoTransporteThrift;

public class FichaBuilder {

	public static Ficha createFicha(DadoTransporteThrift dadoTransporteThrift) {
		Ficha ficha = null;
		if (dadoTransporteThrift.getTipoDadoSerializado() == 2L) {
			ficha = new CadastroIndividual();
		}
		if (dadoTransporteThrift.getTipoDadoSerializado() == 3L) {
			ficha = new CadastroDomiciliar();
		}
		if (dadoTransporteThrift.getTipoDadoSerializado() == 4L) {
			ficha = new AtendimentoIndividual();
		}
		if (dadoTransporteThrift.getTipoDadoSerializado() == 5L) {
			ficha = new AtendimentoOdontologico();
		}
		if (dadoTransporteThrift.getTipoDadoSerializado() == 6L) {
			ficha = new AtividadeColetiva();
		}
		if (dadoTransporteThrift.getTipoDadoSerializado() == 7L) {
			ficha = new Procedimento();
		}
		if (dadoTransporteThrift.getTipoDadoSerializado() == 8L) {
			ficha = new VisitaDomiciliar();
		}
		ficha.setDadoTransporteThrift(dadoTransporteThrift);

		return ficha;
	}

}
