## Xeiam Datasets Reuters-21578

[raw data](http://archive.ics.uci.edu/ml/support/Reuters-21578+Text+Categorization+Collection) 

## How to Generate Java Data

1. Download raw data from here: http://archive.ics.uci.edu/ml/support/Reuters-21578+Text+Categorization+Collection

1. put all files in `raw` folder in project root

1. Run `Data2DB.java`. 

1. The database containing the data will appear in `db`.

1. Manually copy the `DB_REUTERS_21578.*` files into the `src/main/resources` folder. There should be three files. 

## How to build jar containing Data

1. using Maven, run `mvn clean install`