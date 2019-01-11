package fileIO;

import java.io.File;

public class FilePathManager {
	
	public static String getFileExtension(File file) {
		//"."을 포함한 형태로 file extension을 얻는다.
		String fileName = file.getName();
		int lastIndexOf = fileName.lastIndexOf(".");
		if(lastIndexOf == 1) {
			return "";
		}
		else {
			return fileName.substring(lastIndexOf);
		}
	}
	
	public static String getFilePathAndNameWithoutExtension(File file) {
		//file extension을 제외한 파일 이름을 얻는다.
		//file extension이 없으면, 원래 이름 그대로 얻는다.
		String filePathAndName = file.getAbsolutePath();
		int lastIndexOf = filePathAndName.lastIndexOf(".");
		if(lastIndexOf == -1) {
			return filePathAndName;
		}
		else {
			return filePathAndName.substring(0, lastIndexOf); //확장자를 제외한 파일의  경로와 이름
		}
	}
	
}
