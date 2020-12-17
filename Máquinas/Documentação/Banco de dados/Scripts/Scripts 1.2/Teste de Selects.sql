select 
	mc.alerta,
	ma.idMaquina,
    comp.tipoComponente as "tipo de leitura",
    le.leitura,
    comp.metrica,
    le.horaDeLeitura
from leituras as le
    inner join MaqComp as mc
    on le.fkMaqComp = mc.idMaqComp
    inner join Maquina as ma
    on mc.fkMaquina = ma.idMaquina
	inner Join componente as comp
    on mc.fkComponente = comp.idComponente
    where ma.nome = "HSL089"
    order by mc.alerta;
    
desc componente;
desc Maq_Comp;

select
	ma.idMaquina,
    ma.nome,
    mc.idMaqComp,
    co.tipoComponente
from Maquina as ma
    inner join MaqComp as mc
    on mc.fkMaquina = ma.idMaquina
    inner join Componente as co
    on mc.fkComponente = co.idComponente
    where ma.nome like 'HSL089';
    



select 
	ma.idMaquina,
	ma.nome
    from maquina as ma
    where ma.nome = "HSL089";

truncate leituras;
Select * from leituras;


