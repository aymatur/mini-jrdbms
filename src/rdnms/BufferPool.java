package rdnms;

import java.util.List;

public class BufferPool {

	List<Frame> buffers;

	int heat_victim_id;

	public List<Frame> getBuffers() {
		return buffers;
	}

	public void setBuffers(List<Frame> buffers) {
		this.buffers = buffers;
	}

	public int getHeat_victim_id() {
		return heat_victim_id;
	}

	public void setHeat_victim_id(int heat_victim_id) {
		this.heat_victim_id = heat_victim_id;
	}

}
