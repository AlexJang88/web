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

@Data  // set , get , �⺻������ , toString
@NoArgsConstructor   //�⺻������ ������ִ� ������̼�
@Entity // JPA ���� ��ü
@Table(name="test") //���̺� �̸�
public class TestEntity {
		
//		@Column(name="re_step") //JPA ���� ������ũ ǥ����� ���� X , �׷��� ī��ǥ������� ����ؾ��� / DB�� ������ũǥ����϶�
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
