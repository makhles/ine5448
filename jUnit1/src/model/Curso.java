package model;

import java.util.List;

public class Curso {

	private String nome;
	private List<Aluno> alunos;

	public Curso(String nome) {
		this.nome = nome;
	}

	public void addAluno(Aluno aluno) {
//		if (alunos.contains(aluno)) {
//			
//		}
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	
}
