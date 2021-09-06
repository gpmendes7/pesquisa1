-- selecionar todos os pacientes
select * from paciente;

-- pacientes com cpf 63183358700 e alguns outros campos iguais
-- porém o nome está com uma diferença na ordem dos sobrenomes
select * from paciente
where cpf = '63183358700';

-- pacientes com alguma notificação
select p.cpf
from paciente p, 
     ( select n.paciente_id, count(n.numeroNotificacao) as qtd
	   from notificacao n
	   where n.paciente_id is not null
       and not n.descartada
	   group by n.paciente_id
	 ) t
where t.qtd >= 1
and t.paciente_id = p.id;

-- pacientes com alguma notificação
select p.cpf
from paciente p
where exists ( select n.paciente_id, count(n.numeroNotificacao) as qtd
				from notificacao n
				where n.paciente_id is not null
                and n.paciente_id = p.id
                and not n.descartada
                group by n.paciente_id
				having qtd >= 1
            );

-- pacientes com exatamente uma notificação
select p.cpf
from paciente p
where exists ( select n.paciente_id, count(n.numeroNotificacao) as qtd
				from notificacao n
				where n.paciente_id is not null
                and n.paciente_id = p.id
                and not n.descartada
                group by n.paciente_id
				having qtd = 1
            );
		
-- pacientes com pelo menos duas notificações
select p.cpf
from paciente p
where exists ( select n.paciente_id, count(n.numeroNotificacao) as qtd
				from notificacao n
				where n.paciente_id is not null
                and n.paciente_id = p.id
                and not n.descartada
                group by n.paciente_id
				having qtd >= 2
            );

-- total de pacientes sem cpf
select count(p.id)
from paciente p
where p.cpf = '';

-- total de pacientes sem nomeCompleto
select count(p.id)
from paciente p
where p.nomeCompleto = '';

-- total de pacientes sem nomeCompleto
select count(p.id)
from paciente p
where p.cpf = ''
and p.nomeCompleto = '';
