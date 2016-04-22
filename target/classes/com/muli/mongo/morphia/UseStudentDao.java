package com.muli.mongo.morphia;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by muli on 15-5-28.
 */
public class UseStudentDao {
    public static Logger logger = LoggerFactory.getLogger(UseStudentDao.class);
    public static void main(String args[]){
        Datastore datastore = get();
        if(null != datastore){
            StudentsDao studentsDao = new StudentsDao(datastore);
            studentsDao.ensureIndexes();
            logger.info("----------- get the datastore ----------");

            Students students = new Students("ttkk", "hello", "world", 100);
            studentsDao.inserstudent(students);

            List<Students> list = studentsDao.selectStudent("hello");
            for (Students l : list){
                logger.info(l.getAddress());
            }
        }
    }

    public static Datastore get(){
        try {
            Morphia morphia = new Morphia();
            Mongo mongo = new MongoClient("10.202.26.41", 8099);
            return morphia.createDatastore(mongo, "dxbb_test");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

}
