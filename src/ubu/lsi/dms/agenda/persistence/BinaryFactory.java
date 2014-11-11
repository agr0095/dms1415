package ubu.lsi.dms.agenda.persistence;

public class BinaryFactory implements PersistenceFactory {

	private static PersistenceFactory instance = new BinaryFactory();;

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
	public PersistenceFacade createPersistenceFacade() {
		return BinaryFacade.getInstance();
	} // createPersistenceFacade

	/**
	 * Devuelve una referencia a la instancia de la propia clase. La referencia
	 * es a un objeto BinaryFactory.
	 * 
	 * @return
	 */
	public static PersistenceFactory getInstance() {
		return instance;
	}

} // class BinaryFactory
