package warGame;

import java.util.ArrayList;

public class MockInput extends Input {

	private ArrayList<String> mockStrings = new ArrayList<String>();
	private ArrayList<Integer> mockInts = new ArrayList<Integer>();

	@Override
	public String getString() {
		String mockString = mockStrings.remove(0);
		return mockString;
	}

	@Override
	public int getInt() {
		int mockInt = mockInts.remove(0);
		return mockInt;
	}
	
	public void setMockString(String s) {
		mockStrings.add(s);
	}
	
	public void setMockInt(int i) {
		mockInts.add(i);
	}

}
