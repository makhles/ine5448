package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class TesteAgencia {

	/**
	 * Caso de teste 2 com Inline setup
	 * @throws Exception
	 */
	@Test
	public void criarAgencia() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco banco = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		Agencia agencia = banco.criarAgencia("Centro");
		assertEquals("001", agencia.obterIdentificador());
		assertEquals("Banco do Brasil", agencia.obterNome());
		assertEquals(banco, agencia.obterBanco());
	}
}
