package br.ufsc.ine.leb.sistemaBancario.testes.estoria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

/**
 * Realiza o teste da classe Operacoes utilizando a definição de Conta e SistemaBancario.
 * @author m.r.lange
 *
 */

@FixtureSetup({TesteConta.class, TesteBanco.class})
public class TesteOperacoes {

	@Fixture
	private Conta conta;
	@Fixture
	private SistemaBancario sistemaBancario;

	@Test
	public void depositar() throws Exception {
		Dinheiro quantia = new Dinheiro(Moeda.BRL,10,0);
		Operacao operacao = sistemaBancario.depositar(conta, quantia);
		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(quantia, conta.calcularSaldo().obterQuantia());
	}

	@Test
	public void sacarComSaldoSuficiente() throws Exception {
		Dinheiro quantiaDeposito = new Dinheiro(Moeda.BRL,10,0);
		Dinheiro quantiaSaque = new Dinheiro(Moeda.BRL,6,0);
		Dinheiro quantiaSaldo = new Dinheiro(Moeda.BRL,4,0);
		sistemaBancario.depositar(conta, quantiaDeposito);
		Operacao operacao = sistemaBancario.sacar(conta, quantiaSaque);
		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(quantiaSaldo, conta.calcularSaldo().obterQuantia());
	}

	@Test
	public void sacarComSaldoInsuficiente() throws Exception {
		Dinheiro quantiaDeposito = new Dinheiro(Moeda.BRL,4,0);
		Dinheiro quantiaSaque = new Dinheiro(Moeda.BRL,6,0);
		Dinheiro quantiaSaldo = new Dinheiro(Moeda.BRL,4,0);
		sistemaBancario.depositar(conta, quantiaDeposito);
		Operacao operacao = sistemaBancario.sacar(conta, quantiaSaque);
		assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, operacao.obterEstado());
		assertEquals(quantiaSaldo, conta.calcularSaldo().obterQuantia());
	}
}
