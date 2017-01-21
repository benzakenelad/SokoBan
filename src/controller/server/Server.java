package controller.server;

public interface Server {
	public void startServer() throws Exception;
	public void stopServer();
	public void notifyObs(String note);
}
