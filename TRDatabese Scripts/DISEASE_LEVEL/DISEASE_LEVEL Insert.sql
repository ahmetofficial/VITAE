-- Developer: Ahmet Kaymak
-- Date: 27.03.2017
-- Description: Tüm hastalık seviyeleri DISEASE_LEVEL tablosuna insert edildi.

INSERT INTO TRDatabase.DISEASE_LEVEL
(disease_level_id,disease_level_name)
VALUES(0,'unknown'); -- bilinmiyor

INSERT INTO TRDatabase.DISEASE_LEVEL
(disease_level_id,disease_level_name)
VALUES(1,'at beginning'); -- başlangıç

INSERT INTO TRDatabase.DISEASE_LEVEL
(disease_level_id,disease_level_name)
VALUES(2,'preliminary'); -- hafif

INSERT INTO TRDatabase.DISEASE_LEVEL
(disease_level_id,disease_level_name)
VALUES(3,'moderate'); -- orta

INSERT INTO TRDatabase.DISEASE_LEVEL
(disease_level_id,disease_level_name)
VALUES(4,'heavy'); -- ağır

INSERT INTO TRDatabase.DISEASE_LEVEL
(disease_level_id,disease_level_name)
VALUES(5,'severe'); -- ileri

