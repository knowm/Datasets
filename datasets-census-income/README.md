## Xeiam Datasets Census Income

[raw data](http://archive.ics.uci.edu/ml/datasets/Census+Income) 

## How to Generate Java Data

1. Download raw data from here: <http://archive.ics.uci.edu/ml/datasets/Census+Income>

1. put all files in `raw` folder in project root

1. Run `RawData2DB.java`. 

1. The database containing the data will appear in `db`.

1. Manually move the `DB_CENSUS_INCOME.*` files into the `src/main/resources` folder. There should be three files. 

## How to build jar containing Data

1. Using Maven, run `mvn clean install`