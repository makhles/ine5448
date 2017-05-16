package br.ufsc.es.projetoPoquer.modelo.colecaoMapa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class ConjuntoColecaoMapaAbstrato<K, V, F> implements TipoColecaoMapa<K, V, F, Set<F>>, Iterable<K> {
	
	private Map<K, V> valores;
	private Set<K> chavesDosValores;
	
	public ConjuntoColecaoMapaAbstrato() {
		this.valores = new HashMap<K, V>();
		this.chavesDosValores = new HashSet<K>();
	}
	
	public final void adicionar(K chave, V valor) {
		valores.put(chave, valor);
		chavesDosValores.add(chave);
	}
	
	public final void remover(K chave) {
		chavesDosValores.remove(valores.get(chave));
		valores.remove(chave);
	}

	public final V pegar(K chave) {
		return valores.get(chave);
	}

	public final boolean contém(K chave) {
		return valores.containsKey(chave);
	}
	
	public final Set<F> fornecerObjetosFeijão() {
		return fornecerObjetosFeijãoConcreto(chavesDosValores);
	}
	
	public Iterator<K> iterator() {
		return chavesDosValores.iterator();
	}
	
	public int fornecerQuantidade() {
		return chavesDosValores.size();
	}
	
	protected abstract Set<F> fornecerObjetosFeijãoConcreto(Set<K> chavesDosValores);
}