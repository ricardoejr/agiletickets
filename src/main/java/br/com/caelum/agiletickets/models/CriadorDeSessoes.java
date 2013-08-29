package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

public class CriadorDeSessoes {

	public List<Sessao> criarSessoes(Espetaculo espetaculo,
			DateTime inicio, DateTime fim, int incremento) {
		List<Sessao> listaSessoes = new ArrayList<Sessao>();

		DateTime dataHoraEspetaculo = new DateTime(inicio);
		while (dataHoraEspetaculo.isBefore(fim) || dataHoraEspetaculo.equals(fim)) {
			Sessao sessao = new Sessao();
			sessao.setEspetaculo(espetaculo);
			sessao.setInicio(dataHoraEspetaculo);
			sessao.setTotalIngressos(50);

			listaSessoes.add(sessao);
			dataHoraEspetaculo = dataHoraEspetaculo.plusDays(incremento);
		}
		return listaSessoes;
	}

}