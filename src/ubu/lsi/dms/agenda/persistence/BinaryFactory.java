
package ubu.lsi.dms.agenda.persistence;

public class BinaryFactory implements PersistenceFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubu.lsi.dms.agenda.persistence.PersistenceFactory#createPersistenceFacade()
	 * 
	 */
	@Override
	public PersistenceFacade createPersistenceFacade() {
		return BinaryFacade.getInstance();
	} // createPersistenceFacade

} // class BinaryFactory
