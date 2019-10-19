package org.examples;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class JsonStringMain {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

	public static void main(String[] args) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		/*entityManager.persist(new Book().setIsbn("978-9730228236")
				.setProperties("{" + "   \"title\": \"High-Performance Java Persistence\","
						+ "   \"author\": \"Vlad Mihalcea\"," + "   \"publisher\": \"Amazon\"," + "   \"price\": 44.99"
						+ "}"));
		Book book = entityManager.unwrap(Session.class).bySimpleNaturalId(Book.class).load("978-9730228236");
		System.out.println(book.getJsonNodeProperties().get("title").asText());*/
		entityManager.getTransaction().begin();		
		entityManager.persist(
			    new Book()
			        .setIsbn("978-9730228236")
			        .setPresalePeriod(
			            Duration.between(
			                LocalDate
			                    .of(2015, Month.NOVEMBER, 2)
			                    .atStartOfDay(),
			                LocalDate
			                    .of(2016, Month.AUGUST, 25)
			                    .atStartOfDay()
			            )
			        )
			);
		entityManager.getTransaction().commit();
		Book book = entityManager.unwrap(Session.class).bySimpleNaturalId(Book.class).load("978-9730228236");
		assertEquals(
			    Duration.between(
			        LocalDate
			            .of(2015, Month.NOVEMBER, 2)
			            .atStartOfDay(),
			        LocalDate
			            .of(2016, Month.AUGUST, 25)
			            .atStartOfDay()
			    ),
			    book.getPresalePeriod()
			);
	}
}