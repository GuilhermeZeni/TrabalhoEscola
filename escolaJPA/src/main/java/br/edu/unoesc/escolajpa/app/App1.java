package br.edu.unoesc.escolajpa.app;


	import br.edu.unoesc.escolajpa.model.Aluno;
import br.edu.unoesc.escolajpa.model.Curso;
import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;

	public class App1 {
		private static EntityManager em;
		
		public static void main(String[] args) {
			em = JPAUtil.getEntityManager();
			
			Curso c1 = new Curso("fisio");
			Curso c2 = new Curso( "computacao");
			
			Aluno A1 = new Aluno("Maria");
			Aluno A2 = new Aluno("Joao");
			Aluno A3 = new Aluno("Neymar");
			
			em.getTransaction().begin();
			
			em.persist(c1);
			em.persist(c2);
			
			em.persist(A1);
			em.persist(A2);
			em.persist(A3);		
			em.getTransaction().commit();
			
			em.close();
			JPAUtil.closeEntityManagerFactory();
		}
	}
	

