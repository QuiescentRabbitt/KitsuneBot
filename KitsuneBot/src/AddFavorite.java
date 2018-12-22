import net.dv8tion.jda.core.hooks.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
public class AddFavorite extends ListenerAdapter  {
	Writer writer = null;
	public void onMessageReactionAdd(MessageReactionAddEvent event) {
		
		//Initial Variable Setup
		JDA jda = event.getJDA();     
	    MessageChannel channel = event.getChannel();
	    String prefix = "ke!";
	    Message message = channel.getMessageById(event.getMessageId()).complete();
        String content = message.getContentRaw();
	    if (event.isFromType(ChannelType.TEXT)) { 
	    	
	    	//Server specific information
	    	Guild guild = event.getGuild();
	    	TextChannel textChannel = event.getTextChannel();
	    	Member member = event.getMember();
	    	
	    	if (textChannel.getName().equals("kitsune-bot")) {
	    		
	    		if (event.getReaction().getReactionEmote().getName().equals("♥")) {
	    			
	    			try {
	    				
	    	    		FileWriter fw = new FileWriter("C:\\Users\\User1\\Documents\\favorites.txt", true);
	    	    	    writer = new BufferedWriter(fw);
	    	    	    PrintWriter out = new PrintWriter(writer);
	    	    	    out.println(member.getUser().getId() + ":" + message.getContentDisplay().split("posts/", 2)[1]);	    	   
	    	    	    
	    	    	} catch (IOException ex) {
	    	    		
	    	    	    ex.printStackTrace();
	    	    	    
	    	    	} finally {
	    	    		
	    	    	   try {writer.close();} catch (Exception ex) {/*ignore*/}
	    	    	   
	    	    	}
	    			
	    		}
	    		
	    	}

	    }
	}
	
}
