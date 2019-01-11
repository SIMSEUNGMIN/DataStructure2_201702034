package fileIO;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ExtendedBufferedInputStream
	extends BufferedInputStream {
	
	private static ByteBuffer shortBuffer = ByteBuffer.allocate(Short.BYTES);
	private static ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
	private static ByteBuffer longBuffer = ByteBuffer.allocate(Long.BYTES);

	//생성자
	public ExtendedBufferedInputStream(InputStream in) {
		super(in);
	}
	
	//공개 함수
	public short readShort() throws IOException {
		shortBuffer.clear();
		this.read(shortBuffer.array(), 0, Short.BYTES);
		return shortBuffer.getShort();
	}
	
	public int readInt() throws IOException {
		intBuffer.clear();
		this.read(intBuffer.array(), 0, Integer.BYTES);
		return intBuffer.getInt();
	}
	
	public long readLong() throws IOException {
		longBuffer.clear();
		this.read(longBuffer.array(), 0, Long.BYTES);
		return longBuffer.getLong();
	}

}
