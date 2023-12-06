package test.spring.mvc.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.spring.mvc.entity.TestEntity;

// <bean id="testDTO" (괄호없으면) id 바꾸려면 (괄호안에 아이디 작성)>


@Data
@NoArgsConstructor
public class TestDTO {
		private String id;
		private String pw;
		private int age;
		private Date reg;
		
		@Builder
		public TestDTO(String id, String pw, int age, Date reg) {
			super();
			this.id = id;
			this.pw = pw;
			this.age = age;
			this.reg = reg;
		}
		
		public TestEntity toEntity() {			// 객체생성(new) - builder어노테이션 붙은거만 생성가능
			return TestEntity.builder()
					.id(this.id)
					.pw(this.pw)
					.age(this.age)
					.reg(this.reg).build(); 
		}
		
}
