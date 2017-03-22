package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class TesteOperacoes {

	SistemaBancario sistemaBancario;
	Banco banco;
	Agencia agencia;
	Conta conta;

	@Before
	public void implicitSetup() {
		sistemaBancario = new SistemaBancario();
		banco = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		agencia = banco.criarAgencia("Centro");
		conta = agencia.criarConta("Maria");
	}

	/**
	 * Caso de teste 4 com Delegated setup
	 * @throws Exception
	 */
	@Test
	public void depositar() throws Exception {
		Dinheiro quantia = new Dinheiro(Moeda.BRL,10,0);
		Operacao operacao = sistemaBancario.depositar(conta, quantia);
		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(quantia, conta.calcularSaldo().obterQuantia());
	}

	/**
	 * Caso de teste 5 com Delegated setup
	 * @throws Exception
	 */
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

	/**
	 * Caso de teste 6 com Delegated setup
	 * @throws Exception
	 */
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
