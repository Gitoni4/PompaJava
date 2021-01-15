import java.util.ArrayList;
import java.util.List;

public class NewsChannel
{
    String name;
    ClientThread owner;
    String currentNews;
    List<ClientThread> subscribers;

    public NewsChannel(ClientThread owner, String name)
    {
        this.owner = owner;
        this.name = name;
        this.currentNews = "";
        this.subscribers = new ArrayList<ClientThread>();
        subscribers.add(owner);
    }

    public void subscribe(ClientThread newSubscriber)
    {
        this.subscribers.add(newSubscriber);
    }

    public void publishNews(String news)
    {
        for (ClientThread client : this.subscribers)
        {
             client.sendNews(news);
        }
    }

}
