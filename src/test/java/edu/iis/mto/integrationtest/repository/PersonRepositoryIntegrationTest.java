package edu.iis.mto.integrationtest.repository;

import static edu.iis.mto.integrationtest.repository.PersonBuilder.person;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AssertThrows;
import org.springframework.test.annotation.DirtiesContext;

import edu.iis.mto.integrationtest.model.Person;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonRepositoryIntegrationTest extends IntegrationTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testCanAccessDbAndGetTestData() {
		List<Person> foundTestPersons = personRepository.findAll();
		assertEquals(2, foundTestPersons.size());
	}

	@Test
	public void testSaveNewPersonAndCheckIsPersisted() {
		long count = personRepository.count();
		personRepository.save(a(person().withId(count + 1)
				.withFirstName("Roberto").withLastName("Mancini")));
		assertEquals(count + 1, personRepository.count());
		assertEquals("Mancini", personRepository.findOne(count + 1)
				.getLastName());
	}
	
	@Test 
	public void deletePerson(){
		long personId = 1;
		
		if(personRepository.exists(personId)){
			personRepository.delete(personId);
		} else {
			fail("bad input data");
		}
		
		assertEquals(personRepository.exists(personId), false);
	}
	
	@Test
	public void updatePerson(){
		
		long personId = 1;
		
		Person person = personRepository.findOne(personId);
		
		String oldFirstName = person.getFirstName();
		String newFirstName = "Jan";
		
		if(newFirstName.equals(oldFirstName)){
			fail("names are the same, cannot verify updateing");
		}
		
		person.setFirstName(newFirstName);
		
		personRepository.saveAndFlush(person);
		
		Person modifiedPerson = personRepository.findOne(personId);
		
		assertEquals( newFirstName, modifiedPerson.getFirstName());
		
		
	}
	
	
	
	

	private Person a(PersonBuilder builder) {
		return builder.build();
	}

}
