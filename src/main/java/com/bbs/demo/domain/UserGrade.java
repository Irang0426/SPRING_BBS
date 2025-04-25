package com.bbs.demo.domain;


public enum UserGrade {
	TEST1(1),
	TEST2(2),
	TEST3(3),
	TEST4(4),
	TEST5(5),
	TEST6(6),
	TEST7(7),
	TEST8(8),
	TEST9(9),
	ADMIN(10);
	
    private final int grade;	// enum 객체 생성시 value 값

    UserGrade(int value) {
        this.grade = value;		// 생성시 value 설정 | 객체 생성시 상수의 () 내부값이 자동으로 설정
    }

    public int getGrade() {		// 저장된 value
        return grade;
    }

    public static UserGrade fromGrade(int grade) {
        for (UserGrade val : UserGrade.values()) {		// 모든 상수(value)를 가져옴
            if (val.getGrade() == grade) {				// 상수의 value가 입력값과 같으면 리턴
                return val;
            }
        }
        throw new IllegalArgumentException("Invalid grade value: " + grade);		// 범위 초과시 에러 메세지
    }
}
