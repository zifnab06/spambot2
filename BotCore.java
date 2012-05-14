import org.jibble.pircbot.*;
import java.util.Arrays;
import java.util.*;

public class BotCore extends PircBot  {
	String[] swear = {"anal", "anus", "arse", "ass", "ballsack", "bastard", "bitch", "biatch", "blowjob", "blow job", "bollock", "bollok", "boner", "boob", "bugger", "bum", "butt", "buttplug", "clitoris", "clit", "cock", "coon", "cunt", "dick", "dildo", "dyke", "fag", "fellate", "fellatio", "felching", "fuck", "f u c k", "fudgepacker", "fudge packer", "flange", "Goddamn", "God damn", "homo", "labia", "muff", "nigger", "nigga", "penis", "prick", "pussy", "queer", "scrotum", "shit", "s hit", "sh1t", "slut", "smegma", "spunk", "tosser", "twat", "vagina", "wank", "whore"};
	ArrayList<String> AL = new ArrayList<String>();
        public BotCore() {
                this.setName("SpamGuard");
        }
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if (channel.equals("#rootz")){
			for (int i=0; i<swear.length; i++) {
				if (message.contains(swear[i]);
					//start thread
					//quiet
					//sleep 30
					//unquiet
					}
				}
			}
		}
	}
}

