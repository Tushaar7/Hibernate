package com.ans.demo;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Question {
	
	private int id;
	private String name;
	private Map<Answer,User> answers;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public Question(int id, String name, Map<Answer, User>answers){
		this.id = id;
		this.name = name;
		this.answers = answers;
	}
	
	public void displayInfo(){
		System.out.println("Question id: "+id);
		System.out.println("Question Name: "+name);
		System.out.println("Answer...");
		
		Set<Entry<Answer,User>> set = answers.entrySet();
		Iterator<Entry<Answer,User>> itr=set.iterator();
		
		while (itr.hasNext()) {
			Map.Entry<Answer,User> entry = itr.next();
			Answer ans = entry.getKey();
			User user = entry.getValue();
			System.out.println("Answer Information...");
			System.out.println(ans);
			System.out.println("Posted By...");
			System.out.println(user);
		}
	}
}
