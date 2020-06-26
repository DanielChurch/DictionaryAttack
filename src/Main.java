/*
 * Daniel Church foo
 * -02226198
 * 02/07/17
 * Dictionary Link: https://github.com/Legendaries/ComputerSecurity/blob/master/dict.txt
 */

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private Map<String, String> map = new HashMap <String, String>();
	
	private String[] hashes = new String[] {"6f047ccaa1ed3e8e05cde1c7ebc7d958", "275a5602cd91a468a0e10c226a03a39c", "b4ba93170358df216e8648734ac2d539", "dc1c6ca00763a1821c5af993e0b6f60a", "8cd9f1b962128bd3d3ede2f5f101f4fc", "554532464e066aba23aee72b95f18ba2"};
	
	public Main () {
		Scanner s = null;
		try {
			s = new Scanner(new File("dict.txt"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			//Generate Map
			while(s.hasNextLine()) {
				String password = s.nextLine();
				byte[] bytesOfMessage = password.getBytes("UTF-8");
				map.put(new BigInteger(1, md.digest(bytesOfMessage)).toString(16), password);
			}
			//Find passwords
			for(String h : hashes) {
				long start = System.nanoTime();
				for(String hash : map.keySet())
					if(hash.equals(h)) {
						System.out.println("The password for hash value " + hash + " is " + map.get(hash) + ", it takes the program " + (System.nanoTime() - start)/1000000000f + " sec to recover this password");
						break;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}
	
	public static void main(String[] args){
		new Main();
	}
	
}
