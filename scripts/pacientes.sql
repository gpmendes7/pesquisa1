-- selecionar todos os pacientes
select * from paciente;

-- pacientes com cpf 63183358700 e alguns outros campos iguais
-- porém o nome está com uma diferença na ordem dos sobrenomes
select * from paciente
where cpf = '63183358700';