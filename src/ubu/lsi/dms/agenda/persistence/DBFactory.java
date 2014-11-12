package ubu.lsi.dms.agenda.persistence;

/**
 * Clase fábrica que crea una fábrica de persistencia en base de datos.
 * Implementa la interfaz PersistenceFactory. Se aplica el patron de diseño
 * Abstract Factory. Participante como fábrica concreta. Se aplica el patron de
 * diseño Singleton para crear instancia única de la clase.
 * 
 * @author <a href="mailto:ava0031@alu.ubu.es">Alberto Vivar Arribas</a>
 * @author <a href="mailto:mlj0004@alu.ubu.es">Mario López Jiménez</a>
 */
public class DBFactory implements PersistenceFactory {

	/**
	 * Devuelve la referencia a una instancia de la clase DBFactory. Método
	 * necesario para el patron Singleton.
	 * 
	 * @return instance la referencia a la instancia única.
	 */
	public static PersistenceFactory getInstance() {
		return instance;
	} // getInstance

	/**
	 * Guarda una referencia a la instancia de la propia clase. Atributo
	 * necesario para el patron Singleton.
	 */
	private static PersistenceFactory instance = new DBFactory();

	/**
	 * Constructor privado para evitar instanciaciones externas.
	 */
	private DBFactory() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ubu.lsi.dms.agenda.persistence.PersistenceFactory#createPersistenceFacade
	 * ()
	 */
	@Override
	public PersistenceFacade createPersistenceFacade() {
		return DBFacade.getInstance();
	} // createPersistenceFacade

} // class DBFactory
