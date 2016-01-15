## Xeiam Datasets Numenta

[raw data](https://github.com/numenta/NAB) 

## How to populate the DB with NAB Data

1. Pull the "data" and "label" directories from https://github.com/numenta/NAB

1. put both directories in the `raw` folder in project root

1. Run `RawData2DB.java` with args: [jdbcUrl, username, password] in that order.

1. The data tables for each benchmark will appear at the jdbcUrl specified

## How to build jar containing Data

1. using Maven, run `mvn clean install`