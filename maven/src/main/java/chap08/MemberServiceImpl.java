package chap08;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// interface로 구현한 서비스들은 이런식으로 이름짓는다.

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;
	
	@Override
	public List<MemberVo> selectList() {
		
		return dao.selectList();
	}

	@Override
	public MemberVo login(MemberVo vo) {
		return dao.login(vo);
	}

	@Override
	public MemberVo mypage(int mno) {
//		MemberVo vo = (MemberVo)sess.getAttribute("memberInfo")
//		MemberVo m = dao.selecOne(vo.getMno());
//		model.Attribute("vo", m)
//		return "member/mypage";
		return dao.selectOne(mno);
	}

	@Override
	public int update(MemberVo vo) {
		return dao.update(vo);
	}

}
