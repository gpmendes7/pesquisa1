-- selecionar distintos valores de evolucaoCaso
select distinct (n.evolucaoCaso)
from notificacao n;

-- registros sem evolucaoCaso
select count(n.numeroNotificacao) as total
from notificacao n
where n.evolucaoCaso = '';

-- contagem de registros por evolucaoCaso
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
group by n.evolucaoCaso;

-- contagem de registros por evolução caso janeiro
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 1
group by n.evolucaoCaso;

-- contagem de registros por evolução caso janeiro 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 1 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso janeiro 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 1 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where n.dataNotificacao between '2020-01-01' and '2020-01-31'
group by n.evolucaoCaso;

-- contagem de registros por evolução caso fevereiro
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 2
group by n.evolucaoCaso;

-- contagem de registros por evolução caso fevereiro 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 2 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso fevereiro 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 2 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso março
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 3
group by n.evolucaoCaso;

-- contagem de registros por evolução caso março 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 3 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso março 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 3 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso abril
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 4
group by n.evolucaoCaso;

-- contagem de registros por evolução caso abril 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 4 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso abril 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 4 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso maio
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 5
group by n.evolucaoCaso;

-- contagem de registros por evolução caso maio 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 5 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso maio 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 5 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso junho
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 6
group by n.evolucaoCaso;

-- contagem de registros por evolução caso junho 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 6 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso junho 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 6 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso julho
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 7
group by n.evolucaoCaso;

-- contagem de registros por evolução caso julho 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 7 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso julho 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 7 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso agosto
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 8
group by n.evolucaoCaso;

-- contagem de registros por evolução caso agosto 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 8 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso agosto 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 8 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso setembro
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 9
group by n.evolucaoCaso;

-- contagem de registros por evolução caso setembro 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 9 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso setembro 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 9 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso outubro
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 10
group by n.evolucaoCaso;

-- contagem de registros por evolução caso outubro 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 10 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso outubro 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 10 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso novembro 
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 11 
group by n.evolucaoCaso;

-- contagem de registros por evolução caso novembro 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 11 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso novembro 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 11 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;

-- contagem de registros por evolução caso dezembro
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 12
group by n.evolucaoCaso;

-- contagem de registros por evolução caso dezembro 2020
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 12 and year(n.dataNotificacao) = 2020
group by n.evolucaoCaso;

-- contagem de registros por evolução caso dezembro 2021
select evolucaoCaso, count(n.numeroNotificacao) as qtd
from notificacao n
where month(n.dataNotificacao) = 12 and year(n.dataNotificacao) = 2021
group by n.evolucaoCaso;