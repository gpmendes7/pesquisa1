-- registros sem data de notificação
select count(n.numeroNotificacao) as total
from notificacao n
where n.dataNotificacao is null;

-- registros sem data de início sintomas
select count(n.numeroNotificacao) as total
from notificacao n
where n.dataInicioSintomas is null;

-- registros sem data de encerramento
select count(n.numeroNotificacao) as total
from notificacao n
where n.dataEncerramento is null;

-- registros sem data de teste
select count(n.numeroNotificacao) as total
from notificacao n
where n.dataTeste is null;

-- registros sem data de internação
select count(n.numeroNotificacao) as total
from notificacao n
where n.dataInternacao is null;

-- contar notificações com dataInternacao
select count(n.dataInternacao)
from notificacao n;

-- contar notificações com dataInicioSintomas
select count(n.dataInicioSintomas)
from notificacao n;

-- contar notificações com dataEncerramento
select count(n.dataEncerramento)
from notificacao n;

-- contar notificações com dataTeste
select count(n.dataTeste)
from notificacao n;

-- contar notificações com dataNotificacao
select count(n.dataNotificacao)
from notificacao n;

-- contar notificações com dataNascimento
select count(n.dataNascimento)
from notificacao n;