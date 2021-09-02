-- paciente com duas notificações
-- 
-- notificacao1:
-- resultadoTeste Positivo e evolucaoCaso RECUPERADO
-- 
-- notificacao2:
-- resultadoTeste Positivo e evolucaoCaso INTERNADO
-- 
-- antes paciente estava como 
-- resultadoTeste Positivo e evolucaoCaso RECUPERADO
-- 
-- espera-se que após atualização
-- resultadoTeste Positivo e evolucaoCaso INTERNADO

SELECT resultadoTeste, evolucaoCaso 
from notificacao
where cpf = '92410820700';

select resultadoTeste, evolucaoCaso
from paciente
where cpf = '92410820700';

-- paciente com duas notificações
-- 
-- notificacao1:
-- resultadoTeste EM BRANCO e evolucaoCaso OBITO
-- 
-- notificacao2:
-- resultadoTeste Positivo e evolucaoCaso OBITO
-- 
-- antes paciente estava como 
-- resultadoTeste EM BRANCO e evolucaoCaso OBITO
-- 
-- espera-se que após atualização
-- resultadoTeste Positivo e evolucaoCaso OBITO

SELECT resultadoTeste, evolucaoCaso 
from notificacao
where cpf = '7259424796';

select resultadoTeste, evolucaoCaso 
from paciente
where cpf = '7259424796';

-- paciente com três notificações
-- 
-- notificacao1:
-- resultadoTeste Negativo e evolucaoCaso RECUPERADO
-- 
-- notificacao2:
-- resultadoTeste Positivo e evolucaoCaso RECUPERADO
-- 
-- notificacao3:
-- resultadoTeste Positivo e evolucaoCaso OBITO
-- 
-- antes paciente estava como 
-- resultadoTeste Negativo e evolucaoCaso RECUPERADO
-- 
-- espera-se que após atualização
-- resultadoTeste Positivo e evolucaoCaso OBITO

SELECT resultadoTeste, evolucaoCaso 
from notificacao
where cpf = '8769440719';

select resultadoTeste, evolucaoCaso 
from paciente
where cpf = '8769440719';