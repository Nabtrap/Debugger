package core.commandscore;

public interface Command {

	public String get_name();
	public Report run(String cmd_text);
	
	public Report run(CommandMessage cmd);

	public class CommandMessage {

		private String cmd_line;
		private String[] args;

		public CommandMessage(String cmd_line) {
			this.cmd_line = cmd_line;//save the line

			String[] args_ = cmd_line.split(" ");//get the args
			args = new String[args_.length -1];
			for(int i = 1; i < args_.length; i++) {
				args[i -1] = args_[i];
			}

		}

		public String[] getArgs() {
			return args;
		}
		
		public String getCommand() {
			return cmd_line.split(" ",2)[0];
		}
		
		public String getLine() {
			return cmd_line;
		}
		
		public boolean hasArgs() {
			return (args.length != 0);
		}
		
		public int getArgsLength() {
			return args.length;
		}
	}
}