package gov.nasa.jpf.symbc.string.testing;

import gov.nasa.jpf.symbc.string.graph.Edge;
import gov.nasa.jpf.symbc.string.graph.EdgeCharAt;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	int amountOfStringVar = 2;
	int amountOfStringCons = 5;
	int amountOfEdges = 5;
	int amountOfIntegerCons = 2;
	int amountOfIntegerVar = 2;
	
	int[] listOfEdgesToBeUsed = defaultSetOfEdges();
	int stringConsMaxLength = 5;
	
	public static Profile NewProfile () {
		return new Profile();
	}
	
	public static int[] defaultSetOfEdges () {
		int[] result = new int [22];
		for (int i = 0; i < result.length; i++) {
			result[i] = 1;
		}
		result[17] = 0;
		return result;
	}
	
	public static int[] defaultSetOfEdges2 () {
		int[] result = new int [22];
		for (int i = 0; i < result.length; i++) {
			result[i] = 1;
		}
		result[9] = 0;
		result[10] = 0;
		result[17] = 0;
		return result;
	}
	
	public static int[] defaultGoodOfEdges2 () {
		int[] result = new int [22];
		for (int i = 0; i < result.length; i++) {
			result[i] = 1;
		}
		result[6] = 0;
		result[8] = 0;
		result[9] = 0;
		result[10] = 0;
		result[11] = 0;
		result[12] = 0;
		result[17] = 0;
		return result;
	}
	
	public static int[] smallSetOfEdges () {
		int[] result = new int [22];
		result[2] = 1;
		result[3] = 1;
		result[4] = 1;
		result[13] = 1;
		result[14] = 1;
		result[15] = 1;
		result[16] = 1;
		result[18] = 1;
		return result;
	}
	
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append ("Profile,");
		sb.append ("v0,");
		sb.append (amountOfStringVar); sb.append (",");
		sb.append (amountOfStringCons); sb.append (",");
		sb.append (amountOfEdges); sb.append (",");
		sb.append (amountOfIntegerCons); sb.append (",");
		sb.append (amountOfIntegerVar); sb.append (",");
		sb.append (stringConsMaxLength); sb.append (",");
		for (int i = 0; i < listOfEdgesToBeUsed.length; i++) {
			sb.append (listOfEdgesToBeUsed[i]);
			sb.append (",");
		}
		return sb.toString();
	}
}
