package core.commandscore.commands;

import java.util.Scanner;

import core.Debugger;
import core.commandscore.Command;
import core.commandscore.Report;

public class LockCommand implements Command {

	private String pw = "nevermindyoucanpass";
	
	@Override
	public String get_name() {
		return "lock";
	}

	@Override
	public Report run(String cmd_text) {
		return run(new CommandMessage(cmd_text));
	}

	@Override
	public Report run(CommandMessage cmd) {
		if(!cmd.hasArgs()) {//show help
			System.out.println("lock             - show the help");
			System.out.println("lock lock <pw>   - locks this tool");
			System.out.println("lock unlock <pw> - unlocks this tool");
			System.out.println("lock state       - shows the state");
		} else {
			if(cmd.getArgsLength() >= 1) {
				if(cmd.getArgs()[0].equalsIgnoreCase("lock")) {
					if(Debugger.getDebugger().isLocked()) {
						System.out.println("It is allready locked!");
					} else {
						if(cmd.getArgsLength() >= 2) {
							if(cmd.getArgs()[1].equals(pw)) {//lock
								Debugger.getDebugger().lock();
							}
						} else {
							@SuppressWarnings("resource")
							Scanner s  = new Scanner(System.in);
							String scanned = "";
							
							while(!scanned.equals(pw) & !scanned.equalsIgnoreCase("Cancel")) {
								System.out.print("Enter the password or Canclel> ");
								scanned = s.nextLine();
							}
							
							if(scanned.equals(pw)) //lock
								Debugger.getDebugger().lock();
						}
					}
				} else if(cmd.getArgs()[0].equalsIgnoreCase("unlock")) {
					if(!Debugger.getDebugger().isLocked()) {
						System.out.println("It is allready unlocked!");
					} else {
						if(cmd.getArgsLength() >= 2) {
							if(cmd.getArgs()[1].equals(pw)) {//unlock
								Debugger.getDebugger().unlock();
							}
						} else {
							@SuppressWarnings("resource")
							Scanner s  = new Scanner(System.in);
							String scanned = "";
							
							while(!scanned.equals(pw) & !scanned.equalsIgnoreCase("Cancel")) {
								System.out.print("Enter the password or Canclel> ");
								scanned = s.nextLine();
							}
							
							if(scanned.equals(pw)) //unlock
								Debugger.getDebugger().unlock();
						}
					}
				} else if(cmd.getArgs()[0].equalsIgnoreCase("state")) {
					String state  = "";
					if(Debugger.getDebugger().isLocked())
						state = "LOCKED";
					else 
						state = "UNLOCKED";
					System.out.println("The lock is: " + state + "!");
				}
			}
		}
		
		return Report.Success;
	}
}