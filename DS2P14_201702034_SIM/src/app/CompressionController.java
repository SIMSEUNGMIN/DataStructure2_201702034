package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import fileIO.BitOutputManager;
import fileIO.ExtendedBufferedInputStream;
import fileIO.ExtendedBufferedOutputStream;
import fileIO.FileIO_CONST;
import fileIO.FilePathManager;

import huffman.HuffmanCode;
import huffman.HuffmanEncoder;
import huffman.Huffman_CONST;

public class CompressionController {
	
	//private constant
	
	//private Instance Variables
	private File _orinalFile;
	private File _compressedFile;
	
	private ExtendedBufferedInputStream _originalInputStream;
	private ExtendedBufferedOutputStream _compressedOutputStream;
	
	private BitOutputManager _bitOutputManager;
	
	private HuffmanEncoder _huffmanEncoder;
	
	//getter/setter
	private File originalFile() {
		return this._orinalFile;
	}
	
	private void setOriginalFile (File newOrginalFile) {
		this._orinalFile = newOrginalFile;
	}
	
	private File compressedFile() {
		return this._compressedFile;
	}
	
	private void setCompressedFile(File newCompressedFile) {
		this._compressedFile = newCompressedFile;
	}
	
	private ExtendedBufferedInputStream originalInputStream() {
		return this._originalInputStream;
	}
	
	private void setOriginalInputStream(ExtendedBufferedInputStream newOriginalInputStream) {
		this._originalInputStream = newOriginalInputStream;
	}
	
	private ExtendedBufferedOutputStream compressedOutputStream() {
		return this._compressedOutputStream;
	}
	
	private void setCompressedOutputStream(ExtendedBufferedOutputStream newCompressedOutputStream) {
		this._compressedOutputStream = newCompressedOutputStream;
	}
	
	private BitOutputManager bitOutputManager() {
		return this._bitOutputManager;
	}
	
	private void setBitOutputManager(BitOutputManager newBitOutputManager) {
		this._bitOutputManager = newBitOutputManager;
	}
	
	private HuffmanEncoder huffmanEncoder() {
		return this._huffmanEncoder;
	}
	
	private void sethuffmanEncoder(HuffmanEncoder newHuffmanEncoder) {
		this._huffmanEncoder = newHuffmanEncoder;
	}
	
	//private method
	private boolean initOriginalFile() {
		AppView.outputLine("");
		AppView.outputLine("? 압축할 원본 파일의 경로와 이름을 입력하시오: ");
		String filePath = AppView.inputFilePath();
		String fileName = AppView.inputFileName();
		String filePathAndName = filePath + "/" + fileName;
		this.setOriginalFile(new File(filePathAndName));
		if(this.originalFile().exists()) {
			return true;
		}
		else {
			AppView.outputLine("!오류: 파일(" + filePathAndName + ") 이 존재하지 않습니다.");
			return false;
		}
	}
	
	private void initCompressedFile() {
		AppView.outputLine("");
		String filePathAndName = this.originalFile().getAbsolutePath() + App_CONST.COMPRESSED_FILE_EXTENSION;
		this.setCompressedFile(new File(filePathAndName));
		if(this.compressedFile().exists()) {
			AppView.outputLine("!경고: 파일(" + filePathAndName + ")이 이미 존재합니다.");
			AppView.outputLine("- 압축 파일의 이름을 다른 것으로 바꾸어 처리합니다: ");
			String compressedFilePathAndNameWithoutExtension = 
					FilePathManager.getFilePathAndNameWithoutExtension(this.originalFile());
			String originalFileExtension = FilePathManager.getFileExtension(this.originalFile());
			String compressedFilePathAndNameWithInfix = 
					compressedFilePathAndNameWithoutExtension + App_CONST.COMPRESSED_FILE_INFIX;
			String compressedFilePathAndName = null;
			int compressedFileSerialNumber = 0;
			do {
				compressedFileSerialNumber++;
				compressedFilePathAndName = compressedFilePathAndNameWithInfix + compressedFileSerialNumber + originalFileExtension + App_CONST.COMPRESSED_FILE_EXTENSION;
				this.setCompressedFile(new File(compressedFilePathAndName));
			}while(this.compressedFile().exists());
			AppView.outputLine("- 새로운 압축 파일의 이름은 " + compressedFilePathAndName + " 입니다.");
		}
	}
	
	//생성자
	protected CompressionController() {
		
	}
	
	//public method
	protected void run() {
		if(this.initOriginalFile()) {
			this.initCompressedFile();
			try {
				this.compress();
				AppView.outputLine("");
				AppView.outputLine("! 압축을 성공적으로 마쳤습니다.");
				this.showStatistics();
			}
			catch(IOException e) {
				AppView.outputLine("!오류 : 압축을 실행하는 동안에 파일 처리 오류가 발생하였습니다.");
			}
		}
	}
	
	private void compress() throws IOException {
		this.sethuffmanEncoder(new HuffmanEncoder());
		
		this.sendAllByteCodesToHuffmanEncoderForCountionFrequencies();
		this.huffmanEncoder().buildHuffmanCode();
		
		this.openCompressedOutputStream();
		this.openOriginalInputStream();
		
		this.setBitOutputManager(new BitOutputManager(this.compressedOutputStream()));
		this.writeSerializedHuffmanTree();
		this.writeNumberOfBitsOfCompressedData();
		
		this.writeAllCompressedBits();
		
		this.closeOriginalInputStream();
		this.closeCompressedOutputStream();
		
		return;
	}
	
	private int readByteCode() throws IOException{
		try {
			return this.originalInputStream().read();
		}
		catch(IOException e) {
			AppView.outputLine("!오류 : 원본 파일 읽기를 실패하였습니다.");
			throw e;
		}
	}
	
	private void sendAllByteCodesToHuffmanEncoderForCountionFrequencies() throws IOException {
		//원본 파일의 모든 Byte code를 HuffmanEncoder로 보내어, byte code의 빈도를 세개 한다.
		try {
			this.openOriginalInputStream();
			int byteCode = this.readByteCode();
			while(byteCode != FileIO_CONST.END_OF_INPUT_STREAM) {
				this.huffmanEncoder().incrementFrequencyOf(byteCode);
				byteCode = this.readByteCode();
			}
			this.closeOriginalInputStream();
		}
		catch(IOException e) {
			AppView.outputLine("!원본 파일을 읽어 코드 빈도를 세는 작업을 실패하였습니다.");
			throw e;
		}
	}
	
	private void writeSerializedHuffmanTree() throws IOException{
		int numberOfNodesInserializedHuffmanTree = this.huffmanEncoder().numberOfNodesOfSerializedHuffmanTree();
		this.compressedOutputStream().writeShort((short)numberOfNodesInserializedHuffmanTree);
		for(int i = 0; i < numberOfNodesInserializedHuffmanTree; i++) {
			try {
				this.compressedOutputStream().writeShort(
						this.huffmanEncoder().serializedHuffmanTree()[i][Huffman_CONST.LEFT_OF_NODE]);
				this.compressedOutputStream().writeShort(
						this.huffmanEncoder().serializedHuffmanTree()[i][Huffman_CONST.RIGHT_OF_NODE]);
			}
			catch(IOException e) {
				AppView.outputLine("!오류 : [직렬화된 허프만 트리]를 파일에 쓰는 작업을 실패하였습니다.");
				throw e;
			}
		}
	}
	
	private void writeNumberOfBitsOfCompressedData() throws IOException{
		try {
			this.compressedOutputStream().writeLong(this.huffmanEncoder().numberOfBitsOfCompressedData());
		}
		catch(IOException e) {
			AppView.outputLine("!오류 : [압축된 데이터의 비트 수]를 파일에 쓰는 작업을 실패하였습니다.");
			throw e;
		}
	}
	
	private void openOriginalInputStream() throws FileNotFoundException{
		try {
			FileInputStream originalFileInputStream = new FileInputStream(this.originalFile());
			this.setOriginalInputStream(new ExtendedBufferedInputStream(originalFileInputStream));
		}
		catch(FileNotFoundException e) {
			AppView.outputLine("!오류 : 원본 파일을 열 수 없습니다.");
			throw e;
		}
	}
	
	private void openCompressedOutputStream() throws FileNotFoundException{
		try {
			FileOutputStream compressedFileOutputStream = new FileOutputStream(this.compressedFile());
			this.setCompressedOutputStream(new ExtendedBufferedOutputStream(compressedFileOutputStream));
		}
		catch(FileNotFoundException e) {
			AppView.outputLine("!오류 : 압축 파일을 열 수 없습니다.");
			throw e;
		}
	}
	
	private void flushBits() throws IOException{
		try {
			this.bitOutputManager().flush();
		}
		catch(IOException e) {
			AppView.outputLine("!오류 : 마지막 남아 있는 비트들을 쓰는 작업을 실패하였습니다.");
			throw e;
		}
	}
	
	private void writeHuffmanCode(HuffmanCode huffmanCode) throws IOException{
			for(int i = 0; i < huffmanCode.length(); i++) {
				try {
					this.bitOutputManager().writeBit(huffmanCode.bitAtIndex(i));
				}
				catch(IOException e) {
					AppView.outputLine("!오류 : [허프만 코드]를 파일에 쓰는 작업을 실패하였습니다.");
					throw e;
				}
			}
	}
	
	private void writeAllCompressedBits() throws IOException{
		try {
			int byteCode = this.readByteCode();
			while(byteCode != FileIO_CONST.END_OF_INPUT_STREAM) {
				HuffmanCode huffmanCode = this.huffmanEncoder().huffmanCodeOfByteCode(byteCode);
				this.writeHuffmanCode(huffmanCode);
				byteCode = this.readByteCode();
			}
			this.flushBits();
		}
		catch(IOException e) {
			AppView.outputLine("!오류 : 압축 파일에 비트 데이터 쓰기를 실패하였습니다.");
			throw e;
		}
	}
	
	private void closeOriginalInputStream() throws IOException{
		try{
			this.originalInputStream().close();
		}
		catch(IOException e) {
			AppView.outputLine("!오류 : 원본 파일 닫기를 실패하였습니다.");
			throw e;
		}
	}
	
	private void closeCompressedOutputStream() throws IOException{
		try{
			this.compressedOutputStream().close();
		}
		catch(IOException e) {
			AppView.outputLine("!오류  : 압축 파일 닫기를 실패하였습니다.");
			throw e;
			}
	}
	
	private void showStatistics() {
		AppView.outputLine("> 압축 정보 : ");
		long originalFileSize = this.originalFile().length();
		long compressedFileSize = this.compressedFile().length();
		AppView.outputLine("- 원본 파일 : " + this.originalFile().getAbsolutePath() + " (" + originalFileSize + "Byte)");
		AppView.outputLine("- 압축 파일: " + this.compressedFile().getAbsolutePath() + "(" + compressedFileSize + "Byte)");
		double compressionRatio = (double) compressedFileSize / (double) originalFileSize;
		AppView.outputLine("- 압축률 : " + String.format("%2.1f %%", compressionRatio * 100));
	}
	
	
}
