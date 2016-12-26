public class MoleConstants {

	

	//GamePanel 에 속하는 패널들
	public static TopPanel topPanel;
	public static MolePanel molePanel;
	public static StatePanel statePanel;
	public static GamePanel gamePanel;

	//게임 상태를 저장하는 변수
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
	}//init() 초기화

}
