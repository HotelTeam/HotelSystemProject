/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal;

import internal.db.Analysis;
import internal.db.AnalysisJpaController;
import internal.db.Month;
import internal.db.MonthJpaController;
import internal.db.Year;
import internal.db.YearJpaController;
import java.util.List;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author elit
 */
public class DBService {
   
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("HotelResPU");

    private final static DBService INSTANCE = new DBService();
    
    private DBService() {}
    
    public static DBService getDBService() {
        return INSTANCE;
    }
    
    public List<internal.db.Analysis> getAllAnalysis() {
        
        AnalysisJpaController controller = new AnalysisJpaController(emf);
        return controller.findAnalysisEntities();
    }
    
    public List<Year> getAllYars() {
        YearJpaController con = new YearJpaController(emf);
        return con.findYearEntities();
    }
    
    public List<Month> getAllMonths() {
        MonthJpaController con = new MonthJpaController(emf);
        return con.findMonthEntities();
    }
    
    public List<Analysis> getAnalysisByYearAndMonth(String year, String month) {
       AnalysisJpaController controller = new AnalysisJpaController(emf);
       return controller.findAnalysisByYearMonth(year, month);
    }
}
