package org.iesvdm.excepcion;

public class MiExcepcion extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public MiExcepcion() {
		super("Esto es una excepción personalizada.");
	}
}
