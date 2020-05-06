package member.model.dao;

import org.apache.ibatis.session.SqlSession;

import member.model.exception.MemberException;
import member.model.vo.Member;

public class MemberDAO {

	public Member selectMember(SqlSession session, Member m) throws MemberException {
		Member member = null;
		member = session.selectOne("memberMapper.loginMember", m); 
		
		System.out.println(member);
		
		if(member == null) {
			session.close();
			throw new MemberException("로그인에 실패했습니다.");
		}
		return member;
	}

	public void insertMember(SqlSession session, Member m) throws MemberException {
	
		int result = session.insert("memberMapper.insertMember", m);
		
		
//		롤백이나 커밋은 서비스단에서 하기로 했었는데, 여기서는 DAO에서 한다. 
//		왜일까요? 
// 		MemberException이 발생하면 서비스에서 롤백이 될수없어서 
		
		if(result <= 0) {
			session.rollback();
			session.close();
			
			throw new MemberException("회원가입에 실패하였습니다.");
			
		}
	}
	

}
