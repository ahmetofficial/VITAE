-- Developer: Ahmet Kaymak
-- Date: 27.03.2017
-- Description: General Health Module'deki tüm durumlar STATE tablosuna eklendi.

INSERT INTO TRDatabase.STATE
(state_id,state_name)
VALUES(0,'unknown'); -- bilinmiyor

INSERT INTO TRDatabase.STATE
(state_id,state_name)
VALUES(1,'start'); -- teşhis konuldu

INSERT INTO TRDatabase.STATE
(state_id,state_name)
VALUES(2,'continue'); -- devam ediyor

INSERT INTO TRDatabase.STATE
(state_id,state_name)
VALUES(3,'quit'); 

INSERT INTO TRDatabase.STATE
(state_id,state_name)
VALUES(4,'restart'); -- tekrarladı

INSERT INTO TRDatabase.STATE
(state_id,state_name)
VALUES(5,'finish'); -- iyileşti
