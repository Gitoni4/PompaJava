import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server
{
    private static boolean channelExists(String name, List<NewsChannel> list)
    {
        for (NewsChannel n : list)
        {
            if (n.name.equals(name))
            {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException
    {
        final int PORT = 4040;
        ServerSocket serverSocket = new ServerSocket(PORT);
        final List<ClientThread> appClients = new ArrayList<ClientThread>();
        final List<NewsChannel> newsChannels = new ArrayList<NewsChannel>();

        System.out.println("Server started...");
        System.out.println("Wating for clients...");

        while (true)
        {
            final Socket clientSocket = serverSocket.accept();
            ClientThread t = new ClientThread(clientSocket) {
                public void run()
                {
                        this.username = this.in.nextLine();
                        while (in.hasNextLine())
                        {
                            String input = this.in.nextLine();
                            switch (input)
                            {
                                case "Create news channel":
                                    this.out.println("Chose a name for the channel :");
                                    String channelName = this.in.nextLine();
                                    if (!channelExists(channelName, newsChannels))
                                    {
                                        NewsChannel newChannel = new NewsChannel(this, channelName);
                                        newsChannels.add(newChannel);
                                        this.out.println("Channel created");
                                    }
                                    else
                                    {
                                        this.out.println("Channel already exists");
                                    }
                                    break;
                                case "Subscribe":
                                    this.out.println("Channel name : ");
                                    channelName = this.in.nextLine();
                                    for (NewsChannel n : newsChannels)
                                    {
                                        if (n.name.equals(channelName))
                                        {
                                            n.subscribe(this);
                                        }
                                    }
                                    break;
                                case "Show info":
                                    for (NewsChannel n : newsChannels)
                                    {
                                        System.out.println(n.name);
                                        for (ClientThread c : n.subscribers)
                                        {
                                            System.out.println(c.username);
                                        }
                                    }
                                    break;
                                case "Post news":
                                    this.out.println("Channel name : ");
                                    channelName = this.in.nextLine();
                                    this.out.println("News content : ");
                                    for (NewsChannel n : newsChannels)
                                    {
                                        if (n.name.equals(channelName))
                                        {
                                            n.publishNews(this.in.nextLine());
                                        }
                                    }

                            }
                            if (input.equalsIgnoreCase("exit")) {
                                break;
                            }
                            System.out.println("Received radius from client: " + input);
                        }
                }
            };
            t.start();
        }
    }
}