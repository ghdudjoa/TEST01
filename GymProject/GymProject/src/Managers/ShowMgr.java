package Managers;

public class ShowMgr {
	private static ShowMgr instance = null;

	//왠만하면 변수 추가 금지
	//기능만 만들어서 구현하는 디자인 패턴.
	private ShowMgr() {
	}
	
	static public ShowMgr GetInstance()
	{
		if(instance == null)
			instance = new ShowMgr();
		return instance;
	}
	
	
	public void showMain()
	{
		InitDisplay();
		System.out.println("--------------메인 화면--------------");
		System.out.println("1. 입력모드");
		System.out.println("2. 출력모드");
		System.out.println("3. 수정모드");
		System.out.println("4. 프로그램 종료");
	}
	
	public void showInputMode()
	{
		InitDisplay();
		System.out.println("--------------입력 모드--------------");		
		System.out.println("1. 회원 입력");
		System.out.println("2. 트레이너 입력");
		System.out.println("3. 뒤로가기");
	}
	
	public void showOutputMode()
	{
		InitDisplay();
		System.out.println("--------------출력 모드--------------");
		System.out.println("1. 회원 목록 열람");
		System.out.println("2. 트레이너 목록 열람.");
		System.out.println("3. 뒤로 가기");
	}
	
	public void showModifyMode()
	{
		InitDisplay();
		System.out.println("--------------수정 모드--------------");
		System.out.println("1. 회원 정보 수정");
		System.out.println("2. 회원 삭제");
		System.out.println("3. 회원 담당 트레이너 수정");
		System.out.println("4. 뒤로가기");
	}
	
	public void showTrainerReadMode()
	{
		InitDisplay();
		System.out.println("--------------트레이너 출력--------------");
        System.out.println("1. 전체 트레이너 목록 열람하기");
        System.out.println("2. 트레이너 검색하기");
        System.out.println("3. 뒤로가기");
	}
	
	public void showMemberReadMode()
	{
		InitDisplay();
		System.out.println("--------------회원 출력--------------");
		System.out.println("1. 전체 회원목록 열람");
		System.out.println("2. 검색하여 회원정보 조회");
		System.out.println("3. 뒤로가기");
	}
	
	public void showSelectNumber()
	{
		System.out.println("선택할 숫자를 입력해주세요 : ");
	}
	
	public void showSearchName()
	{
		System.out.println("이름을 입력해주세요 : ");
	}
	
	public void selectModify()
	{		
		InitDisplay();
		System.out.println("--------------수정할 정보 선택--------------");
		System.out.println("1. 회원정보 수정");
		System.out.println("2. 트레이너 정보수정");
		System.out.println("3. 뒤로가기");
		showSelectNumber();
	}
	
	public void modifyMember()
	{		
		InitDisplay();
		System.out.println("--------------회원 정보 수정--------------");
		System.out.println("1. 회원정보 수정");
		System.out.println("2. 담당 트레이너 변경");
		System.out.println("3. 뒤로가기");
		showSelectNumber();
	}
	
	public void memberSelectModify()
	{
		System.out.println("1. 회원체중 수정");
		System.out.println("2. 회원 PT 정보수정");		
		System.out.println("3. 회원 정보 삭제");
		System.out.println("4. 뒤로가기");
		//showSelectNumber();
	}
	
	public void trainerModifyMenu()
	{
		InitDisplay();
		System.out.println("--------------트레이너 정보 수정--------------");
		System.out.println("수정할 내용을 선택 해주세요");
		System.out.println("1. 회원 목록 관리");
		System.out.println("2. 트레이너 삭제");
		System.out.println("3. 뒤로가기");
		showSelectNumber();
	}

	private void InitDisplay() 
	{
//		for (int a = 0; a < 20; a++)
//			System.out.println();
	}
}
