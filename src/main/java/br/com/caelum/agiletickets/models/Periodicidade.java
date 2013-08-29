package br.com.caelum.agiletickets.models;

import java.util.List;

import org.joda.time.DateTime;

public enum Periodicidade {
	DIARIA(1) {
		@Override
		public List<Sessao> criarListaSessoes(Espetaculo espetaculo,
				DateTime inicio, DateTime fim) {
			CriadorDeSessoes criadorDeSessoesDiarias = new CriadorDeSessoes();
			return criadorDeSessoesDiarias.criarSessoes(espetaculo, inicio, fim, this.incremento);
		}
	},
	SEMANAL(7) {
		@Override
		public List<Sessao> criarListaSessoes(Espetaculo espetaculo,
				DateTime inicio, DateTime fim) {
			
			CriadorDeSessoes criadorDeSessoesSemais = new CriadorDeSessoes();
			return criadorDeSessoesSemais.criarSessoes(espetaculo, inicio, fim, this.incremento);
		}
	};
	
	int incremento;
	
	private Periodicidade(int incremento){
		this.incremento = incremento;
	}

	
	/* (non-Javadoc)
	 * @see br.com.caelum.agiletickets.models.CriadorDeSessoes#criarListaSessoes(br.com.caelum.agiletickets.models.Espetaculo, org.joda.time.DateTime, org.joda.time.DateTime)
	 */
	public abstract List<Sessao> criarListaSessoes(Espetaculo espetaculo,
			DateTime inicio, DateTime fim);

	
}
