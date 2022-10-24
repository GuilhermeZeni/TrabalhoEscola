	package br.edu.unoesc.escolajpa.app;

	import br.edu.unoesc.escolajpa.util.JPAUtil;
import jakarta.persistence.EntityManager;

	public class App {
		private static EntityManager em;

		public static void main(String[] args) {

			em = JPAUtil.getEntityManager();
			em.close();
			JPAUtil.closeEntityManagerFactory();
		}
	}
