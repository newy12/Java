package common;
import java.io.File;
public class FileNameOverlap {
		//파일 중복체크
		public String rename(String path, String filename) {
			String fileFrontName = filename.substring(0,filename.lastIndexOf('.'));
			String fileExtention = filename.substring(filename.lastIndexOf('.'));
			StringBuilder sb = new StringBuilder();
			int num = 0; 
			while(true) {
				sb.setLength(0);
				sb.append(fileFrontName);
				if(num != 0) {
					sb.append("("+num+")");
				}
				sb.append(fileExtention);
				File checkFile = new File(path+sb.toString());
				if(!checkFile.exists()) {
					break;
				}
				num++;
			}
			return sb.toString();
		}
}
