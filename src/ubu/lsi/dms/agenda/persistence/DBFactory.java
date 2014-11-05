
package ubu.lsi.dms.agenda.persistence;

public class DBFactory implements PersistenceFactory {

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

} // class DBFactory
