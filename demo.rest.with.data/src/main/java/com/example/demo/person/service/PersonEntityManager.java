package com.example.demo.person.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonEntityManager {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void addAPerson(Person person) {
		em.persist(person);
	}


	public Person findPersonById(int id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAllPersons(int page, int size) {
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

}
