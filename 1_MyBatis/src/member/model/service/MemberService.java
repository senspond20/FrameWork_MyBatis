package member.model.service;

import static common.Template.getSqlSession;

import org.apache.ibatis.session.SqlSession;

import member.model.dao.MemberDAO;
import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberService {
	public MemberService() {
		// TODO Auto-generated constructor stub
	}

	public Member selectMember(Member m) throws MemberException {
	//	Connection conn = getConnection();
	//  MyBatis 들어와서는 Connection을 쓰지않고, 
		
		SqlSession session = getSqlSession();
		Member member = new MemberDAO().selectMember(session, m);
		session.close();
		
		return member;
	}
	public void insertMember(Member m ) throws MemberException {
		
		SqlSession session = getSqlSession();
		new MemberDAO().insertMember(session, m);
		
	}
}
