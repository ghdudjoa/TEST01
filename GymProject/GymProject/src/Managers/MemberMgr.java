package Managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Person.Member;
import Person.Trainer;

public class MemberMgr {
	private static MemberMgr instance = null;
	private MemberMgr() {
	}
	public static MemberMgr GetInstance() {
		if (instance == null)
			instance = new MemberMgr();

		return instance;
	}

	public Member CreateMember(List<Trainer> tLst, Scanner scan) {

		System.out.println("이름을 입력 해주세요");
		String name = scan.nextLine();

		System.out.println("나이를 입력 해주세요");
		int age = scan.nextInt();
		scan.nextLine();

		System.out.println("몸무게를 입력해 주세요.");
		double weight = scan.nextDouble();
		scan.nextLine();

		System.out.println("키를 입력해 주세요.");
		double height = scan.nextDouble();
		scan.nextLine();

		System.out.println("트레이너를 배정 받으시겠습니까? " + "\n (예 : 1 / 아니오 : 2)");

		int assignTrainer = scan.nextInt();
		scan.nextLine();

		Member newMember = new Member(name, age, weight, height);

		if (assignTrainer == 1) {
			System.out.println("트레이너 목록:");
			for (Trainer trainer : tLst) {
				System.out.println(trainer.getIndex() + ": " + trainer.getName());
			}
			System.out.println("배정할 트레이너의 인덱스를 입력해주세요:");
			int trainerIndex = scan.nextInt();
			scan.nextLine();
			Trainer selectedTrainer = tLst.get(trainerIndex);
			newMember.setMyTr(selectedTrainer);		
			selectedTrainer.AddMember(newMember);
		} else if (assignTrainer == 2) {
			System.out.println("트레이너를 선택하지 않았습니다.");
			return newMember;
		} else {
			System.out.println("잘못된 입력입니다.");
		}

		System.out.println("PT를 몇회 받으시겠습니까?");
		System.out.println("1. 10회");
		System.out.println("2. 20회");
		System.out.println("3. 30회");
		int ptChoice = scan.nextInt();
		scan.nextLine();

		int ptCount = 0;
		switch (ptChoice) {
		case 1:
			ptCount = 10;
			break;
		case 2:
			ptCount = 20;
			break;
		case 3:
			ptCount = 30;
			break;
		default:
			System.out.println("다시 선택해 주시기 바랍니다.");
			System.exit(0);
		}

		newMember.setPtCount(ptCount);
		return newMember;
	}

	public void modifyMember(List<Member> mLst, List<Trainer> tLst, Scanner scan) {
		//멤버 탐색
		Member m = MemberMgr.GetInstance().findMember(mLst, scan);
		
		if(m == null)
		{
			System.out.println("회원정보 조회 실패");
			return;
		}
		
		boolean loop = true;
		while (loop) {
			ShowMgr.GetInstance().memberSelectModify();
			String read = scan.nextLine();

			switch (read) {
			case "1": {
				MemberMgr.GetInstance().modifyWeight(m, scan);
				
				break;
			} 
			case "2": {				
				System.out.println("검색할 트레이너의 이름을 입력 해주세요 : ");
				
				Trainer tr = TrainerMgr.GetInstance().findTrainer(tLst, scan);
				
				if(tr == null)
				{
					System.out.println("존재하지 않는 트레이너 입니다.");
				}
				else
				{
					m.setMyTr(tr);
				}
				
				
				break;
			} 
			case "3": {
				System.out.println("회원 정보를 삭제했습니다.");
				mLst.remove(m);
				break;
			}
			case "4": {
				//나가기
				loop = false;
				break;
			}

			}
		}
	}
	
	public void modifyWeight(Member m, Scanner scan)
	{
		System.out.println(m.getName() + "님의 체중을 수정합니다.");
		System.out.println("수정할 체중");
		double weight = scan.nextDouble();
		scan.nextLine();
		
		m.setWeight(weight);
		System.out.println("성공적으로 수정하였습니다.");
	}

	public void displayMemberList(List<Member> mLst, Scanner scan) {
		boolean loop = true;
		while (loop) {//회원 정보 읽기 모드 메뉴 선택
			ShowMgr.GetInstance().showMemberReadMode();
			String select = scan.nextLine();
			switch (select) {
			case "1": {//1. 전체 회원 목록 읽기 메뉴
				System.out.println("전체 회원 목록을 출력합니다.");
				System.out.println("정렬 방식을 선택하세요.");
				System.out.println("1.이름 순 2. 회원 번호 순");
				String Orderselect = scan.nextLine();
				switch(Orderselect) {
				case "1" : {//1.이름 순 정렬
					Collections.sort(mLst,Member.Name_Order);
					break;
				}
				case "2" : {//2. 회원 번호 순 정렬
					Collections.sort(mLst);
					break;
				}
				}
				
				for (Member member : mLst) {
					System.out.println(member);
				}
				break;
			}
			case "2": {//1. 회원 검색 메뉴
				System.out.println("회원 검색을 시작합니다.");
				System.out.println("검색할 이름을 입력 해주세요.");
				String searchName = scan.nextLine();
				Collections.sort(mLst);//인덱스 순으로 정렬.
				for (Member member : mLst) {//searchName이 포함된 객체를 출력하는 메소드
					if (member.getName().contains(searchName)) {
						member.ShowMemberInfo();
					}

				}
				break;
			}
			case "3":
				loop = false;
				break;
			}
		}
	}

	public Member findMember(List<Member> gymMemberLst, Scanner scan) {

		List<Member> lst = new ArrayList();

		System.out.println("검색할 회원의 이름을 입력 해주세요");
		String str = scan.nextLine();

		lst = gymMemberLst.stream().filter(x -> x.getName().contains(str)).toList();

		lst.toString();

		for (Member member : gymMemberLst) {
			if (str == member.getName()) {
				lst.add(member);
			}
		}

		if (lst.size() == 0) {
			System.out.println("검색된 회원이 없습니다.");
			return null;
		} else if (lst.size() == 1) {
			return lst.get(0);
		} else if (lst.size() >= 2) {
			return overlabCheck(lst, scan);
		} else {		
			System.out.println("findmember method error");
			return null;
		}
	}

	private Member overlabCheck(List<Member> mLst, Scanner scan) {
		System.out.println(mLst.size() + "명의 중복된 이름의 회원이 존재합니다.");
		System.out.println("검색된 회원 목록");
		for (Member member : mLst) {
			member.ShowMemberInfo();
		}

		System.out.println("선택할 멤버의 인덱스를 입력해주세요 : ");

		int selectNum = scan.nextInt();
		scan.nextLine();

		for (Member member : mLst) {
			if (member.getIndex() == selectNum)
				return member;
		}

		System.out.println("잘못된 입력입니다.");
		return null;
	}

}
