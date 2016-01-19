package core;
import java.util.Scanner;

import core.Events.EventManager;
import core.commandscore.Commandcore;


public class Debugger{
	
	public static boolean run = true;
	public static String version = "v0.0.1";
	public static EventManager eventmanager;
	
	private static Debugger debugger = null;
	
	private boolean unlocked = false;

	public static void main(String[] args) {
		getDebugger().start();
	}
	
	public void start() {
		System.out.println("╔═════════════════════════════╗");
		System.out.println("║ Yannis Debugger Tool "+version+" ║");
		System.out.println("╚═════════════════════════════╝");
		
		eventmanager = new EventManager();
		Commandcore commands = new Commandcore();
		
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		while (run) {
			commands.run_command(s.nextLine());
		}
	}
	
	public static String repeat(String to_repeat, int times) {
		String output = "";
		for(int i = 0; i < times; i++) {
			output = output + to_repeat;
		}
		return output;
	}
	
	
	public static Debugger getDebugger() {
		if(debugger == null) {
			debugger = new Debugger();
			return debugger;
		} else 
			return debugger;
	}
	
	public void unlock() {
		System.out.println("*UNLOCKED*");
		unlocked = true;
	}
	
	public void lock() {
		System.out.println("*LOCKED*");
		unlocked = false;
	}
	
	public boolean isLocked() {
		return !unlocked;
	}
}
