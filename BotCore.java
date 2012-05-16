import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jibble.pircbot.*;
import java.util.*;

public class BotCore extends PircBot {

	private static final int WARNINGS_TO_GIVE = 2;

	private ArrayList<String> userNames = new ArrayList<String>();
		private HashMap<String,Integer> userWarnings = new HashMap<String,Integer>();
	   private boolean userInArray = false;
	private String[] BAD_WORDS;

	public BotCore() {
			this.setName("Mother");
		BAD_WORDS = readPartyList();

	}

	public static String[] readPartyList() {
		//start reader
		File file = new File("swearWords.txt");
		StringBuffer contents = new StringBuffer();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String txt = null;

			//repeat until all lines are read
			while ((txt = reader.readLine()) !=null) {
				contents.append(txt)
					.append("|");
			  }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String[] BAD_WORDS_RETURN = contents.toString().split("|");
		return BAD_WORDS_RETURN;
		}

	public void onMessage(String channel, String sender,
		String login, String hostname, String message) {

		for (String bad : BAD_WORDS) {
			if (message.toLowerCase().contains(bad.toLowerCase())){
				String userInfo;
				userInfo = hostname;
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
		if (sender.equals("zifnab") || sender.equals("jcase") || sender.equals("dougpiston")) {
			if (message.contains("!clear")) {
				userWarnings.clear();
				userNames.clear();
			}
			if (message.contains("!update")){
				BAD_WORDS = null;
				BAD_WORDS = readPartyList();
			}
		}
	}



	private void banUser(String hostname, String channel, String sender, String userInfo) {
		setMode(channel, "+q *" + userInfo + "*");
		sendMessage(channel, sender + ", you have sworn too many times in this channel. You have been quieted and cannot speak.");
	}

	private void warnUser(String channel, String sender, int warnings) {
		kick(channel, sender, "No Swearing! Warning " + warnings + " of " + WARNINGS_TO_GIVE);
	}
}

