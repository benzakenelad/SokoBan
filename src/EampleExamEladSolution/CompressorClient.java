package EampleExamEladSolution;

public interface CompressorClient {
	String compress(String uncompressed);
	void send(String fileName, String ip, int port);
}
