insert into ingredient values('BNBD', '번빵', 'BREAD');

# 주문되었던 모든 버거를 추출한다
select * 
from burger b
where b.id in ( 
	select cb.burger  
	from corder_burger cb
	join corder c on cb.corder = c.id);
	
# 버거의 user 열에 설게한 사용자 ID 저장
update burger b set b.user = (
	select c.user_id
	from corder_burger cb
		join corder c on c.id = cb.corder 
			and cb.burger = b.id)
where b.id in ( 
	select cb.burger  
	from corder_burger cb
	join corder c on cb.corder = c.id);	
	
# 사용자별 설계했던 버거 생성일시 역순 정렬	
select * 
from burger b
where b.id in ( 
	select cb.burger  
	from corder_burger cb
	join corder c on cb.corder = c.id)
order by user asc, created_at desc;

# 버거 테이블 열이름 변경
ALTER TABLE hamburger.burger CHANGE `user` user_id int(11) DEFAULT NULL NULL COMMENT 'id of user who designed this burger';

# 삭제할 (불완전한 자료 가진) 햄버거 수 검색
select count(*) from hamburger.burger_ingredient bi
join hamburger.burger bu on bu.id = bi.burger 
where bu.user_id is null;

# 햄버거 삭제 준비로 버거 재료 레코드 삭제
delete from burger_ingredient
where id in (
	select bi.id from hamburger.burger_ingredient bi
	join hamburger.burger bu on bu.id = bi.burger 
	where bu.user_id is null);

# 불완전 햄버거 레코드 삭제 
DELETE FROM hamburger.burger
WHERE user_id is null;
