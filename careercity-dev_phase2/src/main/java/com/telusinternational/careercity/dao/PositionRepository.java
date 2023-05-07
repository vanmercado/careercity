package com.telusinternational.careercity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/*import com.telusinternational.careercity.model.Account;*/
import com.telusinternational.careercity.model.Position;

@Repository
public interface PositionRepository extends CrudRepository<Position, Integer> {

    @Query("from Position S WHERE S.CATEGORY = :ACCOUNT ORDER BY S.DEPARTMENT ASC")
	List<Position> findAccount(@Param("ACCOUNT") String ACCOUNT);

}
