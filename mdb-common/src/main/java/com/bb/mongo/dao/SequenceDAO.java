package com.bb.mongo.dao;

import com.bb.mongo.core.BasicDAO;
import com.bb.mongo.core.MongoConstants;
import com.bb.mongo.model.Sequence;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public class SequenceDAO extends BasicDAO<Sequence, String> {
	private static SequenceDAO dao;

	private SequenceDAO() {
		super(Sequence.class, MongoConstants.CHI_ITEM);
	}

	public static SequenceDAO getInstance() {
		if (dao == null) {
			dao = new SequenceDAO();
		}
		return dao;
	}

	public Long getID(String collName) {
		// 跟据表名查询
		Query<Sequence> q = ds.find(Sequence.class, "id", collName);
		UpdateOperations<Sequence> uOps = ds.createUpdateOperations(Sequence.class);
		uOps.inc("currentIdValue");
		Sequence sequence = ds.findAndModify(q, uOps); // 执行查询并更新,该操作椐有原子性
		if (sequence == null) {
			sequence = new Sequence(collName, 1l);
			ds.save(sequence);
		}
		return sequence.getCurrentIdValue();
	}
}