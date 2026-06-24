package io.github.williamledo.icompras.pedidos.exception;

public class ItemNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ItemNaoEncontradoException(String message) {
		super(message);
	}

}
