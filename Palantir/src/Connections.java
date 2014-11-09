import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Connections {

	enum SocialNetwork{
		PINTEREST, FACEBOOK, TWITTER;
		public final String str = name();
	}
	
	/**
	 * This is just dummy input which was given in the booklet
	 * @param userId
	 * @param socialNetwork
	 * @return
	 */
	/**
	 * user A is connected to user C via PINTEREST
	 * user B is connected to user C via FACEBOOK
	 * user A is connected to user D via TWITTER
	 * user A is connected to user D via FACEBOOK
	 * user B is connected to user D via PINTEREST
	 * user B is connected to user D via TWITTER
	 * user B is connected to user D via FACEBOOK
	 * user A is connected to user E via TWITTER
	 * user B is connected to user E via PINTEREST
	 * user A is connected to user F via FACEBOOK
	 * user A is connected to user F via PINTEREST
	 * user B is connected to user F via FACEBOOK
	 * 
	 * I have assigned userId of 1,2,3,4,5,6 to A,B,C,D,E and F respectively
	 */
	public static List<Integer> getConnections(int userId, SocialNetwork socialNetwork){
		List<Integer> list = null;
		if(userId == 1 && socialNetwork.str.equals("PINTEREST")){
			list = new LinkedList<Integer>();
			list.add(3);
			list.add(6);
		}
		else if(userId == 1 && socialNetwork.str.equals("TWITTER")){
			list = new LinkedList<Integer>();
			list.add(4);
			list.add(5);
		}
		else if(userId == 1 && socialNetwork.str.equals("FACEBOOK")){
			list = new LinkedList<Integer>();
			list.add(4);
			list.add(6);
		}
		else if(userId == 2 && socialNetwork.str.equals("PINTEREST")){
			list = new LinkedList<Integer>();
			list.add(4);
			list.add(5);
		}
		else if(userId == 2 && socialNetwork.str.equals("FACEBOOK")){
			list = new LinkedList<Integer>();
			list.add(3);
			list.add(4);
			list.add(6);
		}
		else if(userId == 2 && socialNetwork.str.equals("TWITTER")){
			list = new LinkedList<Integer>();
			list.add(4);
		}
		return list;
	}
	
	/**
	 * Get connections of a user in all social networks
	 * @param userId
	 * @return
	 */
	public static Map<SocialNetwork, List<Integer>> getAllConnections(int userId){
		Map<SocialNetwork, List<Integer>> allConnections = new HashMap<Connections.SocialNetwork, List<Integer>>();
		for (SocialNetwork network : SocialNetwork.values()) {
			List<Integer> connections = getConnections(userId, network);
			allConnections.put(network, connections);
		}
		return allConnections;
	}
	/**
	 * This method takes in two pinterest ids and return back all cross-source second degree 
	 * connection combinations for these users
	 * @param userId1
	 * @param userId2
	 * @return
	 */
	public static StringBuilder getSecondDegreeConnections(int userId1, int userId2){
		StringBuilder connectionsList = new StringBuilder();
		Map<SocialNetwork, List<Integer>> allConnectionsUser1 = getAllConnections(userId1);
		Map<SocialNetwork, List<Integer>> allConnectionsUser2 = getAllConnections(userId2);
		
		for (Entry<SocialNetwork, List<Integer>> entry1 : allConnectionsUser1.entrySet()) {
			SocialNetwork network1 = entry1.getKey();
			List<Integer> connections1 = entry1.getValue();
			
			for (Entry<SocialNetwork, List<Integer>> entry2 : allConnectionsUser2.entrySet()) {
				if(entry1.getKey() != entry2.getKey()){
					SocialNetwork network2 = entry2.getKey();
					List<Integer> connections2 = entry2.getValue();
					for (Integer connectionId : connections1) {
						if(isConnectionOfOtherUser(connectionId, connections2)){
							connectionsList.append("[" +network1.str + "," + network2.str + "]\n");
						}
					}					
				}
			}		
		}
		return connectionsList;
	}
	
	private static boolean isConnectionOfOtherUser(Integer integer, List<Integer> connectionsOfUser2) {
		if(connectionsOfUser2!=null){
			for (Integer id : connectionsOfUser2) {
				if(id==integer){
					return true;
				}
			}
		}
		return false;		
	}

	public static void main(String[] args) {
		System.out.println(getSecondDegreeConnections(1,2).toString());
	}
}
