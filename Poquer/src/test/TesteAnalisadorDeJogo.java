package test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AnalisadorDeJogos;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Naipe;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.RankingDeMao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Valor;

public class TesteAnalisadorDeJogo {

	@Test
	public void parDeAs() throws Exception {
		List<Carta> parDeAs = new ArrayList<Carta>();
		parDeAs.add(new Carta(Valor.ÁS, Naipe.ESPADAS));
		parDeAs.add(new Carta(Valor.ÁS, Naipe.COPAS));
		parDeAs.add(new Carta(Valor.CINCO, Naipe.PAUS));
		parDeAs.add(new Carta(Valor.SETE, Naipe.OURO));
		parDeAs.add(new Carta(Valor.NOVE, Naipe.ESPADAS));
		parDeAs.add(new Carta(Valor.VALETE, Naipe.COPAS));
		parDeAs.add(new Carta(Valor.REI, Naipe.PAUS));
		assertEquals(RankingDeMao.PAR, AnalisadorDeJogos.fornecerInstância().fornecerJogo(parDeAs));
	}

	@Test
	public void testaRoyalFlush() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.ÁS, Naipe.COPAS));
	}
}
