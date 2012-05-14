import org.jibble.pircbot.*;

public class core {
	public static void main(String[] args) throws Exception {

		BotCore bot = new BotCore();
		bot.setVerbose(true);
		bot.connect("irc.andirc.net");
		bot.joinChannel("#rootz");


	}

}

