-- selecionar distintos valores de resultadoTeste
select distinct (n.resultadoTeste)
from notificacao n;

-- registros sem resultado teste
select count(n.numeroNotificacao) as total
from notificacao n
where n.resultadoTeste = '';

-- contagem de registros por resultadoTeste 
select n.resultadoTeste, count(n.resultadoTeste)
from notificacao n
group by n.resultadoTeste;