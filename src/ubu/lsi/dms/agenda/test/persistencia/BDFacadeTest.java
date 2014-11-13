package ubu.lsi.dms.agenda.test.persistencia;

import java.util.ArrayList;
import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;
import ubu.lsi.dms.agenda.persistence.DBFactory;
import ubu.lsi.dms.agenda.persistence.PersistenceFacade;
import ubu.lsi.dms.agenda.persistence.PersistenceFactory;

/**
 * Contiene los métodos necesarios para probar la persistencia sobre bases de
 * datos.
 * 
 * @author <a href="mailto:ava0031@alu.ubu.es">Alberto Vivar Arribas</a>
 */
public class BDFacadeTest {
	/**
	 * Creamos la fábrica de persistencia
	 */
	private static PersistenceFactory factory = DBFactory.getInstance();
	/**
	 * Generamos la instancia de la fachada de persistencia.
	 */
	private static PersistenceFacade dbf = factory.createPersistenceFacade();

	/**
	 * Generamos los datos que vamos a emplear.
	 */
	private static CommonData datos = CommonData.getInstance();

	/**
	 * Separamos los contactos, las llamadas y los tipos de contactos.
	 */
	private static List<Contact> contactListFromCode = datos.getContactList();
	private static List<Call> callListFromCode = datos.getCallList();
	private static List<ContactType> contactTypeListFromCode = datos
			.getContactTypeList();

	public static void main(String[] args) {
		/*
		 * Si las aserciones no están activadas en la máquina virtual no tiene
		 * sentido lanzar las pruebas.
		 */
		if (!areAssertsEnabled()) {
			System.out
					.println("Para que las pruebas funcionen correctamente,\n"
							+ "activa las aserciones con -ea como parámetro de la máquina virtual de java,\n"
							+ "además de tener las tablas creadas y vacías...");
			System.err.println("Pruebas abortadas!!!");
			return;
		}
		// Las pruebas en sí
		System.out.println("Comenzando pruebas...\n");
		pruebasTiposDeContacto();
		System.out.println("Pruebas de tipos de contactos pasadas!!!");
		pruebasContactos();
		System.out.println("Pruebas de contactos pasadas!!!");
		pruebasLLamadas();
		System.out.println("Pruebas de llamadas pasadas!!!");
		System.out
				.println("Enhorabuena, todas las pruebas de BBDD han salido correctamente!!!");
	}

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

	/**
	 * Pruebas que se realizan a los tipos de contacto.
	 */
	private static void pruebasTiposDeContacto() {
		/*
		 * Creamos la lista donde almacenar lo que obtengamos de la BBDD.
		 */
		List<ContactType> contactTypeListFromDB = new ArrayList<ContactType>();
		/*
		 * Insertamos los tipos de contacto en la BBDD y comprobamos que estén
		 * todos correctamente.
		 */
		for (ContactType contactType : contactTypeListFromCode) {
			dbf.insertContactType(contactType);
		}
		contactTypeListFromDB.addAll(dbf.getContactTypes());
		assert contactTypeListFromCode.containsAll(contactTypeListFromDB);
		contactTypeListFromDB.clear();

		/*
		 * Actualizamos los tipos de contacto y comprobamos que los cambios se
		 * hayan reflejado en la BBDD.
		 */
		for (ContactType contactType : contactTypeListFromCode) {
			contactType.setTipoContacto(contactType.getTipoContacto() + "1");
			dbf.updateContactType(contactType);
		}
		contactTypeListFromDB.addAll(dbf.getContactTypes());
		assert contactTypeListFromCode.containsAll(contactTypeListFromDB);
		contactTypeListFromDB.clear();
	}

	/**
	 * Pruebas que se realizan a las llamadas.
	 */
	private static void pruebasLLamadas() {
		/*
		 * Creamos la lista donde almacenar lo que obtengamos de la BBDD.
		 */
		List<Call> callListFromDB = new ArrayList<Call>();
		/*
		 * Insertamos las llamadas en la BBDD y comprobamos que estén todos
		 * correctamente.
		 */
		for (Call call : callListFromCode) {
			dbf.insertCall(call);
			callListFromDB.addAll(dbf.getCallsByContact(call.getContacto()));
		}
		assert callListFromCode.containsAll(callListFromDB);
		callListFromDB.clear();

		/*
		 * Actualizamos las llamadas y comprobamos que los cambios se hayan
		 * reflejado en la BBDD.
		 */
		for (Call call : callListFromCode) {
			// Cambiamos un campo, como por ejemplo el de las notas
			call.setNotas(call.getNotas() + "1");
			dbf.updateCall(call);
			callListFromDB.addAll(dbf.getCallsByContact(call.getContacto()));
		}
		assert callListFromCode.containsAll(callListFromDB);
		callListFromDB.clear();
	}

	/**
	 * Pruebas que se realizan a los contactos
	 */
	private static void pruebasContactos() {
		/*
		 * Creamos la lista donde almacenar lo que obtengamos de la BBDD
		 */
		List<Contact> contactListFromDB = new ArrayList<Contact>();
		List<Contact> contactListListFromDB = new ArrayList<Contact>();
		/*
		 * Insertamos los contactos en la BBDD y comprobamos que estén todos
		 * correctamente.
		 */
		for (Contact contact : contactListFromCode) {
			dbf.insertContact(contact);
			contactListListFromDB.addAll(dbf.getContactsBySurname(contact
					.getApellidos()));
			contactListFromDB.add(dbf.getContact(contact.getApellidos()));
		}
		assert contactListFromCode.containsAll(contactListListFromDB);
		assert contactListFromCode.containsAll(contactListFromDB);
		contactListListFromDB.clear();
		contactListFromDB.clear();

		/*
		 * Actualizamos los contactos y comprobamos que los cambios se hayan
		 * reflejado en la BBDD.
		 */
		for (Contact contact : contactListFromCode) {
			// Cambiamos un campo, por ejemplo los apellidos
			contact.setApellidos(contact.getApellidos() + "1");
			dbf.updateContact(contact);
			contactListListFromDB.addAll(dbf.getContactsBySurname(contact
					.getApellidos()));
			contactListFromDB.add(dbf.getContact(contact.getApellidos()));
		}
		assert contactListFromCode.containsAll(contactListListFromDB);
		assert contactListFromCode.containsAll(contactListFromDB);
		contactListListFromDB.clear();
		contactListFromDB.clear();
	}

}
