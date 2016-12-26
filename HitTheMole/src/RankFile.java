
import java.io.*;

public class RankFile {//RankFile 클래스
	public static void RankFile(String str, int score) throws IOException{
		//이름과 점수를 입력받으면 그것을 Rank.txt에 저장하기 위한 RankFile
		BufferedWriter rank = new BufferedWriter(new FileWriter("src/Rank.txt", true));
		String s = str;
		//String num = score + "";
		String num = Integer.toString(score);
		
		rank.write(s);
		rank.write(" ");
		rank.write(num);
		rank.newLine();
		
		rank.close();
	}//RankFile()
	
}//RankFile class
