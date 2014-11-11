package ubu.lsi.dms.agenda.test;

import java.util.ArrayList;
import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;
import ubu.lsi.dms.agenda.persistence.DBFacade;
import ubu.lsi.dms.agenda.persistence.PersistenceFacade;

/**
 * Contiene los métodos necesarios para probar
 * la persistencia sobre bases de datos.
 * 
 * @author killer7129
 *
 */
public class BDFacadeTest {
	/**
	 * Generamos la instancia de la fachada de persistencia.
	 */
	private static PersistenceFacade dbf = DBFacade.getInstance();
	
	/**
	 * Generamos los datos que vamos a emplear.
	 */
	private static CommonData datos = CommonData.getInstance();
	
	/**
	 * Separamos los contactos, las llamadas y los tipos de contactos.
	 */
	private static List<Contact> contactListFromCode = datos.getContactList();
	private static List<Call> callListFromCode = datos.getCallList();
	private static List<ContactType> contactTypeListFromCode = datos.getContactTypeList();
	

	public static void main(String[] args) {
		System.out.println("Para que las pruebas funcionen correctamente,\n" +
	"activa las aserciones con -ea como parámetro de la máquina virtual de java,\n"
	+ "además de tener las tablas creadas y vacías...");
		System.out.println("Comenzando pruebas...\n");
		pruebasTiposDeContacto();
		System.out.println("Pruebas de tipos de contactos pasadas!!!");
		pruebasContactos();
		System.out.println("Pruebas de contactos pasadas!!!");
		pruebasLLamadas();
		System.out.println("Pruebas de llamadas pasadas!!!");
		System.out.println("Enhorabuena, todas las pruebas de BBDD han salido correctamente!!!");
	}

	/**
	 * Pruebas que se realizan a los tipos de contacto.
	 */
	private static void pruebasTiposDeContacto() {
		/* 
		 * Creamos la lista donde almacenar lo que
		 * obtengamos de la BBDD.
		 */
		List<ContactType> contactTypeListFromDB = new ArrayList<ContactType>();
		/*
		 * Insertamos los tipos de contacto en la BBDD
		 * y comprobamos que estén todos correctamente.
		 */
		for(ContactType contactType: contactTypeListFromCode){
			dbf.insertContactType(contactType);
		}
		contactTypeListFromDB.addAll(dbf.getContactTypes());
		assert contactTypeListFromCode.containsAll(contactTypeListFromDB);
		contactTypeListFromDB.clear();
		
		/*
		 * Actualizamos los tipos de contacto y comprobamos
		 * que los cambios se hayan reflejado en la BBDD.
		 */
		for(ContactType contactType: contactTypeListFromCode){
			contactType.setTipoContacto(contactType.getTipoContacto() + "0");
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
		 * Creamos la lista donde almacenar lo que
		 * obtengamos de la BBDD.
		 */
		List<Call> callListFromDB = new ArrayList<Call>();
		/*
		 * Insertamos las llamadas en la BBDD
		 * y comprobamos que estén todos correctamente.
		 */
		for(Call call:callListFromCode){
			dbf.insertCall(call);
			callListFromDB.addAll(dbf.getCallsByContact(call.getContacto()));
		}
		assert callListFromCode.containsAll(callListFromDB);
		callListFromDB.clear();
		
		/*
		 * Actualizamos las llamadas y comprobamos
		 * que los cambios se hayan reflejado en la BBDD.
		 */
		for(Call call:callListFromCode){
			//Cambiamos un campo, como por ejemplo el de las notas
			call.setNotas(call.getNotas() + "0");
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
		 * Creamos la lista donde almacenar lo que
		 * obtengamos de la BBDD
		 */
		List<Contact> contactListFromDB = new ArrayList<Contact>();
		List<Contact> contactListListFromDB = new ArrayList<Contact>();
		/*
		 * Insertamos los contactos en la BBDD
		 * y comprobamos que estén todos correctamente.
		 */
		for(Contact contact: contactListFromCode){
			dbf.insertContact(contact);
			contactListListFromDB.addAll(dbf.getContactsBySurname(contact.getApellidos()));
			contactListFromDB.add(dbf.getContact(contact.getApellidos()));
		}
		assert contactListFromCode.containsAll(contactListListFromDB);
		assert contactListFromCode.containsAll(contactListFromDB);
		contactListListFromDB.clear();
		contactListFromDB.clear();
		
		/*
		 * Actualizamos los contactos y comprobamos
		 * que los cambios se hayan reflejado en la BBDD.
		 */
		for(Contact contact: contactListFromCode){
			//Cambiamos un campo, por ejemplo los apellidos
			contact.setApellidos(contact.getApellidos() + "0");
			dbf.updateContact(contact);
			contactListListFromDB.addAll(dbf.getContactsBySurname(contact.getApellidos()));
			contactListFromDB.add(dbf.getContact(contact.getApellidos()));
		}
		assert contactListFromCode.containsAll(contactListListFromDB);
		assert contactListFromCode.containsAll(contactListFromDB);
		contactListListFromDB.clear();
		contactListFromDB.clear();
	}

}