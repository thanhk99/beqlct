package com.example.beqlct.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.beqlct.Model.transaction;
@Repository
public interface transactionRepo  extends JpaRepository<transaction, Integer> {

    List<transaction> findAllByIdUser(int idUser);

    @Query("SELECT DATE(t.time) as date, SUM(t.amount) as total " +
            "FROM transaction t " +
            "WHERE t.type = :type AND t.idUser = :idUser AND t.time >= :startDate " +
            "GROUP BY DATE(t.time) " +
            "ORDER BY DATE(t.time)")
    List<Object[]> findTotalAmountByDate(String type, Integer idUser, String startDate);

    @Query(value = "SELECT DATE_FORMAT(STR_TO_DATE(time, '%Y-%m-%d'), '%Y-%m') AS month, SUM(amount) AS total " +
           "FROM transaction " +
           "WHERE type = :type AND id_user = :idUser AND STR_TO_DATE(time, '%Y-%m-%d') >= :startDate " +
           "GROUP BY DATE_FORMAT(STR_TO_DATE(time, '%Y-%m-%d'), '%Y-%m') " +
           "ORDER BY month DESC", nativeQuery = true)
    List<Object[]> findTotalAmountByMonth(@Param("type") String type,@Param("idUser") int idUser,@Param("startDate") String startDate);

    @Query(value = "SELECT w.name_wallet AS accountName, SUM(t.amount) AS total " +
           "FROM transaction t " +
           "JOIN wallet w ON t.id_wallet = w.id " +
           "WHERE t.type = :type AND t.id_user = :idUser " +
           "GROUP BY t.id_wallet, w.name_wallet " +
           "ORDER BY w.name_wallet", nativeQuery = true)
    List<Object[]> findTotalAmountByAccount(
            @Param("type") String type,
            @Param("idUser") int idUser);
}
