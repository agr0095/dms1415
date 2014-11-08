package ubu.lsi.dms.agenda.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;
import ubu.lsi.dms.agenda.persistence.BinaryFacade;

/**
 * Clase que contiene tests para probar la persistencia con ficheros binarios.
 * 
 * @author Plamen Petkov
 * 
 */
public class BinaryFacadeTest {

	private static Call[] calls = new Call[6];
	private static Contact[] contacts = new Contact[4];
	private static ContactType[] contactTypes = new ContactType[3];

	/**
	 * Crea varias llamadas de prueba.
	 */
	private static void createCalls() {
		calls[0] = new Call(1, contacts[3], "06/11/2014", "subject", "note");
		calls[1] = new Call(2, contacts[0], "01/11/2014", "subject", "note");
		calls[2] = new Call(3, contacts[2], "29/10/2014", "subject", "note");
		calls[3] = new Call(4, contacts[3], "05/11/2014", "subject", "note");
		calls[4] = new Call(5, contacts[1], "23/09/2014", "subject", "note");
		calls[5] = new Call(6, contacts[0], "09/09/2014", "subject", "note");
	}

	/**
	 * Crea varios contactos de prueba.
	 */
	private static void createContacts() {
		contacts[0] = new Contact(1, "Plamen", "Petkov", "Mr.", "C-Jerez",
				"Burgos", "Burgos", "09006", "Burgos", "España", "UBU",
				"Project Manager", "000-000-000", "Project Manager",
				"111-111-111", "222-222-222", "ppp0015", "note",
				contactTypes[2]);
		contacts[1] = new Contact(2, "Alejandro", "Gonzalez", "Mr.",
				"C-Europa", "Burgos", "Burgos", "09001", "Burgos", "España",
				"UBU", "Project Supervisor", "000-000-000",
				"Project Supervisor", "111-111-111", "222-222-222", "agr0095",
				"Nota", contactTypes[2]);
		contacts[2] = new Contact(3, "Alberto", "Vivar", "Mr.", "Street",
				"Burgos", "Burgos", "postalCode", "Burgos", "España", "UBU",
				"DB Expert", "000-000-000", "DB Expert", "111-111-111",
				"222-222-222", "ava0031", "Nota", contactTypes[2]);
		contacts[3] = new Contact(4, "Mario", "Lopez", "Mr.", "Street",
				"Burgos", "Burgos", "postalCode", "Burgos", "España", "UBU",
				"Test Designer", "000-000-000", "Test Designer", "111-111-111",
				"222-222-222", "mlj0004", "Nota", contactTypes[2]);
	}

	/**
	 * Crea varios tipos de contacto de prueba.
	 */
	private static void createContactTypes() {
		contactTypes[0] = new ContactType(1, "family member");
		contactTypes[1] = new ContactType(1, "friend");
		contactTypes[2] = new ContactType(1, "college");
	}

	/**
	 * Crea los datos para los tests.
	 */
	private static void createTestData() {
		createContactTypes();
		createContacts();
		createCalls();

		System.out.println("Test data creation OK");

	}

	private static void testInsert(BinaryFacade facade) {
		// Insert contacts
		for (int i = 0; i < contacts.length; i++)
			facade.insertContact(contacts[i]);
		// Insert Calls
		for (int i = 0; i < calls.length; i++)
			facade.insertCall(calls[i]);
		// Insert contactTypes
		for (int i = 0; i < contactTypes.length; i++)
			facade.insertContactType(contactTypes[i]);

		System.out.println("Insert test OK");
	}

	private static void testUpdate(BinaryFacade facade) {
		//New Call to update
		//subjectActualidado and noteActualizado are new
		Call call = new Call(1, contacts[3], "06/11/2014", "subjectActualizado", "noteActualizado");
		facade.updateCall(call);
		//New contact to update
		//Valladolid is new
		Contact contact = contacts[1] = new Contact(2, "Alejandro", "Gonzalez", "Mr.",
				"C-Europa", "Valladolid", "Valladolid", "09001", "Valladolid", "España",
				"UBU", "Project Supervisor", "000-000-000",
				"Project Supervisor", "111-111-111", "222-222-222", "agr0095",
				"Nota", contactTypes[2]);
		facade.updateContact(contact);
		
		//New contactType to update
		//Name workCollege is new
		ContactType ct = new ContactType(1, "workCollege");
		facade.updateContactType(ct);

		System.out.println("Update test OK");
	}

	private static void testGet(BinaryFacade facade) {
		
		facade.getContactTypes();
		facade.getContactsBySurname("Gonzalez");
		facade.getCallsByContact(contacts[0]);

		System.out.println("Get test OK");
	}

	public static void main(String args[]) {

		BinaryFacade facade = (BinaryFacade) BinaryFacade.getInstance();

		createTestData();
		
		prepareTestScenario();
		
		testInsert(facade);
		testUpdate(facade);
		testGet(facade);

	}

	/**
	 * It deletes .dat files in case they exist
	 */
	private static void prepareTestScenario() {
		//TODO Es código algo "sucio" esto de poner las rutas directamente.
		// Sirve para test pero mejorear si hay tiempo
		File file;
		file = new File(".\\rsc\\calls.dat");
		if(file.exists())
			file.delete();
		file = new File(".\\rsc\\contacts.dat");
		if(file.exists())
			file.delete();
		file = new File(".\\rsc\\contactTypes.dat");
		if(file.exists())
			file.delete();
		
	}
}
