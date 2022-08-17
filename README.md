# hello-spring
springboot practice

김영한님의 springboot 입문 강의를 들으며 작성한 코드
강의: https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8

# Notes
- 테스트 코드를 클래스 단위로 실행할 때, 절대 메서드가 구현된 순서 의존적으로 해서는 안됨! (순서과 관계없이 실행되기 때문)
- 테스트 코드 작성 시 given when then 으로 나누어 생각하는 것이 권장됨.
- 직접 MemberService 내에서 직접 MemoryMemberRepository를 생성하지 않고, MemberService를 만들 때 외부에서 넣어주는 것 == 의존성 주입
- Spring app이 실행될 때, 스프링 컨테이너라는 거대한 통이 생기게 되는데, @Controller 어노테이션이 있으면 해당 클래스 이름을 가진 객체를 생성해서 컨테이너에 넣어두고, Spring이 이 객체를 관리한다.
### "의존 관계"의 의미
Controller에서 Service객체를 생성하여 사용하는 것을 Controller가 Service에 대해 의존 관계를 가진다고 표현한다.
### Spring에서 객체를 생성할 때
이 때, new 키워드로 Service객체를 생성하면 다른 Controller에서 이 Service를 사용할 때 각각 다른 객체로 생성되어지게 되기 때문에 new 키워드를 사용하는 것을 지양해야 한다.

### @Autowired 어노테이션
이 대신 Controller의 멤버 변수로 Service 변수를 선언해준 뒤, 생성자에서 parameter로 받아 멤버변수로 지정해준 뒤, @Autowired 어노테이션을 해당 생성자에 붙이면, 스프링 내부에서 스프링 컨테이너에 있는 Service 객체를 해당 파라미터에 넣어서 Controller를 생성하게 된다. 

### DI (Dependency Injection) - 의존성 주입
@Autowired 어노테이션을 통해 스프링 컨테이너에 있던 Service(Repository)객체를 Controller(Service)생성자 파라미터로 넣어줌으로써, 의존관계(의존성)을 주입하는 것

### 스프링 빈을 등록하는 두가지 방법
컴포넌트 스캔과 자동 의존관계 설정 (스프링이 컴포넌트 관련 어노테이션 -@Controller, @Service, @Repository-이 달린 클래스를 스캔하여 컨테이너에 컴포넌트로 등록해주고, @Autowired 어노테이션을 통한 의존관계를 설정해 줌)
자바 코드로 직접 스프링 빈 등록하기 (SpringConfig 클래스 생성하여 Service와 Repository이름으로 된 public 메서드를 해당 객체를 new로 생성하여 반환하게 하도록 구현 후, @Bean 어노테이션을 붙인다. 단, Controller는 컴포넌트 스캔과 자동 의존관계 설정 방식을 이용해야 한다.) 
현재는 Reposiroty 구현체를 나중에 다른 클래스(실제 DB와 연동되는 구현클래스)로 변경할 예정이기 때문에, Config 파일만 수정하면 되어서, 이 방법을 사용한다.

- 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다. 같은 스프링 빈이면, 모두 같은 인스턴스다. (특수 케이스에 예외 설정 가능)

### 객체지향적 설계의 장점
- 다형성의 활용!
- 상황에 따라 코드의 수정을 최소화하면서 같은 인터페이스를 구현하는 다른 구현체를 적용할 수 있다. (Config 코드 외에 다른 부분들을 수정하지 않음으로써, 같은 코드로 다른 기능을 구현할 수 있는 것이다)
- Spring에서는 DI가 가능해져, 이런 다형성을 더 편리하게 활용할 수 있다.

### 개방-폐쇄 원칙 (Open-Closed Principle)
- 확장에는 열려있고, 수정 및 변경에는 닫혀있다.
- 객체지향의 다형성을 잘 활용하면 확장성은 용이하게 하되, 최소한의 수정으로 다른 컴포넌트를 통해 구현 (확장)이 가능해진다.

### 스프링 통합 테스트
- 테스트 코드에서의 @Transactional 
  - 테스트 과정에서 DB에 C, U, D 가 일어났으면, 해당 이벤트들을 건마다! 항상! rollback해줌
  - 다음 테스트에 영향을 주지 않게 되어, 테스트 코드를 반복 실행할 수 있게 된다.

### Unit Test VS Integration Test
- 좋은 테스트는 Unit Test다. (실행 시간이 10배 이상 차이 남)
- 순수한 자바 코드 기반인 Unit Test 코드를 잘 짜도록 훈련해야한다! 

### @Autowired의 생략
생성자가 1개인 경우, (생성자 내부에서 new로 생성되는 객체가 그 생성자에서만 생성될 경우) @Autowired 는 생략 가능하다.

### JPA
- JPA 사용 시 이점
  - SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환할 수 있다.
  - 개발 생산성을 크게 높일 수 있다.

### AOP가 필요한 상황
  - 핵심 관심사항(비즈니스 로직)과 공통 관심사항(cross-cutting concern, i.e.: 시간 측정 로직 등)의 분리가 어려울 때
  
### AOP 적용
- AOP (Aspect Oriented Programming) - 관점 지향 프로그래밍
- 공통관심사항과 핵심관심사항을 분리!

### AOP관련 어노테이션들
- @Aspect: 컴포넌트 스캔 대상이 되는 클래스(공통 관심사 로직을 구현한 메서드를 포함한 클래스)에 붙이는 어노테이션
- @Around: 실제 공통관심사 로직이 구현된 메서드 위에 붙여, AOP 적용범위(분리가 필요했던 핵심 관심사가 구현된 클래스/패키지)를 지정하는 어노테이션
