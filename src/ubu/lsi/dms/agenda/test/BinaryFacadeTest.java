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
		contacts[0] = new Contact(1, "Plamen", "Petkov", "Mr.",
				"C-Jerez", "Burgos", "Burgos", "09006", "Burgos", "España",
				"UBU", "Project Manager", "000-000-000", "Project Manager", "111-111-111",
				"222-222-222", "ppp0015", "note", contactTypes[2]);
		contacts[1] = new Contact(2, "Alejandro", "Gonzalez", "Mr.",
				"C-Europa", "Burgos", "Burgos", "09001", "Burgos", "España",
				"UBU", "Project Supervisor", "000-000-000", "Project Supervisor", "111-111-111",
				"222-222-222", "agr0095", "Nota", contactTypes[2]);
		contacts[2] = new Contact(3, "Alberto", "Vivar", "Mr.",
				"Street", "Burgos", "Burgos", "postalCode", "Burgos", "España",
				"UBU", "DB Expert", "000-000-000", "DB Expert", "111-111-111",
				"222-222-222", "ava0031", "Nota", contactTypes[2]);
		contacts[2] = new Contact(4, "Mario", "Lopez", "Mr.",
				"Street", "Burgos", "Burgos", "postalCode", "Burgos", "España",
				"UBU", "Test Designer", "000-000-000", "Test Designer", "111-111-111",
				"222-222-222", "mlj0004", "Nota", contactTypes[2]);
	}
	
	/**
	 * Crea varios tipos de contacto de prueba.
	 */
	private static void createContactTypes() {
		contactTypes[0] = new ContactType(1, "family member");
		contactTypes[1] = new ContactType(1, "friend");
		contactTypes[2] = new ContactType(1, "colleague");
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
	
	private static void testInsert(BinaryFacade facade){
			facade.insertContact(contacts[0]);
//			facade.insertContact(contacts[1]);
//			El metodo insert sobreescribe el archivo
		System.out.println("Insert test OK");
	}
	
	private static void testUpdate(BinaryFacade facade){}
	
	private static void testGet(BinaryFacade facade){}

	public static void main(String args[]) {

		BinaryFacade facade = (BinaryFacade) BinaryFacade.getInstance();
		
		createTestData();
		
		testInsert(facade);
		testUpdate(facade);
		testGet(facade);

	}
}
