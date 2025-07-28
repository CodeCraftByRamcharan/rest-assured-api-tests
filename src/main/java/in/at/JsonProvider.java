package in.at;

public class JsonProvider {


    public static String singleEmployeeJson(){
        return "{\n" +
                "  \"employee\": {\n" +
                "    \"id\": 101,\n" +
                "    \"name\": \"Alice Johnson\",\n" +
                "    \"age\": 30,\n" +
                "    \"department\": \"Engineering\",\n" +
                "    \"isPermanent\": true,\n" +
                "    \"skills\": [\"Java\", \"Spring Boot\", \"React\", \"AWS\"],\n" +
                "    \"contact\": {\n" +
                "      \"email\": \"alice.johnson@example.com\",\n" +
                "      \"phone\": \"123-456-7890\"\n" +
                "    },\n" +
                "    \"projects\": [\n" +
                "      {\n" +
                "        \"name\": \"Inventory System\",\n" +
                "        \"status\": \"Completed\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Customer Portal\",\n" +
                "        \"status\": \"In Progress\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }


    public static String orderJson(){

        return "{\n" +
                "  \"order\": {\n" +
                "    \"orderId\": \"ORD-20250728-98765\",\n" +
                "    \"date\": \"2025-07-28T10:45:00Z\",\n" +
                "    \"status\": \"Processing\",\n" +
                "    \"customer\": {\n" +
                "      \"customerId\": \"CUST-123456\",\n" +
                "      \"name\": {\n" +
                "        \"first\": \"John\",\n" +
                "        \"last\": \"Doe\"\n" +
                "      },\n" +
                "      \"email\": \"john.doe@example.com\",\n" +
                "      \"phone\": \"+1-202-555-0147\",\n" +
                "      \"addresses\": [\n" +
                "        {\n" +
                "          \"type\": \"billing\",\n" +
                "          \"line1\": \"123 Main St\",\n" +
                "          \"line2\": \"Apt 4B\",\n" +
                "          \"city\": \"New York\",\n" +
                "          \"state\": \"NY\",\n" +
                "          \"zip\": \"10001\",\n" +
                "          \"country\": \"USA\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"shipping\",\n" +
                "          \"line1\": \"456 Market Ave\",\n" +
                "          \"line2\": null,\n" +
                "          \"city\": \"Brooklyn\",\n" +
                "          \"state\": \"NY\",\n" +
                "          \"zip\": \"11201\",\n" +
                "          \"country\": \"USA\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"productId\": \"PROD-001\",\n" +
                "        \"name\": \"Wireless Headphones\",\n" +
                "        \"category\": \"Electronics\",\n" +
                "        \"quantity\": 1,\n" +
                "        \"price\": 99.99,\n" +
                "        \"discount\": 10.0\n" +
                "      },\n" +
                "      {\n" +
                "        \"productId\": \"PROD-002\",\n" +
                "        \"name\": \"USB-C Charger\",\n" +
                "        \"category\": \"Accessories\",\n" +
                "        \"quantity\": 2,\n" +
                "        \"price\": 19.99,\n" +
                "        \"discount\": 0.0\n" +
                "      }\n" +
                "    ],\n" +
                "    \"shipping\": {\n" +
                "      \"method\": \"Express\",\n" +
                "      \"cost\": 7.5,\n" +
                "      \"trackingId\": \"TRK123456789\",\n" +
                "      \"estimatedDelivery\": \"2025-07-31T17:00:00Z\"\n" +
                "    },\n" +
                "    \"payment\": {\n" +
                "      \"method\": \"Credit Card\",\n" +
                "      \"transactionId\": \"TXN789654123\",\n" +
                "      \"amount\": 137.47,\n" +
                "      \"currency\": \"USD\",\n" +
                "      \"paid\": true\n" +
                "    },\n" +
                "    \"notes\": null\n" +
                "  }\n" +
                "}\n";
    }

    public static String productListJson(){
        return "[\n" +
                "  {\n" +
                "    \"productId\": \"P1001\",\n" +
                "    \"name\": \"Laptop\",\n" +
                "    \"category\": \"Electronics\",\n" +
                "    \"price\": 799.99,\n" +
                "    \"available\": true,\n" +
                "    \"tags\": [\"portable\", \"16GB RAM\", \"SSD\"],\n" +
                "    \"rating\": {\n" +
                "      \"average\": 4.5,\n" +
                "      \"count\": 120\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"productId\": \"P1002\",\n" +
                "    \"name\": \"Bluetooth Speaker\",\n" +
                "    \"category\": \"Audio\",\n" +
                "    \"price\": 49.99,\n" +
                "    \"available\": false,\n" +
                "    \"tags\": [\"wireless\", \"portable\"],\n" +
                "    \"rating\": {\n" +
                "      \"average\": 4.0,\n" +
                "      \"count\": 85\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"productId\": \"P1003\",\n" +
                "    \"name\": \"Office Chair\",\n" +
                "    \"category\": \"Furniture\",\n" +
                "    \"price\": 129.99,\n" +
                "    \"available\": true,\n" +
                "    \"tags\": [\"ergonomic\", \"adjustable\"],\n" +
                "    \"rating\": {\n" +
                "      \"average\": 4.2,\n" +
                "      \"count\": 200\n" +
                "    }\n" +
                "  }\n" +
                "]\n";
    }

    public static String complexMapJson(){
        return "{\n" +
                "  \"USR-1001\": {\n" +
                "    \"name\": \"Alice Smith\",\n" +
                "    \"age\": 28,\n" +
                "    \"roles\": [\"admin\", \"editor\"],\n" +
                "    \"active\": true,\n" +
                "    \"lastLogin\": \"2025-07-25T09:30:00Z\",\n" +
                "    \"preferences\": {\n" +
                "      \"theme\": \"dark\",\n" +
                "      \"language\": \"en\",\n" +
                "      \"notifications\": {\n" +
                "        \"email\": true,\n" +
                "        \"sms\": false,\n" +
                "        \"push\": true\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"USR-1002\": {\n" +
                "    \"name\": \"Bob Johnson\",\n" +
                "    \"age\": 35,\n" +
                "    \"roles\": [\"viewer\"],\n" +
                "    \"active\": false,\n" +
                "    \"lastLogin\": \"2025-06-30T17:15:00Z\",\n" +
                "    \"preferences\": {\n" +
                "      \"theme\": \"light\",\n" +
                "      \"language\": \"fr\",\n" +
                "      \"notifications\": {\n" +
                "        \"email\": false,\n" +
                "        \"sms\": true,\n" +
                "        \"push\": false\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"USR-1003\": {\n" +
                "    \"name\": \"Carol Davis\",\n" +
                "    \"age\": 42,\n" +
                "    \"roles\": [\"editor\", \"moderator\"],\n" +
                "    \"active\": true,\n" +
                "    \"lastLogin\": \"2025-07-27T22:00:00Z\",\n" +
                "    \"preferences\": {\n" +
                "      \"theme\": \"auto\",\n" +
                "      \"language\": \"es\",\n" +
                "      \"notifications\": {\n" +
                "        \"email\": true,\n" +
                "        \"sms\": true,\n" +
                "        \"push\": true\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
    }
}
