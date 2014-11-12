package ubu.lsi.dms.agenda.persistence;

/**
 * Interfaz fábrica que se utiliza para la creación de una fachada de
 * persistencia. Se aplica el patron de diseño Abstract Factory. Participante
 * como fábrica abstracta.
 * 
 * @author Plamen Petyov Petkov
 *
 */
public interface PersistenceFactory {

	/**
	 * Crea una fachada de persistencia que proporciona los métodos necesarios
	 * para comunicarse con el modelo de datos.
	 * 
	 * @return objeto de tipo PersistenceFacade
	 */
	public PersistenceFacade createPersistenceFacade();

}
