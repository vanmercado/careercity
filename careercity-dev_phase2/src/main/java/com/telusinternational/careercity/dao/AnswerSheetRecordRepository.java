/**
 * 
 */
package com.telusinternational.careercity.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusinternational.careercity.model.AnswerSheetRecord;

/**
 * @author A.C.Estrada Jr. x209486
 * @date Oct 25, 2018
 */

@Repository
public interface AnswerSheetRecordRepository extends JpaRepository<AnswerSheetRecord, Integer> {

}
