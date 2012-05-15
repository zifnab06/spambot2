import org.jibble.pircbot.*;
import java.util.*;

public class BotCore extends PircBot {
	
	private static final String[] BAD_WORDS = new String[]{"fuck","bitch","whore",
		"asshole","cum","cock","shit","fag","nigger","twat",
		"cunt","douche","slut","dick","nigga"};
	private static final int WARNINGS_TO_GIVE = 2;
	
	private ArrayList<String> userNames = new ArrayList<String>();
    private HashMap<String,Integer> userWarnings = new HashMap<String,Integer>();
    private boolean userInArray = false;    
    
    public BotCore() {
    	this.setName("SpamGuard");
    }
    
    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
    	
    	if(Arrays.asList(BAD_WORDS).contains(message.toLowerCase())){
    		
    		String userInfo;
    		if(hostname.contains("gateway/web/freenode")){
                userInfo = login;
    		}else{
                userInfo = hostname;
    		}
    		
    		//Check if user is in the ArrayList    		
    		if(userNames.contains(userInfo)){
    			int warnings = userWarnings.get(userInfo);
    			warnings++;
    			userWarnings.put(userInfo, warnings);
    			if(warnings > WARNINGS_TO_GIVE){    				
    				banUser(hostname,channel,sender,userInfo);
    			}else{    				
    				warnUser(channel,sender,warnings);
    			}
    		}else{
    			//If not in ArrayList, add user
    			userNames.add(userInfo);
    			//Once user is in ArrayList, add 1 to warning
    			userWarnings.put(userInfo, 1);
    			warnUser(channel, sender,1);
    		}
    	}
    }

	private void banUser(String hostname, String channel, String sender, String userInfo) {		
		if(hostname.contains("gateway/web/freenode")){
            userInfo += "@";
		}
		ban(channel, "*" + userInfo + "*");
		kick(channel, sender, "You have sworn too many times in this channel. You have been banned.");
	}

	private void warnUser(String channel, String sender, int warnings) {
		kick(channel, sender, "No Swearing! Warning " + warnings + " of " + WARNINGS_TO_GIVE);
	}
}



/*import org.jibble.pircbot.*;
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
*/
