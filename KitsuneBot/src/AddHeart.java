import net.dv8tion.jda.core.hooks.*;
import net.dv8tion.jda.core.requests.Route.Emotes;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AddHeart extends ListenerAdapter  {

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
	    		
	    		if (author.getId().equals("490647743170215938") && msg.contains("https://safebooru.donmai.us/posts/")) {
	    			
	    			message.addReaction("♥").queue();
	    			
	    		}
	    		
	    	}

	    }
	}
	
}
