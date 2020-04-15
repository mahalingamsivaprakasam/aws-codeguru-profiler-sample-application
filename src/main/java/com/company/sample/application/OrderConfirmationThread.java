/**
 * 
 */
package com.company.sample.application;

import java.util.concurrent.TimeUnit;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.model.TableStatus;

/**
 * Works on the Dynamodb table
 * 
 * @author mahasiva
 *
 */
public class OrderConfirmationThread {

	private static final String ORDERCONFIRMATION_TABLENAME = "OrderConfirmations";

	/**
	 * Checks if table available
	 * 
	 * @return
	 */
	public TableDescription checkTableAvailable() throws Exception {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
		int count = 0;
		while (count++ < 10) {
			DescribeTableResult result = client
					.describeTable(new DescribeTableRequest().withTableName(ORDERCONFIRMATION_TABLENAME));
			String tblStatus = result.getTable().getTableStatus();
			if (TableStatus.ACTIVE.toString().equals(tblStatus)) {
				return result.getTable();
			}
			Thread.sleep(5 * 1000);
		}
		return null;
	}
}
