package Managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Person.Member;
import Person.Trainer;

public class TrainerMgr {
	private static TrainerMgr instance = null;
	private TrainerMgr() {
	}
	public static TrainerMgr GetInstance() {
		if (instance == null)
			instance = new TrainerMgr();
		return instance;
	}

	public Trainer CreateTrainer(Scanner scan) {
		System.out.println("이름을 입력해 주세요.");
		String name = scan.nextLine();

		System.out.println("나이를 입력해 주세요.");
		int age = scan.nextInt();
		scan.nextLine();

		return new Trainer(name, age);
	}
	
	public void modifyTrainer(List<Trainer> tLst, Scanner scan)
	{
		ShowMgr.GetInstance().trainerModifyMenu();		
		String str = scan.nextLine();		
		Trainer tr = findTrainer(tLst, scan);
		
		if(tr == null)
		{
			System.out.println("트레이너 탐색에 실패하였습니다.");
			return;
		}		

		displayTrList(tr.getMemberList(), scan);
		
	}
	
	private void displayTrList(List<Member> mLst, Scanner scan)
	{
		System.out.println("현재 관리중인 회원 목록");
		mLst.stream().forEach(x-> x.ShowMemberInfo());
	}
	

	// 트레이너 검색
	public Trainer findTrainer(List<Trainer> tLst, Scanner scan) 
	{
		List<Trainer> Lst = new ArrayList();

		System.out.println("검색할 트레이너의 이름을 입력 해 주세요 : ");
		String str = scan.nextLine();

		Lst = tLst.stream().filter(x -> x.getName().contains(str)).toList();

		if (Lst.size() == 0) 
		{
			System.out.println("검색된 트레이너가 없습니다.");
		}
		else if (Lst.size() == 1) 
		{
			return Lst.get(0);
		}
		else if (Lst.size() >= 2) 
		{
			return findOverlabCheck(Lst, scan);
		}

		return null;
	}

	// 트레이너 중복 검색중 검색
	private Trainer findOverlabCheck(List<Trainer> Lst, Scanner scan) 
	{
		System.out.println(Lst.size() + "명의 이름이 중복된 트레이너가 검색 되었습니다.");
		System.out.println("검색된 트레이너 목록");
		Lst.stream().forEach(tr -> 
		{
			System.out.println(tr.getIndex() + tr.toString());
		});

		System.out.println("선택할 트레이너의 인덱스를 입력해주세요.");

		int select = scan.nextInt();
		scan.nextLine();
		
		for (Trainer trainer : Lst) {
			if(trainer.getIndex() == select)
				return trainer;
		}
		
		System.out.println("해당 트레이너는 존재하지 않습니다.");

		return null;

	}

	public void displayTrainer(List<Trainer> lst, Scanner scan) {
		boolean loop = true;
		while (loop) {
			ShowMgr.GetInstance().showTrainerReadMode();
			String select = scan.nextLine();
			switch (select) {
			case "1": {

				Collections.sort(lst);
				for (Trainer trainer : lst) {
					System.out.println(trainer);
				}
				break;
			}

			case "2": {
				System.out.println("트레이너 검색을 시작합니다.");
				System.out.println("검색할 이름을 입력 해주세요");
				String trNameinput = scan.nextLine();
				Collections.sort(lst);
				for (Trainer trainer : lst) {
					if (trainer.getName().contains(trNameinput)) {
						trainer.ShowTrainerInfo();
					} else {
						System.out.println("검색 결과가 없습니다.");
						break;
					}
				}
			}
			case "3": {
				loop = false;
				break;
			}
			default:
				System.out.println("잘못된 입력입니다.");
				break;

			}
		}
	}

}
