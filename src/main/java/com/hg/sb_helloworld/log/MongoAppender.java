
package com.hg.sb_helloworld.log;

import java.net.UnknownHostException;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoAppender extends AppenderSkeleton
{

    private MongoClient mongoClient;
    private DB mongoDatabase;
    private DBCollection logsCollection;

    private String connectionUrl;
    private String databaseName;
    private String collectionName;

    @Override
    protected void append( LoggingEvent loggingEvent )
    {

        if ( mongoDatabase == null )
        {
            try
            {
                MongoClientURI connectionString = new MongoClientURI( connectionUrl );
                mongoClient = new MongoClient( connectionString );
                mongoDatabase = mongoClient.getDB( databaseName );
                logsCollection = mongoDatabase.getCollection( collectionName );
                logsCollection.insert( (BasicDBObject)loggingEvent.getMessage() );
            } catch ( UnknownHostException e )
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void close()
    {
        if ( mongoClient != null )
        {
            mongoClient.close();
        }
    }

    @Override
    public boolean requiresLayout()
    {
        return false;
    }

    public String getConnectionUrl()
    {
        return connectionUrl;
    }

    public void setConnectionUrl( String connectionUrl )
    {
        this.connectionUrl = connectionUrl;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public void setDatabaseName( String databaseName )
    {
        this.databaseName = databaseName;
    }

    public String getCollectionName()
    {
        return collectionName;
    }

    public void setCollectionName( String collectionName )
    {
        this.collectionName = collectionName;
    }

}
