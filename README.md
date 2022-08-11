# hello-spring
springboot practice

김영한님의 springboot 입문 강의를 들으며 작성한 코드
강의: https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8

# Notes
- 테스트 코드를 클래스 단위로 실행할 때, 절대 메서드가 구현된 순서 의존적으로 해서는 안됨! (순서과 관계없이 실행되기 때문)
- 테스트 코드 작성 시 given when then 으로 나누어 생각하는 것이 권장됨.
- 직접 MemberService 내에서 직접 MemoryMemberRepository를 생성하지 않고, MemberService를 만들 때 외부에서 넣어주는 것 == 의존성 주입
