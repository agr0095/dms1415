package ubu.lsi.dms.agenda.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

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

	static CommonData data = CommonData.getInstance();
	static BinaryFacade facade = (BinaryFacade) BinaryFacade.getInstance();
	static File[] files = { new File(".\\rsc\\calls.dat"),
			new File(".\\rsc\\contacts.dat"),
			new File(".\\rsc\\contactTypes.dat") };
	// Datos de prueba obtenidos de la clase CommonData
	static List<Call> calls = data.getCallList();
	static List<Contact> contacts = data.getContactList();
	static List<ContactType> contactTypes = data.getContactTypeList();
	

	public static void main(String args[]) {

		removeFiles();

		testInsert();
		testGet();
		testUpdate();
		System.out.println("All tests concluded successfully!");

	}

	private static void testGet() {

		List<Call> callsByContact;
		List<ContactType> listOfCT;
		List<Contact> contactsBySurname;

		
		// Pruebas de getCallsByContact
		callsByContact = facade.getCallsByContact(contacts.get(0));
		assert callsByContact.size() == 1;
		callsByContact = facade.getCallsByContact(new Contact(0, "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", null));
		assert callsByContact.size() == 0;
		
		// Pruebas de getContact
		Contact contact = facade.getContact("Apellidos001");
		assert contact.compareTo(contacts.get(0)) == 0;
		contact = facade.getContact("Petkov");
		assert contact == null;
		
		// Pruebas de getContactsBySurname
		contactsBySurname = facade.getContactsBySurname("Apellidos001");
		assert contactsBySurname.size() == 1;
		facade.insertContact(new Contact(0, "", "Apellidos001", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "", "", null));
		contactsBySurname = facade.getContactsBySurname("Apellidos001");
		assert contactsBySurname.size() == 2;
		contactsBySurname = facade.getContactsBySurname("Petkov");
		assert contactsBySurname.size() == 0;
		
		// Pruebas de getContactTypes
		listOfCT = facade.getContactTypes();
		assert listOfCT.size() == contactTypes.size();
		
		System.out.println("Get test OK");
	}

	/**
	 * Prueba los métodos de inertar de la fachada de persistencia binaria.
	 */
	private static void testInsert() {

		// Almacena los objetos de un fichero de persistencia binario
		List<Object> objects = null;

		for (Call call : calls)
			facade.insertCall(call);

		for (Contact contact : contacts)
			facade.insertContact(contact);

		for (ContactType ct : contactTypes)
			facade.insertContactType(ct);

		// files[0] corresponde a rsc/calls.dat
		objects = loadFile(files[0]);
		assert calls.size() == objects.size();

		// files[1] corresponde a rsc/contactTypes.dat
		objects = loadFile(files[1]);
		assert contacts.size() == objects.size();

		// files[2] corresponde a rsc/contactTypes.dat
		objects = loadFile(files[2]);
		assert contactTypes.size() == objects.size();

		System.out.println("Insert test OK");

	}// testInsert

	private static void testUpdate() {
		// TODO
		System.out.println("Update test OK");
	} // testUpdate

	/**
	 * Elimina los archivos de persistencia en caso de que existan.
	 */
	private static void removeFiles() {
		for (File file : files)
			if (file.exists())
				file.delete();
	} // removeFiles

	/**
	 * Carga un archivo de persistencia en una lista de objetos.
	 * 
	 * @param file
	 *            el archivo a cargar
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	private static List<Object> loadFile(File file) {
		ObjectInputStream input = null;
		List<Object> list = new ArrayList<Object>();
		try {
			input = new ObjectInputStream(new FileInputStream(file));
			list = (List<Object>) input.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	} // loadFile

} // class BinaryFacadeTest
