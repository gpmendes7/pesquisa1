-- selecionar distintos valores de resultadoTeste
select distinct (p.resultadoTeste)
from paciente p;

-- registros sem resultadoTeste
select count(p.id) as total
from paciente p
where p.resultadoTeste = '';

-- contagem de registros por resultadoTeste 
select p.resultadoTeste, count(p.resultadoTeste)
from paciente p
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste janeiro 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 1 
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste janeiro 2021
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 1 
and year(p.dataNotificacao) = 2021
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste fevereiro 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 2
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste fevereiro 2021
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 2
and year(p.dataNotificacao) = 2021
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste março 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 3
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste março 2021
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 3
and year(p.dataNotificacao) = 2021
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste abril 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 4
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste abril 2021
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 4
and year(p.dataNotificacao) = 2021
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste maio 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 5
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste maio 2021
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 5
and year(p.dataNotificacao) = 2021
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste maio 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 6
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste maio 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 6
and year(p.dataNotificacao) = 2021
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste julho 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 7
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste julho 2021
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 7
and year(p.dataNotificacao) = 2021
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste agosto 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 8
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste setembro 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 9
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste outubro 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 10
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste novembro 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 11
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;

-- contagem de registros por resultadoTeste dezembro 2020
select resultadoTeste, count(p.id) as qtd
from paciente p
where month(p.dataNotificacao) = 12
and year(p.dataNotificacao) = 2020
group by p.resultadoTeste;