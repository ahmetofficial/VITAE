-- Developer: Ahmet Kaymak
-- Date: 18.02.2017
-- Description: RELATIONSHIP_STATUS tablosuna uygulamada kullanıcılar arasındaki ilişkiyi belirlemek için kullanılan tablo.
--              pending: bağlantı isteği gönderildi yanıt bekleniyor.
--              accepted: bağlantı isteği onaylandı.
--              cancelled: bağlantı isteği red edildi.
--              blocked: kullanıcı başka bir kullanıcıyı engellemiştir.

INSERT INTO `TRDatabase`.`RELATIONSHIP_STATUS`
VALUES(1,'pending');

INSERT INTO `TRDatabase`.`RELATIONSHIP_STATUS`
VALUES(2,'accepted');

INSERT INTO `TRDatabase`.`RELATIONSHIP_STATUS`
VALUES(3,'cancelled');

INSERT INTO `TRDatabase`.`RELATIONSHIP_STATUS`
VALUES(4,'blocked');

