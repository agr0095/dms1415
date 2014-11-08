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

	public static void main(String args[]) {
		
//		removeFiles();
		// testGet();
		testInsert();
		// testUpdate();

	}

	private static void testGet() {
		System.out.println("Get test OK");
	}

	private static void testInsert() {

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
		
		loadFile(files[0], objects);
		assert calls.size() == objects.size();
		
		loadFile(files[1], objects);
		assert contacts.size() == objects.size();
		
		loadFile(files[2], objects);
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
	}

	@SuppressWarnings("unchecked")
	private static void loadFile(File file, List<Object> list) {
		ObjectInputStream input = null;
		try {
			input = new ObjectInputStream(new FileInputStream(file));
			list = (List<Object>) input.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
