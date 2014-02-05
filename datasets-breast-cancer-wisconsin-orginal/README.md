## Xeiam Datasets Breast Cancer Wisconsin Original

[raw data](http://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29)

## How to Generate Java Data

1. Download raw data from here: http://archive.ics.uci.edu/ml/datasets/Breast+Cancer+Wisconsin+%28Original%29

1. put all files in `raw` folder in project root

1. Run `RawData2DB.java`. 

1. The database containing the data will appear in `db`.

1. Manually move the `DB_BREAST_CANCER.*` files into the `src/main/resources/data` folder. There should be three files. 

## How to build jar containing Data

1. using Maven, run `mvn clean install`