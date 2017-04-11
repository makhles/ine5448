package br.ufsc.ine.leb.sistemaBancario.testes.estoria;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;

/**
 * Realiza o teste da classe Agencia utilizando a definição de Banco de TesteBanco.
 * @author m.r.lange
 *
 */

@FixtureSetup(TesteBanco.class)
public class TesteAgencia {

	@Fixture
	private Banco banco;
	private Agencia agencia;

	@Before
	public void configurar() {
		agencia = banco.criarAgencia("Centro");
	}

	@Test
	public void criarAgenciaCentro() throws Exception {
		assertEquals("001", agencia.obterIdentificador());
		assertEquals("Centro", agencia.obterNome());
		assertEquals(banco, agencia.obterBanco());
	}
}
