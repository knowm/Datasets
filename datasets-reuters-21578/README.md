## Knowm Datasets Reuters-21578

[raw data](http://archive.ics.uci.edu/ml/support/Reuters-21578+Text+Categorization+Collection) 

## How to Generate Java Data

1. Download raw data (`reuters21578.tar.gz`) from here: http://archive.ics.uci.edu/ml/machine-learning-databases/reuters21578-mld/

1. unzip and put all files in `raw` folder in project root

1. Run `RawData2DB.java`. 

1. The database containing the data will appear in `db`.

1. Manually copy the `DB_REUTERS_21578.*` files into the `src/main/resources` folder. There should be three files. 

## How to build jar containing Data

1. Using Maven, run `mvn clean install`.