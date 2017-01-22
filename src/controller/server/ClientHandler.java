package controller.server;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class ClientHandler {
	protected Server server = null;
	
	public abstract void HandleClient(InputStream inFromClient, OutputStream outToClient) throws Exception; // handle a client by a well defined protocal
	public void setServer(Server server)
	{
		this.server = server;
	}
}
