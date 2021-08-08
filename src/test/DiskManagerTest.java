package test;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import rdnms.DiskManager;

public class DiskManagerTest {

	@Test
	public void readPageDataTest() {

		try {
			Path p = Paths.get("");
			DiskManager disk1 = DiskManager.open(p);
			ByteBuffer bb1 = ByteBuffer.allocate(64);
			bb1.put("hello".getBytes(StandardCharsets.UTF_8));
			ByteBuffer readBuffer = ByteBuffer.allocate(64);
			long hello_page_id = disk1.allocate_page();
			disk1.read_page_data(hello_page_id, readBuffer);
			bb1.flip();
			readBuffer.flip();
			String stbb = StandardCharsets.UTF_8.decode(bb1).toString();
			String strd = StandardCharsets.UTF_8.decode(readBuffer).toString();
			assertEquals(stbb, strd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void writePagaDataTest() {
		try {
			Path p = Paths.get("");
			DiskManager disk1 = DiskManager.open(p);
			ByteBuffer writeBuffer = ByteBuffer.allocate(64);
			writeBuffer.put("hello".getBytes(StandardCharsets.UTF_8)).flip();
			long hello_page_id = disk1.allocate_page();
			disk1.write_page_data(hello_page_id, writeBuffer);
			ByteBuffer readBuffer = ByteBuffer.allocate(64);
			disk1.read_page_data(hello_page_id, readBuffer);
			writeBuffer.flip();
			readBuffer.flip();
			String stwr = StandardCharsets.UTF_8.decode(writeBuffer).toString();
			String strd = StandardCharsets.UTF_8.decode(readBuffer).toString().trim();
			assertEquals(stwr, strd);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
