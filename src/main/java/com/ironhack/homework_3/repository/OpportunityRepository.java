package com.ironhack.homework_3.repository;

import com.ironhack.homework_3.model.Contact;
import com.ironhack.homework_3.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {

    Optional<Opportunity> findByDecisionMaker(Contact contact);

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table GROUP BY product", nativeQuery = true)
    String[][] reportOpportunityByProduct();

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table WHERE opportunity_status = 'CLOSED_WON' " +
            "GROUP BY product", nativeQuery = true)
    String[][] reportClosedWonOpportunityByProduct();

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table WHERE opportunity_status = 'CLOSED_LOST' " +
            "GROUP BY product", nativeQuery = true)
    String[][] reportClosedLostOpportunityByProduct();

    @Query(value = "SELECT product, COUNT(product) FROM opportunities_table WHERE opportunity_status = 'OPEN' " +
            "GROUP BY product", nativeQuery = true)
    String[][] reportOpenOpportunityByProduct();

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_WON' " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportClosedWonOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_LOST' " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportClosedLostOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.country, COUNT(accounts_table.country) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='OPEN' " +
            "GROUP BY accounts_table.country", nativeQuery = true)
    String[][] reportOpenOpportunitiesByCountry();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_WON' " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportClosedWonOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_LOST' " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportClosedLostOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.city, COUNT(accounts_table.city) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='OPEN' " +
            "GROUP BY accounts_table.city", nativeQuery = true)
    String[][] reportOpenOpportunitiesByCity();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportOpportunitiesByIndustry();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_WON' " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportClosedWonOpportunitiesByIndustry();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='CLOSED_LOST' " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportClosedLostOpportunitiesByIndustry();

    @Query(value = "SELECT accounts_table.industry, COUNT(accounts_table.industry) " +
            "FROM accounts_table JOIN opportunities_table ON accounts_table.id=opportunities_table.account " +
            "WHERE opportunities_table.opportunity_status='OPEN' " +
            "GROUP BY accounts_table.industry", nativeQuery = true)
    String[][] reportOpenOpportunitiesByIndustry();

    // Mean quantity of products ordered
    @Query(value = "SELECT AVG(quantity) FROM opportunities_table", nativeQuery = true)
    double meanQuantity();

//    // Median quantity of products ordered
//    @Query(value = "SELECT AVG(dd.quantity) AS median_val " +
//
//
//
//            , nativeQuery = true)
//    double medianQuantity();

    // Maximum number of orders placed in one opportunity
    @Query(value = "SELECT MAX(quantity) FROM opportunities_table", nativeQuery = true)
    int maxQuantity();

    // Minimum number of orders placed in one opportunity
    @Query(value = "SELECT MIN(quantity) FROM opportunities_table", nativeQuery = true)
    int minQuantity();

    // Mean number of Opportunities associated with an Account
    @Query(value = "SELECT AVG(a.opps_per_account) " +
            "FROM (SELECT account, COUNT(account) AS opps_per_account " +
            "FROM opportunities_table GROUP BY account) AS a", nativeQuery = true)
    double meanOppsPerAccount();

    // Median number of Opportunities associated with an Account
//    @Query(value = "SELECT AVG(dd.opps_per_account) AS median_val "
//
//
//
//            , nativeQuery = true)
//    double medianOppsPerAccount();

    // Maximum number of Opportunities associated with an Account
    @Query(value = "SELECT MAX(a.opps_per_account) FROM (SELECT account, COUNT(account) AS opps_per_account " +
            "FROM opportunities_table GROUP BY account) AS a", nativeQuery = true)
    int maxOppsPerAccount();

    // Minimum number of Opportunities associated with an Account
    @Query(value = "SELECT MIN(a.opps_per_account) FROM (SELECT account, COUNT(account) AS opps_per_account " +
            "FROM opportunities_table GROUP BY account) AS a", nativeQuery = true)
    int minOppsPerAccount();
}
