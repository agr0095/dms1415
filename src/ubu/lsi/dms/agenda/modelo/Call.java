package ubu.lsi.dms.agenda.modelo;

import java.io.Serializable;

/**
 * Clase de entidad con la información de Llamada
 * 
 * @author Carlos López
 *
 */
@SuppressWarnings("serial")
public class Call implements Serializable {

	private int idLlamada;
	private Contact contacto;
	private String fechaLlamada;
	private String asunto, notas;

	public Call(int idLlamada, Contact contacto, String fechaLlamada,
			String asunto, String notas) {
		super();
		this.idLlamada = idLlamada;
		this.contacto = contacto;
		this.fechaLlamada = fechaLlamada;
		this.asunto = asunto;
		this.notas = notas;
	}

	/**
	 * @return the contacto
	 */
	public Contact getContacto() {
		return contacto;
	}

	/**
	 * @param contacto
	 *            the contacto to set
	 */
	public void setContacto(Contact contacto) {
		this.contacto = contacto;
	}

	/**
	 * @return the fechaLlamada
	 */
	public String getFechaLlamada() {
		return fechaLlamada;
	}

	/**
	 * @param fechaLlamada
	 *            the fechaLlamada to set
	 */
	public void setFechaLlamada(String fechaLlamada) {
		this.fechaLlamada = fechaLlamada;
	}

	/**
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	/**
	 * @param asunto
	 *            the asunto to set
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	/**
	 * @return the notas
	 */
	public String getNotas() {
		return notas;
	}

	/**
	 * @param notas
	 *            the notas to set
	 */
	public void setNotas(String notas) {
		this.notas = notas;
	}

	/**
	 * @return the idLlamada
	 */
	public int getIdLlamada() {
		return idLlamada;
	}

	@Override
	public String toString() {
		return "Llamada [idLlamada=" + idLlamada + ", contacto=" + contacto
				+ ", fechaLlamada=" + fechaLlamada + ", asunto=" + asunto
				+ ", notas=" + notas + "]";
	}

}
