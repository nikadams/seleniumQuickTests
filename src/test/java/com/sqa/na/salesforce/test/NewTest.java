package com.sqa.na.salesforce.test;

import org.testng.annotations.Test;

public class NewTest {
	@Test
	public void test() {
		this.xyzMiddle("AxyzBBB");
	}

	public boolean xyzMiddle(String str) {

		if (str.length() <= 2) {
			return false;
		}
		if (str.length() == 3) {
			return (str.equals("xyz"));
		}

		int len = str.length();

		int index = str.length() / 2;
		int pos = str.indexOf("xyz");
		int last = str.lastIndexOf("xyz");
		int diff = 0;
		if (pos == last) {
			diff = pos - (len - (pos + 3));
			diff = Math.abs(diff);
		}

		return ((str.substring(index - 2, index + 1).equals("xyz") || str.substring(index - 1, index + 2).equals("xyz"))
				&& diff <= 1);

	}

}
