
package ubu.lsi.dms.agenda.persistence;

public class DBFactory implements PersistenceFactory {
	
	private static PersistenceFactory instance = new DBFactory();
	
	private DBFactory(){
		
	}
	
	/* 

	 * (non-Javadoc)
	 * 
	 * @see ubu.lsi.dms.agenda.persistence.PersistenceFactory#createPersistenceFacade()
	 * 
	 */
	@Override
	public PersistenceFacade createPersistenceFacade() {
		return DBFacade.getInstance();
	} // createPersistenceFacade

	/**
	 * Devuelve una referencia a la instancia de la propia clase. La referencia
	 * es a un objeto DBFactory.
	 * 
	 * @return
	 */
	public static PersistenceFactory getInstance(){
		return instance;
	}
} // class DBFactory
