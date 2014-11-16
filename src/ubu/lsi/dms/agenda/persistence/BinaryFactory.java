package ubu.lsi.dms.agenda.persistence;

/**
 * Clase fábrica que crea una fábrica de persistencia binaria. Implementa la
 * interfaz PersistenceFactory. Se aplica el patron de diseño Abstract Factory.
 * Participante como fábrica concreta. Se aplica el patron de diseño Singleton
 * para crear instancia única de la clase.
 * 
 * @author <a href="mailto:agr0095@alu.ubu.es">Alejandro González Rogel</a>
 * @author <a href="mailto:ppp0015@alu.ubu.es">Plamen Petyov Petkov</a>
 */
public class BinaryFactory implements PersistenceFactory {

	/**
	 * Devuelve la referencia a una instancia de la clase BinaryFactory. Método
	 * necesario para el patron Singleton.
	 * 
	 * @return instance la referencia a la instancia única.
	 */
	public static BinaryFactory getInstance() {
		return instance;
	} // getInstance

	/**
	 * Guarda una referencia a la instancia de la propia clase. Atributo
	 * necesario para el patron Singleton.
	 */
	private static BinaryFactory instance = new BinaryFactory();

	/**
	 * Constructor privado para evitar instanciaciones externas.
	 */
	private BinaryFactory() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ubu.lsi.dms.agenda.persistence.PersistenceFactory#createPersistenceFacade
	 * ()
	 */
	@Override
	public BinaryFacade createPersistenceFacade() {
		return BinaryFacade.getInstance();
	} // createPersistenceFacade

} // class BinaryFactory
