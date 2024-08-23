package Person;

import java.util.ArrayList;
import java.util.List;

public class Trainer extends Person implements Comparable<Trainer> {

	public Trainer(String name, int age) {
		super(name, age);

		index = count;
		count++;

		Init();
	}

	private List<Member> mLst;

	private int index = 0;
	static int count = 0;

	private void Init() {
		mLst = new ArrayList();
		mLst.clear();
	}

	public void AddMember(Member m) {
		m.setMyTr(this);
		mLst.add(m);
	}

	// 관리 회원 전체 열람.
	public void ShowManageMember() 
	{
		System.out.println(this.toString());
		for (Member member : mLst) 
		{
			member.ShowMemberInfo();
		}
	}

	public int getIndex() {
		return index;
	}

	public void ShowTrainerInfo() {
		System.out.println(this.toString());
		mLst.stream().forEach(x -> x.ShowMemberInfo());
	}// ShowTrainerInfo
	
	public List<Member> getMemberList()
	{
		return mLst;
	}

	@Override
	public String toString() {
		return "Trainer index : " + getIndex() + "[이름 : " + super.getName() + "]" + "\n 관리회원 목록 : " + getMemberList();
	}

	@Override
	public int compareTo(Trainer trainer) {
		int nameCmp = this.getName().compareTo(trainer.getName());
		return (nameCmp != 0 ? nameCmp : Integer.compare(this.getIndex(), trainer.getIndex()));
	}

}
