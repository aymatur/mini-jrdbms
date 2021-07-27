package rdnms;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DiskManager {

	public static final int PAGE_SIZE = 4069;

	Path heap_file;

	long next_page_id;

	public DiskManager(Path heap_file) throws IOException {
		long heap_file_size = Files.size(heap_file);
		long next_page_id = heap_file_size/PAGE_SIZE;
		this.heap_file = heap_file;
		this.next_page_id = next_page_id;
	}

	public void open(URI heap_file_path) throws IOException {
		Path path = Paths.get(heap_file_path);

		if(Files.notExists(path, null)) {
			path = Files.createFile(path, null);
		}
		heap_file = path;
	}

}

