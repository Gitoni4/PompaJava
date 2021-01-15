import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread
{
    String username;
    Socket clientSocket;
    PrintWriter out;
    Scanner in;

    public ClientThread(Socket clientSocket) throws IOException
    {
        this.username = "";
        this.clientSocket = clientSocket;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new Scanner(clientSocket.getInputStream());
    }

    public void sendNews(String news)
    {
        this.out.println(news);
    }
}
