-- selecionar todas as notificações
select * FROM notificacao;

-- selecionar todas as notificações descartadas
SELECT * FROM notificacao
where descartada;

-- selecionar todas as notificações não descartadas
SELECT * FROM notificacao
where not descartada;

-- notificações com mesmo nome completo mas com cpf diferentes
-- provavelmente inconsistentes entre si
select numeroNotificacao, cpf, nomeCompleto, paciente_id, dataNascimento
from notificacao n
where cpf = '11099557747';

-- notificações duplicadas por cpf e data nascimento
-- quaddo possível, uma delas vai receber o nome completo válido de outra
select numeroNotificacao, cpf, nomeCompleto, paciente_id, dataNascimento
from notificacao n
where cpf = '4391957728';

-- notificações duplicadas por nome completo e data nascimento
-- quando possível, uma delas vai receber o cpf válido de outra
select numeroNotificacao, cpf, nomeCompleto, paciente_id, dataNascimento
from notificacao n
where nomeCompleto = 'MARIA DO CARMO SILVA' and dataNascimento = '1948-04-25';

-- identificar notificações duplicadas por nome completo e data nascimento
select numeroNotificacao, cpf, nomeCompleto, paciente_id, dataNascimento
from Notificacao n1
where n1.nomeCompleto != '' 
and n1.dataNascimento is not null
and exists (
select *
from Notificacao n2
where n2.nomeCompleto = n1.nomeCompleto
and  n2.dataNascimento = n1.dataNascimento
and n2.numeroNotificacao != n1.numeroNotificacao
);

-- identificar notificações duplicadas com nome completo MARIA DO CARMO
select numeroNotificacao, cpf, nomeCompleto, paciente_id, dataNascimento
from Notificacao n1
where n1.nomeCompleto = 'MARIA DO CARMO SILVA' 
and n1.dataNascimento is not null
and exists (
select *
from Notificacao n2
where n2.nomeCompleto = n1.nomeCompleto
and  n2.dataNascimento = n1.dataNascimento
and n2.numeroNotificacao != n1.numeroNotificacao
);

-- identificar notificações duplicadas por cpf e data nascimento
select numeroNotificacao, cpf, nomeCompleto, paciente_id, dataNascimento
from Notificacao n1
where cpf != ''
and n1.dataNascimento is not null
and exists (
select *
from Notificacao n2
where n2.cpf = n1.cpf
and n2.dataNascimento = n1.dataNascimento
and n2.numeroNotificacao != n1.numeroNotificacao
);

-- identificar notificações duplicadas com cpf 4391957728
select numeroNotificacao, cpf, nomeCompleto, paciente_id, dataNascimento
from Notificacao n1
where cpf = '4391957728'
and n1.dataNascimento is not null
and exists (
select *
from Notificacao n2
where n2.cpf = n1.cpf
and n2.dataNascimento = n1.dataNascimento
and n2.numeroNotificacao != n1.numeroNotificacao
);

-- ordenar registros por dataNotificacao
select n.dataNotificacao
from notificacao n
order by n.dataNotificacao;

-- agrupar quantidade de notificações por pacientes
select n.paciente_id, count(n.numeroNotificacao) 
from notificacao n
where n.paciente_id is not null
group by n.paciente_id;

-- quantidade de notificações de paciente com id 80000
select count(n.numeroNotificacao) 
from notificacao n
where n.paciente_id = 80000;

-- id de paciente 12223 com 3 notificações
select count(n.numeroNotificacao) 
from notificacao n
where n.paciente_id = 12223;

-- total de notificações válidas com paciente repetido
select count(n1.numeroNotificacao) as total
from notificacao n1
where not n1.descartada
and exists (select *
			   from notificacao n2
               where n2.paciente_id = n1.paciente_id
               and not n2.descartada
               and n2.numeroNotificacao != n1.numeroNotificacao);
               
-- total de notificações válidas sem paciente repetido
select count(n1.numeroNotificacao) as total
from notificacao n1
where not n1.descartada
and not exists (select *
			   from notificacao n2
               where n2.paciente_id = n1.paciente_id
               and not n2.descartada
               and n2.numeroNotificacao != n1.numeroNotificacao);			