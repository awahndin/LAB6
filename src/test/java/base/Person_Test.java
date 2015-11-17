package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {

	private static PersonDomainModel person1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1972-07-31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		person1 = new PersonDomainModel();
		person1.setFirstName("Awah");
		person1.setLastName("Ndingwan");
		person1.setBirthday(dDate);
		person1.setCity("Greensboror");
		person1.setPostalCode(10101);
		person1.setStreet("3301 BristleCone Road");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		PersonDomainModel per;	
		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());
		assertNull("The Person shouldn't have been in the database",per);		
	}
	
	@Test
	public void AddPersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(person1);	
		
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
	}
	
	@Test
	public void UpdatePersonTest()
	{		
		PersonDomainModel per;
		final String C_LASTNAME = "Smith";
		
		per = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);		
		PersonDAL.addPerson(person1);	
		
		person1.setLastName(C_LASTNAME);
		PersonDAL.updatePerson(person1);
		
		per = PersonDAL.getPerson(person1.getPersonID());

		assertTrue("Name Didn't Change",person1.getLastName() == C_LASTNAME);
	}

	@Test
	public void DeletePersonTest()
	{		
		PersonDomainModel per;		
		per = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
		PersonDAL.addPerson(person1);			
		per = PersonDAL.getPerson(person1.getPersonID());
		System.out.println(person1.getPersonID() + " found");
		assertNotNull("The Person should have been added to the database",per);
		
		PersonDAL.deletePerson(person1.getPersonID());
		per = PersonDAL.getPerson(person1.getPersonID());		
		assertNull("The Person shouldn't have been in the database",per);	
		
	}
	
}
