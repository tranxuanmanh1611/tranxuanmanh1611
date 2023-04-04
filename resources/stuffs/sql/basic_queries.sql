
-- 2.	Hiển thị thông tin của tất cả nhân viên có trong hệ thống có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
SELECT *
FROM nhan_vien WHERE (ten LIKE 'H%' OR ten LIKE 'T%' OR ten LIKE 'K%') AND CHAR_LENGTH(CONCAT(ho_ten_lot,' ',ten)) <= 15; 

-- 3.	Hiển thị thông tin của tất cả khách hàng có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
SELECT *
FROM khach_hang
WHERE ((YEAR(NOW())-YEAR(ngay_sinh)) BETWEEN 18 AND 50)
AND   (dia_chi LIKE '%Đà Nẵng%' OR dia_chi LIKE '%Quảng Trị%');

-- 4.	Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. 
-- Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng. Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
SELECT khach_hang.ma_khach_hang,
	   khach_hang.ho_ten,
       COUNT(*) AS so_lan_dat
FROM khach_hang
JOIN hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
WHERE khach_hang.ma_loai_khach =1
GROUP BY 1
ORDER BY 3 DESC;

-- 5.Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien 
-- (Với tổng tiền được tính theo công thức như sau: Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet) 
-- cho tất cả các khách hàng đã từng đặt phòng. (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).
SELECT khach_hang.ma_khach_hang,
	   khach_hang.ho_ten,
       loai_khach.ten_loai_khach,
       hop_dong.ma_hop_dong,
       dich_vu.ten_dich_vu,
       hop_dong.ngay_lam_hop_dong,
       hop_dong.ngay_ket_thuc,
     ( IF(dich_vu.chi_phi_thue IS NULL,0,dich_vu.chi_phi_thue) +
		SUM(IF(dich_vu_di_kem.gia IS NULL,0,dich_vu_di_kem.gia*hop_dong_chi_tiet.so_luong)) )        AS tong_chi_phi
FROM khach_hang
LEFT JOIN hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang	
LEFT JOIN dich_vu ON hop_dong.ma_dich_vu = dich_vu.ma_dich_vu
LEFT JOIN hop_dong_chi_tiet ON hop_dong.ma_hop_dong = hop_dong_chi_tiet.ma_hop_dong
LEFT JOIN dich_vu_di_kem ON hop_dong_chi_tiet.ma_dich_vu_di_kem = dich_vu_di_kem.ma_dich_vu_di_kem
JOIN loai_khach ON khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
GROUP BY 1,4;

-- 6.	Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu
-- của tất cả các loại dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3).
SELECT dich_vu.ma_dich_vu, dich_vu.ten_dich_vu, dich_vu.dien_tich, dich_vu.chi_phi_thue, loai_dich_vu.ten_loai_dich_vu
FROM dich_vu
JOIN loai_dich_vu ON dich_vu.ma_loai_dich_vu = loai_dich_vu.ma_loai_dich_vu
JOIN hop_dong ON dich_vu.ma_dich_vu = hop_dong.ma_dich_vu
WHERE hop_dong.ma_hop_dong NOT IN (SELECT ma_hop_dong FROM hop_dong WHERE (ngay_lam_hop_dong)>'2020-12-31' AND DATE(ngay_lam_hop_dong)<'2021-04-01')
    AND hop_dong.ngay_lam_hop_dong > '2021-04-01'
    GROUP BY 1;

-- 7.	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ 
-- đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021.
SELECT dich_vu.ma_dich_vu, dich_vu.ten_dich_vu, dich_vu.dien_tich, dich_vu.so_nguoi_toi_da, dich_vu.chi_phi_thue, loai_dich_vu.ten_loai_dich_vu
FROM hop_dong
JOIN dich_vu
ON hop_dong.ma_dich_vu = dich_vu.ma_dich_vu
JOIN loai_dich_vu 
ON loai_dich_vu.ma_loai_dich_vu = dich_vu.ma_loai_dich_vu
WHERE YEAR(hop_dong.ngay_lam_hop_dong) =2020 
AND hop_dong.ma_dich_vu NOT IN (SELECT hop_dong.ma_dich_vu FROM hop_dong
								WHERE YEAR(hop_dong.ngay_lam_hop_dong) = 2021)
GROUP BY 1;

-- 8.	Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau.
SELECT DISTINCT ho_ten
FROM khach_hang;

SELECT ho_ten
FROM khach_hang
GROUP BY 1;

SELECT ho_ten
FROM khach_hang
UNION
SELECT ho_ten
FROM khach_hang;

-- 9.	Thực hiện thống kê doanh thu theo tháng, nghĩa là tương ứng với mỗi tháng trong năm 2023 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.
SELECT MONTH(ngay_lam_hop_dong) AS thang,
	   COUNT(*) AS doanh_thu
FROM hop_dong
WHERE ngay_lam_hop_dong > DATE('2020-12-31') AND ngay_lam_hop_dong < DATE('2022-01-01')
GROUP BY 1
ORDER BY 1;

-- 10.	Hiển thị thông tin tương ứng với từng hợp đồng thì đã sử dụng bao nhiêu dịch vụ đi kèm. 
-- Kết quả hiển thị bao gồm ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, so_luong_dich_vu_di_kem 
-- (được tính dựa trên việc sum so_luong ở dich_vu_di_kem).
SELECT hop_dong.ma_hop_dong, hop_dong.ngay_lam_hop_dong, hop_dong.ngay_ket_thuc, hop_dong.tien_dat_coc,
		SUM(IF(hop_dong_chi_tiet.so_luong IS NULL,0,hop_dong_chi_tiet.so_luong)) AS so_luong_dich_vu_di_kem
FROM hop_dong
LEFT JOIN hop_dong_chi_tiet
ON hop_dong.ma_hop_dong = hop_dong_chi_tiet.ma_hop_dong
GROUP BY 1;

-- 11.	Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng bởi những khách hàng
-- có ten_loai_khach là “Diamond” và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”.
SELECT khach_hang.ma_khach_hang, khach_hang.ho_ten, loai_khach.ten_loai_khach, khach_hang.dia_chi
FROM khach_hang
JOIN loai_khach ON khach_hang.ma_loai_khach=loai_khach.ma_loai_khach
WHERE loai_khach.ten_loai_khach = 'Diamond' AND (khach_hang.dia_chi LIKE '%Vinh' OR khach_hang.dia_chi LIKE '%Quảng Ngãi%');

-- 12.	Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng), so_dien_thoai (khách hàng), 
-- ten_dich_vu, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem), tien_dat_coc
-- của tất cả các dịch vụ đã từng được khách hàng đặt vào 3 tháng cuối năm 2020 nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021.

SELECT hop_dong.ma_hop_dong,
	   CONCAT(nhan_vien.ho_ten_lot," ",nhan_vien.ten) AS ten_nhan_vien,
       khach_hang.ho_ten AS ten_khach_hang,
       khach_hang.so_dien_thoai AS sdt_khach,
       dich_vu.ten_dich_vu,
       SUM(IF(hop_dong_chi_tiet.so_luong IS NULL,0,hop_dong_chi_tiet.so_luong)) AS So_luong_dv_di_kem,
       hop_dong.tien_dat_coc
FROM hop_dong
JOIN dich_vu ON hop_dong.ma_dich_vu = dich_vu.ma_dich_vu
JOIN nhan_vien ON hop_dong.ma_nhan_vien = nhan_vien.ma_nhan_vien
JOIN khach_hang ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
LEFT JOIN hop_dong_chi_tiet ON hop_dong.ma_hop_dong =hop_dong_chi_tiet.ma_hop_dong
WHERE hop_dong.ngay_lam_hop_dong >='2020-10-01' 
AND hop_dong.ngay_lam_hop_dong<='2020-12-31'
 AND hop_dong.ma_dich_vu NOT IN (
SELECT ma_dich_vu FROM hop_dong
WHERE ngay_lam_hop_dong BETWEEN '2021-01-01' AND '2021-06-30')
 GROUP BY 1;

-- 13.	Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất bởi các Khách hàng đã đặt phòng. 
-- (Lưu ý là có thể có nhiều dịch vụ có số lần sử dụng nhiều như nhau).       
 SELECT dich_vu_di_kem.ma_dich_vu_di_kem,
		dich_vu_di_kem.ten_dich_vu_di_kem,
		SUM(hop_dong_chi_tiet.so_luong) AS so_lan_sd
FROM hop_dong_chi_tiet
JOIN dich_vu_di_kem ON hop_dong_chi_tiet.ma_dich_vu_di_kem = dich_vu_di_kem.ma_dich_vu_di_kem
GROUP BY 2,1
ORDER BY 3 DESC
LIMIT 2;

-- 14.	Hiển thị thông tin tất cả các Dịch vụ đi kèm chỉ mới được sử dụng một lần duy nhất. 
-- Thông tin hiển thị bao gồm ma_hop_dong, ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung 
-- (được tính dựa trên việc count các ma_dich_vu_di_kem).		
SELECT hop_dong.ma_hop_dong,
		loai_dich_vu.ten_loai_dich_vu,
        dich_vu_di_kem.ten_dich_vu_di_kem,
        COUNT(dich_vu_di_kem.ma_dich_vu_di_kem) AS so_lan_sd
FROM hop_dong
JOIN dich_vu ON hop_dong.ma_dich_vu = dich_vu.ma_dich_vu
JOIN loai_dich_vu ON dich_vu.ma_loai_dich_vu = loai_dich_vu.ma_loai_dich_vu
JOIN hop_dong_chi_tiet ON hop_dong.ma_hop_dong = hop_dong_chi_tiet.ma_hop_dong
JOIN dich_vu_di_kem ON hop_dong_chi_tiet.ma_dich_vu_di_kem = dich_vu_di_kem.ma_dich_vu_di_kem
WHERE hop_dong_chi_tiet.ma_dich_vu_di_kem IN (SELECT ma_dich_vu_di_kem
											FROM hop_dong_chi_tiet
											GROUP BY 1 HAVING COUNT(ma_dich_vu_di_kem)=1) 
GROUP BY 3,1;

-- 15.	Hiển thi thông tin của tất cả nhân viên bao gồm ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai, dia_chi
-- mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021.			
SELECT nhan_vien.ma_nhan_vien,
		CONCAT(nhan_vien.ho_ten_lot," ",nhan_vien.ten) AS ten_nv,
        trinh_do.ten_trinh_do,
        bo_phan.ten_bo_phan,
        nhan_vien.so_dien_thoai,
        nhan_vien.dia_chi
FROM nhan_vien
JOIN trinh_do ON nhan_vien.ma_trinh_do = trinh_do.ma_trinh_do
JOIN bo_phan ON nhan_vien.ma_bo_phan = bo_phan.ma_bo_phan
JOIN hop_dong ON hop_dong.ma_nhan_vien = nhan_vien.ma_nhan_vien
GROUP BY 1 HAVING COUNT(nhan_vien.ma_nhan_vien) <=3;

-- 16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021. (tạo tùy chọn xóa cascade trong constraint)
DELETE FROM nhan_vien
WHERE ma_nhan_vien  IN(
SELECT * FROM (SELECT nhan_vien.ma_nhan_vien FROM nhan_vien
LEFT JOIN hop_dong ON hop_dong.ma_nhan_vien = nhan_vien.ma_nhan_vien
WHERE hop_dong.ma_hop_dong IS NULL)AS sub)

-- 17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond,
-- chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán 2021 là lớn hơn 10.000.000 VNĐ.
UPDATE khach_hang
SET ma_loai_khach =1 
WHERE ma_khach_hang IN (SELECT sub.ma_khach_hang FROM(
SELECT khach_hang.ma_khach_hang,
       (dich_vu.chi_phi_thue +SUM(hop_dong_chi_tiet.so_luong*dich_vu_di_kem.gia)) AS Tong_cp
FROM khach_hang
JOIN hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
JOIN dich_vu ON hop_dong.ma_dich_vu = dich_vu.ma_dich_vu
JOIN hop_dong_chi_tiet ON hop_dong.ma_hop_dong = hop_dong_chi_tiet.ma_hop_dong
JOIN dich_vu_di_kem ON hop_dong_chi_tiet.ma_dich_vu_di_kem = dich_vu_di_kem.ma_dich_vu_di_kem
WHERE YEAR(hop_dong.ngay_lam_hop_dong) =2021 AND khach_hang.ma_loai_khach =2
GROUP BY hop_dong_chi_tiet.ma_hop_dong HAVING Tong_cp>10000000) AS sub);

-- 18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).
DELETE FROM khach_hang
WHERE ma_khach_hang IN (SELECT * FROM (
SELECT khach_hang.ma_khach_hang
FROM khach_hang
JOIN hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
WHERE YEAR(hop_dong.ngay_lam_hop_dong) <2021) AS sub)

-- 19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi.
UPDATE dich_vu_di_kem
SET gia = gia*2
WHERE ma_dich_vu_di_kem IN(SELECT sub.ma_dich_vu_di_kem FROM(
SELECT dich_vu_di_kem.ma_dich_vu_di_kem
FROM hop_dong
JOIN hop_dong_chi_tiet ON hop_dong_chi_tiet.ma_hop_dong = hop_dong.ma_hop_dong
JOIN dich_vu_di_kem ON hop_dong_chi_tiet.ma_dich_vu_di_kem = dich_vu_di_kem.ma_dich_vu_di_kem
WHERE YEAR (hop_dong.ngay_lam_hop_dong)=2020
GROUP BY 1 HAVING SUM(hop_dong_chi_tiet.so_luong) >10)AS sub);

-- 20.	Hiển thị thông tin của tất cả các nhân viên và khách hàng có trong hệ thống,
-- thông tin hiển thị bao gồm id (ma_nhan_vien, ma_khach_hang), ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi.
SELECT ma_nhan_vien AS id,
		CONCAT(ho_ten_lot," ",ten) AS ho_ten,
		email,
        so_dien_thoai,
        ngay_sinh,
        dia_chi
FROM nhan_vien
UNION
SELECT ma_khach_hang AS id,
		 ho_ten,
		email,
        so_dien_thoai,
        ngay_sinh,
        dia_chi
FROM khach_hang;