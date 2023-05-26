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