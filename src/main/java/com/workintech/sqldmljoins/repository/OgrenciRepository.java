package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "select * from ogrenci as o inner join islem as i \n" +
            "on o.ogrno=i.ogrno inner join kitap as k \n" +
            "on k.kitapno=i.kitapno;";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "select * from ogrenci as o left join islem as i \n" +
            "on o.ogrno=i.ogrno where i.ogrno is null;";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "select o.sinif, count(i.kitapno) as kitap_sayisi\n" +
            "\tfrom ogrenci as o inner join islem as i on i.ogrno=o.ogrno\n" +
            "\twhere o.sinif='10A' or o.sinif='10B'\n" +
            "\tgroup by o.sinif;";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "select count(ogrno) as ogrenci_sayisi from ogrenci;";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "select distinct o.ad from ogrenci as o;";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "select o.ad, count(o.ogrno) from ogrenci as o group by o.ad;";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();

    //Her sınıftaki öğrenci sayısını bulunuz.
    String QUESTION_8 = "select sinif,count(ogrno) as ogrenci_sayisi\n" +
        "\tfrom ogrenci as o group by o.sinif;";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();
    //Her öğrencinin ad soyad karşılığında okuduğu kitap sayısını getiriniz.
    String QUESTION_9 = "SELECT o.ad, o.soyad, COUNT(i.kitapno) AS kitap_sayisi\n" +
            "FROM ogrenci AS o\n" +
            "left JOIN islem AS i ON o.ogrno = i.ogrno\n" +
            "GROUP BY o.ad, o.soyad;";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
