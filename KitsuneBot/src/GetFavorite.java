import net.dv8tion.jda.core.hooks.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GetFavorite extends ListenerAdapter  {

	public void onMessageReceived(MessageReceivedEvent event) {
		
		//Initial Variable Setup
		JDA jda = event.getJDA();
		User author = event.getAuthor();                
	    Message message = event.getMessage();          
	    MessageChannel channel = event.getChannel();
	    String msg = message.getContentDisplay();
	    boolean bot = author.isBot();
	    boolean botOwner = author.getId().equals(KitsuneBot.BotOwner);
	    String prefix = "ke!";
	    
	    if (event.isFromType(ChannelType.TEXT)) { 
	    	
	    	//Server specific information
	    	Guild guild = event.getGuild();
	    	TextChannel textChannel = event.getTextChannel();
	    	Member member = event.getMember();
	    	
	    	if (textChannel.getName().equals("kitsune-bot") || botOwner) {
	    		
	    		if (msg.contains(prefix + "collection") && !bot) {
	    			Member collectionOwner = event.getGuild().getMembersByName(msg.split(" ")[1], true).get(0);
	    			String line;
	    			 try {
	    			        
	    			        FileReader fileReader = new FileReader("C:\\Users\\User1\\Documents\\favorites.txt");
	    			        BufferedReader bufferedReader = new BufferedReader(fileReader);
	    			        
	    			        while((line = bufferedReader.readLine()) != null) {

	    			        	for (int i = 0; i < 0; i++) {
	    			    	    	   line = bufferedReader.readLine();
	    			    	     }
	    			        	
	    			        }    			        

	    			        bufferedReader.close();
	    			    } catch(FileNotFoundException ex) {
	    			        
	    			        System.out.println("File not found!");
	    			        
	    			        
	    			        
	    			    }
	    			   
	    			    catch(IOException ex) {
	    			        
	    			        System.out.println("File can't be read!");
	    			        
	    			    }
	    			
	    		}
	    		
	    	}

	    }
	}
	
}
