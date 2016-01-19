package core.commandscore.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import File.Filereader;
import File.Filewriter;
import core.Debugger;
import core.Events.Event;
import core.Events.Event.EventType;
import core.Events.EventHandler;
import core.commandscore.Command;
import core.commandscore.Report;

public class InspCommand implements Command, EventHandler{

	boolean firsttime = false;
	boolean enabled = true;

	long lastsave;

	List<Interaction> interactions = new ArrayList<Interaction>();

	Interaction this_session;

	@Override
	public String get_name() {
		return "insp";
	}

	@Override
	public Report run(String cmd_text) {
		return run(new CommandMessage(cmd_text));
	}

	@SuppressWarnings("resource")
	@Override
	public Report run(CommandMessage cmd) {
		if(enabled) {
			if(cmd.getArgs().length == 0) {//command help
				System.out.println("insp             - Show Help");
				System.out.println("insp list        - list all previus interactions");
				System.out.println("insp current     - Show the current log entry");
				System.out.println("insp edit [text] - edit the current entry");
				System.out.println("insp save        - saves the log");
				System.out.println("insp rm <id>     - removes the entry");
			}else if(cmd.getArgs().length >= 1) {
				if(cmd.getArgs()[0].equalsIgnoreCase("list")){
					for(int i = 0; i < interactions.size(); i++) {
						System.out.println(i + ": " +interactions.get(i).view());
					}
				} else if(cmd.getArgs()[0].equalsIgnoreCase("current")){
					System.out.println(this_session.view());
				}else if(cmd.getArgs()[0].equalsIgnoreCase("edit")){
					if(cmd.getArgs().length >= 2) {
						int a = 0;
						for(int i = 0; i < cmd.getLine().length(); i++) {
							if(cmd.getLine().charAt(i) == ' '){
								if(a == 0) {
									a = 1;
								}else {
									a = i;
									break;
								}
							}
						}
						this_session.text = cmd.getLine().substring(a);
						System.out.println(this_session.view());
					} else {
						System.out.println("Enter your text:");
						this_session.text = (new Scanner(System.in)).nextLine();
					}
				}else if(cmd.getArgs()[0].equalsIgnoreCase("save")) {
					System.out.println("Saving...");
					save_file();
				} else if(cmd.getArgs()[0].equalsIgnoreCase("rm")) {
					if(cmd.getArgs().length == 2) {
						interactions.remove(Integer.parseInt(cmd.getArgs()[1]));
					} else {
						System.out.println("Error, missing Argument: insp rm <id>");
					}
				} else {
					System.out.println("Argument Error");
				}
			}

		} else {
			System.out.println("This command is not avaible on this os!");
		}
		return Report.Success;
	}
	
	public InspCommand() {
		String path = getfile();

		if(new File(path).exists()) {
			String[] reader = new Filereader(path).getContent();

			for(String line: reader) {
				interactions.add(new Interaction(line));
			}
		} else {
			firsttime = true;
		}

		this_session = new Interaction();

		Debugger.eventmanager.registerHandler(this);//self saving on exit
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() { save_file(); }
		});
	}

	public void save_file() {
		if((System.currentTimeMillis() - lastsave) > 500) {//letzte speicherung länger her
			String[] tosave = new String[interactions.size()+1];
			for(int i = 0; i < interactions.size(); i++) {
				tosave[i] = interactions.get(i).to_string();
			}
			tosave[tosave.length-1] = this_session.to_string();
			new Filewriter(getfile()).write(tosave);

			lastsave = System.currentTimeMillis();
			System.out.println("Saved.");
		}
		System.out.println("Last save is ok");
	}

	private String getfile(){
		if(System.getProperty("os.name").contains("Windows")) {
			return "C:\\Users\\Default\\run.txt";
		} else if(System.getProperty("os.name").contains("Linux")) { 
			return "/etc/run.txt";
		} else {
			enabled =false;
			return "";
		}
	}

	@Override
	public void Handle(Event event) {
		if(event.type == EventType.Exit) {
			System.out.println("saving...");
			save_file();
		}
	}

}
