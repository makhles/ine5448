package matchers;

import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Transacao;

public class ContaMatcher extends BaseMatcher<Conta> implements Matcher<Conta> {

	private String titular;
	private String codigo;
	private Agencia agencia;
	private List<Transacao> transacoes;

	public ContaMatcher(String titular, String codigo, Agencia agencia,
			List<Transacao> transacoes) {
		super();
		this.titular = titular;
		this.codigo = codigo;
		this.agencia = agencia;
		this.transacoes = transacoes;
	}

	@Override
	public boolean matches(Object obj) {
		if (obj != null && obj instanceof Conta) {
			Conta outra = (Conta) obj;
			return titular.equals(outra.obterTitular()) &&
					codigo.equals(outra.obterIdentificador()) &&
					agencia.equals(outra.obterAgencia());  // transações?
		}
		return false;
	}

	@Override
	public void describeTo(Description descricao) {
		descricao.appendText("Titular de nome ");
		descricao.appendValue(titular);
		descricao.appendText(" e código ");
		descricao.appendValue(codigo);
		descricao.appendText(", pertencente à agência ");
		descricao.appendValue(agencia.obterNome());
	}

	@Override
	public void describeMismatch(Object item, Description descricao) {
		if (item != null && item instanceof Conta) {
			Conta outra = (Conta) item;
			descricao.appendText("Titular de nome ");
			descricao.appendValue(outra.obterTitular());
			descricao.appendText(" e código ");
			descricao.appendValue(outra.obterIdentificador());
			descricao.appendText(", pertencente à agência ");
			descricao.appendValue(outra.obterAgencia().obterNome());
		
		}
		super.describeMismatch(item, descricao);
	}
}
