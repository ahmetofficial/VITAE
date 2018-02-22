-- Developer: Ahmet Kaymak
-- Date: 14.04.2017
-- Description: HOSPITALS tablosu oluşturuldu.
CREATE TABLE `HOSPITALS` (
  `hospital_id` int(11) NOT NULL,
  `hospital_user_id` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hospital_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `hospital_type` int(11) NOT NULL,
  `establishement_date` date DEFAULT NULL,
  `total_score` bigint(20) NOT NULL,
  `total_vote_number` int(11) NOT NULL,
  `overall_score` double(10,5) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `website` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone_number` varchar(13) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mail` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `adress` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `latitude` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`hospital_id`),
  FULLTEXT KEY `hospital_name` (`hospital_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT= 'hospital_type = 0 | devlet/state
hospital_type = 1 | özel/private
isActive = true | hastane kendi sayfasını yönetiyor.
isActive = false | hastanenin sistem tarafından bilgilarinin gösterildiği bir sayfası var.';
-- End of file.

