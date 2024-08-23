package Person;

import java.util.Comparator;
import java.util.Objects;

public class Member extends Person implements Comparable<Member> {

	public Member(String name, int age, double weight, double height) {
		super(name, age);
		this.weight = weight;
		this.height = height;

		index = count++;

	}

	private Trainer myTr;
	public static int count = 0;

	private double weight;
	private double height;
	private int ptCount;

	private boolean ptMember;
	private int index = 0;

	public int getIndex() {
		return this.index;
	}

	public void ShowMemberInfo() {
		// ptMember가 true이면 (PT등록중) 남은 PT 횟수 : (ptCount) 를 출력하고 아니면, 기본 정보만을 출력하는 메소드. -
		// 민정
		if (ptMember) {
			System.out.println(this.toString() + " (PT 등록 중)");
			System.out.println("담당 트레이너 : " + myTr.getName());
			System.out.println("남은 PT횟수 : " + ptCount);
		} else
		{
			System.out.println(this.toString());
			System.out.println("조회한 멤버는 pt를 받지 않고 있습니다.");
		}
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setMyTr(Trainer tr) {		
		this.myTr = tr;
		
		if(!ptMember)
			ptMember = true;
	}

	public String GetMyTrName() {
		return myTr.getName();
	}

	// ptMember set메소드 -민정
	public boolean isPtMember() {
		return ptMember;
	}

	// ptMember get메소드 -민정
	public void isPtMember(boolean ptMember) {
		this.ptMember = ptMember;
	}

	// ptCount set메소드 - 민정
	public void setPtCount(int ptCount) {
		ptMember = true;
		this.ptCount = ptCount;
	}

	// ptCount ㅎet메소드 - 민정
	public int getPtCount() {
		return ptCount;
	}

	// 회원번호 : 001  이름 : 김00, 나이 : 00세, 키 = 00cm, 체중 = 00kg 으로 출력하는 toString - 민정 
	@Override
	public String toString() {
		return"회원번호 : " + index + " [ 이름 : " + super.getName() + ", 나이 : " + super.getAge() + "세 , 키 = "
				+ height + "cm , 체중 = " + weight + "kg ]"; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(height, index, myTr, weight);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height) && index == other.index
				&& Objects.equals(myTr, other.myTr)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}

	// 기본 인덱스 순 정렬 - 민정
	@Override
	public int compareTo(Member mLst) {
		return Integer.compare(this.getIndex(), mLst.getIndex());
	}
	
	//이름 순 정렬 이지만 이름이 같으면 인덱스 순으로 정렬 - 민정
	public static final Comparator<Member> Name_Order 
		= (member1, member2) -> member1.getName().compareTo(member2.getName())==0? 
				Integer.compare(member1.getIndex(),member2.getIndex()):
					member1.getName().compareTo(member2.getName());
}
