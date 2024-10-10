package task1;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.ObjectMapper;

class Driver {
	
	public static void main(String[] args) {
		// Task 1
		List<Person> persons = new ArrayList<>();
		try  {
			InputStream input;
			// input 1
			input = new FileInputStream("input1.json");
			persons.add(parseFile(input));
			// input 2
			input = new FileInputStream("input2.json");
			persons.add(parseFile(input));
			//input 3
			input = new FileInputStream("input3.json");
			persons.add(parseFile(input));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (JSONException e) {
			e.printStackTrace();
		}
		
		for(Person p : persons) {
			p.PrintPerson();
		}
		
		
		// Task 2
		ObjectMapper mapper = new ObjectMapper();
		List<Person> persons2 = new ArrayList<>();
		try {
			// input 1
			persons2.add(mapper.readValue(new File("input1.json"), Person.class));
			// input 2
			persons2.add(mapper.readValue(new File("input2.json"), Person.class));
			// input 3
			persons2.add(mapper.readValue(new File("input3.json"), Person.class));
			
			for(Person p : persons2) {
				p.PrintPerson();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Person parseFile(InputStream input) throws JSONException {
		JSONTokener jt = new JSONTokener(input);
        JSONObject jo = (JSONObject) jt.nextValue();
        
        JSONArray awardsJSONArray = jo.getJSONArray("awards");
        JSONArray knownForJSONArray = jo.getJSONArray("knownFor");
        String name = jo.getString("name");
        
        // create an array of Award objects that will be passed into the Person object
        Award[] awards = new Award[awardsJSONArray.length()];
        for(int i = 0; i < awardsJSONArray.length(); i++) {
        	awards[i] = new Award(awardsJSONArray.getJSONObject(i).getString("name"), awardsJSONArray.getJSONObject(i).getInt("year"));
        }
        
        // create an array of knownFor Strings that will be passed into the Person object
        String[] knownFor = new String[knownForJSONArray.length()];
        for(int i = 0; i < knownForJSONArray.length(); i++) {
        	knownFor[i] = knownForJSONArray.getString(i);
        }
        
        Person p = new Person(name, knownFor, awards);
        return p;
	}
	
}
