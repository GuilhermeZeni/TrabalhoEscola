package br.edu.unoesc.escolajpa.dao;

	import java.util.List;

import br.edu.unoesc.escolajpa.model.Aluno;
import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

	public class DaoAluno {
		private EntityManager em;

		public DaoAluno() {
			em = JPAUtil.getEntityManager();
		}
		// métodos privados
		private DaoAluno abrirTransacao() {
			em.getTransaction().begin();
			return this;
		}
		private DaoAluno fecharTransacao() {
			em.getTransaction().commit();
			return this;
		}
		private DaoAluno incluir(Aluno a) {
			em.persist(a);
			return this;
		}
		private DaoAluno remover(Aluno a) {
			em.remove(a);
			return this;
		}
		// métodos públicos
		public DaoAluno salvar(Aluno a) {
			return this.abrirTransacao().incluir(a).fecharTransacao();
		}
		public DaoAluno excluir(Aluno a) {
			return this.abrirTransacao().remover(a).fecharTransacao();
		}
		public List<Aluno> obterTodos() {
			String jpql = "SELECT a FROM Aluno a";
			return em.createQuery(jpql, Aluno.class).getResultList();
		}
		public Aluno buscarPorId(Integer id) {
			return em.find(Aluno.class, id);
		}
		public List<Aluno> buscarPorNome(String nome) {
			String jpql = "SELECT a FROM Aluno a WHERE a.nome LIKE :nome";
			TypedQuery<Aluno> consulta = em.createQuery(jpql, Aluno.class);
			consulta.setParameter("nome", "%" + nome + "%");
			return consulta.getResultList();
		}

		public void fechar() {
			em.close();
		}

	}

