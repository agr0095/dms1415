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

/**
 * Clase fachada que realiza la función de fachada para las operaciones de
 * persistencia sobre una base de datos. Además cumple el patrón singleton.
 * 
 * @author <a href="mailto:ava0031@alu.ubu.es">Alberto Vivar Arribas</a>
 * @author <a href="mailto:mlj0004@alu.ubu.es">Mario López Jiménez</a>
 */
public class DBFacade implements PersistenceFacade {

	/**
	 * Devuelve la referencia a la única instancia que debería existir de la
	 * DBFacade.
	 * 
	 * @return instancia de DBFacade.
	 */
	public static PersistenceFacade getInstance() {
		return instance;
	}

	private static final PersistenceFacade instance = new DBFacade();

	/*
	 * Datos importantes de la base de datos.
	 * 
	 * Primero, la url para la conexión a la base de datos. Usuario en dicha
	 * BBDD. Contraseña para el usuario.
	 * 
	 * Nombres descriptivos que almacenan la sentencia correspondiente a las
	 * diferentes operaciones.
	 */
	private final String urlDB, usuario, contraseña, getContactSentence,
			getContactsBySurnameSentence, getContactTypesSentence,
			getCallsByContactSentence, insertContactSentence,
			insertContactTypeSentence, insertCallSentence,
			updateContactSentence, updateContactTypeSentence,
			updateCallSentence;

	/**
	 * Constructor de la fachada: hemos impedido su uso para seguir el patrón
	 * singleton. Ahora es un constructor sin visibilidad fuera de la clase.
	 */
	private DBFacade() {
		urlDB = "jdbc:hsqldb:hsql://localhost/mydatabase";
		usuario = "SA";
		contraseña = "";

		// Creamos las sentencias de seleción
		getContactSentence = "select * from contactos left join tiposdecontacto using (idtipocontacto) where apellidos = ?";
		insertContactSentence = "insert into contactos (idcontacto, nombre, apellidos, estimado, direccion, ciudad, prov, codpostal, region, pais, nombrecompania, cargo, telefonotrabajo, extensiontrabajo, telefonomovil, numfax, nomcorreoelectronico, idtipocontacto, notas) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		insertCallSentence = "insert into llamadas (idllamada, fechallamada, asunto, notas ,idcontacto) values ( ? , ? , ? , ? , ? );";
		insertContactTypeSentence = "insert into tiposdecontacto (idtipocontacto, tipocontacto) values ( ? , ? );";
		updateContactSentence = "update contactos set nombre = ?, apellidos = ?, estimado = ?, direccion = ?, ciudad = ?, prov = ?, codpostal = ?, region = ?, pais = ?, nombrecompania = ?, cargo = ?, telefonotrabajo = ?, extensiontrabajo = ?, telefonomovil = ?, numfax = ?, nomcorreoelectronico = ?, idtipocontacto = ?, notas = ? where idcontacto = ? ";
		updateCallSentence = "update llamadas set idcontacto = ?, fechallamada = ?, asunto = ?, notas = ? where idllamada = ?";
		updateContactTypeSentence = "update tiposdecontacto set tipocontacto = ? where idtipocontacto = ?;";
		getContactsBySurnameSentence = "select * from contactos left join tiposdecontacto using (idtipocontacto) where apellidos = ?";
		getCallsByContactSentence = "select * from llamadas where idcontacto = ? ";
		getContactTypesSentence = "select * from tiposdecontacto";
	}

	@Override
	public List<Call> getCallsByContact(Contact contacto) {
		// Creamos una lista para meter los tipos de contacto
		List<Call> callList = new ArrayList<Call>();

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn
					.prepareStatement(getCallsByContactSentence);
			ps.setInt(1, contacto.getIdContacto());
			ResultSet rs = ps.executeQuery();

			// Añadimos todos los tipos de contacto a la lista
			while (rs.next()) {
				callList.add(new Call(rs.getInt("idLlamada"), contacto, rs
						.getString("fechallamada"), rs.getString("asunto"), rs
						.getString("notas")));
			}

			// Cerramos los recursos
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return callList;
	}

	@Override
	public Contact getContact(String surname) {

		// Creamos unas variables necesarias
		int idContacto = 0, idTipoContacto = 0;
		String nombre = null, apellidos = null, estimado = null, direccion = null, ciudad = null, prov = null, codPostal = null, region = null, pais = null, nombreCompania = null, cargo = null, telefonoTrabajo = null, extensionTrabajo = null, telefonoMovil = null, numFax = null, nomCorreoElectronico = null, notas = null, tipoContacto = null;

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {

			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn.prepareStatement(getContactSentence);
			ps.setString(1, surname);
			ResultSet rs = ps.executeQuery();

			// Recogemos todos los datos necesarios para crear el contacto
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
			// Cerramos los recursos
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return new Contact(idContacto, nombre, apellidos, estimado, direccion,
				ciudad, prov, codPostal, region, pais, nombreCompania, cargo,
				telefonoTrabajo, extensionTrabajo, telefonoMovil, numFax,
				nomCorreoElectronico, notas, new ContactType(idTipoContacto,
						tipoContacto));
	}

	@Override
	public List<Contact> getContactsBySurname(String surname) {
		// Creamos la lista que posteriormente vamos a llena
		List<Contact> contactList = new ArrayList<Contact>();

		int idContacto = 0, idTipoContacto = 0;
		String nombre = null, apellidos = null, estimado = null, direccion = null, ciudad = null, prov = null, codPostal = null, region = null, pais = null, nombreCompania = null, cargo = null, telefonoTrabajo = null, extensionTrabajo = null, telefonoMovil = null, numFax = null, nomCorreoElectronico = null, notas = null, tipoContacto = null;

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn
					.prepareStatement(getContactsBySurnameSentence);
			ps.setString(1, surname);
			ResultSet rs = ps.executeQuery();

			// Recogemos todos los datos necesarios para crear el contacto
			while (rs.next()) {
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
				contactList.add(new Contact(idContacto, nombre, apellidos,
						estimado, direccion, ciudad, prov, codPostal, region,
						pais, nombreCompania, cargo, telefonoTrabajo,
						extensionTrabajo, telefonoMovil, numFax,
						nomCorreoElectronico, notas, new ContactType(
								idTipoContacto, tipoContacto)));
			}
			// Cerramos los recursos
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return contactList;
	}

	@Override
	public List<ContactType> getContactTypes() {
		// Creamos una lista para meter los tipos de contacto
		List<ContactType> contactList = new ArrayList<ContactType>();

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn
					.prepareStatement(getContactTypesSentence);
			ResultSet rs = ps.executeQuery();

			// Añadimos todos los tipos de contacto a la lista
			while (rs.next()) {
				contactList.add(new ContactType(rs.getInt("idtipocontacto"), rs
						.getString("tipocontacto")));
			}

			// Cerramos los recursos
			rs.close();
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return contactList;
	}

	@Override
	public void insertCall(Call call) {

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			if (getContact(call.getContacto().getApellidos()).getApellidos() == null) {
				insertContact(call.getContacto());
			}
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn.prepareStatement(insertCallSentence);

			// Establecemos los parámetros de la inserción
			ps.setInt(1, call.getIdLlamada());
			ps.setString(2, call.getFechaLlamada());
			ps.setString(3, call.getAsunto());
			ps.setString(4, call.getNotas());
			ps.setInt(5, call.getContacto().getIdContacto());

			if (ps.executeUpdate() == 0) {
				new SQLException("No se han producido inserciones!");
			}

			// Cerramos los recursos
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void insertContact(Contact contact) {

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {

			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn.prepareStatement(insertContactSentence);

			// Establecemos los parámetros de la inserción
			ps.setInt(1, contact.getIdContacto());
			ps.setString(2, contact.getNombre());
			ps.setString(3, contact.getApellidos());
			ps.setString(4, contact.getEstimado());
			ps.setString(5, contact.getDireccion());
			ps.setString(6, contact.getCiudad());
			ps.setString(7, contact.getProv());
			ps.setString(8, contact.getCodPostal());
			ps.setString(9, contact.getRegion());
			ps.setString(10, contact.getPais());
			ps.setString(11, contact.getNombreCompania());
			ps.setString(12, contact.getCargo());
			ps.setString(13, contact.getTelefonoTrabajo());
			ps.setString(14, contact.getExtensionTrabajo());
			ps.setString(15, contact.getTelefonoMovil());
			ps.setString(16, contact.getNumFax());
			ps.setString(17, contact.getNomCorreoElectronico());
			ps.setInt(18, contact.getTipoContacto().getIdTipoContacto());
			ps.setString(19, contact.getNotas());

			// Comprobamos que la actualización haya sido exitosa.
			if (ps.executeUpdate() == 0) {
				new SQLException("No se han producido inserciones!");
			}

			// Cerramos los recursos
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void insertContactType(ContactType ct) {

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn
					.prepareStatement(insertContactTypeSentence);

			// Establecemos los parámetros de la inserción
			ps.setInt(1, ct.getIdTipoContacto());
			ps.setString(2, ct.getTipoContacto());

			if (ps.executeUpdate() == 0)
				new SQLException("No se han producido inserciones!");

			// Cerramos los recursos
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

	}

	@Override
	public void updateCall(Call call) {

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn.prepareStatement(updateCallSentence);

			// Establecemos los parámetros de la inserción
			ps.setInt(1, call.getContacto().getIdContacto());
			ps.setString(2, call.getFechaLlamada());
			ps.setString(3, call.getAsunto());
			ps.setString(4, call.getNotas());
			ps.setInt(5, call.getIdLlamada());

			// Comprobamos que la actualización haya sido exitosa.
			if (ps.executeUpdate() == 0) {
				new SQLException("No se ha podido actualizar la llamada");
			}

			// Cerramos los recursos
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void updateContact(Contact contact) {

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn.prepareStatement(updateContactSentence);

			// Establecemos los parámetros de la inserción
			ps.setString(1, contact.getNombre());
			ps.setString(2, contact.getApellidos());
			ps.setString(3, contact.getEstimado());
			ps.setString(4, contact.getDireccion());
			ps.setString(5, contact.getCiudad());
			ps.setString(6, contact.getProv());
			ps.setString(7, contact.getCodPostal());
			ps.setString(8, contact.getRegion());
			ps.setString(9, contact.getPais());
			ps.setString(10, contact.getNombreCompania());
			ps.setString(11, contact.getCargo());
			ps.setString(12, contact.getTelefonoTrabajo());
			ps.setString(13, contact.getExtensionTrabajo());
			ps.setString(14, contact.getTelefonoMovil());
			ps.setString(15, contact.getNumFax());
			ps.setString(16, contact.getNomCorreoElectronico());
			ps.setInt(17, contact.getTipoContacto().getIdTipoContacto());
			ps.setString(18, contact.getNotas());
			ps.setInt(19, contact.getIdContacto());

			// Comprobamos que la actualización haya sido exitosa.
			if (ps.executeUpdate() == 0) {
				new SQLException("No se han producido inserciones!");
			}

			// Cerramos los recursos
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void updateContactType(ContactType ct) {

		try (Connection conn = DriverManager.getConnection(urlDB, usuario,
				contraseña)) {
			// Preparamos la sentencia y la ejecutamos
			PreparedStatement ps = conn
					.prepareStatement(updateContactTypeSentence);

			// Establecemos los parámetros de la inserción
			ps.setString(1, ct.getTipoContacto());
			ps.setInt(2, ct.getIdTipoContacto());

			if (ps.executeUpdate() == 0) {
				new SQLException("No se han producido inserciones!");
			}

			// Cerramos los recursos
			ps.close();

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
