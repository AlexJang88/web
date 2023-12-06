package test.spring.mvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // set , get , 기본생성자 , toString
@NoArgsConstructor   //기본생성자 만들어주는 어노테이션
@Entity // JPA 연동 객체
@Table(name="test") //테이블 이름
public class TestEntity {
		
//		@Column(name="re_step") //JPA 에선 스네이크 표기법이 지원 X , 그래서 카멜표기법으로 사용해야함 / DB가 스네이크표기법일때
//		private int reStep;
		
		@Id //primary key
		private String id;
		private String pw;
		private int age;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="reg" , updatable = false)
		private Date reg;
		
		@Builder
		public TestEntity(String id, String pw, int age, Date reg) {
			super();
			this.id = id;
			this.pw = pw;
			this.age = age;
			this.reg = reg;
		}
		
		
}
