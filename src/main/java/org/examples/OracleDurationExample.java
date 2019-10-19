package org.examples;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class OracleDurationExample {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

	public static void main(String[] args) {
		/*EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Book().setIsbn("978-9730228236")
				.setProperties("{" + "   \"title\": \"High-Performance Java Persistence\","
						+ "   \"author\": \"Vlad Mihalcea\"," + "   \"publisher\": \"Amazon\"," + "   \"price\": 44.99"
						+ "}"));
		entityManager.getTransaction().commit();
		Book book = entityManager.unwrap(Session.class).bySimpleNaturalId(Book.class).load("978-9730228236");
		System.out.println(book.getJsonNodeProperties().get("title").asText());*/
	}
}