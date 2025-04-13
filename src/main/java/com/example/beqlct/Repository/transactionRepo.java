package com.example.beqlct.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
}
