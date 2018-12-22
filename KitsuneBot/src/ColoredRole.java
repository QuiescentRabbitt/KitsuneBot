import net.dv8tion.jda.core.entities.Role;

public class ColoredRole {
	
	Role role;
	int redLowerLimit;
	int redUpperLimit;
	int greenLowerLimit;
	int greenUpperLimit;
	int blueLowerLimit;
	int blueUpperLimit;
	
	public ColoredRole(Role role,int redLL, int redUL, int greenLL, int greenUL, int blueLL, int blueUL) {
		
		this.role = role;
		redLowerLimit = redLL;
		redUpperLimit = redUL;
		greenLowerLimit = greenLL;
		greenUpperLimit = greenUL;
		blueLowerLimit = blueLL;
		blueUpperLimit = blueUL;
		
	}

}
