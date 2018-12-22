import net.dv8tion.jda.core.hooks.*;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class OJ extends ListenerAdapter  {

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
	    //Evasion
	    //Defense
	    //Enemy Attack Roll
	    //Health
	    if (event.isFromType(ChannelType.TEXT)) {
	    	
	    	//Server specific information
	    	Guild guild = event.getGuild();
	    	TextChannel textChannel = event.getTextChannel();
	    	Member member = event.getMember();
	    	
	    	if (textChannel.getName().equals("kitsune-bot") || botOwner) {
	    		if (msg.contains(prefix + "OJ") && !bot) {
	    			
	    			try {
		    			int evasion = Integer.parseInt(msg.split(" ")[1]);
		    			int attack = Integer.parseInt(msg.split(" ")[4]);
		    			int defense = Integer.parseInt(msg.split(" ")[2]);
		    			int health = Integer.parseInt(msg.split(" ")[3]);
		    			int count = 0;
		    			int count2 = 0;
		    			for (int i = 1; i < 7; i++) {
		    				if (i + evasion > attack) {
		    					count+=1;
		    				}
		    			}
		    			
		    			for (int i = 1; i < 7; i++) {
		    				if (i + defense + health > attack) {
		    					count2+=1;
		    				}
		    			}	
		    			textChannel.sendMessage("Dodge: " + (count/6.0) * 100 + "%").queue();
		    			textChannel.sendMessage("Defend: " + (count2/6.0) * 100 + "%").queue();
	    			} catch (Exception e) {
	    				textChannel.sendMessage("Invalid Parameters").queue();
	    			}
		    			
	    		}
	    	}

	    }
	}
	
}
