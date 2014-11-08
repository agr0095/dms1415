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

	public static void main(String args[]) {

		// testGet();
		 testInsert();
		// testUpdate();

	}

	private static void testGet() {
		System.out.println("Get test OK");
	}

	@SuppressWarnings({ "unchecked", "resource" })
	private static void testInsert() {

		ObjectInputStream input = null;
		List<Object> objects = new ArrayList<Object>();

		List<Call> calls = data.getCallList();
		List<Contact> contacts = data.getContactList();
		List<ContactType> contactTypes = data.getContactTypeList();

		for (Call call : calls)
			facade.insertCall(call);

		for (Contact contact : contacts)
			facade.insertContact(contact);

		for (ContactType ct : contactTypes)
			facade.insertContactType(ct);

		try {
			input = new ObjectInputStream(new FileInputStream(
					".\\rsc\\calls.dat"));
			objects = (List<Object>) input.readObject();
			// Postcondicion: número de llamadas insertadas igual al número de
			// objetos recuperados
			assert calls.size() == objects.size();

			input = new ObjectInputStream(new FileInputStream(
					".\\rsc\\contacts.dat"));
			objects = (List<Object>) input.readObject();
			// Postcondicion: número de contactos insertados igual al número de
			// objetos recuperados
			assert contacts.size() == objects.size();

			input = new ObjectInputStream(new FileInputStream(
					".\\rsc\\contactTypes.dat"));
			objects = (List<Object>) input.readObject();
			// Postcondicion: número de tipos insertados igual al número de
			// objetos recuperados
			assert contactTypes.size() == objects.size();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		System.out.println("Insert test OK");

	}// testInsert

	private static void testUpdate() {
		// TODO
		System.out.println("Update test OK");
	} // testUpdate

}
