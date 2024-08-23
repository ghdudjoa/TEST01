package Gym;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Interface.GymOpen;
import Managers.MemberMgr;
import Managers.ShowMgr;
import Managers.TrainerMgr;
import Person.Member;
import Person.Trainer;

public class Gym implements GymOpen {

	public Gym() {
		Init();
	}

	private List<Member> mLst;
	private List<Trainer> tLst;
	private boolean isRun;

	// 사실상 목적과 가장 관계 없음.
	private Scanner scan;

	@Override
	public void run() {
		while (isRun) {
			mainScreen();
		}
		scan.close();
	}

	private void Init() {
		mLst = new ArrayList();
		mLst.clear();

		tLst = new ArrayList();
		tLst.clear();

		isRun = true;

		// 임시 객체 삽입
		TestInput();
		scan = new Scanner(System.in);
	}

	private void mainScreen() {
		ShowMgr.GetInstance().showMain();
		ShowMgr.GetInstance().showSelectNumber();

		String input = scan.nextLine();

		switch (input) {
		case "1": {
			System.out.println("입력모드를 실행합니다.");
			inputMode();

			break;
		}
		case "2": {
			System.out.println("출력모드를 실행합니다.");
			displayMode();

			break;
		}
		case "3": {
			System.out.println("수정모드를 실행합니다.");
			modifyMode();

			break;
		}
		case "4": {
			isRun = false;
			break;
		}
		}
	}

	// 입력모드 페이지
	private void inputMode() {
		boolean loop = true;
		while (loop) {
			ShowMgr.GetInstance().showInputMode();
			String input = scan.nextLine();

			switch (input) {
			case "1": {
				mLst.add(MemberMgr.GetInstance().CreateMember(tLst, scan));
				System.out.println("성공적으로 멤버 등록하였습니다.");
				break;
			}
			case "2": {
				tLst.add(TrainerMgr.GetInstance().CreateTrainer(scan));
				System.out.println("성공적으로 트레이너를 등록하였습니다.");

				for (Trainer trainer : tLst) {
					System.out.println(trainer);
				}
				break;
			}
			case "3":
				loop = false;
				break;
			}
		}
	}

	private void displayMode() {
		boolean loop = true;
		while (loop) {
			ShowMgr.GetInstance().showOutputMode();
			String read = scan.nextLine();

			switch (read) {
			case "1": {
				System.out.println("회원 목록 출력합니다.");
				MemberMgr.GetInstance().displayMemberList(mLst, scan);
			}
			case "2": {
				System.out.println("트레이너 목록 출력합니다.");
				TrainerMgr.GetInstance().displayTrainer(tLst, scan);
			}
			case "3": {
				loop = false;
				break;
			}

			}
		}
	}

	private void modifyMode() {
		boolean loop = true;
		while (loop) {
			ShowMgr.GetInstance().selectModify();
			String read = scan.nextLine();

			switch (read) {
			case "1": {
				modifyMember();
			}
			case "2": {
				modifyTrainer();
			}
			case "3": {
				loop = false;
				break;
			}

			}
		}
	}

	private void modifyMember() {
		boolean loop = true;
		while (loop) {
			ShowMgr.GetInstance().modifyMember();
			//ShowMgr.GetInstance().selectModify();
			String read = scan.nextLine();

			switch (read) {
			case "1": {
				System.out.println("회원 정보 수정");
				// Member m = MemberMgr.GetInstance().findMember(mLst, scan);
				MemberMgr.GetInstance().modifyMember(mLst,tLst, scan);

			}
			case "2": {
				System.out.println("담당 트레이너를 변경합니다.");
				TrainerMgr.GetInstance().modifyTrainer(tLst, scan);

			}
			case "3": {
				loop = false;
				break;
			}

			}
		}
	}

	private void modifyTrainer() {
		TrainerMgr.GetInstance().modifyTrainer(tLst, scan);
	}

	private void TestInput() {
		Member m1 = new Member("방탄", 33, 33.4d, 204.7d);
		mLst.add(m1);
		mLst.add(new Member("방탄", 31, 33.4d, 204.7d));
		mLst.add(new Member("방탄", 30, 33.4d, 204.7d));
		mLst.add(new Member("방탄", 17, 33.4d, 204.7d));
		mLst.add(new Member("방탄", 7, 33.4d, 204.7d));
		mLst.add(new Member("홍길동", 22, 133.4d, 194.7d));
		mLst.add(new Member("김길동", 23, 73.4d, 162.7d));
		mLst.add(new Member("강동", 13, 68.4d, 173.7d));
		mLst.add(new Member("이형동", 27, 43.4d, 174.7d));
		mLst.add(new Member("강형동", 83, 63.4d, 173.d));
		mLst.add(new Member("강호동", 73, 53.4d, 157.d));
		mLst.add(new Member("강호동", 73, 53.4d, 157.d));
		mLst.add(new Member("강호동", 73, 53.4d, 157.d));
		mLst.add(new Member("강호동", 73, 53.4d, 157.d));
		mLst.add(new Member("강호동", 73, 53.4d, 157.d));
		mLst.add(new Member("최우동", 53, 43.4d, 152.d));
		mLst.add(new Member("이김동", 34, 103.4d, 164.7d));
		mLst.add(new Member("고길동", 33, 133.4d, 178.7d));

		Trainer tr1 = new Trainer("김트레", 33);
		tr1.AddMember(m1);
		
		tLst.add(tr1);
		tLst.add(new Trainer("최트레", 33));
	}

}
