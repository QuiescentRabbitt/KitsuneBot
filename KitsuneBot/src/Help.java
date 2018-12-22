import net.dv8tion.jda.core.hooks.*;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Help extends ListenerAdapter  {

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
	    		
	    		if (msg.equals(prefix + "help") && !bot) {
	    			
	    			textChannel.sendMessage("User Commands: help OJ ratewaifu nsfw \nAscended Regular Commands: kemonomimi \nAdmin Commands: randomColor").queue();
	    			
	    		} else if (msg.equals(prefix + "help randomColor")) {
	    			
	    			textChannel.sendMessage("This command lets you set a role to be randomly colored in a select range every message.\n Usage: se!randomColor roleID redLowerLimit redUpperLimit greenLowerLimit greenUpperLimit blueLowerLimit blueUpperLimit").queue();
	    			
	    		} else if (msg.equals(prefix + "help ratewaifu")) {
	    				
	    			textChannel.sendMessage("This command lets you rate someone by mentioning them. Ex: ke!ratewaifu @QuiescentRabbitt#3153").queue();
	    			
	    		} else if (msg.equals(prefix + "help OJ")) {
	    			
	    			textChannel.sendMessage("This command lets you determine the surviability for a certain roll in OJ. The order of input is Evasion Stat, Defense Stat, Current Health, Enemy Attack Roll + Stat. Ex: ke!OJ 2 -1 4 8 would show Suguri's chance to live at 4 health against an 8").queue();
	    			
	    		} else if (msg.equals(prefix + "help kemonomimi")) {
	    			
	    			textChannel.sendMessage("This command is locked to #kitsune-bot, and lets you search for sfw pictures. You can put up to one tag after the command").queue();
	    					
	    		} else if (msg.equals(prefix + "help nsfw")) {
	    			
	    			textChannel.sendMessage("This command is locked to other servers, and must be in a nsfw channel named \"kitsune-bot\" and lets you search for nsfw pictures. You can put up to one tag after the command").queue();
	    					
	    		}
	    		
	    	}

	    }
	}
	
}
