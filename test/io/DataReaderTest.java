package io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

public class DataReaderTest {

	String nl = "\r\n";

	@Test
	public void testDataReader_PortList() {
		String data ="";//= "2222 3333 4444 5555" + nl;
		data += "3333 3" + nl;
		data += "4444 5" + nl;
		data += "-1 -1" + nl;
		data += "2222 3" + nl;
		data += "4444 1" + nl;
		data += "5555 6" + nl;
		data += "-1 -1" + nl;
		data += "2222 5" + nl;
		data += "3333 1" + nl;
		data += "5555 2" + nl;
		data += "-1 -1" + nl;
		data += "3333 6" + nl;
		data += "4444 2" + nl;
		data += "-1 -1" + nl;

		InputStream stdin = System.in;
		OutputStream stOut = System.out;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));

			DataReader dataReader = new DataReader(new String[] { "2222",
					"3333", "4444", "5555" });
			int[] portList = new int[] { 2222, 3333, 4444, 5555 };
			int[][] adjMatrix = new int[][] { { 0, 3, 5, 0 }, { 3, 0, 1, 6 },
					{ 5, 1, 0, 2 }, { 0, 6, 2, 0 } };

			Assert.assertArrayEquals(portList, dataReader.getPortList());
			for (int i = 0; i < dataReader.getAdjMatrix()[0].length; i++) {
				Assert.assertArrayEquals(adjMatrix[i],
						dataReader.getAdjMatrix()[i]);
			}
		} finally {
			System.setIn(stdin);
		}

	}

	// @Test
	// public void testReadPorts() {
	// fail("Not yet implemented");
	// }

}
