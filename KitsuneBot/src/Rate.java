import net.dv8tion.jda.core.hooks.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Rate extends ListenerAdapter  {
	Writer writer = null;
	public void onMessageReceived(MessageReceivedEvent event) {
		File fileName = new File("C:\\Users\\User1\\Documents\\Ratings.txt");
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
	    	
	    	Guild guild = event.getGuild();
	    	TextChannel textChannel = event.getTextChannel();
	    	Member member = event.getMember();
	    	
	    	if (textChannel.getName().equals("kitsune-bot") || botOwner) {
	    		
	    		if ((msg.toLowerCase().contains(prefix + "ratewaifu") || msg.toLowerCase().contains(prefix + "rate") ) && !bot) {
	    			Random random = new Random();
	    			int rate = random.nextInt(10);
	    			int otherrate = random.nextInt(10);
	    			if (otherrate > rate) {
	    				rate = otherrate; 
	    			}
	    			if (!msg.contains("everyone") || !msg.contains("here")) {
	    					if (!message.getMentionedMembers().isEmpty()) {
	    	    				if (message.getMentionedMembers().get(0).getUser().getName().equals("KitsuneBot")) {
	    	    					textChannel.sendMessage("I can't rate myself >///<").queue();
	    	    				} else {
			    				Boolean newRate = true;
			    				try {
		    				        String line = "";
		    				        FileReader fileReader = new FileReader(fileName);
		    				        BufferedReader bufferedReader = new BufferedReader(fileReader);
		    				        
		    				        while((line = bufferedReader.readLine()) != null) {
		    				        	if (line.split(":")[0].equalsIgnoreCase(message.getMentionedMembers().get(0).getUser().getName())) {
		    				        		newRate = false;
		    				        		rate = Integer.parseInt(line.split(":")[1]);
		    				        	}
		    				        }
		
		    				        bufferedReader.close();
		    				        
		    				    } catch(FileNotFoundException ex) {
		    				        
		    				        System.out.println("File not found!");
		    				                
		    				    }
		    				   
		    				    catch(IOException ex) {
		    				        
		    				        System.out.println("File can't be read!");
		    				        
		    				    }
			    				if (newRate) {
	
				    					textChannel.sendMessage("I'd rate " + message.getMentionedMembers().get(0).getUser().getName() + " a " + rate + "/10").queue();
				    					
				    					try {	
					    		    		FileWriter fw = new FileWriter("C:\\Users\\User1\\Documents\\Ratings.txt", true);
					    		    	    writer = new BufferedWriter(fw);
					    		    	    PrintWriter out = new PrintWriter(writer);
					    		    	    out.println(message.getMentionedMembers().get(0).getUser().getName().toLowerCase() + ":" + rate);   
					    		    	} catch (IOException ex) {   		    		
					    		    	    ex.printStackTrace();    	    
					    		    	} finally {   		    		
					    		    	   try {writer.close();} catch (Exception ex) {/*ignore*/}
					    		    	   
					    		    	}
			    				} else {
			    					textChannel.sendMessage("I'd rate " + message.getMentionedMembers().get(0).getUser().getName() + " a " + rate + "/10").queue();
			    				}
	    	    				}
	    					} else {
	    						if (msg.split(" ")[1].equalsIgnoreCase("KitsuneBot")) {
	    	    					textChannel.sendMessage("I can't rate myself >///<").queue();
	    	    				} else {
	    						Boolean newRate = true;
			    				try {
		    				        String line = "";
		    				        FileReader fileReader = new FileReader(fileName);
		    				        BufferedReader bufferedReader = new BufferedReader(fileReader);
		    				        
		    				        while((line = bufferedReader.readLine()) != null) {
		    				        	if (line.split(":")[0].equalsIgnoreCase((msg.split(" ", 2)[1]))) {
		    				        		newRate = false;
		    				        		rate = Integer.parseInt(line.split(":")[1].toLowerCase());
		    				        	}
		    				        }
		
		    				        bufferedReader.close();
		    				        
		    				    } catch(FileNotFoundException ex) {
		    				        
		    				        System.out.println("File not found!");
		    				                
		    				    }
		    				   
		    				    catch(IOException ex) {
		    				        
		    				        System.out.println("File can't be read!");
		    				        
		    				    }
			    				if (newRate) {
	
				    					textChannel.sendMessage("I'd rate " + msg.split(" ", 2)[1] + " a " + rate + "/10").queue();
				    					
				    					try {	
					    		    		FileWriter fw = new FileWriter("C:\\Users\\User1\\Documents\\Ratings.txt", true);
					    		    	    writer = new BufferedWriter(fw);
					    		    	    PrintWriter out = new PrintWriter(writer);
					    		    	    out.println(msg.split(" ", 2)[1] + ":" + rate);   
					    		    	} catch (IOException ex) {   		    		
					    		    	    ex.printStackTrace();    	    
					    		    	} finally {   		    		
					    		    	   try {writer.close();} catch (Exception ex) {/*ignore*/}
					    		    	   
					    		    	}
			    				} else {
			    					textChannel.sendMessage("I'd rate " + msg.split(" ")[1] + " a " + rate + "/10").queue();
			    				}
	    	    			}
	    					
	    				}
	    			}			
	    			
	    		}
	    		
	    	}

	    }
	}
	
}
