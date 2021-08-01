package rdnms;

import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;
import java.util.Set;

public class DiskManager {

	public static final int PAGE_SIZE = 4069;

	Path heap_file;

	long next_page_id;

	private DiskManager(Path heap_file) throws IOException {
		long heap_file_size = Files.size(heap_file);
		long next_page_id = heap_file_size/PAGE_SIZE;
		this.heap_file = heap_file;
		this.next_page_id = next_page_id;
	}

	public DiskManager open(URI heap_file_path) throws IOException {
		Path path = Paths.get(heap_file_path);
		Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-rw-rw-");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(permissions);

		if(Files.notExists(path, null)) {
			path = Files.createFile(path, attr);
		}
		return new DiskManager(path);
	}

	public void read_page_data(long page_id, ByteBuffer bf) throws IOException{
		Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("r--r--r--");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(permissions);

		long offset = (long) PAGE_SIZE * page_id;

		try(SeekableByteChannel channel = Files.newByteChannel(heap_file, EnumSet.of(READ), attr)){
			channel.position(offset).read(bf);
		}
	}

	public void write_page_data(long page_id, ByteBuffer bf) throws IOException{
		Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("-w--w--w-");
		FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(permissions);

		long offset = (long) PAGE_SIZE * page_id;

		try(SeekableByteChannel channel = Files.newByteChannel(heap_file, EnumSet.of(WRITE), attr)){
			channel.position(offset).write(bf);
		}
	}

	public long allocate_page() {
		long page_id = next_page_id;
		next_page_id++;
		return page_id;
	}

}

