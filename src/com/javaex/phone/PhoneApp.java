package com.javaex.phone;

import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
				
		Scanner sc = new Scanner(System.in);
		
		PhoneDao phoneDao = new PhoneDao();
		
		System.out.println("************************************************************");
		System.out.println("*                     전화번호 관리 프로그램                      *");
		System.out.println("************************************************************");
		
		boolean flag = true;
		
		while (flag) {
			System.out.println();
			System.out.println("1. 리스트     2.등록     3.수정     4.삭제     5.검색    6.종료");
			System.out.println("---------------------------------------------------------");
			System.out.print("> 메뉴번호: ");

			int num = sc.nextInt();
			
			switch (num) {
				case 1:
					System.out.println("<1. 리스트>");
					int count = phoneDao.personSelect();
					check(count);
					break;
					
				case 2:
					System.out.println("<2. 등록>");
					
					System.out.print("> 이름: ");
					String name = sc.next();
					System.out.print("> 휴대전화: ");
					String hp = sc.next();					
					System.out.print("> 회사전화: ");
					String company = sc.next();	
					
					count = phoneDao.personInsert(new PersonVo(name, hp, company));
					
					check(count, "등록");
					break;
					
				case 3:
					System.out.println("<3. 수정>");
					
					System.out.print("> 번호: ");
					int personId = sc.nextInt();
					System.out.print("> 이름: ");
					name = sc.next();
					System.out.print("> 휴대전화: ");
					hp = sc.next();					
					System.out.print("> 회사전화: ");
					company = sc.next();	
					
					count = phoneDao.personUpdate(personId, new PersonVo(name, hp, company));
					
					check(count, "수정");
					break;
					
				case 4:
					System.out.println("<4. 삭제>");
					System.out.print("> 번호: ");
					int no = sc.nextInt();
					
					count = phoneDao.personDelete(no);
					
					check(count, "삭제");
					break;
					
				case 5:
					System.out.println("<5. 검색>");
					System.out.print("> 검색어: ");
					String search = sc.next();	

					count = phoneDao.personSelect(search);
					check(count);
					break;
					
				case 6:
					flag = false;
					break;
					
				default:
					System.out.println("[다시 입력해 주세요.]");
					break;
			}
		}
		System.out.println();
		System.out.println("**********************************************************");
		System.out.println("*                        감사합니다                         *");
		System.out.println("**********************************************************");
			
		sc.close();
	}
	
	
	public static void check(int count, String str) {
		if (count > 0) {
			System.out.println("[" + count + "건 " + str + "되었습니다.]");
		} else {
			System.out.println("[정보가 " + str + "되지 않았습니다.]");
		}
	}

	
	public static void check(int count) {
		if (count <= 0) {
			System.out.println("[정보가 조회되지 않았습니다.]");
		}
	}
}
