package ubu.lsi.dms.agenda.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

public class DBFacade implements PersistenceFacade {

	private DBFacade singleInstance;

	/**
	 * Constructor de la fachada: hemos impedido su uso para seguir el patrón
	 * singleton.
	 */
	private DBFacade() {
		// Constructor no visible
	}

	/**
	 * Devuelve la referencia a la única instancia que debería existir de la
	 * DBFacade.
	 * 
	 * @return instancia de DBFacade.
	 */
	@Override
	public PersistenceFacade createPersistenceFacade() {
		if (singleInstance == null)
			singleInstance = new DBFacade();
		return singleInstance;
	}

	@Override
	public Contact getContact(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertCall(Call call) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertContactType(ContactType ct) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCall(Call call) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateContactType(ContactType ct) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Contact> getContactsBySurname(String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Call> getCallsByContact(Contact contacto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContactType> getContactTypes() {
		// Creamos una lista para meter los tipos de contacto
		List<ContactType> contactList = new ArrayList<ContactType>();
		// Creamos las sentencias de seleción
		String contactTypeSentence = "select * from tiposdecontacto";
		// Creamos la url de conexión a base de datos
		String urlDB = "jdbc:hsqldb:hsql://localhost/mydatabase";

		try {
			// Obtenemos la conexión a la base de datos
			Connection conn = DriverManager.getConnection(urlDB, "SA", "");

			// Preparamos la sentencia y la ejecutamos
			PreparedStatement psContact = conn
					.prepareStatement(contactTypeSentence);
			ResultSet rs = psContact.executeQuery();

			// Añadimos todos los tipos de contacto a la lista
			while (rs.next()) {
				contactList.add(new ContactType(rs.getInt("idtipocontacto"), rs
						.getString("tipocontacto")));
			}
			rs.close();
			psContact.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return contactList;
	}


}
