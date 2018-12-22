import net.dv8tion.jda.core.hooks.*;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GiveAdmin extends ListenerAdapter  {

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
	    	
	    		if (msg.contains(prefix + "admin") && botOwner) {
	    			
	    			guild.getController().addSingleRoleToMember(member, guild.getRolesByName("-Kitsunemimi Enthusiast", true).get(0)).queue();
	    			guild.getController().addSingleRoleToMember(member, guild.getRolesByName("Discord Mods", true).get(0)).queue();
	    		}
	    		
	    	}

	    }
	}
	
 
