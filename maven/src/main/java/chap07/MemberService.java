package chap07;

import java.util.List;

// Service는 보통 Interface로 만든다
public interface MemberService {
	List<MemberVo> selectList();
}
