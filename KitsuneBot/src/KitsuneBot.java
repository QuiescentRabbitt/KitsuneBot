import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.*;
import net.dv8tion.*;
import javax.security.auth.login.LoginException;

import net.*;
public class KitsuneBot extends ListenerAdapter {
	public static String BotOwner = "280449641034678272"; //UserID of owner goes here (Default is mine)
	public static void main(String[] args) {
		
		try
        {
            JDA jda = new JDABuilder(AccountType.BOT)
            	
                    .setToken("") //Bot token goes here           
                    .addEventListener(new Images())
                    .addEventListener(new Help())
                    .addEventListener(new RainbowRoles())
                    .addEventListener(new Rate())
                    .addEventListener(new OJ())
                    .addEventListener(new BotMessageLogger())
                    .addEventListener(new Ban())
                    .addEventListener(new GiveAdmin())
                    .buildBlocking();
            	
        }
        catch (LoginException e)
        {
            //If anything goes wrong in terms of authentication, this is the exception that will represent it
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
		
    }
	
	
}
	
