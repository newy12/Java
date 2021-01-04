package common;
import java.security.MessageDigest;
import org.springframework.stereotype.Component;

@Component
public class SHA256Util {
	public String encPw(String memberPw) throws Exception{
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		mDigest.update(memberPw.getBytes());
		byte[] msgStr = mDigest.digest();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<msgStr.length; i++) {
			byte tmpStr = msgStr[i];
			String tmpTxt = Integer.toString((tmpStr & 0xff)+0x100, 16).substring(1);
			sb.append(tmpTxt);
		}
		return sb.toString();
	}
}
