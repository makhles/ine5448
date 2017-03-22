package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class TesteConta {

	public Agencia criarAgencia() {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco banco = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		return banco.criarAgencia("Centro");
	}

	/**
	 * Caso de teste 3 com Inline e Delegated setups.
	 * @throws Exception
	 */
	@Test
	public void verificarCriacaoConta() throws Exception {
		Agencia agencia = criarAgencia();
		Conta conta = agencia.criarConta("Maria");
		assertEquals("0001-5", conta.obterIdentificador());
		assertEquals("Maria", conta.obterTitular());
		assertTrue(conta.calcularSaldo().zero());
		assertEquals("Centro", agencia.obterNome());
	}
}
