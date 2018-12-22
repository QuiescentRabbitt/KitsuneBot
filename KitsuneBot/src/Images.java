import net.dv8tion.jda.core.hooks.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Images extends ListenerAdapter  {
	private final static String USER_AGENT = "Mozilla/5.0";
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
	    String msgCommand = msg.split(" ", 2)[0];
	    Boolean validTags = true;
	    
	    ArrayList<String> tagList = new ArrayList<String>();
	    ArrayList<String> blacklistedTags = new ArrayList<String>();
	    blacklistedTags.add("Furry");
	    blacklistedTags.add("Loli");
	    
	    if (event.isFromType(ChannelType.TEXT)) { 
	    	
	    	//Server specific information
	    	Guild guild = event.getGuild();
	    	TextChannel textChannel = event.getTextChannel();
	    	Member member = event.getMember();
	    	
	    	try {
		    		
	    		if (textChannel.getName().equals("kitsune-bot")) {
	    			
	    			if (msgCommand.equals(prefix + "kemono") || msgCommand.equals(prefix + "kemonomimi") && !bot) {
	    				String trimmedMessage = msg.replaceAll(msgCommand, "");
	    				for (String tag : trimmedMessage.split(" ")) {
	    					
	    					tagList.add(tag);
	    					
	    				}
	    				
	    				for(int x = 0; x < tagList.size(); x++) {
	    					
	    					for(int y = 0; y < blacklistedTags.size(); y++) {
	    					
	    						if (tagList.get(x).equalsIgnoreCase(blacklistedTags.get(y))) {
	    							
	    							validTags = false;
	    							
	    						}
	    						
	    					}
	    					
	    				}
	    				
	    				if (validTags) {
	    					
	    					URL url;
	    					URL finalURL;
	    					String buildURL = "https://safebooru.donmai.us/posts.xml?random=true";
	    					ArrayList<String> urlAdditionList = new ArrayList<String>();
	    					ArrayList<String> content = new ArrayList<String>();
	    					int ID = -1;
	    					for (int x = 0; x < tagList.size(); x++) {
	    							urlAdditionList.add("&tags=" + tagList.get(x));		
	    					}
	    					
	    					for (String tag : urlAdditionList) {
	    						buildURL+=tag;
	    					}	
	    					
	    					url = new URL(buildURL);
	    						
	    					HttpURLConnection con = (HttpURLConnection) url.openConnection();
	    					con.setRequestProperty("User-Agent", USER_AGENT);
	    					con.setRequestMethod("GET");
	    					con.setConnectTimeout(2000);
	    					con.setReadTimeout(2000);
	    					con.setDoOutput(true);
	    					
	    					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    					String inputLine;
	    					while ((inputLine = in.readLine()) != null) {
	    						content.add(inputLine);
	    					}
	    					for (String line : content) {
	    						if (line.contains("<id type=\"integer\">")) {
	    							ID = Integer.parseInt(line.split(">", 2)[1].split("<", 2)[0]);
	    						}
	    					}
	    					
	    					finalURL = new URL("https://safebooru.donmai.us/posts/" + ID);
	    					
	    					if (ID!=-1) {
	    						textChannel.sendMessage(finalURL.toExternalForm()).queue();
	    					} else {
	    						textChannel.sendMessage("Invalid tags").queue();
	    					}
	    					
	    					in.close();
	    					con.disconnect();
	    				} else {
	    					
	    					textChannel.sendMessage("Invalid tags").queue();
	    					
	    				 }
			
	    			}
	    			
	    		}
		    		
		    	
	    	} catch (Exception e) {
	    		
	    		e.printStackTrace();
	    		textChannel.sendMessage("An error has occured").queue();
	    		
	    	}

	    }
	}
	
}
