import net.dv8tion.jda.core.hooks.*;

import java.awt.Color;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RainbowRoles extends ListenerAdapter  {
	Random randomColor = new Random();
	ArrayList<ColoredRole> roleList = new ArrayList<ColoredRole>();
	ArrayList<Guild> serverList = new ArrayList<Guild>();
	Writer writer = null;
	Writer writer2 = null;
	boolean startup = false;
	
	public void startUp(ArrayList<Role> serverRoleList, Guild guild) {
		synchronized(this) {
		if (!serverList.contains(guild)) {
			Writer writer = null;
			File fileName = new File("C:\\Users\\User1\\Documents\\Startup.txt");
		    String line = "";
		    try {	
		    	
		        FileReader fileReader = new FileReader(fileName);
		        BufferedReader bufferedReader = new BufferedReader(fileReader);
		        while(((line = bufferedReader.readLine()) != null)) {
		        	if (line!="") {
			        	for (Role serverRole : serverRoleList) {
			        		if (line.split(" ")[0].equals(serverRole.getId())) {		        				
			        			roleList.add(new ColoredRole(serverRole, Integer.parseInt(line.split(" ")[1]), Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]), Integer.parseInt(line.split(" ")[4]), Integer.parseInt(line.split(" ")[5]), Integer.parseInt(line.split(" ")[6])));	  
			        		}
			        	}
		        	} else {
		        		continue;
		        	}
		        }
		        
	
		        bufferedReader.close();
		    } catch(FileNotFoundException ex) {
		        
		        System.out.println("File not found!");
		        
		        
		        
		    }
		   
		    catch(IOException ex) {
		        
		        System.out.println("File can't be read!");
		        
		    }
		    serverList.add(guild);
		}
		}
	}
	
	public void onMessageReceived(MessageReceivedEvent event) {
		synchronized(this) {
			
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
	    	Member selfMember = guild.getSelfMember();
	    	
	    	ArrayList<Role> currentServerRoles = new ArrayList<Role>();		
		    for (Role serverRole : event.getGuild().getRoles()) {
		    		currentServerRoles.add(serverRole);
		    }
		    startUp(currentServerRoles, guild);										

	    	
	    	if (botOwner && selfMember.hasPermission(Permission.MANAGE_ROLES)) {
	    		
	    		if (msg.split(" ")[0].equalsIgnoreCase(prefix + "randomColor") && !bot) {
	    			Role role = event.getGuild().getRoleById(msg.split(" ")[1]);
	    			int redLowerLimit = Integer.parseInt(msg.split(" ")[2]);
	    			int redUpperLimit = Integer.parseInt(msg.split(" ")[3]);
	    			int greenLowerLimit = Integer.parseInt(msg.split(" ")[4]);
	    			int greenUpperLimit = Integer.parseInt(msg.split(" ")[5]);
	    			int blueLowerLimit = Integer.parseInt(msg.split(" ")[6]);
	    			int blueUpperLimit = Integer.parseInt(msg.split(" ")[7]);
	    			
	    			ColoredRole colorRole = new ColoredRole(role, redLowerLimit, redUpperLimit, greenLowerLimit, greenUpperLimit, blueLowerLimit, blueUpperLimit);
	    			roleList.add(colorRole);
	    			
	    			try {
	    	    		FileWriter fw = new FileWriter("C:\\Users\\User1\\Documents\\Startup.txt", true);
	    	    	    writer = new BufferedWriter(fw);
	    	    	    PrintWriter out = new PrintWriter(writer);
	    	    	    out.println(role.getId() + " " + redLowerLimit + " " + redUpperLimit + " " + greenLowerLimit + " " + greenUpperLimit + " " + blueLowerLimit + " " + blueUpperLimit);
	    	    	    
	    	    	} catch (IOException ex) {
	    	    	    ex.printStackTrace();
	    	    	} finally {
	    	    	   try {writer.close();} catch (Exception ex) {/*ignore*/}
	    	    	}
	    			
	    		}
	    		
	    	}
	    	
		    	for (Role memberRole : member.getRoles()) {
		    		for (int x = 0; x < roleList.size(); x++) {
		    			if (memberRole.getId().equals(roleList.get(x).role.getId())) {	
		    				try {
			    				int r = randomColor.nextInt(roleList.get(x).redUpperLimit - roleList.get(x).redLowerLimit) + roleList.get(x).redLowerLimit;
			    				int g = randomColor.nextInt(roleList.get(x).greenUpperLimit - roleList.get(x).greenLowerLimit) + roleList.get(x).greenLowerLimit;
			    				int b = randomColor.nextInt(roleList.get(x).blueUpperLimit - roleList.get(x).blueLowerLimit) + roleList.get(x).blueLowerLimit;
			    				memberRole.getManager().setColor(new Color(r,g,b)).queue();	
		    				} catch (Exception e) {
		    					roleList.remove(roleList.get(x));
		    				}
			    				
		    			}
		    		}
		    	}
	    	}
	    }
	}
	
}
