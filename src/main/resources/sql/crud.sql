insert into ingredient values('BNBD', '번빵', 'BREAD');

# 주문되었던 모든 버거를 추출한다
select * 
from burger b
where b.id in ( 
	select cb.burger  
	from corder_burger cb
	join corder c on cb.corder = c.id);