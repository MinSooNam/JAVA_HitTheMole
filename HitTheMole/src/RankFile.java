
import java.io.*;

public class RankFile {//RankFile Ŭ����
	public static void RankFile(String str, int score) throws IOException{
		//�̸��� ������ �Է¹����� �װ��� Rank.txt�� �����ϱ� ���� RankFile
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
