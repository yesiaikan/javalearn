package com.muli.mongo.morphia;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import org.bson.types.ObjectId;

import java.util.List;


/**
 * Created by muli on 15-5-28.
 */
public class StudentsDao extends BasicDAO<Students, ObjectId>{
    public StudentsDao(Datastore datastore){
        super(datastore);
    }

    public void inserstudent(Students students){
        super.save(students);
    }

    public List<Students> selectStudent(String name){
        Query<Students> query = super.createQuery();
        query.and(query.criteria("name").equal(name));
        return query.asList();
    }


}
