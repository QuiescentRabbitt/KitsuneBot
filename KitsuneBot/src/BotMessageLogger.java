import net.dv8tion.jda.core.hooks.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class BotMessageLogger extends ListenerAdapter  {
	Writer writer = null;
	public void onMessageReceived(MessageReceivedEvent event) {
		
		//Initial Variable Setup
		JDA jda = event.getJDA();
		User author = event.getAuthor();                
	    Message message = event.getMessage();          
	    MessageChannel channel = event.getChannel();
	    String msg = message.getContentDisplay();
	    boolean bot = author.isBot();
	    
	    //If this message was sent to a Guild TextChannel
	    if (event.isFromType(ChannelType.PRIVATE)) {
	    	
	    	PrivateChannel privateChannel = event.getPrivateChannel();
	    
	    	try {
	    		
	    		//Writes any messages sent to the bot to a textfile
	    		FileWriter fw = new FileWriter("C:\\Users\\User1\\Documents\\BotMessages.txt", true);
	    	    writer = new BufferedWriter(fw);
	    	    PrintWriter out = new PrintWriter(writer);
	    	    out.println(author.getName() + ": " + message.getContentDisplay());
	    	    
	    	} catch (IOException ex) {
	    		
	    	    ex.printStackTrace();
	    	    
	    	} finally {
	    		
	    	   try {writer.close();} catch (Exception ex) {/*ignore*/}
	    	   
	    	}
	    
	    }
	}
	
}
