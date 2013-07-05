package main.java.com.mkyong.rest.client;

import java.io.BufferedReader;
import java.sql.Timestamp;
import java.util.Date;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

public class NetClientPost {

	/*
	 * Add User having attributes firstName,lastName,email,login,mobilePhone
	 */
	public static void addUser(String firstName, String lastName, String email,
			String login, String mobilePhone) {

		int emailLength = 0;
		String[] splitLogin;
		String[] splitEmail;
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpPost request = new HttpPost(
					"https://bmcdev.okta-admin.com/api/v1/users?activate=false");
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");
			httpClient.getParams().setParameter(
					"http.protocol.content-charset", "UTF-8");
			emailLength = email.length();
			if (emailLength > 100) {
				System.out
						.println("Email Length is too long greater than 100 characters(should be <100 characters)");
			}
			splitEmail = email.split("@");
			splitLogin = login.split("@");

			StringEntity params = new StringEntity(
					"{\"profile\": { \"firstName\":\""
							+ URLEncoder.encode(firstName, "UTF-8")
							+ "\", \"lastName\": \""
							+ URLEncoder.encode(lastName, "UTF-8")
							+ "\", \"email\": \""
							+ URLEncoder.encode(splitEmail[0], "UTF-8") + "@"
							+ URLEncoder.encode(splitEmail[1], "UTF-8")
							+ "\",  \"login\": \""
							+ URLEncoder.encode(splitLogin[0], "UTF-8") + "@"
							+ URLEncoder.encode(splitLogin[1], "UTF-8")
							+ "\", \"mobilePhone\": \"" + mobilePhone + "\"}}");

			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);

			System.out.println("Response" + response.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(URLDecoder.decode(output, "UTF-8"));
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Update User Details using userid and can update
	 * firstName,lastName,email,login,mobilePhone
	 */
	public static void updateUser(String userId, String firstName,
			String lastName, String email, String login, String mobilePhone) {

		int emailLength = 0;
		String[] splitLogin;
		String[] splitEmail;
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpPut request = new HttpPut(
					"https://bmcdev.okta-admin.com/api/v1/users/" + userId);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");
			emailLength = email.length();
			if (emailLength > 100) {
				System.out
						.println("Email Length is too long greater than 100 characters(should be <100 characters)");
			}
			splitEmail = email.split("@");
			splitLogin = login.split("@");

			StringEntity params = new StringEntity(
					"{\"profile\": { \"firstName\":\""
							+ URLEncoder.encode(firstName, "UTF-8")
							+ "\", \"lastName\": \""
							+ URLEncoder.encode(lastName, "UTF-8")
							+ "\", \"email\": \""
							+ URLEncoder.encode(splitEmail[0], "UTF-8") + "@"
							+ URLEncoder.encode(splitEmail[1], "UTF-8")
							+ "\",  \"login\": \""
							+ URLEncoder.encode(splitLogin[0], "UTF-8") + "@"
							+ URLEncoder.encode(splitLogin[1], "UTF-8")
							+ "\", \"mobilePhone\": \"" + mobilePhone + "\"}}");

			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(URLDecoder.decode(output, "UTF-8"));
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Delete User using Userid
	 */

	public static void deleteUser(String userId) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpDelete request = new HttpDelete(
					"https://bmcdev.okta-admin.com/api/v1/users/" + userId);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Remove User to Group using UserId and GroupId
	 */

	public static void removeUserToGroup(String userId, String groupId) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpDelete request = new HttpDelete(
					"https://bmcdev.okta-admin.com/api/v1/groups/" + groupId
							+ "/users/" + userId);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Add User to Group using UserId and GroupId
	 */

	public static void addUserToGroup(String userId, String groupId) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpPut request = new HttpPut(
					"https://bmcdev.okta-admin.com/api/v1/groups/" + groupId
							+ "/users/" + userId);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Create a Group with below groupName and groupDescription
	 */

	public static void createGroup(String groupName, String groupDescription) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpPost request = new HttpPost(
					"https://bmcdev.okta-admin.com/api/v1/groups");
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			StringEntity params = new StringEntity(
					"{\"profile\": { \"name\": \"" + groupName
							+ "\", \"description\": \"" + groupDescription
							+ "\"}}");

			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Fetch all Users using groupid that belongs to that particular group
	 */
	public static void fetchUserToGroup(String groupId) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpGet request = new HttpGet(
					"https://bmcdev.okta-admin.com/api/v1/groups/" + groupId
							+ "/users");
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	/*
	 * List Apps corresponding to particular User using UserId
	 */
	public static void listAppLinks(String userid) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpGet request = new HttpGet(
					"https://bmcdev.okta-admin.com/api/v1/users/" + userid
							+ "/appLinks");
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Look up User using UserId
	 */
	public static void lookupUserId(String userId) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpGet request = new HttpGet(
					"https://bmcdev.okta-admin.com/api/v1/users/" + userId);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	/*
	 * Look up user using Login Id
	 */
	public static void lookupUserLogin(String login) {
		try {

			HttpClient httpClient = new DefaultHttpClient();

			HttpGet request = new HttpGet(
					"https://bmcdev.okta.com/api/v1/users/" + login);
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Accept", "application/json");
			request.addHeader("Authorization",
					"SSWS 00_vn_4tORwWD-qApq43UAWvitqncSyQaIq5wp3aWd");

			HttpResponse response = httpClient.execute(request);
			System.out.println("Response" + response.toString());

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public static void differenceTimeStampRestCalls(long totalTime) {
		long diffMilliSeconds;
		long diffSeconds = totalTime / 1000 % 60;
		long diffMinutes = totalTime / (60 * 1000) % 60;
		long diffHours = totalTime / (60 * 60 * 1000) % 24;
		long diffDays = totalTime / (24 * 60 * 60 * 1000);
		System.out.print("\nTotal Time in ms:-"+totalTime);
		System.out.print("\n");
		System.out.print(diffDays + " days, ");
		System.out.print("\n");
		System.out.print(diffHours + " hours, ");
		System.out.print("\n");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print("\n");
		System.out.print(diffSeconds + " seconds.");
		System.out.print("\n");
		diffMilliSeconds=totalTime-((diffHours*60*60*1000)+(diffMinutes*60*1000)+(diffSeconds*1000));
		System.out.print(diffMilliSeconds + " milliseconds.");
		
	}


	public static void main(String[] args) {

		long startTime=0,endTime=0,totalTime=0;
		
		System.out.println("\n ********Test case1 Time Add Users Time Stamp Difference*******************");
        startTime = System.currentTimeMillis();
        addUser("AKoo11", "abool11", "a1oo11l@24privetdrive.com",
				"o11j@hogwarts.edu", "555-555-5555"); 
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case1 Time Add Users Time Stamp Difference*******************");

		
        /*System.out.println("\n ********Test case2 Time Update Users Time Stamp Difference*******************");
        startTime = System.currentTimeMillis();
		updateUser("00u5qlnvytYNNNRLPBCO","ddd?H36810112","aaa?d38910121","?380?2@4prietdve.com","?cd3?222@hogarts.edu","555-555-5555");
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case2 Time Update Users Time Stamp Difference*******************");
		
		
		System.out.println("\n ********Test case3 Time Delete Users Time Stamp Difference*******************");
		startTime = System.currentTimeMillis();
		deleteUser("00u5qlnvytYNNNRLPBCO");
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case3 Time Delete Users Time Stamp Difference*******************");*/
		
		
		/*System.out.println("\n ********Test case4 Time Create Group Time Stamp Difference*******************");
		startTime = System.currentTimeMillis();
		createGroup("Slytherinkkkkkk","Cold and slimykkkkkkkkkkk");// New Group id Working
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case4 Time Create Group Time Stamp Difference*******************");*/
		
		
		/*System.out.println("\n ********Test case5 Add User to Group Time Stamp Difference*******************");
		startTime = System.currentTimeMillis();
		addUserToGroup("00u5qmvkrkOPCPRFIRRO","00g5qmn5joCCBWEYFWXV");
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case5 Add User to Group Time Stamp Difference*******************");*/
		
		
		/*System.out.println("\n ********Test case6 Fetch User to Group Time Stamp Difference*******************");
		startTime = System.currentTimeMillis();
		fetchUserToGroup("00g5qmn5joCCBWEYFWXV");
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case6 Fetch User to Group Time Stamp Difference*******************");*/
		
		/*System.out.println("\n ********Test case7 Remove User to Group Time Stamp Difference*******************");
		startTime = System.currentTimeMillis();
		removeUserToGroup("00u5qmvkrkOPCPRFIRRO","00g5qmn5joCCBWEYFWXV");
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case7 Remove User to Group Time Stamp Difference*******************");*/
		
		
		/*System.out.println("\n ********Test case8 listAppLinks*******************");
		startTime = System.currentTimeMillis();
		listAppLinks("00u5qmvkrkOPCPRFIRRO");
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case8 listAppLinks*******************");*/
		
		/*System.out.println("\n ********Test case9 lookupUserId*******************");
		startTime = System.currentTimeMillis();
		lookupUserId("00u5qmvkrkOPCPRFIRRO"); 
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case9 lookupUserId*******************");
		
		
		System.out.println("\n ********Test case10 lookupUserLogin*******************");
		startTime = System.currentTimeMillis();
		lookupUserLogin("oj@hogwarts.edu");
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		differenceTimeStampRestCalls(totalTime);
		System.out.println("\n ********Test case10 lookupUserLogin*******************");*/

	}

}
