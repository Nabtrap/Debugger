package core.commandscore.commands;

import java.util.Date;

public class Interaction {
	Date time;
	String text = "–";
	
	
	public String to_string() {
		return time.getTime() + "×" + text;
	}
	
	@SuppressWarnings("deprecation")
	public String view() {
		return time.toGMTString() + ": " + text;
	}
	
	public Interaction() {
		time = new Date(System.currentTimeMillis());
	}
	
	public Interaction(String data) {
		String[] split = data.split("×",2);
		time = new Date(Long.parseLong(split[0]));
		text = split[1];
	}
}