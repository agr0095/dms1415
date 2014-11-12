package ubu.lsi.dms.agenda.persistence;

import java.util.List;

import ubu.lsi.dms.agenda.modelo.Call;
import ubu.lsi.dms.agenda.modelo.Contact;
import ubu.lsi.dms.agenda.modelo.ContactType;

/**
 * Interfaz fachada que se utiliza para la creación de una fachada persistente.
 * Se aplica el patron de diseño Abstract Factory. Participante como producto
 * abstracto.
 * 
 * @author Plamen Petyov Petkov
 * 
 */
public interface PersistenceFacade {

	/**
	 * Obtiene las llamadas de un contacto
	 * 
	 * @param contacto
	 *            Contacto del que obtener las llamadas.
	 */
	public List<Call> getCallsByContact(Contact contacto);

	/**
	 * Busca a un contacto por apellido.
	 * 
	 * @param surname
	 * @return el primer contacto con ese apellido.
	 */
	public Contact getContact(String surname);

	/**
	 * Obtiene una lista de todos los contactos existentes en la persistencia
	 * filtrados por el apellido.
	 * 
	 * @param surname
	 * @return lista de contactos con ese apellido.
	 */
	public List<Contact> getContactsBySurname(String surname);

	/**
	 * Devuelve una lista con todos los tipos de contacto existentes en la
	 * persistencia.
	 * 
	 * @return lista de todos los tipos de contacto.
	 */
	public List<ContactType> getContactTypes();

	/**
	 * Inserta una llamada (incluyendo la referencia a un contacto) (Al final
	 * lanzas dos insert -llamada y contacto-.
	 *
	 * @param call
	 *            LLamada a insertar
	 */
	public void insertCall(Call call);

	/**
	 * Inserta un contacto en la persistencia.
	 * 
	 * @param contact
	 *            contacto a insertar.
	 */
	public void insertContact(Contact contact);

	/**
	 * Inserta un tipo de contacto en la persistencia.
	 * 
	 * @param ct
	 *            Tipo de contacto a insertar.
	 */
	public void insertContactType(ContactType ct);

	/**
	 * Actualiza una llamada en la persistencia.
	 * 
	 * @param call
	 *            LLamada a actualizar.
	 */
	public void updateCall(Call call);

	/**
	 * Actualiza los datos de un contacto en la base de datos. Solamente
	 * actualiza, no inserta nada. Lanza algo que diga que no había nada que
	 * actualizar.
	 * 
	 * @param contact
	 *            Contacto a actualizar.
	 */
	public void updateContact(Contact contact);

	/**
	 * Actualiza el nombre del tipo de contacto contra la persistencia.
	 * 
	 * @param ct
	 *            Tipo de contacto a actualizar
	 */
	public void updateContactType(ContactType ct);

}
