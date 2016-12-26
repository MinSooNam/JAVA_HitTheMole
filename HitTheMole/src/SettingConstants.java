import java.awt.Color;

public class SettingConstants {

	public static final int PANEL_WIDTH = 800;
	public static final int PANEL_HEIGHT = 600;
	public static final int MENU_COUNT = 5;
	public static final String[] MENU = {"START", "RANKING", "TUTORIAL", "INFORMATION", "EXIT"};
	
	public static final int START = 0;
	public static final int RANKING = 1;
	public static final int TUTORIAL = 2;
	public static final int INFORMATION = 3;
	public static final int EXIT = 4;
	public static final int NONE = 5;
	
	public static final Color EXIT_BACK = Color.white;
	public static final Color EXIT_FORE = Color.black;
	public static final Color ENTER_BACK = Color.cyan;
	public static final Color ENTER_FORE = Color.orange;
	
	
	/*
	 * 	게임 옵션 설정.
	 */
	public static final int FINAL_STAGE = 4;
	public static final int MAX_HEART = 5;
	public static final int MOLE_NUMBER = 9;
	
	//TopPanel에 Title 라벨
	public static final String ROUND[] = 	{ "ROUND 1", "ROUND 2", "ROUND 3", "FINAL ROUND" };
	//스테이지별 시간초
	public static final int TIMER[] = 		{ 60, 45, 30, 15 };
	//다음 스테이지로 가기위한 최소 점수
	public static final int LIMIT_SCORE[] = { 80, 200, 500, 999999999 };
	//스테이지별 두더지를 잡을 때마다 획득하는 점수
	public static final int ADD_SCORE[] = 	{ 10, 20, 50, 100 };
	//스테이지별 두더지를 잘 못 잡을 때마다 감점되는 점수
	public static final int SUB_SCORE[] =	{ -5, -50, -500, -2000 };
	
	
	
}
