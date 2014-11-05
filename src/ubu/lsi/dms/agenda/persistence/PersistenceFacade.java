package ubu.lsi.dms.agenda.persistence;

import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

public interface PersistenceFacade {

	/**
	 * Busca un contacto por apellido dentro de la agenda
	 * 
	 * @param surname
	 *            Apellido del contacto
	 * @return
	 */
	public Contact getContact(String surname);

	/**
	 * Inserta un contacto en la persistencia.
	 * 
	 * @param contact
	 */
	public void insertContact(Contact contact);

	/**
	 * Inserta una llamada (incluyendo la referencia a un contacto) (Al final
	 * lanzas dos insert -llamada y contacto-.
	 *
	 * @param call
	 *            LLamada a insertar
	 */
	public void insertCall(Call call);

	/**
	 * Insertamos un tipo de contacto en la persistencia
	 * 
	 * @param ct
	 */
	public void insertContactType(ContactType ct);

	/**
	 * Actualizamos los datos de un contacto en la base de datos. Solamente
	 * actualiza, no inserta nada. Lanzamos algo que diga que no había nada que
	 * actualizar.
	 * 
	 * @param contact
	 *            Contacto a actualizar.
	 */
	public void updateContact(Contact contact);

	/**
	 * Actualiza una llamada en la persistencia.
	 * 
	 * @param call
	 *            LLamada a actualizar.
	 */
	public void updateCall(Call call);

	/**
	 * Actualiza el nombre del tipo de contacto contra la persistencia.
	 * 
	 * @param ct
	 *            Tipo de contacto a actualizar
	 */
	public void updateContactType(ContactType ct);

	/**
	 * Obtiene una lista de todos los contactos existentes en la persistencia
	 * filtrados por el apellido
	 * 
	 * @param surname
	 * @return
	 */
	public List<Contact> getContactsBySurname(String surname);

	/**
	 * Obtener las llamadas de un contacto
	 * 
	 * @param contacto
	 *            Contacto del que obtener las llamadas
	 */
	public List<Call> getCallsByContact(Contact contacto);

	/**
	 * Devuelve una lista con todos los tipos de contacto existentes en la
	 * persistencia.
	 * 
	 * @return Tipos de contacto
	 */
	public List<ContactType> getContactTypes();

}
