package br.edu.unoesc.escolajpa.app;

import java.util.List;

import br.edu.unoesc.escolajpa.dao.DaoAluno;
import br.edu.unoesc.escolajpa.dao.DaoCurso;
import br.edu.unoesc.escolajpa.model.Aluno;
import br.edu.unoesc.escolajpa.model.Curso;

public class App2 {

	public static void main(String[] args) {

		DaoCurso daoCurso = new DaoCurso();

		Curso curso1 = new Curso("fisio");
		Curso curso2 = new Curso("computacao");

		daoCurso.salvar(curso1).salvar(curso2);

		DaoAluno daoAluno = new DaoAluno();
		Aluno aluno1 = new Aluno("joao", curso1);
		Aluno aluno2 = new Aluno("Thomas", curso1);
		Aluno aluno3 = new Aluno("Casemiro", curso2);

		daoAluno.salvar(aluno1).salvar(aluno2).salvar(aluno3);

		System.out.println(daoAluno.buscarPorId(1));
		System.out.println(daoAluno.buscarPorId(2));

		List<Aluno> alunos = daoAluno.obterTodos();
		System.out.println("Aluno\tCurso");
		for (Aluno a : alunos) {
			System.out.println(a.getNome() + "\t\t" + a.getCurso().getNome());
		}

		List<Curso> cursos = daoCurso.obterTodos();
		System.out.println("Cursos e Alunos");
		for (Curso c : cursos) {
			System.out.println(c.getNome());
			for (Aluno a : c.getAlunos()) {
				System.out.println("\t" + a.getNome());
			}
		}

		daoCurso.fechar();
		daoAluno.fechar();

	}
}
