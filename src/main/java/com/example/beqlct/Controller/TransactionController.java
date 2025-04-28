package com.example.beqlct.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.beqlct.Model.transaction;
import com.example.beqlct.Repository.transactionRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Autowired 
    private transactionRepo transactionRepo;

    @PostMapping("save")
    public ResponseEntity<?> saveTrans(@RequestBody transaction entity) {
        transactionRepo.save(entity);        
        return ResponseEntity.ok(entity);
    }
    @PostMapping("getHisTrans")
    public ResponseEntity<?> getHisTrans(@RequestBody transaction entity) {
        List<transaction> transactions=transactionRepo.findAllByIdUser(entity.getIdUser());        
        return ResponseEntity.ok(transactions);
    }
    @PostMapping("staticsExpense")
    public List<Map<String, Object>> getTotalExpenses(@RequestBody transaction entity) {
        String startDate = LocalDate.now().minusDays(7)
                .format(DateTimeFormatter.ISO_LOCAL_DATE);

        List<Object[]> results = transactionRepo.findTotalAmountByDate(entity.getType(),entity.getIdUser(),startDate);
        List<Map<String, Object>> dailyExpenses = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> expense = new HashMap<>();
            expense.put("date", result[0].toString());
            expense.put("total", result[1]);
            dailyExpenses.add(expense);
        }
        return dailyExpenses;
    }
    @PostMapping("staticsExpenseMonth")
    public List<Map<String, Object>> getTotalExpensesMonth(@RequestBody transaction entity) {
        // Lấy ngày bắt đầu từ 7 tháng trước
        String startDate = LocalDate.now().minusMonths(7)
                .withDayOfMonth(1) // Bắt đầu từ ngày đầu tiên của tháng
                .format(DateTimeFormatter.ISO_LOCAL_DATE);

        // Truy vấn tổng chi phí theo tháng từ repository
        List<Object[]> results = transactionRepo.findTotalAmountByMonth(entity.getType(), entity.getIdUser(), startDate);

        // Chuyển đổi kết quả thành danh sách Map
        List<Map<String, Object>> monthlyExpenses = new ArrayList<>();
        for (Object[] result : results) {
            Map<String, Object> expense = new HashMap<>();
            expense.put("month", result[0].toString()); // Định dạng YYYY-MM
            expense.put("total", result[1]);
            monthlyExpenses.add(expense);
        }

        return monthlyExpenses;
    }
    
    @PostMapping("staticsExpenseWallet")
    public List<Map<String ,Object>> TotalExpenseWallet(@RequestBody transaction entity) {
        List<Object[]> results = transactionRepo.findTotalAmountByAccount(entity.getType(), entity.getIdUser());
        List<Map<String, Object>> expenses = new ArrayList<>();
            for (Object[] result : results) {
                Map<String, Object> expense = new HashMap<>();
                expense.put("accountName", result[0].toString()); // Tên ví
                expense.put("total", result[1]);
                expenses.add(expense);
            }
        return expenses;
    }
}

