package br.ufsc.es.projetoPoquer.modelo.jogador;

import br.ufsc.es.projetoPoquer.modelo.Poquer.DinheiroFicticio;
import br.ufsc.es.projetoPoquer.modelo.jogador.dados.ChaveDeSecao;

public final class JogadorComum implements TipoJogador {
	
	private ChaveDeSecao chaveDeSeção;
	private DinheiroFicticio dinheiroFictício;
	
	public JogadorComum(DinheiroFicticio dinheiroFictício) {
		this.dinheiroFictício = dinheiroFictício;
	}

	public ChaveDeSecao gerarChaveDeSeção() {
		chaveDeSeção = new ChaveDeSecao();
		
		return chaveDeSeção;
	}

	public boolean validarChaveDeSeção(ChaveDeSecao outraChaveDeSeção) {
		return chaveDeSeção.éIgual(outraChaveDeSeção);
	}

	public DinheiroFicticio pagarEntradaNoTorneio(int valorDaEntrada) {
		return dinheiroFictício.removerDoMontante(valorDaEntrada);
	}

	public void receberDinheiroFictício(DinheiroFicticio dinheiroFictício) {
		this.dinheiroFictício.adicionarAoMontante(dinheiroFictício);
	}
}