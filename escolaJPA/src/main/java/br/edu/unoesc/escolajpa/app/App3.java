package br.edu.unoesc.escolajpa.app;

import java.util.List;

import br.edu.unoesc.escolajpa.model.Aluno;
import br.edu.unoesc.escolajpa.model.Curso;
import br.edu.unoesc.escolajpa.model.Disciplina;
import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class App3 {

	public static EntityManager em;

	public static void main(String[] args) {
		adicionarDados();

		listarPorDisciplina();

		listarPorAluno();
	}

	public static void adicionarDados() {
		em = JPAUtil.getEntityManager();

		Disciplina d1 = new Disciplina("Disciplina D1");
		Disciplina d2 = new Disciplina("Disciplina D2");

		Curso c = new Curso("Curso");

		Aluno a1 = new Aluno("Joao", c);
		Aluno a2 = new Aluno("Julia", c);
		Aluno a3 = new Aluno("Richarlison", c);

		d1.adicionarAluno(a1);
		d1.adicionarAluno(a2);

		a1.adicionarDisciplina(d1);
		a2.adicionarDisciplina(d1);
		a3.adicionarDisciplina(d2);

		em.getTransaction().begin();

		em.persist(d1);
		em.persist(c);
		em.persist(d2);
		em.persist(a1);
		em.persist(a2);
		em.persist(a3);

		em.getTransaction().commit();

		em.close();
	}

	public static void listarPorDisciplina() {
		em = JPAUtil.getEntityManager();
		String jpql = "SELECT d FROM Disciplina d";
		List<Disciplina> disciplinas = em.createQuery(jpql, Disciplina.class).getResultList();
		System.out.println("================================");
		System.out.println("Disciplinas e seus alunos");
		System.out.println("================================");
		for (Disciplina d : disciplinas) {
			System.out.println(d.getId() + " - " + d.getNome());
			for (Aluno a : d.getAlunos())
				System.out.println("\t" + a.getId() + " - " + a.getNome());
		}
	}

	public static void listarPorAluno() {
		em = JPAUtil.getEntityManager();
		String jpql = "SELECT a FROM Aluno a";
		List<Aluno> alunos = em.createQuery(jpql, Aluno.class).getResultList();
		System.out.println("================================");
		System.out.println("Alunos e suas disciplinas");
		System.out.println("================================");
		for (Aluno a : alunos) {
			System.out.println(a.getId() + " - " + a.getNome());
			for (Disciplina d : a.getdisciplinasAluno()) {
				System.out.println("\t" + d.getId() + " - " + d.getNome());
			}
		}
	}
}
	
