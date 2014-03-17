## Xeiam Datasets HJA Birdsong

[raw data](http://nsl.cs.unb.ca/NSL-KDD/)

## How to Generate Java Data

1. Download raw data from here: http://nsl.cs.unb.ca/NSL-KDD/

1. put all files (`KDDTrain+.txt` and `KDDTest+.txt`) in `raw` folder in project root

1. Run `RawData2DB.java`.

1. The database containing the data will appear in `db`.

1. Manually copy the `NSL_KDD.*` files into the `src/main/resources` folder. There should be three files. 

## Build jar containing Data

1. using Maven, run `mvn clean install`

## Data Information
