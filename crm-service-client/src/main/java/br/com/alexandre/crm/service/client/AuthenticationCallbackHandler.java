package br.com.alexandre.crm.service.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class AuthenticationCallbackHandler implements CallbackHandler {

	private final String user;
	private final String password;
		
	public AuthenticationCallbackHandler(final String user, final String password) {
		this.user = user;
		this.password = password;
	}



	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		if (callbacks != null) {
			for (Callback callback : callbacks) {
				WSPasswordCallback passwordCallback = (WSPasswordCallback) callback;
			       
		        if (passwordCallback.getIdentifier().equals(user)) {
		        	passwordCallback.setPassword(password);
		        }
			}
		}		
	}
}
