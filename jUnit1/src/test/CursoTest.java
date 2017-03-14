package test;

import static org.junit.Assert.*;

import model.Curso;

import org.junit.Test;

public class CursoTest {

	@Test()
	public void verificarListaAlunosCriada() {
		Curso computacao = new Curso("Computação");
		assertNotNull(computacao.getAlunos());
	}

	
//	@Test
//	public void verificar2AlunosNoCurso() {
//		Curso computacao = new Curso("Computação");
//		
//	}
	
}
