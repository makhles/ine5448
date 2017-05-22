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

	// ---- Teste do caminho 1-2-3-4-5-6
	// ---- O teste falha - há algum erro no código!
	@Test
	public void testaRoyalFlush() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.COPAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.COPAS));
		cartas.add(new Carta(Valor.REI, Naipe.COPAS));
		cartas.add(new Carta(Valor.ÁS, Naipe.COPAS));
		assertEquals(RankingDeMao.ROYAL_FLUSH, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-4-5-7
	@Test
	public void testaStraightFlush() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.NOVE, Naipe.COPAS));
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.COPAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.COPAS));
		assertEquals(RankingDeMao.STRAIGHT_FLUSH, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}


	// ---- Teste do caminho 1-2-3-8-(9-17-18-19-20-22)-[(9-17-18-20-22)^(n-3)]-9-17-18-20-21-22-9-10-
	// -11-12-16-11-12-15-16-11-23-24-25
	// Onde n é o número de cartas iguais. Subtrai-se de 3, pois, como as cartas estão ordenadas,
	// na primeira passagem encontra-se uma carta diferente em "i+1" (número 19 no GFC); na última passagem
	// entra-se no número 21 do GFC, e o laço FOR percorre o tamanho da lista menos 1.
	@Test
	public void testaQuadra() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.DAMA, Naipe.COPAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.PAUS));
		cartas.add(new Carta(Valor.DAMA, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE, Naipe.COPAS));
		assertEquals(RankingDeMao.QUADRA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-(9-17-18-20-22)-(9-17-18-19-20-22)-(9-17-18-20-22)-9-17-18-20-21-22-9-10-
	// -11-12-13-16-11-12-14-16-11-23-24-26-27-28-29-30-31-28-32
	@Test
	public void testaFullHouse() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.DAMA, Naipe.COPAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.PAUS));
		cartas.add(new Carta(Valor.DAMA, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE, Naipe.COPAS));
		assertEquals(RankingDeMao.FULL_HOUSE, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-[(9-17-18-19-20-22)^3]-(9-17-18-20-21-22)-9-10-
	// -[(11-12-16)^5]-11-23-24-26-33-34
	@Test
	public void testaFlush() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.DOIS, Naipe.COPAS));
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.QUATRO, Naipe.COPAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.COPAS));
		assertEquals(RankingDeMao.FLUSH, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-[(9-17-18-19-20-22)^3]-(9-17-18-20-21-22)-9-10-
	// -[(11-12-16)^5]-11-23-24-26-33-35-36
	@Test
	public void testaSequencia() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.NOVE, Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.COPAS));
		assertEquals(RankingDeMao.STRAIGHT, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-[(9-17-18-20-22)^3]-(9-17-18-19-20-22)-(9-17-18-19-20-21-22)-9-10-
	// -[(11-12-16)^3]-(11-12-14-16)-11-23-24-26-33-35-37-38-[(39-40-41)^3]-39-42-44
	@Test
	public void testaTrinca() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.OITO, Naipe.PAUS));
		cartas.add(new Carta(Valor.OITO, Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.REI, Naipe.COPAS));
		assertEquals(RankingDeMao.TRINCA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-[(9-17-18-20-22)^3]-[(9-17-18-19-20-22)^2]-(9-17-18-19-20-21-22)-9-10-
	// -[(11-12-16)^4]-(11-12-14-16)-11-23-24-26-33-35-37-38-[(39-40-41)^4]-39-(42-43)-42-44
	@Test
	public void testaTrincaComMuitasCartas() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.OITO, Naipe.PAUS));
		cartas.add(new Carta(Valor.OITO, Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.DAMA, Naipe.PAUS));
		cartas.add(new Carta(Valor.REI, Naipe.COPAS));
		assertEquals(RankingDeMao.TRINCA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-(9-17-18-20-22)-[(9-17-18-19-20-22)^2]-(9-17-18-20-21-22)-9-10-
	// -(11-12-16)-[(11-12-13-16)^2]-11-23-24-26-33-35-37-45-46
	@Test
	public void testaParMaiorQueUm() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.OITO, Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.COPAS));
		assertEquals(RankingDeMao.DOIS_PARES, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-(9-17-18-20-22)-[(9-17-18-19-20-22)^2]-(9-17-18-19-20-21-22)-9-10-
	// -[(11-12-16)^3]-(11-12-13-16)-11-23-24-26-33-35-37-45-47-48
	@Test
	public void testaParMaiorQueZero() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.OITO, Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.REI, Naipe.COPAS));  // Gera IndexOutOfBounds utilizando DAMA!!!!!
		assertEquals(RankingDeMao.PAR, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-[(9-17-18-19-20-22)^3]-(9-17-18-19-20-21-22)-9-10-
	// -[(11-12-16)^5]-11-23-24-26-33-35-37-45-47-49-51
	@Test
	public void testaCartaAlta() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.CINCO, Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.REI, Naipe.COPAS));
		assertEquals(RankingDeMao.CARTA_ALTA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}

	// ---- Teste do caminho 1-2-3-8-[(9-17-18-19-20-22)^6]-(9-17-18-19-20-21-22)-9-10-
	// -[(11-12-16)^8]-11-23-24-26-33-35-37-45-47-[(49-50)^3]-49-51
	@Test
	public void testaCartaAltaComMuitasCartas() throws Exception {
		List<Carta> cartas = new ArrayList<Carta>();
		cartas.add(new Carta(Valor.OITO, Naipe.COPAS));
		cartas.add(new Carta(Valor.TRÊS, Naipe.OURO));
		cartas.add(new Carta(Valor.CINCO, Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, Naipe.PAUS));
		cartas.add(new Carta(Valor.SEIS, Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, Naipe.COPAS));
		cartas.add(new Carta(Valor.VALETE, Naipe.ESPADAS));
		cartas.add(new Carta(Valor.REI, Naipe.COPAS));
		assertEquals(RankingDeMao.CARTA_ALTA, AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
}
