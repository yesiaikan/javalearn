package com.muli.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by muli on 15-5-7.
 */
public class OperateLucene {
    public static Version luceneVersion = Version.LUCENE_4_10_2;

    public static void createIndex(){
        IndexWriter writer = null;
        try {
            Directory directory = FSDirectory.open(new File("/home/muli/local/test/lucene_test"));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneVersion, new StandardAnalyzer());
            writer = new IndexWriter(directory, indexWriterConfig);

            Document document = new Document();
            document.add(new Field("name", "Chenghui", Field.Store.YES, Field.Index.ANALYZED));
            document.add(new Field("age", "18", Field.Store.YES, Field.Index.ANALYZED));
            document.add(new Field("doSomething", "I'm learning lucene", Field.Store.YES, Field.Index.ANALYZED));

            Document document1 = new Document();
            document1.add(new Field("name", "dashuaige", Field.Store.YES, Field.Index.ANALYZED));
            document1.add(new Field("age", "20", Field.Store.YES, Field.Index.ANALYZED));
            document1.add(new Field("doSomething", "coding", Field.Store.YES, Field.Index.ANALYZED));
            writer.addDocument(document1);
            writer.addDocument(document);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void indexSearch(String age){
        DirectoryReader reader = null;
        try {
            Directory directory = FSDirectory.open(new File("/home/muli/local/test/lucene_test"));
            reader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(reader);
            QueryParser parser = new QueryParser("age", new StandardAnalyzer());
            Query query = parser.parse(age);
            TopDocs topDocs = indexSearcher.search(query, 10);
            ScoreDoc[] docs = topDocs.scoreDocs;
            for(ScoreDoc doc:docs){
                Document d = indexSearcher.doc(doc.doc);
                System.out.println(d.get("name") + "\t" + d.get("age") + "\t" + d.get("doSomething"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除数据存在回收站中,可以恢复
     */
    public static void deleteIndexByAge(String age){
        IndexWriter writer = null;
        try {
            Directory directory = FSDirectory.open(new File("/home/muli/local/test/lucene_test"));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneVersion, new StandardAnalyzer());
            writer = new IndexWriter(directory, indexWriterConfig);
            writer.deleteDocuments(new Term("age", age));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteAllIndex(){
        IndexWriter writer = null;
        try {
            Directory directory = FSDirectory.open(new File("/home/muli/local/test/lucene_test"));
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(luceneVersion, new StandardAnalyzer());
            writer = new IndexWriter(directory, indexWriterConfig);
            writer.deleteAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        createIndex();
        indexSearch("18");
        indexSearch("20");
        deleteIndexByAge("18");
        System.out.println("---------");
        indexSearch("18");
        indexSearch("20");
        deleteAllIndex();
        System.out.println("---------");
        indexSearch("18");
        indexSearch("20");
    }
}
