package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}

	@Test
	public void deveCriarUmaSessaoParaEspetaculoComDataInicioIgualDataFimPeriodicidadeDiaria() {
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFim = new LocalDate(dataInicio);
		LocalTime hora = new LocalTime().withHourOfDay(10).minusMinutes(0)
				.withSecondOfMinute(0);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> listaSessoes = espetaculo.criaSessoes(dataInicio, dataFim,
				hora, Periodicidade.DIARIA);

		Assert.assertEquals(1, listaSessoes.size());
		Assert.assertEquals(listaSessoes.get(0).getInicio(),
				dataInicio.toDateTime(hora));
		Assert.assertSame(espetaculo, listaSessoes.get(0).getEspetaculo());
	}

	@Test
	public void deveCriarUmaSessaoParaEspetaculoComDataInicioIgualDataFimPeriodicidadeSemanal() {
		LocalDate dataInicio = new LocalDate();
		LocalDate dataFim = new LocalDate(dataInicio);
		LocalTime hora = new LocalTime().withHourOfDay(10).minusMinutes(0)
				.withSecondOfMinute(0);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> listaSessoes = espetaculo.criaSessoes(dataInicio, dataFim,
				hora, Periodicidade.SEMANAL);

		Assert.assertEquals(1, listaSessoes.size());
		Assert.assertEquals(listaSessoes.get(0).getInicio(),
				dataInicio.toDateTime(hora));
		Assert.assertSame(espetaculo, listaSessoes.get(0).getEspetaculo());
	}

	@Test
	public void deveCriarOnzeSessoesParaEspetaculoComDataInicio_Dia10_DataFimDia_20_PeriodicidadeDiario() {
		LocalDate dataInicio = new LocalDate().withDayOfMonth(10);
		LocalDate dataFim = new LocalDate().withDayOfMonth(20);
		LocalTime hora = new LocalTime().withHourOfDay(10).minusMinutes(0)
				.withSecondOfMinute(0);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> listaSessoes = espetaculo.criaSessoes(dataInicio, dataFim,
				hora, Periodicidade.DIARIA);

		Assert.assertEquals(11, listaSessoes.size());
		Assert.assertSame(espetaculo, listaSessoes.get(0).getEspetaculo());
		for (int i = 0; i <= 10; i++) {
			DateTime dataHoraComparacao = dataInicio.plusDays(i).toDateTime(hora);
			Assert.assertEquals(listaSessoes.get(i).getInicio(),
					dataHoraComparacao);
		}
	}

	@Test
	public void deveCriarDuasSessoesParaEspetaculoComDataInicio_Dia10_DataFimDia_20_PeriodicidadeSemanal() {
		LocalDate dataInicio = new LocalDate().withDayOfMonth(10);
		LocalDate dataFim = new LocalDate().withDayOfMonth(20);
		LocalTime hora = new LocalTime().withHourOfDay(10).minusMinutes(0)
				.withSecondOfMinute(0);
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> listaSessoes = espetaculo.criaSessoes(dataInicio, dataFim,
				hora, Periodicidade.SEMANAL);

		Assert.assertEquals(29, listaSessoes.size());
		Assert.assertSame(espetaculo, listaSessoes.get(0).getEspetaculo());

		for (int i = 0; i <= 1; i++) {
			DateTime dataHoraComparacao = dataInicio.plusWeeks(i).toDateTime(
					hora);
			Assert.assertEquals(listaSessoes.get(i).getInicio(),
					dataHoraComparacao);
		}
	}
}
