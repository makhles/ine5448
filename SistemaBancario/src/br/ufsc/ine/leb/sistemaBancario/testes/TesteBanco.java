package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class TesteBanco {

	/**
	 * Caso de teste 1 com Inline setup
	 * @throws Exception
	 */
	@Test
	public void criarBanco() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco banco = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		assertEquals("Banco do Brasil", banco.obterNome());
		assertEquals(Moeda.BRL, banco.obterMoeda());
	}
}
