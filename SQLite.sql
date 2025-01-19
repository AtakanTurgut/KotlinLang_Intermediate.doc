CREATE Table If NOT EXISTS urunler (id INTEGER PRIMARY Key, isim VARCHAR, fiyat DOUBLE);

INSERT INTO urunler (isim, fiyat) VALUES ('Ayakkabı', 1000);
INSERT INTO urunler (isim, fiyat) VALUES ('Elbise', 1200);
INSERT INTO urunler (isim, fiyat) VALUES ('Tshirt', 300);
INSERT INTO urunler (isim, fiyat) VALUES ('Atkı', 120);
INSERT INTO urunler (isim, fiyat) VALUES ('Bere', 80);
INSERT INTO urunler (isim, fiyat) VALUES ('Palto', 2500);
INSERT INTO urunler (isim, fiyat) VALUES ('Şapka', 110);

SELECT * FROM urunler;
SELECT isim, fiyat FROM urunler;

SELECT * FROM urunler WHERE id = 5;
SELECT * FROM urunler WHERE fiyat < 500;

SELECT * FROM urunler where isim LIKE 'A%';
SELECT * FROM urunler where isim LIKE '%o';
SELECT * FROM urunler where isim LIKE '%a%';

DELETE FROM urunler where id = 2;
UPDATE urunler SET fiyat = 250 where isim = 'Tshirt'
