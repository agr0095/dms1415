package ubu.lsi.dms.agenda.test;

import java.io.File;
import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;
import ubu.lsi.dms.agenda.persistence.BinaryFactory;
import ubu.lsi.dms.agenda.persistence.PersistenceFacade;
import ubu.lsi.dms.agenda.persistence.PersistenceFactory;

/**
 * Clase que contiene tests para probar la persistencia con ficheros binarios.
 * 
 * @author Plamen Petkov
 * 
 */
public class BinaryFacadeTest {

	static CommonData data = CommonData.getInstance();
	static PersistenceFactory factory = BinaryFactory.getInstance();
	static PersistenceFacade facade = factory.createPersistenceFacade();
	static File[] files = { new File(".\\rsc\\calls.dat"),
			new File(".\\rsc\\contacts.dat"),
			new File(".\\rsc\\contactTypes.dat") };

	// Datos de prueba obtenidos de la clase CommonData
	static List<Call> calls = data.getCallList();
	static List<Contact> contacts = data.getContactList();
	static List<ContactType> contactTypes = data.getContactTypeList();

	public static void main(String args[]) {

		removeFiles();
		/*
		 * Si las aserciones no están activadas en la máquina virtual no tiene
		 * sentido lanzar las pruebas.
		 */
		if (!areAssertsEnabled()) {
			System.out
					.println("Para que las pruebas funcionen correctamente,\n"
							+ "activa las aserciones con -ea como parámetro de la máquina virtual de java,\n");
			System.err.println("Pruebas abortadas");
			return;
		}

		ctTest();

		contactTest();

		callTest();

		System.out.println("All tests concluded successfully!");

	} // main

	/**
	 * Test para los metodos de la fachada que trabajan con la clase Call
	 */
	private static void callTest() {
		List<Call> listOfCalls;

		// Inserta las llamadas de la lista de datos
		for (Call call : calls)
			facade.insertCall(call);

		// Obtiene las llamadas del primer contacto de la lista de datos
		assert facade.getCallsByContact(contacts.get(0)).size() == 1;

		// Actualiza las llamadas cambiando el contacto de cada llamada por el
		// segundo contacto de la lista de datos
		for (Call call : calls) {
			Call newCall = new Call(call.getIdLlamada(), contacts.get(1), "",
					"", "");
			facade.updateCall(newCall);
		}

		// Obtiene las llamadas del segundo contacto de la lista de datos
		// Comprueba que el numero de llamadas obtenidas es igual al numero
		// total de llamadas
		// Comprueba que el numero de llamadas del primer contacto de la lista
		// es 0
		listOfCalls = facade.getCallsByContact(contacts.get(1));
		assert listOfCalls.size() == calls.size();
		assert facade.getCallsByContact(contacts.get(0)).size() == 0;
	}

	/**
	 * Tests para los metodos de la fachada que trabajan con la clase
	 */
	private static void contactTest() {
		List<Contact> listOfContacts;

		// Inserta los contactos de la lista de datos
		for (Contact contact : contacts)
			facade.insertContact(contact);

		// Obtiene el contacto con apellido Apellidos001 y comprueba que es el
		// primer contacto insertado (primer contacto de la lista de datos)
		assert facade.getContact("Apellidos001").equals(contacts.get(0));
		// Comprueba que no hay contacto con apellido Petkov
		assert facade.getContact("Petkov") == null;

		// Actualiza los contactos añadiendo los mismos datos a todos los
		// contactos excepto el idContacto
		for (Contact contact : contacts) {
			Contact newContact = new Contact(contact.getIdContacto(),
					"NombreNuevo", "ApellidosNuevos", "", "", "", "", "", "",
					"", "", "", "", "", "", "", "", "", null);
			facade.updateContact(newContact);
		}

		// Obtiene los contactos con apellido ApellidosNuevos y comprueba que
		// la lista inicial no contiene ninguno de ellos
		listOfContacts = facade.getContactsBySurname("ApellidosNuevos");
		for (Contact contact : listOfContacts)
			assert !(contacts.contains(contact));
	}

	/**
	 * Tests para los metodos de la fachada que trabajan con la clase
	 */
	private static void ctTest() {
		List<ContactType> listOfCTs;

		// Inserta los tipo de contacto de la lista de datos
		for (ContactType ct : contactTypes)
			facade.insertContactType(ct);

		// Obtiene la los tipos de contacto
		listOfCTs = facade.getContactTypes();
		// Comprueba que la lista contiene a todos los tipos de contacto
		// insertados
		assert listOfCTs.containsAll(contactTypes);

		// Actualiza un tipo de contacto añadiendo la cadena "updated"
		for (ContactType ct : listOfCTs)
			facade.updateContactType(new ContactType(ct.getIdTipoContacto(), ct
					.getTipoContacto() + "updated"));

		listOfCTs = facade.getContactTypes();
		// Comprueba que todos los tipos de contacto contienen la cadena
		// "updated"
		for (ContactType ct : listOfCTs)
			assert ct.getTipoContacto().contains("updated");
	}

	/**
	 * Elimina los archivos de persistencia en caso de que existan.
	 */
	private static void removeFiles() {
		for (File file : files)
			if (file.exists())
				file.delete();
	} // removeFiles

	/**
	 * Comprueba si los asertos están activadas
	 * 
	 * @return Cierto si están activadas, falso si no.
	 */
	public static boolean areAssertsEnabled() {
		boolean isEnabled = false;
		try {
			assert false;
		} catch (AssertionError ex) {
			isEnabled = true;
		}
		return isEnabled;
	}

} // class BinaryFacadeTest
