public class MoleConstants {

	

	//GamePanel �� ���ϴ� �гε�
	public static TopPanel topPanel;
	public static MolePanel molePanel;
	public static StatePanel statePanel;
	public static GamePanel gamePanel;

	//���� ���¸� �����ϴ� ����
	public static int nStage = 1;
	public static int nHeart = SettingConstants.MAX_HEART;
	public static boolean bTimeOver = false;
	public static boolean bHeartLack = false;
	public static boolean bGameNext = false;

	public static void init() {
		nStage = 1;
		nHeart = SettingConstants.MAX_HEART;
		bTimeOver = false;
		bHeartLack = false;
		bGameNext = false;
	}//init() �ʱ�ȭ

}
