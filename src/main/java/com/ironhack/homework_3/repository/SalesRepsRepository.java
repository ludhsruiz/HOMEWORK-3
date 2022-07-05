package com.ironhack.homework_3.repository;

import com.ironhack.homework_3.model.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepsRepository extends JpaRepository<SalesRep, Integer> {


    @Query(value = "select sales_rep_table.sales_rep_name , count(leads_table.sales_rep) from leads_table inner join sales_rep_table" +
            " on sales_rep_table.id = leads_table.sales_rep group by sales_rep", nativeQuery = true)
    String[][] reportLeadsBySalesRep();

    @Query(value = "select sales_rep_table.sales_rep_name , count(opportunities_table.sales_rep) from opportunities_table inner join " +
            "sales_rep_table on sales_rep_table.id = opportunities_table.sales_rep group by sales_rep", nativeQuery = true)
    String[][] reportOpportunitiesBySalesRep();

    @Query(value = "select sales_rep_table.sales_rep_name, count(opportunities_table.opportunity_status)" +
            " from sales_rep_table inner join opportunities_table on sales_rep_table.id = opportunities_table.sales_rep" +
            " where opportunities_table.opportunity_status = 'CLOSED_WON' group by sales_rep", nativeQuery = true)
    String[][] reportClosedWonOpportunitiesBySalesRep();

    @Query(value = "select sales_rep_table.sales_rep_name, count(opportunities_table.opportunity_status)" +
            " from sales_rep_table inner join opportunities_table on sales_rep_table.id = opportunities_table.sales_rep" +
            " where opportunities_table.opportunity_status = 'CLOSED_LOST' group by sales_rep", nativeQuery = true)
    String[][] reportClosedLostOpportunitiesBySalesRep();

    @Query(value = "select sales_rep_table.sales_rep_name, count(opportunities_table.opportunity_status)" +
            " from sales_rep_table inner join opportunities_table on sales_rep_table.id = opportunities_table.sales_rep" +
            " where opportunities_table.opportunity_status = 'OPEN' group by sales_rep", nativeQuery = true)
    String[][] reportOpenOpportunitiesBySalesRep();


}
