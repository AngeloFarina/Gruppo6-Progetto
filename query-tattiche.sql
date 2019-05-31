-- query eseguita da visualizza mezzi provincia
SELECT M.id, C.nome, C.luogo
FROM Caserma C, Mezzo M
WHERE M.idCaserma=C.id and C.idProvincia=? --'MO001' OPPURE 'BO001'
group by M.id
order by C.luogo
--***************************************
--visualizza mezzi caserma
SELECT M.id, C.nome, C.luogo
FROM Caserma C, Mezzo M
WHERE M.idCaserma=C.id and C.id=? --'id' caserma che esegue il visualizza mezzi
group by M.id
order by C.luogo