package org.examples;

import java.time.Duration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Entity
@Table(name = "book")
@TypeDefs({
	@TypeDef(typeClass = OracleIntervalDayToSecondType.class,defaultForType=Duration.class)
})
public class Book {

	@Id
	@SequenceGenerator(sequenceName = "book_seq", name = "book_gen_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen_id")
	private Long id;

	@NaturalId
	@Column
	private String isbn;

	@Column(columnDefinition = "INTERVAL DAY (6) TO SECOND (6)")
	private Duration presalePeriod;

	public Long getId() {
		return id;
	}

	public Book setId(Long id) {
		this.id = id;
		return this;
	}

	public String getIsbn() {
		return isbn;
	}

	public Book setIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}


	public Duration getPresalePeriod() {
		return presalePeriod;
	}

	public Book setPresalePeriod(Duration presalePeriod) {
		this.presalePeriod = presalePeriod;
		return this;
	}

}
