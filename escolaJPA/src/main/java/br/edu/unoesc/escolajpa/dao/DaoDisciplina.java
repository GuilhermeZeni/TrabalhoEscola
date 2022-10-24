package br.edu.unoesc.escolajpa.dao;

import java.util.List;

import br.edu.unoesc.escolajpa.model.Disciplina;
import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class DaoDisciplina {
	private EntityManager em;

	public DaoDisciplina() {
		em = JPAUtil.getEntityManager();
	}

	// mÃ©todos privados
	private DaoDisciplina abrirTransacao() {
		em.getTransaction().begin();
		return this;
	}
	private DaoDisciplina fecharTransacao() {
		em.getTransaction().commit();
		return this;
	}
	private DaoDisciplina incluir(Disciplina d) {
		em.persist(d);
		return this;
	}
	private DaoDisciplina remover(Disciplina d) {
		em.remove(d);
		return this;
	}
	// mÃ©todos pÃºblicos
	public DaoDisciplina salvar(Disciplina d) {
		return this.abrirTransacao().incluir(d).fecharTransacao();
	}
	public DaoDisciplina excluir(Disciplina d) {
		return this.abrirTransacao().remover(d).fecharTransacao();
	}
	public List<Disciplina> obterTodos() {
		String jpql = "SELECT d FROM Disciplina d";
		return em.createQuery(jpql, Disciplina.class).getResultList();
	}
	public Disciplina buscarPorId(Integer id) {
		return em.find(Disciplina.class, id);
	}
	public List<Disciplina> buscarPorNome(String nome) {
		String jpql = "SELECT d FROM Disciplina d WHERE d.nome LIKE :nome";
		TypedQuery<Disciplina> consulta = em.createQuery(jpql, Disciplina.class);
		consulta.setParameter("nome", "%" + nome + "%");
		return consulta.getResultList();
	}
	public void fechar() {
		em.close();
	}
}
