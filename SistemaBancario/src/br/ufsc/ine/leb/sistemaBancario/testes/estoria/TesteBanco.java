package br.ufsc.ine.leb.sistemaBancario.testes.estoria;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Singular;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

/**
 * Realiza o teste da classe Banco utilizando implicit setup.
 * @author m.r.lange
 *
 */
@Singular
public class TesteBanco {

	private SistemaBancario sistemaBancario;
	private Banco banco;

	@Before
	public void configurar() {
		sistemaBancario = new SistemaBancario();
		banco = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
	}

	@Test
	public void criarBancoDoBrasil() throws Exception {
		assertEquals("Banco do Brasil", banco.obterNome());
		assertEquals(Moeda.BRL, banco.obterMoeda());
	}
}
