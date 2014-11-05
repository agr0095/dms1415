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
		// Creamos las sentencias de seleción		
		String getContactBySurnameSentence = "select * from contactos join tiposdecontacto using (idtipocontacto) where apellidos = ?";

		// Creamos la url de conexión a base de datos
		String urlDB = "jdbc:hsqldb:hsql://localhost/mydatabase";
		int idContacto = 0, idTipoContacto = 0;
		String nombre = null, apellidos = null, estimado = null, direccion = null, ciudad = null, prov = null, codPostal = null, region = null, pais = null, nombreCompania = null, cargo = null, telefonoTrabajo = null, extensionTrabajo = null, telefonoMovil = null, numFax = null, nomCorreoElectronico = null, notas = null, tipoContacto = null;

		try {
			// Obtenemos la conexión a la base de datos
			Connection conn = DriverManager.getConnection(urlDB, "SA", "");

			// Preparamos la sentencia y la ejecutamos
			PreparedStatement psContact = conn
					.prepareStatement(getContactBySurnameSentence);
			psContact.setString(1, surname);
			ResultSet rs = psContact.executeQuery();

			//Recogemos todos los datos necesarios para crear el contacto
			if (rs.next()) {
				idContacto = rs.getInt("idcontacto");
				nombre = rs.getString("nombre");
				apellidos = rs.getString("apellidos");
				estimado = rs.getString("estimado");
				direccion = rs.getString("direccion");
				ciudad = rs.getString("ciudad");
				prov = rs.getString("prov");
				codPostal = rs.getString("codpostal");
				region = rs.getString("region");
				pais = rs.getString("pais");
				nombreCompania = rs.getString("nombrecompania");
				cargo = rs.getString("cargo");
				telefonoTrabajo = rs.getString("telefonotrabajo");
				extensionTrabajo = rs.getString("extensiontrabajo");
				telefonoMovil = rs.getString("telefonomovil");
				numFax = rs.getString("numfax");
				nomCorreoElectronico = rs.getString("nomcorreoelectronico");
				notas = rs.getString("notas");
				idTipoContacto = rs.getInt("idtipocontacto");
				tipoContacto = rs.getString("tipocontacto");
			}
			//Cerramos los recursos
			rs.close();
			psContact.close();
			conn.close();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return new Contact(idContacto, nombre, apellidos, estimado, direccion,
				ciudad, prov, codPostal, region, pais, nombreCompania, cargo,
				telefonoTrabajo, extensionTrabajo, telefonoMovil, numFax,
				nomCorreoElectronico, notas, new ContactType(idTipoContacto,
						tipoContacto));
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
		// Creamos una lista para meter los tipos de contacto
		List<Call> callList = new ArrayList<Call>();
		// Creamos las sentencias de seleción
		String contactTypeSentence = "select * from llamadas where idcontacto = ? ";
		// Creamos la url de conexión a base de datos
		String urlDB = "jdbc:hsqldb:hsql://localhost/mydatabase";

		try {
			// Obtenemos la conexión a la base de datos
			Connection conn = DriverManager.getConnection(urlDB, "SA", "");

			// Preparamos la sentencia y la ejecutamos
			PreparedStatement psContact = conn
					.prepareStatement(contactTypeSentence);
			psContact.setInt(1, contacto.getIdContacto());
			ResultSet rs = psContact.executeQuery();

			// Añadimos todos los tipos de contacto a la lista
			while (rs.next()) {
				callList.add(new Call(rs.getInt("idLlamada"), contacto, rs
						.getString("fechallamada"), rs.getString("asunto"), rs
						.getString("notas")));
			}

			rs.close();
			psContact.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return callList;
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
